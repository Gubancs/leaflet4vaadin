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
	
	constructor(){
		this.basicTypes = ["Point", "Bounds", "LatLng", "LatLngBounds", "Icon", "DivIcon"];
	}
	
  toLeafletLayer(layer, map) {
    //console.log("LeafletTypeConverter - toLeafletLayer() leafletType", {leafletType: layer.leafletType});
    let leafletLayer;
    
    if(map.hasLayer(layer.uuid)){
    	return map.getLayer(layer.uuid);
    }
    
    if(layer.icon){
      layer.icon = this.convert(layer.icon);
    } else if(layer.layers){
      layer.layers = layer.layers.slice().map(childLayer => this.toLeafletLayer(childLayer, map));
    }

    if (layer.leafletType === "GeoJSON") {
      leafletLayer = L.geoJSON(null, layer);
      leafletLayer._layers = layer.layers;
    } else {
      let factoryFn = this.getLayerFactoryFn(layer.leafletType);
      if(layer.constructorArgumentNames){
        //console.log("LeafletTypeConverter - toLeafletLayer() constructorArgumentNames", layer.constructorArgumentNames);
        let initArgs = layer.constructorArgumentNames.map(argName => this.convert(layer[argName], map));
        initArgs.push(layer);
        //console.log("LeafletTypeConverter - toLeafletLayer() initArgs", initArgs);
        leafletLayer = factoryFn.apply(null, initArgs);
      } else {
        leafletLayer = factoryFn.call(layer);
      }
    }

    this._applyOptions(leafletLayer, layer);
    
    //apply layer event listeners
    if (layer.events) {
    	layer.events.forEach(event => map.registerEventListener(leafletLayer, event));
    }
    console.log("LeafletTypeConverter - toLeafletLayer() result", leafletLayer);
    return leafletLayer;
  }

  getLayerFactoryFn(leafletType){
      return L[leafletType.charAt(0).toLowerCase() + leafletType.slice(1)];
  }
  
  getControlFactoryFn(leafletType){
      return L.control[leafletType];
  }

  toLeafletControl(control, mapTemplate) {
	let leafletControl = control;
	if(control){
	    let controlFn = this.getControlFactoryFn(control.leafletType);
	    if (!controlFn) {
	      throw "Unsupported control type : " + control.leafletType;
	    }
	    if(control.constructorArgumentNames){
	        //console.log("LeafletTypeConverter - toLeafletControl() constructorArgumentNames", control.constructorArgumentNames);
	        let initArgs = control.constructorArgumentNames.map(argName => this.convert(control[argName], mapTemplate));
	        initArgs.push(control);
	        //console.log("LeafletTypeConverter - toLeafletControl() initArgs", initArgs);
	        leafletControl = controlFn.apply(null, initArgs);
	      }
	    else {
	    	leafletControl = controlFn(control);
	    }
	    mapTemplate.map._controls[control.uuid] = leafletControl;
	    console.log("LeafletTypeConverter - toLeafletControl() result", leafletControl);
	}
    return leafletControl;
  }

  convert(object, map) {
    let converted = object;
    if(this.isLeafletType(object)){
	    if (this.isBasicType(object.leafletType)) {
	    	converted = this.convertBasicType(object);
	    }
	    else if(this.isLeafletLayer(object.leafletType)){
	    	converted = this.toLeafletLayer(object, map);
	    }
	    else if(this.isLeafletControl(object.leafletType)){
	    	converted = this.toLeafletControl(object, map);
	    }
	  //Override the internal leaflet id
	    if(object.uuid) {
	    	converted._leaflet_id = object.uuid;
	    }
    }
    else if(object instanceof Object){
    	Object.keys(object).forEach((key, index) => {
    		if(this.isLeafletType(object[key])) {
    			converted[key] = this.convert(object[key], map);
    		}
    	});
    }
    
    //console.log("LeafletTypeConverter - convert() result", converted);
    return converted;
  }
  

  convertBasicType(basicType) {
    let converted = basicType;
	  if (basicType.leafletType === "Point") {
	    converted = this.toPoint(basicType);
	  } else if (basicType.leafletType === "Bounds") {
	    converted = this.toBounds(basicType);
	  } else if (basicType.leafletType === "LatLng") {
	    converted = this.toLatLng(basicType);
	  } else if (basicType.leafletType === "LatLngBounds") {
	    converted = this.toLatLngBounds(basicType);
	  } else if (basicType.leafletType === "Icon") {
	    converted = this.toIcon(basicType);
	  } else if (basicType.leafletType === "DivIcon") {
	    converted = this.toDivIcon(basicType);
	  }
	//console.log("LeafletTypeConverter - convertBasicType() result", converted);
    return converted;
  }
  

  isLeafletType(object) {
	  return object && typeof(object.leafletType) !== "undefined"; 
  }
  
  isBasicType(object) {
	  return this.basicTypes.indexOf(object) >= 0;
  }
  
  isLeafletControl(object) {
	  return this.getControlFactoryFn(object) != null;
  }
  
  isLeafletLayer(object) {
	  return this.getLayerFactoryFn(object) != null;
  }

  /**
	 * Convert the given JsonObject to Leaflet Icon
	 */
  toIcon(iconOptions) {
    let icon = L.icon(iconOptions);
    icon.popupAnchor = this.convert(iconOptions.popupAnchor);
    icon.tooltipAnchor = this.convert(iconOptions.tooltipAnchor);
    icon.shadowAnchor = this.convert(iconOptions.shadowAnchor);
    icon.iconAnchor = this.convert(iconOptions.iconAnchor);
    icon.iconSize = this.convert(iconOptions.iconSize);
    icon.shadowSize = this.convert(iconOptions.shadowSize);
    return icon;
  }
  
  /**
   * Convert the given JsonObject to Leaflet DivIcon
   */
  toDivIcon(divIcon) {
	  return L.divIcon(divIcon);
  }

  /**
	 * Convert the given JsonObject to Leaflet Point
	 */
  toPoint(point) {
    return point ? L.point(point.x, point.y) : point;
  }

  /**
	 * Convert the given JsonObject to Leaflet LatLng
	 */
  toLatLng(latLng) {
    return latLng ? L.latLng(latLng.lat, latLng.lng, latLng.altitude) : latLng;
  }

  /**
	 * Convert the given JsonObject to Leaflet Bounds
	 */
  toBounds(bounds) {
    let corner1 = L.point(bounds.topLeft.x, bounds.topLeft.y);
    let corner2 = L.point(bounds.bottomRight.x, bounds.bottomRight.y);
    return L.bounds(corner1, corner2);
  }

  /**
	 * Convert the given JsonObject to Leaflet LatLngBounds
	 */
  toLatLngBounds(bounds) {
    let corner1 = L.latLng(bounds._northEast);
    let corner2 = L.latLng(bounds._southWest);
    return L.latLngBounds(corner1, corner2);
  }
  
  _applyOptions(layer, options) {
    if (options.tooltip) {
      let leafletTooltip = L.tooltip(options.tooltip).setContent(
        options.tooltip.content
      );
      //console.log("LeafletConverter - binding tooltip to layer",leafletTooltip);
      layer.bindTooltip(leafletTooltip);
    }
    if (options.popup) {
      let leafletPopup = L.popup(options.popup).setContent(
        options.popup.content
      );
      //console.log("LeafletConverter - binding popup to layer", leafletPopup);
      layer.bindPopup(leafletPopup);
    }
  }
}
