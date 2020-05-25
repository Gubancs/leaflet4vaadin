
// Copyright 2020 Gabor Kokeny and contributors
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.vaadin.addon.leaflet4vaadin.layer.raster;

import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.layer.InteractiveLayer;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * Used to load and display a single image over specific bounds of the map.
 * Extends InteractiveLayer.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-06
 * @version 1.0
 */
public class ImageOverlay extends InteractiveLayer {

    public static enum Events implements LeafletEventType {
        load, error;
    }

    private static final long serialVersionUID = 4991481613331548678L;
    @LeafletArgument(index = 0)
    private String imageUrl;
    @LeafletArgument(index = 1)
    private LatLngBounds bounds;
    private double opacity = 1.0;
    private String alt;
    private String crossOrigin;
    private String errorOverlayUrl;
    private int zIndex = 1;
    private String className;

    /**
     * Instantiates an image overlay object given the URL of the image and the
     * geographical bounds it is tied to.
     * 
     * @param imageUrl the image url
     */
    public ImageOverlay(String imageUrl) {
        super();
        this.imageUrl = imageUrl;
        setInteractive(false);
    }

    /**
     * Fired when the ImageOverlay layer has loaded its image
     * 
     * @param listener the listener to call when the event occurs, not {@code null}
     */
    public void onLoad(LeafletEventListener<LeafletEvent> listener) {
        on(Events.load, listener);
    }

    /**
     * Fired when the ImageOverlay layer fails to load its image
     * 
     * @param listener the listener to call when the event occurs, not {@code null}
     */
    public void onError(LeafletEventListener<LeafletEvent> listener) {
        on(Events.error, listener);
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @return the opacity
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * The opacity of the image overlay.
     * 
     * @param opacity the opacity to set
     */
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the alt
     */
    public String getAlt() {
        return alt;
    }

    /**
     * Text for the alt attribute of the image (useful for accessibility).
     * 
     * @param alt the alt to set
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * @return the crossOrigin
     */
    public String getCrossOrigin() {
        return crossOrigin;
    }

    /**
     * Whether the crossOrigin attribute will be added to the image. If a String is
     * provided, the image will have its crossOrigin attribute set to the String
     * provided. This is needed if you want to access image pixel data. Refer to
     * CORS Settings for valid String values.
     * 
     * @param crossOrigin the crossOrigin to set
     */
    public void setCrossOrigin(String crossOrigin) {
        this.crossOrigin = crossOrigin;
    }

    /**
     * @return the errorOverlayUrl
     */
    public String getErrorOverlayUrl() {
        return errorOverlayUrl;
    }

    /**
     * URL to the overlay image to show in place of the overlay that failed to load.
     * 
     * @param errorOverlayUrl the errorOverlayUrl to set
     */
    public void setErrorOverlayUrl(String errorOverlayUrl) {
        this.errorOverlayUrl = errorOverlayUrl;
    }

    /**
     * @return the zIndex
     */
    public int getzIndex() {
        return zIndex;
    }

    /**
     * The explicit zIndex of the overlay layer.
     * 
     * @param zIndex the zIndex to set
     */
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * A custom class name to assign to the image. Empty by default.
     * 
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the bounds
     */
    public LatLngBounds getBounds() {
        return bounds;
    }

    /**
     * @param bounds the bounds to set
     */
    public void setBounds(LatLngBounds bounds) {
        this.bounds = bounds;
    }
}
