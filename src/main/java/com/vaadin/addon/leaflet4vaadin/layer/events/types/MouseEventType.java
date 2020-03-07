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

public enum MouseEventType implements LeafletEventType {

	/**
	 * Fired when the user clicks (or taps) the layer.
	 */
	click,

	/**
	 * Fired when the user double-clicks (or double-taps) the layer.
	 */
	dblclick,

	/**
	 * Fired when the user pushes the mouse button on the layer.
	 */
	mousedown,

	/**
	 * Fired when the user releases the mouse button pushed on the layer.
	 */
	mouseup,

	/**
	 * Fired when the mouse enters the layer.
	 */
	mouseover,

	/**
	 * Fired when the mouse leaves the layer.
	 */
	mouseout,

	/**
	 * Fired when the user right-clicks on the layer, prevents default browser
	 * context menu from showing if there are listeners on this event. Also fired on
	 * mobile when the user holds a single touch for a second (also called long
	 * press).
	 */
	contextmenu;

}
