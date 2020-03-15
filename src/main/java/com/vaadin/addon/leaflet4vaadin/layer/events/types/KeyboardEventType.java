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

/**
 * Keyboard events
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-14
 * @version 1.0
 */
public enum KeyboardEventType implements LeafletEventType {

    /**
     * Fired when the user presses a key from the keyboard that produces a character
     * value while the map is focused.
     */
    keypress,

    /**
     * Fired when the user presses a key from the keyboard while the map is focused.
     * Unlike the keypress event, the keydown event is fired for keys that produce a
     * character value and for keys that do not produce a character value.
     */
    keydown,

    /**
     * Fired when the user releases a key from the keyboard while the map is
     * focused.
     */
    keyup;
}
