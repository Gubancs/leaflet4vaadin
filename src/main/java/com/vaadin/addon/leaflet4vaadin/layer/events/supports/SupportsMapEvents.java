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

import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.load;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.move;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.moveend;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.movestart;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.resize;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.unload;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.viewreset;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.zoom;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.zoomanim;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.zoomend;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.zoomlevelschange;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType.zoomstart;

import com.vaadin.addon.leaflet4vaadin.layer.events.Evented;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.ResizeEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.ZoomAnimEvent;

public interface SupportsMapEvents extends Evented {

	/**
	 * Fired when the number of zoomlevels on the map is changed due to adding or
	 * removing a layer.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onZoomLevelsChange(LeafletEventListener<LeafletEvent> listener) {
		on(zoomlevelschange, listener);
	}

	/**
	 * Fired when the map is resized.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onResize(LeafletEventListener<ResizeEvent> listener) {
		on(resize, listener);
	}

	/**
	 * Fired when the map is destroyed with remove method.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onUnload(LeafletEventListener<LeafletEvent> listener) {
		on(unload, listener);
	}

	/**
	 * Fired when the map needs to redraw its content (this usually happens on map
	 * zoom or load). Very useful for creating custom overlays.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onViewReset(LeafletEventListener<LeafletEvent> listener) {
		on(viewreset, listener);
	}

	/**
	 * Fired when the map is initialized (when its center and zoom are set for the
	 * first time).
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onLoad(LeafletEventListener<LeafletEvent> listener) {
		on(load, listener);
	}

	/**
	 * Fired when the map zoom is about to change (e.g. before zoom animation).
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onZoomStart(LeafletEventListener<LeafletEvent> listener) {
		on(zoomstart, listener);
	}

	/**
	 * Fired when the view of the map starts changing (e.g. user starts dragging the
	 * map).
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onMoveStart(LeafletEventListener<LeafletEvent> listener) {
		on(movestart, listener);
	}

	/**
	 * Fired repeatedly during any change in zoom level, including zoom and fly
	 * animations.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onZoom(LeafletEventListener<LeafletEvent> listener) {
		on(zoom, listener);
	}

	/**
	 * Fired repeatedly during any movement of the map, including pan and fly
	 * animations.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onMove(LeafletEventListener<LeafletEvent> listener) {
		on(move, listener);
	}

	/**
	 * Fired when the map has changed, after any animations.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onZoomEnd(LeafletEventListener<LeafletEvent> listener) {
		on(zoomend, listener);
	}

	/**
	 * Fired when the center of the map stops changing (e.g. user stopped dragging
	 * the map).
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onMoveEnd(LeafletEventListener<LeafletEvent> listener) {
		on(moveend, listener);
	}

	/**
	 * Fired at least once per zoom animation. For continuous zoom, like pinch
	 * zooming, fired once per frame during zoom.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onZoomAnim(LeafletEventListener<ZoomAnimEvent> listener) {
		on(zoomanim, listener);
	}
}
