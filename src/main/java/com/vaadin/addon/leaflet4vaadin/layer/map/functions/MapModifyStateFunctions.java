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

import com.vaadin.addon.leaflet4vaadin.options.FitBoundsOptions;
import com.vaadin.addon.leaflet4vaadin.options.PanOptions;
import com.vaadin.addon.leaflet4vaadin.options.ZoomOptions;
import com.vaadin.addon.leaflet4vaadin.types.Bounds;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;
import com.vaadin.addon.leaflet4vaadin.types.Point;

/**
 * Methods for modifying map state
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-02-06
 * @version 1.1
 */
public interface MapModifyStateFunctions extends ExecutableFunctions {

    /**
     * Sets the view of the map (geographical center and zoom)
     * 
     * @param latlng the new center coordiantes
     */
    default void flyTo(LatLng latlng) {
        execute("flyTo", latlng);
    }

    /**
     * Sets the view of the map (geographical center and zoom)
     * 
     * @param latlng the new center coordiantes
     * @param zoom   the new zoom level
     */
    default void flyTo(LatLng latlng, int zoom) {
        execute("flyTo", latlng, zoom);
    }

    /**
     * Sets the view of the map (geographical center and zoom) performing a smooth
     * pan animation.
     * 
     * @param latlng  the new center coordiantes
     * @param zoom    the new zoom level
     * @param options the pan animation options
     */
    default void flyTo(LatLng latlng, int zoom, PanOptions options) {
        execute("flyTo", latlng, zoom, options);
    }

    /**
     * Sets the view of the map (geographical center and zoom) performing a smooth
     * zoom animation.
     * 
     * @param latlng  the new center coordiantes
     * @param zoom    the new zoom level
     * @param options the zoom animation options
     */
    default void flyTo(LatLng latlng, int zoom, ZoomOptions options) {
        execute("flyTo", latlng, zoom, options);
    }

    /**
     * Pans the map to a given center.
     * 
     * @param latlng pans the map to a given latlng.
     */
    default void panTo(LatLng latlng) {
        execute("panTo", latlng);
    }

    /**
     * Pans the map to a given center.
     * 
     * @param latlng  pans the map to a given latlng.
     * @param options the pan animation options
     */
    default void panTo(LatLng latlng, PanOptions options) {
        execute("panTo", latlng, options);
    }

    /**
     * Sets the view of the map (geographical center and zoom)
     * 
     * @param center the new center coordiantes
     * @param zoom   the new zoom level
     */
    default void setView(LatLng center, int zoom) {
        execute("setView", center, zoom);
    }

    /**
     * Sets the view of the map (geographical center and zoom) with the given
     * animation options.
     * 
     * @param center  the new center coordiantes
     * @param zoom    the new zoom level
     * @param options the zoom animation options
     */
    default void setView(LatLng center, int zoom, ZoomOptions options) {
        execute("setView", center, zoom, options);
    }

    /**
     * Sets the view of the map (geographical center and zoom) with the given
     * animation options.
     * 
     * @param center  the new center coordiantes
     * @param zoom    the new zoom level
     * @param options the pan animation options
     */
    default void setView(LatLng center, int zoom, PanOptions options) {
        execute("setView", center, zoom, options);
    }

    /**
     * Sets the zoom of the map.
     * 
     * @param zoom the new zoom level
     */
    default void setZoom(int zoom) {
        execute("setZoom", zoom);
    }

    /**
     * Sets the zoom of the map.
     * 
     * @param zoom    the new zoom level
     * @param options the pan animation options
     */
    default void setZoom(int zoom, PanOptions options) {
        execute("setZoom", zoom, options);
    }

    /**
     * Increases the zoom of the map by delta (zoomDelta by default).
     * 
     * @param delta increases the zoom of the map by delta
     */
    default void zoomIn(int delta) {
        execute("zoomIn", delta);
    }

    /**
     * Increases the zoom of the map by delta (zoomDelta by default).
     * 
     * @param delta   increases the zoom of the map by delta
     * @param options the zoom animation options
     */
    default void zoomIn(int delta, ZoomOptions options) {
        execute("zoomIn", delta, options);
    }

    /**
     * Decreases the zoom of the map by delta (zoomDelta by default).
     * 
     * @param delta decreases the zoom of the map by delta
     */
    default void zoomOut(int delta) {
        execute("zoomOut", delta);
    }

    /**
     * Decreases the zoom of the map by delta (zoomDelta by default).
     * 
     * @param delta   decreases the zoom of the map by delta
     * @param options the pan animation options
     */
    default void zoomOut(int delta, ZoomOptions options) {
        execute("zoomOut", delta, options);
    }

    /**
     * Zooms the map while keeping a specified geographical point on the map
     * stationary (e.g. used internally for scroll zoom and double-click zoom).
     * 
     * @param latlng the geographical point
     * @param zoom   the zoom level
     */
    default void setZoomAround(LatLng latlng, int zoom) {
        execute("setZoomAround", latlng, zoom);
    }

    /**
     * Zooms the map while keeping a specified pixel on the map (relative to the
     * top-left corner) stationary.
     * 
     * @param offset  the offset relative to the top-left corner
     * @param zoom    the zoom level
     * @param options the zoom animation options
     * 
     */
    default void setZoomAround(Point offset, int zoom, ZoomOptions options) {
        execute("setZoomAround", offset, zoom, options);
    }

    /**
     * Sets a map view that contains the given geographical bounds with the maximum
     * zoom level possible.
     * 
     * @param bounds the bounds
     */
    default void fitBounds(LatLngBounds bounds) {
        execute("fitBounds", bounds);
    }

    /**
     * Sets a map view that contains the given geographical bounds with the maximum
     * zoom level possible.
     * 
     * @param bounds  the bounds
     * @param options the fit bounds options
     */
    default void fitBounds(LatLngBounds bounds, FitBoundsOptions options) {
        execute("fitBounds", bounds, options);
    }

    /**
     * Sets a map view that mostly contains the whole world with the maximum zoom
     * level possible.
     * 
     */
    default void fitWorld() {
        execute("fitWorld");
    }

    /**
     * Sets a map view that mostly contains the whole world with the maximum zoom
     * level possible.
     * 
     * @param options the fit bounds options
     */
    default void fitWorld(FitBoundsOptions options) {
        execute("fitWorld", options);
    }

    /**
     * Pans the map by a given number of pixels (animated).
     * 
     * @param offset number of pixels
     */
    default void panBy(Point offset) {
        execute("panBy", offset);
    }

    /**
     * Pans the map by a given number of pixels (animated).
     * 
     * @param offset  number of pixels
     * @param options the pan options
     */
    default void panBy(Point offset, PanOptions options) {
        execute("panBy", offset, options);
    }

    /**
     * Sets the view of the map with a smooth animation like flyTo, but takes a
     * bounds parameter like fitBounds.
     * 
     * @param bounds the bounds
     */
    default void flyToBounds(LatLngBounds bounds) {
        execute("flyToBounds", bounds);
    }

    /**
     * Sets the view of the map with a smooth animation like flyTo, but takes a
     * bounds parameter like fitBounds.
     * 
     * @param bounds  the bounds
     * @param options the fit bounds options
     */
    default void flyToBounds(LatLngBounds bounds, FitBoundsOptions options) {
        execute("flyToBounds", bounds, options);
    }

    /**
     * Restricts the map view to the given bounds (see the maxBounds option).
     * 
     * @param bounds the bounds
     */
    default void setMaxBounds(Bounds bounds) {
        execute("setMaxBounds", bounds);
    }

    /**
     * Sets the lower limit for the available zoom levels (see the minZoom option).
     * 
     * @param minZoom the lower limit for the available zoom levels
     */
    default void setMinZoom(int minZoom) {
        execute("setMinZoom", minZoom);
    }

    /**
     * Sets the upper limit for the available zoom levels (see the maxZoom option).
     * 
     * @param maxZoom the upper limit for the available zoom levels
     */
    default void setMaxZoom(int maxZoom) {
        execute("setMaxZoom", maxZoom);
    }

    /**
     * Pans the map to the closest view that would lie inside the given bounds (if
     * it's not already), controlling the animation using the options specific, if
     * any.
     * 
     * @param bounds the bounds
     */
    default void panInsideBounds(LatLngBounds bounds) {
        execute("panInsideBounds", bounds);
    }

    /**
     * Pans the map to the closest view that would lie inside the given bounds (if
     * it's not already), controlling the animation using the options specific, if
     * any.
     * 
     * @param bounds  the bounds
     * @param options the pan options
     */
    default void panInsideBounds(LatLngBounds bounds, PanOptions options) {
        execute("panInsideBounds", bounds, options);
    }

    /**
     * Pans the map the minimum amount to make the latlng visible. Use padding,
     * paddingTopLeft and paddingTopRight options to fit the display to more
     * restricted bounds, like fitBounds. If latlng is already within the
     * (optionally padded) display bounds, the map will not be panned.
     * 
     * @param latlng the latlng
     */
    default void panInside(LatLng latlng) {
        execute("panInside", latlng);
    }

    /**
     * Pans the map the minimum amount to make the latlng visible. Use padding,
     * paddingTopLeft and paddingTopRight options to fit the display to more
     * restricted bounds, like fitBounds. If latlng is already within the
     * (optionally padded) display bounds, the map will not be panned.
     * 
     * @param latlng  the latlng
     * @param options the pan options
     */
    default void panInside(LatLng latlng, PanOptions options) {
        execute("panInside", latlng, options);
    }

    /**
     * Checks if the map container size changed and updates the map if so — call it
     * after you've changed the map size dynamically, also animating pan by default.
     * If options.pan is false, panning will not occur. If options.debounceMoveend
     * is true, it will delay moveend event so that it doesn't happen often even if
     * the method is called many times in a row.
     */
    default void invalidateSize() {
        execute("invalidateSize");
    }

    /**
     * Checks if the map container size changed and updates the map if so — call it
     * after you've changed the map size dynamically, also animating pan by default.
     * If options.pan is false, panning will not occur. If options.debounceMoveend
     * is true, it will delay moveend event so that it doesn't happen often even if
     * the method is called many times in a row.
     * 
     * @param options the pan options
     */
    default void invalidateSize(PanOptions options) {
        execute("invalidateSize", options);
    }

    /**
     * Checks if the map container size changed and updates the map if so — call it
     * after you've changed the map size dynamically, also animating pan by default.
     * If options.pan is false, panning will not occur. If options.debounceMoveend
     * is true, it will delay moveend event so that it doesn't happen often even if
     * the method is called many times in a row.
     * 
     * @param options the zoom options
     */
    default void invalidateSize(ZoomOptions options) {
        execute("invalidateSize", options);
    }

    /**
     * Checks if the map container size changed and updates the map if so — call it
     * after you've changed the map size dynamically, also animating pan by default.
     * 
     * @param animate set true to animating
     */
    default void invalidateSize(boolean animate) {
        execute("invalidateSize", animate);
    }

    /**
     * Stops the currently running panTo or flyTo animation, if any.
     */
    default void stop() {
        execute("stop");
    }
}