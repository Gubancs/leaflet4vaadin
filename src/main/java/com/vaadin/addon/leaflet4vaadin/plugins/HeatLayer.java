package com.vaadin.addon.leaflet4vaadin.plugins;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

public class HeatLayer extends Layer implements HeatLayerFunctions {

    private HeatLayerOptions options;
    private List<LatLng> latLngs;

    public HeatLayer() {
        this(new ArrayList<>(), new HeatLayerOptions());
    }

    public HeatLayer(List<LatLng> latLngs) {
        this(latLngs, new HeatLayerOptions());
    }

    public HeatLayer(HeatLayerOptions heatLayerOptions) {
        this(new ArrayList<>(), heatLayerOptions);
    }

    public HeatLayer(List<LatLng> latLngs, HeatLayerOptions options) {
        this.latLngs = latLngs;
        this.options = options;
    }


    @Override
    public void addLatLng(LatLng latLng) {
        this.latLngs.add(latLng);
        HeatLayerFunctions.super.addLatLng(latLng);
    }

    @Override
    public void setLatLngs(List<LatLng> laLngs) {
        this.latLngs = laLngs;
        HeatLayerFunctions.super.setLatLngs(laLngs);
    }

    @Override
    public void setOptions(HeatLayerOptions options) {
        this.options = options;
        HeatLayerFunctions.super.setOptions(options);
    }

    public List<LatLng> getLatLngs() {
        return latLngs;
    }

    public HeatLayerOptions getOptions() {
        return options;
    }

}
