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

import com.vaadin.addon.leaflet4vaadin.layer.events.MouseEvent;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;
import com.vaadin.addon.leaflet4vaadin.types.Point;

/**
 * Map Conversion Methods
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-16
 * @version 1.1
 */
public interface MapConversionFunctions extends ExecutableFunctions {

    /**
     * Projects a geographical coordinate LatLng according to the projection of the
     * map's CRS, then scales it according to zoom and the CRS's Transformation. The
     * result is pixel coordinate relative to the CRS origin.
     * 
     * @param latLng the coordinate to be projected
     * @param zoom   the zoom level
     * @return the pixel coordinate relative to the CRS origin
     */
    default CompletableFuture<Point> project(LatLng latLng, int zoom) {
        return call("project", Point.class, latLng, zoom);
    }

    /**
     * Inverse of project.
     * 
     * @param point the point to be unprojected
     * @param zoom   the zoom level
     * @return the geographical coordinate
     */
    default CompletableFuture<LatLng> unproject(Point point, int zoom) {
        return call("unproject", LatLng.class, point, zoom);
    }

    /**
     * Given a pixel coordinate relative to the origin pixel, returns the
     * corresponding geographical coordinate (for the current zoom level).
     * 
     * @param point a pixel coordinate relative to the origin pixel
     * @return the corresponding geographical coordinate
     */
    default CompletableFuture<LatLng> layerPointToLatLng(Point point) {
        return call("layerPointToLatLng", LatLng.class, point);
    }

    /**
     * Given a geographical coordinate, returns the corresponding pixel coordinate
     * relative to the origin pixel.
     * 
     * @param latLng a geographical coordinate
     * @return the corresponding pixel coordinate
     */
    default CompletableFuture<Point> latLngToLayerPoint(LatLng latLng) {
        return call("latLngToLayerPoint", Point.class, latLng);
    }

    /**
     * Returns a LatLng where lat and lng has been wrapped according to the map's
     * CRS's wrapLat and wrapLng properties, if they are outside the CRS's bounds.
     * By default this means longitude is wrapped around the dateline so its value
     * is between -180 and +180 degrees.
     * 
     * 
     * @param latLng a geographical coordinate
     * @return the wrapped latlng
     */
    default CompletableFuture<LatLng> wrapLatLng(LatLng latLng) {
        return call("wrapLatLng", LatLng.class, latLng);
    }

    /**
     * Returns a LatLngBounds with the same size as the given one, ensuring that its
     * center is within the CRS's bounds. By default this means the center longitude
     * is wrapped around the dateline so its value is between -180 and +180 degrees,
     * and the majority of the bounds overlaps the CRS's bounds.
     * 
     * 
     * @param bounds a LatLngBounds to be wrapped
     * @return the wrapped LatLngBounds
     * @see LatLngBounds
     */
    default CompletableFuture<LatLngBounds> wrapLatLngBounds(LatLngBounds bounds) {
        return call("wrapLatLng", LatLngBounds.class, bounds);
    }

    /**
     * Returns the distance between two geographical coordinates according to the
     * map's CRS. By default this measures distance in meters.
     * 
     * @param latLng1 the first geographical coordinate
     * @param latLng2 the second geographical coordinate
     * @return the distance between the given geographical coordinates
     * @see LatLng
     */
    default CompletableFuture<Double> distance(LatLng latLng1, LatLng latLng2) {
        return call("distance", Double.class, latLng1, latLng2);
    }

    /**
     * Given a pixel coordinate relative to the map container, returns the
     * corresponding pixel coordinate relative to the origin pixel.
     * 
     * 
     * @param point a pixel coordinate relative to the map container
     * @return the corresponding pixel coordinate relative to the origin pixel
     * @see Point
     */
    default CompletableFuture<Point> containerPointToLayerPoint(Point point) {
        return call("containerPointToLayerPoint", Point.class, point);
    }

    /**
     * Given a pixel coordinate relative to the origin pixel, returns the
     * corresponding pixel coordinate relative to the map container.
     * 
     * @param point a pixel coordinate relative to the origin pixel
     * @return the corresponding pixel coordinate relative to the map container
     * @see Point
     */
    default CompletableFuture<Point> layerPointToContainerPoint(Point point) {
        return call("layerPointToContainerPoint", Point.class, point);
    }

    /**
     * Given a pixel coordinate relative to the map container, returns the
     * corresponding geographical coordinate (for the current zoom level).
     * 
     * @param point a pixel coordinate relative to the map container
     * @return the corresponding geographical coordinate (for the current zoom
     *         level)
     * @see Point
     * @see LatLng
     */
    default CompletableFuture<LatLng> containerPointToLatLng(Point point) {
        return call("containerPointToLatLng", LatLng.class, point);
    }

    /**
     * Given a geographical coordinate, returns the corresponding pixel coordinate
     * relative to the map container.
     * 
     * 
     * @param latLng a geographical coordinate
     * @return the corresponding pixel coordinate relative to the map container
     * @see Point
     * @see LatLng
     */
    default CompletableFuture<Point> latLngToContainerPoint(LatLng latLng) {
        return call("latLngToContainerPoint", Point.class, latLng);
    }

    /**
     * Given a MouseEvent object, returns the pixel coordinate relative to the map
     * container where the event took place.
     * 
     * 
     * @param mouseEvent a MouseEvent object
     * @return the pixel coordinate relative to the map container where the event
     *         took place
     * @see Point
     * @see LatLng
     */
    default CompletableFuture<Point> mouseEventToContainerPoint(MouseEvent mouseEvent) {
        throw unsupportedOperation();
    }

    /**
     * Given a MouseEvent object, returns the pixel coordinate relative to the
     * origin pixel where the event took place.
     * 
     * 
     * 
     * @param mouseEvent a MouseEvent object
     * @return the origin pixel where the event took place
     * @see Point
     * @see LatLng
     */
    default CompletableFuture<Point> mouseEventToLayerPoint(MouseEvent mouseEvent) {
        throw unsupportedOperation();
    }

    /**
     * Given a MouseEvent object, returns geographical coordinate where the event
     * took place.
     * 
     * 
     * @param mouseEvent a MouseEvent object
     * @return the geographical coordinate where the event took place.
     * @see Point
     * @see LatLng
     */
    default CompletableFuture<LatLng> mouseEventToLatLng(MouseEvent mouseEvent) {
        throw unsupportedOperation();
    }
}