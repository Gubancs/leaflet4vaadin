package com.vaadin.addon.leaflet4vaadin.plugins;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HeatLayerOptions implements Serializable {

    private double minOpacity = 0.5;
    private int maxZoom = 18;
    private double max = 1.0;
    private double radius = 25;
    private double blur = 15;
    private Map<Double, String> gradient = defaultGradient();

    public double getMinOpacity() {
        return minOpacity;
    }

    public static Map<Double, String> defaultGradient() {
        Map<Double, String> colors = new HashMap<>();
        colors.put(0.4, "blue");
        colors.put(0.6, "cyan");
        colors.put(0.7, "lime");
        colors.put(0.8, "yellow");
        colors.put(1.0, "red");
        return colors;
    }

    public void setMinOpacity(double minOpacity) {
        this.minOpacity = minOpacity;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getBlur() {
        return blur;
    }

    public void setBlur(double blur) {
        this.blur = blur;
    }

    public void setGradient(Map<Double, String> gradient) {
        this.gradient = gradient;
    }

    public Map<Double, String> getGradient() {
        return gradient;
    }

}
