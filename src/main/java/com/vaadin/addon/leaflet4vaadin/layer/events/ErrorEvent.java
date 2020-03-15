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
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;

/**
 * Represents the leaflet ErrorEvent
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-11
 * @version 1.0
 */
public class ErrorEvent extends LeafletEvent {

    private final String message;
    private final int code;

    public ErrorEvent(Layer layer, LeafletEventType eventType, String message, int code) {
        super(layer, eventType);
        this.message = message;
        this.code = code;
    }

    /**
     * Error code (if applicable).
     * 
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Error message.
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorEvent [type=" + super.getType() + ", code=" + code + ", message=" + message + "]";
    }

}
