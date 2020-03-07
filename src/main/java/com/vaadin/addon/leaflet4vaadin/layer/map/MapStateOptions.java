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

package com.vaadin.addon.leaflet4vaadin.layer.map;

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

public interface MapStateOptions extends Serializable {

    LatLng getCenter();

    /**
     * Initial geographic center of the map
     * 
     * @param center the intial center of the map
     */
    void setCenter(LatLng center);

    int getZoom();

    /**
     * Initial map zoom level
     * 
     * @param zoom the initial map zoom level
     */
    void setZoom(int zoom);

    /**
     * Sets a map view that contains the given geographical bounds with the maximum
     * zoom level possible.
     * 
     * @param bounds the geographical bounds of the map
     */
    void setBounds(LatLngBounds bounds);

    /**
     * Returns the geographical bounds visible in the current map view
     * 
     * @return the geographical bounds of the map
     */
    LatLngBounds getBounds();

    Integer getMinZoom();

    /**
     * Minimum zoom level of the map. If not specified and at least one GridLayer or
     * TileLayer is in the map, the lowest of their minZoom options will be used
     * instead.
     * 
     * @param minZoom the minimum zoom level of the map
     */
    void setMinZoom(Integer minZoom);

    Integer getMaxZoom();

    /**
     * Maximum zoom level of the map. If not specified and at least one GridLayer or
     * TileLayer is in the map, the highest of their maxZoom options will be used
     * instead.
     * 
     * @param maxZoom the maximum zoom level of the map
     */
    void setMaxZoom(Integer maxZoom);
}
