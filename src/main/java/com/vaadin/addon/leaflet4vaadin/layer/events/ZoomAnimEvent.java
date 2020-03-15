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
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

public class ZoomAnimEvent extends LeafletEvent {

    private final LatLng center;
    private final int zoom;
    private boolean noUpdate;

    public ZoomAnimEvent(Layer layer, MapEventType eventType, LatLng center, int zoom, boolean noUpdate) {
        super(layer, eventType);
        this.center = center;
        this.zoom = zoom;
        this.noUpdate = noUpdate;
    }

    /**
     * The current center of the map
     * 
     * @return the current center of the map
     */
    public LatLng getCenter() {
        return center;
    }

    /**
     * The current zoom level of the map
     * 
     * @return the current zoom level of the map
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * Whether layers should update their contents due to this event
     * 
     * @return true if layers should update their contents due to this event
     */
    public boolean isNoUpdate() {
        return noUpdate;
    }

    @Override
    public String toString() {
        return "ZoomAnimEvent [type=" + super.getType() + ", center=" + center + ", noUpdate=" + noUpdate + ", zoom="
                + zoom + "]";
    }

}
