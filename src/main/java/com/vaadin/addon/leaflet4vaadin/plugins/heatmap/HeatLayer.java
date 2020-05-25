package com.vaadin.addon.leaflet4vaadin.plugins.heatmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * <h3>Leaflet.heat</h3> A tiny, simple and fast Leaflet heatmap plugin. Uses
 * <a href="https://github.com/Leaflet/Leaflet.heat">leaflet.heat</a> which uses
 * <a href="https://github.com/mourner/simpleheat">simpleheat</a> under the
 * hood, additionally clustering points into a grid for performance.
 * 
 * 
 * <h3>Heatmap options</h3>
 * <ul>
 * <li><b>minOpacity</b> - the minimum opacity the heat will start at</li>
 * <li><b>maxZoom</b> - zoom level where the points reach maximum intensity (as
 * intensity scales with zoom), equals maxZoom of the map by default</li>
 * <li><b>max</b> - maximum point intensity, 1.0 by default</li>
 * <li><b>radius</b> - radius of each "point" of the heatmap, 25 by default</li>
 * <li><b>blur</b> - amount of blur, 15 by default</li>
 * <li><b>gradient</b> - color gradient config, e.g. {0.4: 'blue', 0.65: 'lime',
 * 1: 'red'}</li>
 * </ul>
 * 
 * <h3>Basic Usage</h3>
 * 
 * <pre>
 * HeatLayer heatLayer = new HeatLayer();
 * heatLayer.setLatLngs(randomLatLngs());
 * heatLayer.setOptions(new HeatLayerOptions());
 * heatLayer.addTo(leafletMap);
 * </pre>
 * 
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-05-25
 * @version 1.0
 * @see HeatLayerOptions
 */
@NpmPackage(value = "leaflet.heat", version = "0.2.0")
@JsModule("leaflet.heat/dist/leaflet-heat.js")
public class HeatLayer extends Layer {

    private static final long serialVersionUID = 8264047409630317556L;
    private HeatLayerOptions options;

    @LeafletArgument
    private List<LatLng> latLngs;

    /**
     * Constructs a heatmap layer
     */
    public HeatLayer() {
        this(new ArrayList<>(), new HeatLayerOptions());
    }

    /**
     * Constructs a heatmap layer given an array of points
     * 
     * @param latLngs the initial points to be added
     */
    public HeatLayer(List<LatLng> latLngs) {
        this(latLngs, new HeatLayerOptions());
    }

    /**
     * Constructs a heatmap layer with custom layer options
     * 
     * @param options the custom options to be used
     * @see @HeatLayerOptions
     */
    public HeatLayer(HeatLayerOptions options) {
        this(new ArrayList<>(), options);
    }

    /**
     * Constructs a heatmap layer given an array of points and an object with the
     * following options
     * 
     * @param latLngs the initial points to be added
     * @param options the custom options to be used
     * @see @HeatLayerOptions
     * @see @LatLng
     */
    public HeatLayer(List<LatLng> latLngs, HeatLayerOptions options) {
        this.latLngs = latLngs;
        this.options = options;
    }

    /**
     * Adds a new point to the heatmap and redraws it.
     * 
     * @param latLng a new point to be added
     * @see @LatLng
     */
    public void addLatLng(LatLng latLng) {
        this.latLngs.add(latLng);
        execute("addLatLng", latLng);
    }

    /**
     * Resets heatmap data and redraws it.
     * 
     * @param laLngs the list of the new points to be added
     * @see @LatLng
     */
    public void setLatLngs(List<LatLng> laLngs) {
        this.latLngs = laLngs;
        execute("setLatLngs", new ArrayList<>(laLngs));
    }

    /**
     * Sets new heatmap options and redraws it.
     * 
     * @param options the custom options to be used
     */
    public void setOptions(HeatLayerOptions options) {
        this.options = options;
        execute("setOptions", options);
    }

    /**
     * Redraws the heatmap.
     */
    public void redraw() {
        execute("redraw");
    }

    public List<LatLng> getLatLngs() {
        return latLngs;
    }

    public double getMinOpacity() {
        return options.getMinOpacity();
    }

    public int getMaxZoom() {
        return options.getMaxZoom();
    }

    public double getMax() {
        return options.getMax();
    }

    public double getRadius() {
        return options.getRadius();
    }

    public double getBlur() {
        return options.getBlur();
    }

    public Map<Double, String> getGradient() {
        return options.getGradient();
    }
}
