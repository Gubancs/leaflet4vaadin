package com.vaadin.addon.leaflet4vaadin.plugins.fullscreen;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;

class LeafletMapFullScreenAdapter implements WithFullScreenControl {

    private final LeafletMap leafletMap;

    LeafletMapFullScreenAdapter(LeafletMap leafletMap) {
        this.leafletMap = leafletMap;
    }

    @Override
    public void onEnterFullscreen(LeafletEventListener<LeafletEvent> listener) {
        leafletMap.on(FullScreenControl.FullScreenEventType.enterFullscreen, listener);
    }

    @Override
    public void onExitFullscreen(LeafletEventListener<LeafletEvent> listener) {
        leafletMap.on(FullScreenControl.FullScreenEventType.exitFullscreen, listener);
    }

    @Override
    public void toggleFullScreen() {
        leafletMap.execute("toggleFullScreen");
    }

}
