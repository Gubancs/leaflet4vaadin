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

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.KeyboardEventType;

public class KeyboardEvent extends LeafletEvent {

    private final String key;
    private final String code;
    private final int keyCode;
    private final boolean ctrlKey;
    private final boolean shiftKey;
    private final boolean altKey;

    public KeyboardEvent(Layer layer, KeyboardEventType eventType, String key, String code, int keyCode,
            boolean ctrlKey, boolean shiftKey, boolean altKey) {
        super(layer, eventType);
        this.key = key;
        this.code = code;
        this.keyCode = keyCode;
        this.ctrlKey = ctrlKey;
        this.shiftKey = shiftKey;
        this.altKey = altKey;
    }

    /**
     * Returns the code of the key that triggered the event
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the key value of the key represented by the event
     * 
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the Unicode character code of the key that triggered the onkeypress
     * event, or the Unicode key code of the key that triggered the onkeydown or
     * onkeyup event
     * 
     * @return the keyCode
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * Returns whether the "SHIFT" key was pressed when the key event was triggered
     * 
     * @return the shiftKey
     */
    public boolean isShiftKey() {
        return shiftKey;
    }

    /**
     * Returns whether the "ALT" key was pressed when the key event was triggered
     * 
     * @return the altKey
     */
    public boolean isAltKey() {
        return altKey;
    }

    /**
     * Returns whether the "CTRL" key was pressed when the key event was triggered
     * 
     * @return the ctrlKey
     */
    public boolean isCtrlKey() {
        return ctrlKey;
    }

    @Override
    public String toString() {
        return "KeyboardEvent [type=" + super.getType() + ", altKey=" + altKey + ", code=" + code + ", ctrlKey="
                + ctrlKey + ", key=" + key + ", keyCode=" + keyCode + ", shiftKey=" + shiftKey + "]";
    }

}
