// Copyright 2020 Gabor Kokeny and contributors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import { html, PolymerElement } from "@polymer/polymer/polymer-element.js";
import * as L from "leaflet/dist/leaflet-src.js";
import { LeafletTypeConverter } from "./leaflet-type-converter.js";

class LeafletMap extends PolymerElement {
  static get template() {
    return html`
      <style include="leaflet-css lumo-leaflet-map"></style>
      <div id="map" style="position: relative; width: 100%; height: 100%;"></div>
    `;
  }

  static get is() {
    return "leaflet-map";
  }

  /**
   * Complex observers are declared in the observers array.
   * Complex observers can monitor one or more paths.
   * These paths are called the observer's dependencies.
   *
   * Each item of observers array is a method name followed by
   * a comma-separated list of one or more dependencies.
   */
  static get observers() {
    return ["updateZoom(mapOptions.zoom)", "updateBounds(mapOptions.bounds)", "handleOperations(operations.splices)", "updateMapListeners(events.splices)", "updateLayers(layers.splices)"];
  }

  /**
   * Called when the element is added to a document.
   * Can be called multiple times during the lifetime of an element.
   * Uses include adding document-level event listeners.
   * (For listeners local to the element, you can use annotated event listeners.)
   */
  connectedCallback() {
    super.connectedCallback();
    console.log("LeafletMap - connectedCallback()");
  }

  /**
   * Called when the element is removed from a document.
   * Can be called multiple times during the lifetime of an element.
   * Uses include removing event listeners added in connectedCallback.
   */
  disconnectedCallback() {
    super.disconnectedCallback();
    console.log("LeafletMap - disconnectedCallback()");
  }

  /**
   * 	Called after property values are set and local DOM is initialized.
   *  Use for one-time configuration of your component after local DOM is initialized.
   * (For configuration based on property values, it may be preferable to use an observer.)
   */
  ready() {
    super.ready();
    console.log("LeafletMap - ready() leaflet map is ready.");
    console.log("LeafletMap - ready() mapOptions: {}", this.mapOptions);

    this.leafletConverter = new LeafletTypeConverter();
    console.log("LeafletMap - ready() using converter", this.leafletConverter);

    // init leaflet map
    let map = this.toLeafletMap(this.mapOptions);
    this.leafletLayers = [];
    this.overlays = {};

    // init map event listeners
    // map.on("click", this.onClicked, this);
    // map.on("zoom", this.onZoomChanged, this);

    this.map = map;
  }

  /**
   * Called each time after the component is updated from the server side.
   */
  afterServerUpdate() {
    console.log("LeafletMap - afterServerUpdate() !!!!!!");
    console.log("LeafletMap - afterServerUpdate() mapOptions: {}", this.mapOptions);
    if (!this.mapInitialized) {
      console.log("LeafletMap - afterServerUpdate() baseUrl: {}", this.baseUrl);
      if (this.baseUrl) {
        L.tileLayer(this.baseUrl).addTo(this.map);
      }

      console.log("LeafletMap - afterServerUpdate() layer control options: {}", this.layerControlOptions);
      if (this.layers) {
        this.overlayControl = L.control.layers({}, this.overlays, this.layerControlOptions);
        this.overlayControl.addTo(this.map);
      }
      console.log("LeafletMap - afterServerUpdate() initial bounds: {}", this.mapOptions.bounds);
      if (this.mapOptions.bounds) {
        this.fitBounds(this.mapOptions.bounds);
      }
      console.log("LeafletMap - afterServerUpdate() initial layers: {}", this.layers);
      this.layers.forEach(layer => this.addLayerToMap(layer));

      console.log("LeafletMap - afterServerUpdate() initial controls: {}", this.controls);
      this.controls.forEach(control => {
        console.log("LeafletMap --- add control to map: {}", control);
        this.leafletConverter.toLeafletControl(control).addTo(this.map);
      });

      this.map.whenReady(() => {
        console.log("LeafletMap - whenReady() invalidate map size");
        this.map.invalidateSize();
      });

      this.mapInitialized = true;
    } else {
      console.log("LeafletMap - afterServerUpdate() Leaflet Map already initialized");
    }
  }

  addLayerToMap(layer) {
    console.log("LeafletMap - add layer to map", layer);
    let layerOptions = JSON.parse(layer.json);
    layerOptions.nodeId = layer.nodeId;
    let leafletLayer = this.leafletConverter.toLeafletLayer(layerOptions);
    this.applyEventListeners(leafletLayer, layerOptions);
    leafletLayer.addTo(this.map);
    this.leafletLayers.push(leafletLayer);
    if (layer.name) {
      this.overlayControl.addBaseLayer(leafletLayer, layer.name);
    }
    console.log("LeafletMap - " + layer.leafletType + " has been added to map", leafletLayer);
  }

  /**
   * Called when the 'zoom' property changed on the server side.
   */
  updateZoom(zoom) {
    console.log("LeafletMap - zoom property changed", zoom);
    if (this.map) {
      this.map.setZoom(zoom);
    }
  }

  /**
   * Called when the 'bounds' property changed on the server side.
   */
  updateBounds(bounds) {
    console.log("LeafletMap - bounds property changed", bounds);
    this.fitBounds(bounds);
  }

  updateMapListeners(changeRecord) {
    console.log("LeafletMap - Map listeners has been changed", changeRecord);
  }
  /**
   * Called when when marker item is added or deleted.
   */
  updateLayers(changeRecord) {
    console.log("LeafletMap - layers property changed", changeRecord);
    console.log("LeafletMap - layers", this.layers);
    if (changeRecord) {
      changeRecord.indexSplices.forEach(function(indexSplice) {
        indexSplice.removed.forEach(layer => {
          this.leafletLayers
            .slice()
            .filter(l => l.options.uuid === layer.uuid)
            .forEach(l => {
              this.map.removeLayer(l);
              if (l.options.name) {
                this.overlayControl.removeLayer(l);
              }
              console.log("LeafletMap - layer has been removed from map", layer);
            });
        });
        for (var i = 0; i < indexSplice.addedCount; i++) {
          var index = indexSplice.index + i;
          var newLayer = indexSplice.object[index];
          this.addLayerToMap(newLayer);
        }
      }, this);
    }
  }

  /**
   * Called when any fucntion operation created on server-side
   */
  handleOperations(changeRecord) {
    console.log("LeafletMap - handleOperations() operations property changed", this.operations);
    if (changeRecord) {
      changeRecord.indexSplices.forEach(function(indexSplice) {
        for (var i = 0; i < indexSplice.addedCount; i++) {
          let operation = indexSplice.object[i];
          let layer = this.findLayer(this.map, operation.laryerId);
          let fn = layer[operation.functionName];
          let args = JSON.parse(operation.arguments);
          args = args.map(argument => this.leafletConverter.convert(argument));
          console.log("LeafletMap handleOperations()", operation);
          console.log("LeafletMap handleOperations() - layerID", operation.layerId);
          console.log("LeafletMap handleOperations() - layer", layer);
          console.log("LeafletMap handleOperations() - function", fn);
          console.log("LeafletMap handleOperations() - arguments", args);
          let result = fn.apply(layer, args);
          console.log("LeafletMap handleOperations() - result", result);
          this.operations.splice(i, 1);
        }
      }, this);
    }
  }

  findLayer(head, uuid) {
    console.log("LeafletMap findLayer() ", { head: head, uuid: uuid });
    if (head.options.uuid == uuid) {
      console.log("LeafletMap findLayer()  found", head);
      return head;
    }
    head.eachLayer(child => {
      if (child.options.uuid === uuid) {
        console.log("LeafletMap findLayer()  found", head);
        return child;
      }
      if (child.eachLayer) {
        return findLayerByUUID(child, uuid);
      }
    });
    return head;
  }

  /**
   * Sets a map view that contains the given geographical bounds with the maximum zoom level possible.
   */
  fitBounds(bounds) {
    console.log("LeafletMap -  fitBounds()", bounds);
    if (this.map && bounds) {
      let leafletBounds = this.leafletConverter.toLatLngBounds(bounds);
      this.map.fitBounds(leafletBounds);
    }
  }

  toLeafletMap(options) {
    console.log("LeafletMap - initialize map with options: {}", options);
    let mapElement = this.shadowRoot.getElementById("map");
    console.log("LeafletMap - using DOM element: {}", mapElement);
    let leafletMap = L.map(mapElement, options);
    this.events.slice().forEach(event => this.registerEventListener(leafletMap, event.leafletEvent));
    console.log("LeafletMap - map has been created with options", options);
    return leafletMap;
  }

  applyEventListeners(layer, options) {
    if (options.events) {
      options.events.forEach(event => this.registerEventListener(layer, event));
    }
  }

  registerEventListener(layer, event) {
    console.log("LeafletMap - try to register event listener for event type:", { event: event });

    this.getEventMap()
      .slice()
      .filter(e => e.events.indexOf(event) >= 0)
      .forEach(e => {
        console.log("LeafletMap - binding event listener to layer:", { event: event });
        layer.on(event, e.handler, this);
      });
  }

  getEventMap() {
    if (!this.eventMap) {
      this.eventMap = [
        {
          events: ["click", "dblclick", "mousedown", "mouseup", "mouseover", "mouseout", "mousemove", "contextmenu", "preclick"],
          handler: this.onMouseEventEventHandler
        },
        {
          events: ["dragstart", "movestart", "moveend"],
          handler: this.onBaseEventHandler
        },
        {
          events: ["drag"],
          handler: this.onDragEventHandler
        },
        {
          events: ["resize"],
          handler: this.onResizeEventHandler
        },
        {
          events: ["dragend"],
          handler: this.onDragEndEventHandler
        },
        {
          events: ["add", "remove"],
          handler: this.onBaseEventHandler
        },
        {
          events: ["move"],
          handler: this.onMoveEventHandler
        },
        {
          events: ["zoomanim"],
          handler: this.onZoomAnimEventHandler
        },
        {
          events: ["popupclose", "popupopen"],
          handler: this.onPopupEventHandler
        },
        {
          events: ["tooltipclose", "tooltipopen"],
          handler: this.onTooltipEventHandler
        },
        {
          events: ["zoomlevelschange", "unload", "viewreset", "load", "zoom", "zoomend", "zoomstart", "movestart", "moveend"],
          handler: this.onBaseEventHandler
        }
      ];
    }
    return this.eventMap;
  }

  onDragEventHandler(e) {
    console.warn("LeafletMap - onDragEventHandler()", e);
  }

  onMoveEventHandler(e) {
    console.warn("LeafletMap - onMoveEventHandler()", e);
  }

  onDragEndEventHandler(e) {
    console.warn("LeafletMap - onDragEndEventHandler()", e);
  }

  onBaseEventHandler(e) {
    console.warn("LeafletMap - onBaseEventHandler()", e);
  }

  onZoomAnimEventHandler(e) {
    console.warn("LeafletMap - onZoomAnimEventHandler()", e);
  }
}

customElements.define(LeafletMap.is, LeafletMap);
