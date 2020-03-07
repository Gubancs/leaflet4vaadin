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

import * as L from "leaflet/dist/leaflet-src.js";

export class LeafletTypeConverter {
  /**
   * Convert the given jsonObject to Leaflet Object based on 'leafletType' attribute
   */
  toLeafletLayer(layer) {
    let leafletLayer;
    if (layer.leafletType === "Marker") {
      layer.icon = this.toLeafletIcon(layer.icon);
      leafletLayer = L.marker(layer.latLng, layer);
    } else if (layer.leafletType === "LayerGroup") {
      let layers = layer.layers.slice().map(l => this.toLeafletLayer(l));
      leafletLayer = L.layerGroup(layers, layer);
    } else if (layer.leafletType === "FeatureGroup") {
      let layers = layer.layers.slice().map(l => this.toLeafletLayer(l));
      leafletLayer = L.featureGroup(layers, layer);
    } else if (layer.leafletType === "Polyline") {
      leafletLayer = L.polyline(layer.latlngs, layer);
    } else if (layer.leafletType === "Polygon") {
      leafletLayer = L.polygon(layer.latlngs, layer);
    } else if (layer.leafletType === "Rectangle") {
      leafletLayer = L.rectangle(layer.latlngs, layer);
    } else if (layer.leafletType === "CircleMarker") {
      leafletLayer = L.circleMarker(layer.latlng, layer);
    } else if (layer.leafletType === "Circle") {
      leafletLayer = L.circle(layer.latlng, layer);
    } else if (layer.leafletType === "GridLayer") {
      leafletLayer = L.gridLayer(layer);
    } else if (layer.leafletType === "TileLayer") {
      leafletLayer = L.tileLayer(layer.urlTemplate, layer);
    } else {
      throw "Unsupported object type : " + layer.leafletType;
    }
    this._applyOptions(leafletLayer, layer);
    console.log("LeafletTypeConverter - toLeafletLayer() result", leafletLayer);
    return leafletLayer;
  }

  toLeafletControl(control) {
    let controlFn = L.control[control.leafletType];
    if (!controlFn) {
      throw "Unsupported control type : " + control.leafletType;
    }
    let leafletControl = controlFn(control);
    console.log("LeafletTypeConverter --- toLeafletControl() result", leafletControl);
    return leafletControl;
  }

  /**
   * Convert the given JsonObject to Leaflet Icon
   */
  toLeafletIcon(iconOptions) {
    let icon = L.icon(iconOptions);
    icon.popupAnchor = this.toLeafletPoint(iconOptions.popupAnchor);
    icon.tooltipAnchor = this.toLeafletPoint(iconOptions.tooltipAnchor);
    icon.shadowAnchor = this.toLeafletPoint(iconOptions.shadowAnchor);
    icon.iconAnchor = this.toLeafletPoint(iconOptions.iconAnchor);
    icon.iconSize = this.toLeafletPoint(iconOptions.iconSize);
    icon.shadowSize = this.toLeafletPoint(iconOptions.shadowSize);
    return icon;
  }

  /**
   * Convert the given JsonObject to Leaflet Point
   */
  toLeafletPoint(point) {
    return point ? L.point(point.x, point.y) : point;
  }

  /**
   * Convert the given JsonObject to Leaflet LatLngBounds
   */
  toLatLngBounds(bounds) {
    let corner1 = L.latLng(bounds.northEastLat, bounds.northEastLon);
    let corner2 = L.latLng(bounds.southWestLat, bounds.southWestLon);
    return L.latLngBounds(corner1, corner2);
  }

  _applyOptions(layer, options) {
    if (options.tooltip) {
      let leafletTooltip = L.tooltip(options.tooltip).setContent(options.tooltip.content);
      console.log("LeafletConverter - binding tooltip to layer", leafletTooltip);
      layer.bindTooltip(leafletTooltip);
    }
    if (options.popup) {
      let leafletPopup = L.popup(options.popup).setContent(options.popup.content);
      console.log("LeafletConverter - binding popup to layer", leafletPopup);
      layer.bindPopup(leafletPopup);
    }
  }
}
