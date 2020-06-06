package com.vaadin.addon.leaflet4vaadin.plugins.draw;

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;
import com.vaadin.addon.leaflet4vaadin.types.DivIcon;
import com.vaadin.addon.leaflet4vaadin.types.Icon;

/**
 * <h3>PolylineOptions</h3>
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-06-05
 * @version 1.0
 */
public class DrawOptions implements Serializable {

    private static final long serialVersionUID = -9021759276279468491L;
    private PolylineOptions polyline;
    private PolygonOptions polygon;
    private RectangleOptions rectangle;
    private CircleOptions circle;
    private MarkerOptions marker;
    private CircleMarkerOptions circlemarker;

    public DrawOptions() {
        polygon = new PolygonOptions();
        polyline = new PolylineOptions();
        rectangle = new RectangleOptions();
        circle = new CircleOptions();
        marker = new MarkerOptions();
        circlemarker = new CircleMarkerOptions();
    }

    public PolylineOptions getPolyline() {
        return polyline;
    }

    public PolygonOptions getPolygon() {
        return polygon;
    }

    public RectangleOptions getRectangle() {
        return rectangle;
    }

    public CircleOptions getCircle() {
        return circle;
    }

    public MarkerOptions getMarker() {
        return marker;
    }
    
    public void setMarker(MarkerOptions marker) {
        this.marker = marker;
    }
    
    public void setCirclemarker(CircleMarkerOptions circlemarker) {
        this.circlemarker = circlemarker;
    }

    public CircleMarkerOptions getCirclemarker() {
        return circlemarker;
    }

    static class MarkerOptions implements Serializable {

        private static final long serialVersionUID = 2968645746362831669L;
        private Icon icon = Icon.DEFAULT_ICON;
        private int zIndexOffset = 2000;
        private boolean repeatMode = false;

        public Icon getIcon() {
            return icon;
        }

        public void setIcon(Icon icon) {
            this.icon = icon;
        }

        public int getzIndexOffset() {
            return zIndexOffset;
        }

        public void setzIndexOffset(int zIndexOffset) {
            this.zIndexOffset = zIndexOffset;
        }

        public boolean isRepeatMode() {
            return repeatMode;
        }

        public void setRepeatMode(boolean repeatMode) {
            this.repeatMode = repeatMode;
        }

    }

    static class CircleMarkerOptions extends MarkerOptions {

        private static final long serialVersionUID = 8724179517612582859L;

        private PathOptions shapeOptions = new PathOptions();

        public PathOptions getShapeOptions() {
            return shapeOptions;
        }

        public void setShapeOptions(PathOptions shapeOptions) {
            this.shapeOptions = shapeOptions;
        }
    }

    static class CircleOptions extends DrawShapeOptions {

        private static final long serialVersionUID = -4237528728056720018L;
        private boolean showRadius = false;

        public boolean isShowRadius() {
            return showRadius;
        }

        public void setShowRadius(boolean showRadius) {
            this.showRadius = showRadius;
        }

    }

    static class RectangleOptions extends DrawShapeOptions {
        private static final long serialVersionUID = 5426996272782349464L;

    }

    static class PolygonOptions extends PolylineOptions {
        private static final long serialVersionUID = -5813553500480127720L;
        private boolean showArea = false;

        public boolean isShowArea() {
            return showArea;
        }

        public void setShowArea(boolean showArea) {
            this.showArea = showArea;
        }
    }

    static class PolylineOptions implements Serializable {
        private static final long serialVersionUID = -4096597806539172884L;
        private boolean allowIntersection = true;
        private double guidelineDistance = 20.0;
        private boolean showLength = true;
        private boolean metric = true;
        private boolean feet = true;
        private int factor = 1;
        private int maxPoints = 0;
        private boolean nautic = false;
        private int zIndexOffset = 2000;
        private int maxGuideLineLength = 4000;
        private boolean repeatMode = false;
        private PathOptions shapeOptions = new PathOptions();
        private Icon icon = new DivIcon("leaflet-div-icon leaflet-editing-icon", 8);
        private Icon touchIcon = new DivIcon("leaflet-div-icon leaflet-editing-icon leaflet-touch-icon", 20);

        public boolean isAllowIntersection() {
            return allowIntersection;
        }

        public void setAllowIntersection(boolean allowIntersection) {
            this.allowIntersection = allowIntersection;
        }

        public double getGuidelineDistance() {
            return guidelineDistance;
        }

        public void setGuidelineDistance(double guidelineDistance) {
            this.guidelineDistance = guidelineDistance;
        }

        public boolean isShowLength() {
            return showLength;
        }

        public void setShowLength(boolean showLength) {
            this.showLength = showLength;
        }

        public boolean isMetric() {
            return metric;
        }

        public void setMetric(boolean metric) {
            this.metric = metric;
        }

        public boolean isFeet() {
            return feet;
        }

        public void setFeet(boolean feet) {
            this.feet = feet;
        }

        public int getFactor() {
            return factor;
        }

        public void setFactor(int factor) {
            this.factor = factor;
        }

        public int getMaxPoints() {
            return maxPoints;
        }

        public void setMaxPoints(int maxPoints) {
            this.maxPoints = maxPoints;
        }

        public boolean isNautic() {
            return nautic;
        }

        public void setNautic(boolean nautic) {
            this.nautic = nautic;
        }

        public int getzIndexOffset() {
            return zIndexOffset;
        }

        public void setzIndexOffset(int zIndexOffset) {
            this.zIndexOffset = zIndexOffset;
        }

        public int getMaxGuideLineLength() {
            return maxGuideLineLength;
        }

        public void setMaxGuideLineLength(int maxGuideLineLength) {
            this.maxGuideLineLength = maxGuideLineLength;
        }

        public boolean isRepeatMode() {
            return repeatMode;
        }

        public void setRepeatMode(boolean repeatMode) {
            this.repeatMode = repeatMode;
        }

        public PathOptions getShapeOptions() {
            return shapeOptions;
        }

        public void setShapeOptions(PathOptions shapeOptions) {
            this.shapeOptions = shapeOptions;
        }

        public Icon getIcon() {
            return icon;
        }

        public void setIcon(Icon icon) {
            this.icon = icon;
        }

        public Icon getTouchIcon() {
            return touchIcon;
        }

        public void setTouchIcon(Icon touchIcon) {
            this.touchIcon = touchIcon;
        }

    }

    static class DrawShapeOptions implements Serializable {
        private static final long serialVersionUID = 5426996272782349464L;
        private boolean repeatMode = false;
        private boolean metric = true;
        private boolean showArea = true;
        private boolean feet = true;
        private boolean nautic = false;
        private PathOptions shapeOptions = new PathOptions();

        public boolean isRepeatMode() {
            return repeatMode;
        }

        public void setRepeatMode(boolean repeatMode) {
            this.repeatMode = repeatMode;
        }

        public boolean isMetric() {
            return metric;
        }

        public void setMetric(boolean metric) {
            this.metric = metric;
        }

        public boolean isShowArea() {
            return showArea;
        }

        public void setShowArea(boolean showArea) {
            this.showArea = showArea;
        }

        public boolean isFeet() {
            return feet;
        }

        public void setFeet(boolean feet) {
            this.feet = feet;
        }

        public boolean isNautic() {
            return nautic;
        }

        public void setNautic(boolean nautic) {
            this.nautic = nautic;
        }

        public PathOptions getShapeOptions() {
            return shapeOptions;
        }

        public void setShapeOptions(PathOptions shapeOptions) {
            this.shapeOptions = shapeOptions;
        }

    }

    public static class DrawErrorOptions implements Serializable {
        private static final long serialVersionUID = 8755836304362633230L;
        private String color;
        private String message;
        private int timeout;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }

    }

}
