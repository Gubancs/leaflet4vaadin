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

import static com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType.drag;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType.dragend;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType.dragstart;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType.move;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType.moveend;
import static com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType.movestart;

import com.vaadin.addon.leaflet4vaadin.layer.events.DragEndEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.Evented;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.MoveEvent;

public interface SupportsDragEvents extends Evented {

	/**
	 * Fired when this layer is moved via setLatLng or by dragging. Old and new
	 * coordinates are included in event arguments as oldLatLng, latlng.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onMove(LeafletEventListener<MoveEvent> listener) {
		on(move, listener);
	}

	/**
	 * Fired when the user starts dragging this layer.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onDragStart(LeafletEventListener<LeafletEvent> listener) {
		on(dragstart, listener);
	}

	/**
	 * Fired when this layer starts moving (because of dragging).
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onMoveStart(LeafletEventListener<LeafletEvent> listener) {
		on(movestart, listener);
	}

	/**
	 * Fired repeatedly while the user drags this layer.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onDrag(LeafletEventListener<LeafletEvent> listener) {
		on(drag, listener);
	}

	/**
	 * Fired when the user stops dragging this layer.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onDragEnd(LeafletEventListener<DragEndEvent> listener) {
		on(dragend, listener);
	}

	/**
	 * Fired when this layer stops moving (because of dragging).
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 */
	default void onMoveEnd(LeafletEventListener<LeafletEvent> listener) {
		on(moveend, listener);
	}
}
