package com.vaadin.addon.leaflet4vaadin.plugins.markercluster;

import com.vaadin.addon.leaflet4vaadin.layer.events.types.EventTypeRegistry;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;

public enum MarkerClusterEventType implements LeafletEventType {

    /**
     * Fired when the user clicks (or taps) the marker cluster
     */
    clusterclick,

    /**
     * Fired when the mouse enters the marker cluster
     */
    clustermouseover,

    /**
     * Fired when the mouse leaves the marker cluster
     */
    clustermouseout,

    /**
     * Fires when marker clustering/unclustering animation has completed
     */
    animationend,

    /**
     * Fires when overlapping markers get spiderified (Contains cluster and
     * markers attributes)
     */
    spiderfied,

    /**
     * Fires when overlapping markers get unspiderified (Contains cluster and
     * markers attributes)
     */
    unspiderfied;
    
    static {
        EventTypeRegistry.register(MarkerClusterEventType.class);
    }
}
