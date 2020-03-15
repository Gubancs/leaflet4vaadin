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
import com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType;
import com.vaadin.addon.leaflet4vaadin.types.Point;

public class ResizeEvent extends LeafletEvent {

    private final Point oldSize;
    private final Point newSize;

    public ResizeEvent(Layer layer, Point oldSize, Point newSize) {
        super(layer, MapEventType.resize);
        this.newSize = newSize;
        this.oldSize = oldSize;
    }

    /**
     * The old size before resize event.
     * 
     * @return the old size before resize event
     */
    public Point getOldSize() {
        return oldSize;
    }

    /**
     * The new size after the resize event.
     * 
     * @return the new size after the resize event.
     */
    public Point getNewSize() {
        return newSize;
    }

    @Override
    public String toString() {
        return "ResizeEvent [type=" + super.getType() + ", newSize=" + newSize + ", oldSize=" + oldSize + "]";
    }

}
