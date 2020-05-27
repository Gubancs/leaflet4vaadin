package com.vaadin.addon.leaflet4vaadin.plugins.fullscreen;

import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;

public interface WithFullScreenControl {

    /**
     * Fired when map entered to full screen mode
     * 
     * @param listener
     *            the listener to call when the event occurs, not {@code null}
     */
    void onEnterFullscreen(LeafletEventListener<LeafletEvent> listener);

    /**
     * Fired when map exited from full screen mode
     * 
     * @param listener
     *            the listener to call when the event occurs, not {@code null}
     */
    void onExitFullscreen(LeafletEventListener<LeafletEvent> listener);

    void toggleFullscreen();

}
