package com.vaadin.addon.leaflet4vaadin.plugins.kmz;

import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.EventTypeRegistry;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * <h3>KMZ file loader for Leaflet Maps</h3>
 * 
 * Web component: <a href=
 * "https://www.npmjs.com/package/leaflet-kmz">https://www.npmjs.com/package/leaflet-kmz</a><br>
 * <br>
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-07-23
 * @version 1.0
 */
@NpmPackage(value = "leaflet-kmz", version = "1.0.4")
@JsModule("leaflet-kmz/dist/leaflet-kmz-src.js")
public class KmzLayer extends Layer {

    private static final long serialVersionUID = 6515861726518730409L;

    public static enum KmzLayerEventType implements LeafletEventType {
        load;
    }

    static {
        EventTypeRegistry.register(KmzLayerEventType.class);
    }

    @LeafletArgument
    private String url;

    public KmzLayer(String url) {
        this.url = url;
    }

    public void load(String url) {
        executeJs("load", url);
    }

    public void onLoad(LeafletEventListener<LeafletEvent> listener) {
        on(KmzLayerEventType.load, listener);
    }

    public String getUrl() {
        return url;
    }
}
