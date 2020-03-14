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

package com.vaadin.addon.leaflet4vaadin.layer.map.options;

import java.io.Serializable;

/**
 * Leaflet map mouse wheel options
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-14
 * @version 1.0
 */
public interface MousewheelOptions extends Serializable {

    boolean isScrollWheelZoom();

    /**
     * Whether the map can be zoomed by using the mouse wheel. If passed 'center',
     * it will zoom to the center of the view regardless of where the mouse was.
     * 
     * @param scrollWheelZoom whether the map can be zoomed by using the mouse wheel
     */
    void setScrollWheelZoom(boolean scrollWheelZoom);

    double getWheelDebounceTime();

    /**
     * Limits the rate at which a wheel can fire (in milliseconds). By default user
     * can't zoom via wheel more often than once per 40 ms.
     * 
     * @param wheelDebounceTime he bounce time in milliseconds
     */
    void setWheelDebounceTime(double wheelDebounceTime);

    double getWheelPxPerZoomLevel();

    /**
     * How many scroll pixels (as reported by L.DomEvent.getWheelDelta) mean a
     * change of one full zoom level. Smaller values will make wheel-zooming faster
     * (and vice versa).
     * 
     * @param wheelPxPerZoomLevel pixel per zoom level
     */
    void setWheelPxPerZoomLevel(double wheelPxPerZoomLevel);
}
