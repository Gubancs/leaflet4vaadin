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

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.options.FitBoundsOptions;
import com.vaadin.addon.leaflet4vaadin.options.PanOptions;
import com.vaadin.addon.leaflet4vaadin.options.ZoomOptions;
import com.vaadin.addon.leaflet4vaadin.types.Bounds;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;
import com.vaadin.addon.leaflet4vaadin.types.Point;

public interface MapFunctions extends ExecutableFunctions {

    /**
     * Sets the view of the map (geographical center and zoom) performing a smooth
     * pan-zoom animation.
     */
    default void flyTo(LatLng latlng, int zoom, ZoomOptions options) {
        execute("flyTo", latlng, zoom, options);
    }

    /**
     * Sets the view of the map (geographical center and zoom) performing a smooth
     * pan-zoom animation.
     */
    default void flyTo(LatLng latlng, int zoom, PanOptions options) {
        execute("flyTo", latlng, zoom, options);
    }

    /**
     * Pans the map to a given center.
     */
    default void panTo(LatLng latlng, PanOptions options) {
        execute("panTo", latlng, options);
    }

    /**
     * Sets the view of the map (geographical center and zoom) with the given
     * animation options.
     */
    default void setView(LatLng center, int zoom, ZoomOptions options) {
        execute("setView", center, zoom, options);
    }

    /**
     * Sets the view of the map (geographical center and zoom) with the given
     * animation options.
     */
    default void setView(LatLng center, int zoom, PanOptions options) {
        execute("setView", center, zoom, options);
    }

    /**
     * Sets the zoom of the map.
     * 
     */
    default void setZoom(int zoom, PanOptions options) {
        execute("setZoom", zoom, options);
    }

    /**
     * Increases the zoom of the map by delta (zoomDelta by default).
     */
    default void zoomIn(int delta, ZoomOptions options) {
        execute("zoomIn", delta, options);
    }

    /**
     * Decreases the zoom of the map by delta (zoomDelta by default).
     */
    default void zoomOut(int delta, ZoomOptions options) {
        execute("zoomOut", delta, options);
    }

    /**
     * 
     * Zooms the map while keeping a specified geographical point on the map
     * stationary (e.g. used internally for scroll zoom and double-click zoom).
     */
    default void setZoomAround(LatLng latlng, int zoom, ZoomOptions options) {
        execute("setZoomAround", latlng, zoom, options);
    }

    /**
     * Zooms the map while keeping a specified pixel on the map (relative to the
     * top-left corner) stationary.
     * 
     */
    default void setZoomAround(Point offset, int zoom, ZoomOptions options) {
        execute("setZoomAround", offset, zoom, options);
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
     */
    default void setMinZoom(int minZoom) {
        execute("setMinZoom", minZoom);
    }

    /**
     * Sets the upper limit for the available zoom levels (see the maxZoom option).
     */
    default void setMaxZoom(int maxZoom) {
        execute("setMaxZoom", maxZoom);
    }

    /**
     * Pans the map to the closest view that would lie inside the given bounds (if
     * it's not already), controlling the animation using the options specific, if
     * any.
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
     * 
     * 
     */
    default void invalidateSize(ZoomOptions options) {
        execute("invalidateSize", options);
    }

    /**
     * Checks if the map container size changed and updates the map if so — call it
     * after you've changed the map size dynamically, also animating pan by default.
     * If options.pan is false, panning will not occur. If options.debounceMoveend
     * is true, it will delay moveend event so that it doesn't happen often even if
     * the method is called many times in a row.
     * 
     */
    default void invalidateSize(PanOptions options) {
        execute("invalidateSize", options);
    }

    /**
     * Checks if the map container size changed and updates the map if so — call it
     * after you've changed the map size dynamically, also animating pan by default.
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

    void execute(String functionName, Serializable... arguments);
}