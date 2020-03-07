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

import com.vaadin.addon.leaflet4vaadin.layer.events.DragEndEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.DragEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.Evented;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.MoveEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType;

public interface SupportsDragEvents extends Evented {

	/**
	 * Fired when this layer is moved via setLatLng or by dragging. Old and new
	 * coordinates are included in event arguments as oldLatLng, latlng.
	 * 
	 * @param listener the event listener
	 */
	default void onMove(LeafletEventListener<MoveEvent> listener) {
		on(DragEventType.move, listener);
	}

	/**
	 * Fired when the user starts dragging this layer.
	 * 
	 * @param listener the event listener
	 */
	default void onDragStart(LeafletEventListener<LeafletEvent> listener) {
		on(DragEventType.dragstart, listener);
	}

	/**
	 * Fired when this layer starts moving (because of dragging).
	 * 
	 * @param listener the event listener
	 */
	default void onMoveStart(LeafletEventListener<LeafletEvent> listener) {
		on(DragEventType.movestart, listener);
	}

	/**
	 * Fired repeatedly while the user drags this layer.
	 * 
	 * @param listener the event listener
	 */
	default void onDrag(LeafletEventListener<DragEvent> listener) {
		on(DragEventType.drag, listener);
	}

	/**
	 * Fired when the user stops dragging this layer.
	 * 
	 * @param listener the event listener
	 */
	default void onDragEnd(LeafletEventListener<DragEndEvent> listener) {
		on(DragEventType.dragend, listener);
	}

	/**
	 * Fired when this layer stops moving (because of dragging).
	 * 
	 * @param listener the event listener
	 */
	default void onMoveEnd(LeafletEventListener<LeafletEvent> listener) {
		on(DragEventType.moveend, listener);
	}
}
