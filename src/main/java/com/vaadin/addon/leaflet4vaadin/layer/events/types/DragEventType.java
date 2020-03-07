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

package com.vaadin.addon.leaflet4vaadin.layer.events.types;

public enum DragEventType implements LeafletEventType {

	/**
	 * Fired when the marker is moved via setLatLng or by dragging. Old and new
	 * coordinates are included in event arguments as oldLatLng, latlng.
	 */
	move,

	/**
	 * Fired when the user starts dragging the marker.
	 */
	dragstart,

	/**
	 * Fired when the marker starts moving (because of dragging).
	 */
	movestart,

	/**
	 * Fired repeatedly while the user drags the marker.
	 */
	drag,

	/**
	 * Fired when the user stops dragging the marker.
	 */
	dragend,

	/**
	 * Fired when the marker stops moving (because of dragging).
	 */
	moveend;

	@Override
	public String getLeafletEvent() {
		return name();
	}

}
