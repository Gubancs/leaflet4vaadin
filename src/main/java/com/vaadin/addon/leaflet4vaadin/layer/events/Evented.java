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

package com.vaadin.addon.leaflet4vaadin.layer.events;

import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;

/**
 * A set of methods shared between event-powered classes (like Map and Marker).
 * Generally, events allow you to execute some function when something happens
 * with an object (e.g. the user clicks on the map, causing the map to fire
 * 'click' event).
 *
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-02-06
 * @version 1.0
 */
public interface Evented {
	/**
	 * Adds a listener function (fn) to a particular event type of the object. You
	 * can optionally specify the context of the listener (object the this keyword
	 * will point to). You can also pass several space-separated types (e.g. 'click
	 * dblclick').
	 * 
	 * @param eventType type of the event to be listening
	 * @param listener  the event listener
	 * @param <T>       the generic type of the leaflet object
	 */
	<T extends LeafletEvent> void addEventListener(LeafletEventType eventType, LeafletEventListener<T> listener);

	/**
	 * Alias to addEventListener(…)
	 * 
	 * @param eventType type of the event to be listening
	 * @param listener  the event listener
	 * @param <T>       the generic type of the leaflet object
	 */
	default <T extends LeafletEvent> void on(LeafletEventType eventType, LeafletEventListener<T> listener) {
		addEventListener(eventType, listener);
	}

	//
	// /**
	// * Removes all listeners to all events on the object. This includes implicitly
	// * attached events.
	// */
	// void off();
	//
	// /**
	// * Removes a previously added listener function. It will remove all the
	// * listeners of that particular event from the object.
	// */
	// void off(LeafletEventType leafletEventType);
	//
	// /**
	// * Fires an event of the specified type.
	// */
	// void fireEvent(LeafletEventType leafletEventType);
	//
	// /**
	// * Alias to fireEvent(…)
	// */
	// default void fire(LeafletEventType leafletEventType) {
	// fireEvent(leafletEventType);
	// }

}
