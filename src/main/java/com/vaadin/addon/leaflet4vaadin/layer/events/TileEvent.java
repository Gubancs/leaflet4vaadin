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
import com.vaadin.addon.leaflet4vaadin.layer.events.types.TooltipEventType;
import com.vaadin.addon.leaflet4vaadin.types.Point;

public class TileEvent extends LeafletEvent {

    private Point coords;

    public TileEvent(Layer layer, TooltipEventType eventType, Point coords) {
        super(layer, eventType);
        this.coords = coords;
    }

    /**
     * Point object with the tile's x, y, and z (zoom level) coordinates.
     * 
     * @return the coords
     */
    public Point getCoords() {
        return coords;
    }
}