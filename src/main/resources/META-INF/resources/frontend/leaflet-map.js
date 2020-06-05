/*
 *   Copyright (c) 2020 Gabor Kokeny
 *   All rights reserved.

 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

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
import { ThemableMixin } from "@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js";
import * as L from "leaflet/dist/leaflet-src.js";
import { LeafletTypeConverter } from "./leaflet-type-converter.js";

class LeafletMap extends ThemableMixin(PolymerElement) {
  static get template() {
    return html`
      <div
        id="map"
        style="position: relative; width: 100%; height: 100%;"
      ></div>
    `;
  }

  static get is() {
    return "leaflet-map";
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
    console.info("LeafletMap - ready() leaflet map is ready.");
    console.log("LeafletMap - ready() mapOptions: {}", this.mapOptions);

    this.leafletConverter = new LeafletTypeConverter();
    console.log("LeafletMap - ready() using converter", this.leafletConverter);

    // init leaflet map
    let map = this.toLeafletMap(this.mapOptions);
    this.map = map;
  }

  /**
   * Called each time after the component is updated from the server side.
   */
  afterServerUpdate() {
    console.log("LeafletMap - afterServerUpdate() !!!!!!");
    console.log(
      "LeafletMap - afterServerUpdate() mapOptions: {}",
      this.mapOptions
    );
    if (!this.mapInitialized) {
      this.map.whenReady(() => {
        console.log("LeafletMap - whenReady()");
        this.map.invalidateSize();
        this.onMapReadyEventHandler();
      });
    } else {
      console.log(
        "LeafletMap - afterServerUpdate() Leaflet Map already initialized"
      );
    }
  }

  callLeafletFunction(operation) {
    console.info("LeafletMap - callLeafletFunction()", operation);

    let target = this._findTargetLayer(operation);

    let leafletArgs = JSON.parse(operation.arguments);
    leafletArgs = leafletArgs.map((arg) =>
      this.leafletConverter.convert(arg, this)
    );
    //console.log("LeafletMap - callLeafletFunction() - leafletArgs", leafletArgs);

    let leafletFn = target[operation.functionName];
    //console.log("LeafletMap - callLeafletFunction() - leafletFn", leafletFn);

    let result = leafletFn.apply(target, leafletArgs);
    console.log("LeafletMap - callLeafletFunction() - result", result);
    return result;
  }

  _findTargetLayer(operation) {
    let target;
    if (this.map.options.uuid === operation.layerId) {
      target = this.map;
    } else {
      if (operation.controlOperation) {
        target = this.getControl(operation.layerId);
      } else {
        target = this.getLayer(operation.layerId);
      }
    }

    if (target == null) {
      target = this._findChildLayer(this.map, operation.layerId);
    }

    return target;
  }

  _findChildLayer(head, layerId) {
    if (head.options.uuid === layerId) {
      return head;
    }
    if (head.eachLayer) {
      let found;
      head.eachLayer((child) => {
        if (!found) {
          found = this._findChildLayer(child, layerId);
        } else {
          return found;
        }
      });
      if (found) {
        return found;
      }
    }
  }

  hasLayer(layerId) {
    return this.map._layers && this.getLayer(layerId);
  }

  getLayer(layerId) {
    return this.map._layers[layerId];
  }

  getControl(controlId) {
    return this.map._controls[controlId];
  }

  toLeafletMap(options) {
    console.log("LeafletMap - initialize map with options: {}", options);
    let mapElement = this.shadowRoot.getElementById("map");
    console.log("LeafletMap - using DOM element: {}", mapElement);
    let leafletMap = L.map(mapElement, options);
    leafletMap._controls = [];
    this.events
      .slice()
      .forEach((event) =>
        this.registerEventListener(leafletMap, event.leafletEvent)
      );
    console.log("LeafletMap - map has been created with options", options);
    return leafletMap;
  }

  registerEventListener(layer, event) {
    let found = this.getEventMap().find((e) => e.events.indexOf(event) >= 0);
    let eventListener = this.onBaseEventHandler;
    if (found) {
      if (!found.condition || found.condition.call(this, layer)) {
        eventListener = found.handler;
      }
    }
    console.info(
      "LeafletMap - registerEventListener() register listener for event",
      { event: event }
    );
    layer.on(event, eventListener, this);
  }

  getEventMap() {
    if (!this.eventMap) {
      this.eventMap = [
        {
          events: [
            "click",
            "dblclick",
            "mousedown",
            "mouseup",
            "mouseover",
            "mouseout",
            "mousemove",
            "contextmenu",
            "preclick",
          ],
          handler: this.onMouseEventEventHandler,
        },
        {
          events: ["keypress", "keydown", "keyup"],
          handler: this.onKeyboardEventHandler,
        },
        {
          events: ["resize"],
          handler: this.onResizeEventHandler,
        },
        {
          events: ["zoomanim"],
          handler: this.onZoomAnimEventHandler,
        },
        {
          events: ["dragend"],
          handler: this.onDragEndEventHandler,
        },
        {
          events: ["layeradd", "layerremove"],
          handler: this.onLayerEventHandler,
        },
        {
          events: ["baselayerchange", "overlayadd", "overlayremove"],
          handler: this.onLayersControlEventHandler,
        },
        {
          events: ["move"],
          condition: (layer) => layer.options.leafletType === "Marker",
          handler: this.onMoveEventHandler,
        },
        {
          events: ["popupclose", "popupopen"],
          handler: this.onPopupEventHandler,
        },
        {
          events: ["tooltipclose", "tooltipopen"],
          handler: this.onTooltipEventHandler,
        },
        {
          events: ["locationfound"],
          handler: this.onLocationEventHandler,
        },
        {
          events: ["locationerror"],
          handler: this.onErrorEventHandler,
        },
      ];
    }
    return this.eventMap;
  }

  onMouseEventEventHandler(event) {
    console.info("LeafletMap - onMouseEventEventHandler()", event);
  }
  onKeyboardEventHandler(event) {
    console.info("LeafletMap - onKeyboardEventHandler()", event);
  }
  onResizeEventHandler(event) {
    console.info("LeafletMap - onResizeEventHandler()", event);
  }
  onZoomAnimEventHandler(event) {
    console.info("LeafletMap - onZoomAnimEventHandler()", event);
  }
  onDragEndEventHandler(event) {
    console.info("LeafletMap - onDragEndEventHandler()", event);
  }
  onLayerEventHandler(event) {
    console.info("LeafletMap - onLayerEventHandler()", event);
  }
  onMoveEventHandler(event) {
    console.info("LeafletMap - onMoveEventHandler()", event);
  }
  onPopupEventHandler(event) {
    console.info("LeafletMap - onPopupEventHandler()", event);
  }
  onTooltipEventHandler(event) {
    console.info("LeafletMap - onTooltipEventHandler()", event);
  }
  onLocationEventHandler(event) {
    console.info("LeafletMap - onLocationEventHandler()", event);
  }
  onErrorEventHandler(event) {
    console.info("LeafletMap - onErrorEventHandler()", event);
  }
  onLayersControlEventHandler(event) {
    console.info("LeafletMap - onLayersControlEventHandler()", event);
  }
  onBaseEventHandler(event) {
    console.info("LeafletMap - onBaseEventHandler()", event);
  }
}

customElements.define(LeafletMap.is, LeafletMap);
