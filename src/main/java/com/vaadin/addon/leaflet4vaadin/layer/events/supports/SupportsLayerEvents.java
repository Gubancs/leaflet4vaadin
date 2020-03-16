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

package com.vaadin.addon.leaflet4vaadin.layer.events.supports;

import static com.vaadin.addon.leaflet4vaadin.layer.events.types.LayerEventType.layeradd;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.LayerEventType.layerremove;

import com.vaadin.addon.leaflet4vaadin.layer.events.ErrorEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.Evented;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.LocationEvent;

public interface SupportsLayerEvents extends Evented {

	/**
	 * Fired when a layer is added to this layer
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onLayerAdd(LeafletEventListener<ErrorEvent> listener) {
		on(layeradd, listener);
	}

	/**
	 * Fired when a layer is removed from this layer
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onLayerRemove(LeafletEventListener<LocationEvent> listener) {
		on(layerremove, listener);
	}
}
