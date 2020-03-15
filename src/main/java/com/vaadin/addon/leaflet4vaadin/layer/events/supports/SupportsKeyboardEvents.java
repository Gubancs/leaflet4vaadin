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

import com.vaadin.addon.leaflet4vaadin.layer.events.Evented;
import com.vaadin.addon.leaflet4vaadin.layer.events.KeyboardEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.KeyboardEventType;

public interface SupportsKeyboardEvents extends Evented {

    /**
     * Fired when the user presses a key from the keyboard that produces a character
     * value while the map is focused.
     * 
     * @param listener the event listener
     */
    default void onKeyPress(LeafletEventListener<KeyboardEvent> listener) {
        on(KeyboardEventType.keypress, listener);
    }

    /**
     * Fired when the user presses a key from the keyboard while the map is focused.
     * 
     * @param listener the event listener
     */
    default void onKeyDown(LeafletEventListener<KeyboardEvent> listener) {
        on(KeyboardEventType.keydown, listener);
    }

    /**
     * Fired when the user releases a key from the keyboard while the map is
     * focused.
     * 
     * @param listener the event listener
     */
    default void onKeyUp(LeafletEventListener<KeyboardEvent> listener) {
        on(KeyboardEventType.keyup, listener);
    }
}
