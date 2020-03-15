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

package com.vaadin.addon.leaflet4vaadin.layer.map.functions;

import java.util.concurrent.CompletableFuture;

import com.vaadin.addon.leaflet4vaadin.types.Bounds;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;
import com.vaadin.addon.leaflet4vaadin.types.Point;

/**
 * Methods for Getting Map State
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-15
 * @version 1.1
 */
public interface MapGetStateFunctions extends ExecutableFunctions {

    /**
     * Returns the geographical center of the map view
     * 
     * @return the geographical center of the map view
     */
    default CompletableFuture<LatLng> getCenter() {
        return call("getCenter", LatLng.class);
    }

    /**
     * Returns the current zoom level of the map view
     * 
     * @return the current zoom level of the map view
     */
    default CompletableFuture<Integer> getZoom() {
        return call("getZoom", Integer.class);
    }

    /**
     * Returns the geographical bounds visible in the current map view
     * 
     * @return the geographical bounds visible in the current map view
     */
    default CompletableFuture<LatLngBounds> getBounds() {
        return call("getBounds", LatLngBounds.class);
    }

    /**
     * Returns the minimum zoom level of the map (if set in the minZoom option of
     * the map or of any layers), or 0 by default.
     * 
     * @return the minimum zoom level of the map
     */
    default CompletableFuture<Double> getMinZoom() {
        return call("getMinZoom", Double.class);
    }

    /**
     * Returns the maximum zoom level of the map (if set in the maxZoom option of
     * the map or of any layers).
     * 
     * @return the maximum zoom level of the map
     */
    default CompletableFuture<Double> getMaxZoom() {
        return call("getMaxZoom", Double.class);
    }

    /**
     * Returns the maximum zoom level on which the given bounds fit to the map view
     * in its entirety. If inside (optional) is set to true, the method instead
     * returns the minimum zoom level on which the map view fits into the given
     * bounds in its entirety.
     * 
     * @param bounds  the bounds
     * @param inside  the inside
     * @param padding the padding
     * @return the maximum zoom level on which the given bounds fit to the map view
     *         in its entirety
     */
    default CompletableFuture<Double> getBoundsZoom(LatLngBounds bounds, boolean inside, Point padding) {
        return call("getBoundsZoom", Double.class, bounds, inside, padding);
    }

    /**
     * Returns the current size of the map container (in pixels).
     * 
     * @return the current size of the map container
     */
    default CompletableFuture<Point> getSize() {
        return call("getSize", Point.class);
    }

    /**
     * Returns the bounds of the current map view in projected pixel coordinates
     * (sometimes useful in layer and overlay implementations).
     * 
     * @return the bounds of the current map view
     */
    default CompletableFuture<Bounds> getPixelBounds() {
        return call("getPixelBounds", Bounds.class);
    }

    /**
     * Returns the projected pixel coordinates of the top left point of the map
     * layer (useful in custom layer and overlay implementations).
     * 
     * @return the projected pixel coordinates of the top left point of the map
     */
    default CompletableFuture<Point> getPixelOrigin() {
        return call("getPixelOrigin", Point.class);
    }

    /**
     * Returns the world's bounds in pixel coordinates for zoom level zoom. If zoom
     * is omitted, the map's current zoom level is used.
     * 
     * @param zoom the value of zoom level
     * @return the world's bounds in pixel coordinates for zoom level zoom
     */
    default CompletableFuture<Bounds> getPixelWorldBounds(Double zoom) {
        return call("getPixelWorldBounds", Bounds.class, zoom);
    }
}