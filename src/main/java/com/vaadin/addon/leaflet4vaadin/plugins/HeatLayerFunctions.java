package com.vaadin.addon.leaflet4vaadin.plugins;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

public interface HeatLayerFunctions extends ExecutableFunctions {

    /**
     * Sets new heatmap options and redraws it.
     */
    default void setOptions(HeatLayerOptions options) {
        execute("setOptions", options);
    }

    /**
     * Adds a new point to the heatmap and redraws it.
     */
    default void addLatLng(LatLng latlng) {
        execute("addLatLng", latlng);
    }

    /**
     * Resets heatmap data and redraws it.
     */
    default void setLatLngs(List<LatLng> laLngs) {
        execute("setLatLngs", new ArrayList<>(laLngs));
    }

    /**
     * Redraws the heatmap.
     */
    default void redraw() {
        execute("redraw");
    }
}
