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
import com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType;

/**
 * The base event object. All other event objects contain these properties too.
 */
public class DragEndEvent extends LeafletEvent {

    private final double distance;

    public DragEndEvent(Layer layer, DragEventType eventType, double distance) {
        super(layer, eventType);
        this.distance = distance;
    }

    /**
     * The distance in pixels the draggable element was moved by.
     * 
     * @return the distance in pixels.
     */
    public double getDistance() {
        return distance;
    }
}
