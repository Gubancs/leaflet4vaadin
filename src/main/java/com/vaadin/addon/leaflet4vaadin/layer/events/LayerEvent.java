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
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LayerEventType;

/**
 * Represents the Leaflet LayerEevent
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-14
 * @version 1.0
 */
public class LayerEvent extends LeafletEvent {

    private Layer child;

    public LayerEvent(Layer layer, LayerEventType eventType, Layer child) {
        super(layer, eventType);
    }

    /**
     * The layer that was added or removed.
     * 
     * @return the child
     */
    public Layer getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "LayerEvent [type=" + super.getType() + ", child=" + child + "]";
    }

}
