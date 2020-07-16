package com.vaadin.addon.leaflet4vaadin.plugins.canvasiconlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * <h3>Leaflet.Canvas-Markers</h3> Leaflet plugin for displaying markers on
 * canvas instead of DOM. Working with Leaflet 1.0.0 and above. Feel free to
 * contribute. Github: <a href=
 * "https://github.com/eJuke/Leaflet.Canvas-Markers">https://github.com/eJuke/Leaflet.Canvas-Markers</a>
 * 
 * <h3>Methods</h3>
 * <ul>
 * <li><b>addMarker(marker)</b> - Adds a marker to the layer.</li>
 * <li><b>addMarkers(markers)</b> - Adds a markers to the layer.</li>
 * <li><b>removeMarker(marker, redraw)</b> - Removes a marker from the layer.
 * Set redraw to true if you want to redraw layer after marker remove</li>
 * <li><b>redraw()</b> - : Redraws the layer</li>
 * <li><b>clearLayers()</b> - Removes all added markers from the layer.</li>
 * </ul>
 * 
 * <h3>Basic Usage</h3>
 * 
 * <pre>
 * CanvasIconLayer canvasIconLayer = new CanvasIconLayer();
 * canvasIconLayer.onAdd((event) -> {
 *     canvasIconLayer.addMarkers(randomMarkers());
 * });
 * canvasIconLayer.addTo(leafletMap);
 * </pre>
 * 
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-07-16
 * @version 1.0
 */
@NpmPackage(value = "leaflet-canvas-marker", version = "0.2.1")
@JsModule("leaflet-canvas-marker/src/_full.js")
public class CanvasIconLayer extends Layer {

    private List<Marker> markers = new ArrayList<>();

    public void addMarkers(Collection<Marker> markers) {
        markers.addAll(markers);
        if (markers instanceof Serializable) {
            executeJs("addMarkers", (Serializable) markers);
        } else {
            addMarkers(new ArrayList<>(markers));
        }
    }

    /**
     * Adds a marker to the layer.
     * 
     * @param marker
     *            The marker to be added to this layer
     */
    public void addMarker(Marker marker) {
        markers.add(marker);
        executeJs("addMarker", marker);
    }

    /**
     * Redraws the canvas icon layer
     */
    public void redraw() {
        executeJs("redraw");
    }

    /**
     * Removes all added markers from the layer
     */
    public void clearLayers() {
        this.markers = new ArrayList<>();
        executeJs("clearLayers");
    }

    /**
     * Removes a marker from the layer. Set redraw to true if you want to redraw
     * layer after marker remove
     * 
     * @param marker
     *            The marker to be removed from the layer
     * @param redraw
     *            If true the redraw() function will be called on client side
     *            after marker remove
     */
    public void removeMarker(Marker marker, boolean redraw) {
        executeJs("removeMarker", marker, redraw);
    }

    /**
     * Get the makers. If you want to remove a maker you should use the
     * removeMarker(marker) method.
     * 
     * @return Return the unmidifiable list of added markers
     */
    public List<Marker> getMarkers() {
        return Collections.unmodifiableList(markers);
    }

    @Override
    public String getLeafletType() {
        return CanvasIconLayer.class.getSimpleName();
    }
}
