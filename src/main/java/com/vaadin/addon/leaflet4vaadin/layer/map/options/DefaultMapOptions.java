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

package com.vaadin.addon.leaflet4vaadin.layer.map.options;

import java.util.UUID;

import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * Map options with leaflet default values
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-06
 * @version 1.0
 */
public class DefaultMapOptions implements MapOptions {

    private static final long serialVersionUID = -3495449596566756164L;

    private final String uuid;

    // Map options
    private boolean preferCanvas = false;

    // Control options
    private boolean attributionControl = true;
    private boolean zoomControl = true;

    // Interaction Options
    private boolean closePopupOnClick = true;
    private double zoomSnap = 1;
    private double zoomDelta = 1;
    private boolean trackResize = true;
    private boolean boxZoom = true;
    private boolean doubleClickZoom = true;
    private boolean dragging = true;

    // State options
    private LatLng center;
    private Integer zoom;
    private Integer minZoom = 0;
    private Integer maxZoom = 30;
    private LatLngBounds maxBounds;
    private LatLngBounds bounds;

    // Animation Options
    private boolean zoomAnimation = true;
    private double zoomAnimationThreshold = 4;
    private boolean fadeAnimation = true;
    private boolean markerZoomAnimation = true;
    private double transform3DLimit = Math.pow(2, 23);

    // Panning Inertia Options
    private boolean interia = true;
    private double inertiaDeceleration = 3000;
    private double inertiaMaxSpeed = Double.MAX_VALUE;
    private double easeLinearity = 0.2;
    private boolean worldCopyJump = false;
    private double maxBoundsViscosity = 0.0;

    // Keyboard Navigation Options
    private boolean keyboard = true;
    private double keyboardPanDelta = 80;

    // Mousewheel options
    private boolean scrollWheelZoom = true;
    private double wheelDebounceTime = 40;
    private double wheelPxPerZoomLevel = 60;

    // Touch interaction options
    private boolean tap = true;
    private int tapTolerance = 15;
    private String touchZoom;
    private boolean bounceAtZoomLimits = true;

    public DefaultMapOptions() {
        uuid = UUID.randomUUID().toString();
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public boolean isZoomAnimation() {
        return this.zoomAnimation;
    }

    public void setZoomAnimation(boolean zoomAnimation) {
        this.zoomAnimation = zoomAnimation;
    }

    public LatLngBounds getBounds() {
        return bounds;
    }

    public void setBounds(LatLngBounds bounds) {
        this.bounds = bounds;
    }

    public boolean isPreferCanvas() {
        return preferCanvas;
    }

    public void setPreferCanvas(boolean preferCanvas) {
        this.preferCanvas = preferCanvas;
    }

    @Override
    public Integer getMinZoom() {
        return this.minZoom;
    }

    public void setMinZoom(Integer minZoom) {
        this.minZoom = minZoom;
    }

    public Integer getMaxZoom() {
        return this.maxZoom;
    }

    public void setMaxZoom(Integer maxZoom) {
        this.maxZoom = maxZoom;
    }

    public boolean isAttributionControl() {
        return attributionControl;
    }

    public void setAttributionControl(boolean attributionControl) {
        this.attributionControl = attributionControl;
    }

    public boolean isZoomControl() {
        return zoomControl;
    }

    public void setZoomControl(boolean zoomControl) {
        this.zoomControl = zoomControl;
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }

    @Override
    public LatLngBounds getMaxBounds() {
        return this.maxBounds;
    }

    @Override
    public void setMaxBounds(LatLngBounds maxBounds) {
        this.maxBounds = maxBounds;
    }

    @Override
    public boolean isClosePopupOnClick() {
        return this.closePopupOnClick;
    }

    @Override
    public void setClosePopupOnClick(boolean closePopupOnClick) {
        this.closePopupOnClick = closePopupOnClick;
    }

    @Override
    public double getZoomSnap() {
        return this.zoomSnap;
    }

    @Override
    public void setZoomSnap(double zoomSnap) {
        this.zoomSnap = zoomSnap;
    }

    @Override
    public double getZoomDelta() {
        return this.zoomDelta;
    }

    @Override
    public void setZoomDelta(double zoomDelta) {
        this.zoomDelta = zoomDelta;
    }

    @Override
    public boolean isTrackResize() {
        return trackResize;
    }

    @Override
    public void setTrackResize(boolean trackResize) {
        this.trackResize = trackResize;
    }

    @Override
    public boolean isBoxZoom() {
        return this.boxZoom;
    }

    @Override
    public void setBoxZoom(boolean boxZoom) {
        this.boxZoom = boxZoom;
    }

    @Override
    public boolean isDoubleClickZoom() {
        return this.doubleClickZoom;
    }

    @Override
    public void setDoubleClickZoom(boolean doubleClickZoom) {
        this.doubleClickZoom = doubleClickZoom;
    }

    @Override
    public boolean isDragging() {
        return this.dragging;
    }

    @Override
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    @Override
    public boolean isKeyboard() {
        return this.keyboard;
    }

    @Override
    public void setKeyboard(boolean keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public double getKeyboardPanDelta() {
        return this.keyboardPanDelta;
    }

    @Override
    public void setKeyboardPanDelta(double keyboardPanDelta) {
        this.keyboardPanDelta = keyboardPanDelta;
    }

    @Override
    public double getZoomAnimationThreshold() {
        return this.zoomAnimationThreshold;
    }

    @Override
    public void setZoomAnimationThreshold(double zoomAnimationThreshold) {
        this.zoomAnimationThreshold = zoomAnimationThreshold;
    }

    @Override
    public boolean isFadeAnimation() {
        return this.fadeAnimation;
    }

    @Override
    public void setFadeAnimation(boolean fadeAnimation) {
        this.fadeAnimation = fadeAnimation;
    }

    @Override
    public boolean isMarkerZoomAnimation() {
        return this.markerZoomAnimation;
    }

    @Override
    public void setMarkerZoomAnimation(boolean markerZoomAnimation) {
        this.markerZoomAnimation = markerZoomAnimation;
    }

    @Override
    public double getTransform3DLimit() {
        return this.transform3DLimit;
    }

    @Override
    public void setTransform3DLimit(double transform3dLimit) {
        this.transform3DLimit = transform3dLimit;
    }

    @Override
    public boolean isTap() {
        return this.tap;
    }

    @Override
    public void setTap(boolean tap) {
        this.tap = tap;
    }

    @Override
    public int getTapTolerance() {
        return this.tapTolerance;
    }

    @Override
    public void setTaptolerance(int tapTolerance) {
        this.tapTolerance = tapTolerance;
    }

    @Override
    public String getTouchZoom() {
        return touchZoom;
    }

    @Override
    public void setTouchZoom(String touchZoom) {
        this.touchZoom = touchZoom;
    }

    @Override
    public boolean isBounceAtZoomLimits() {
        return this.bounceAtZoomLimits;
    }

    @Override
    public void setBounceAtZoomLimits(boolean bounceAtZoomLimits) {
        this.bounceAtZoomLimits = bounceAtZoomLimits;
    }

    @Override
    public boolean isInertia() {
        return this.interia;
    }

    @Override
    public void setInertia(boolean interia) {
        this.interia = interia;
    }

    @Override
    public double getInertiaDeceleration() {
        return this.inertiaDeceleration;
    }

    @Override
    public void setInertiaDeceleration(double inertiaDeceleration) {
        this.inertiaDeceleration = inertiaDeceleration;
    }

    @Override
    public double getInertiaMaxSpeed() {
        return this.inertiaMaxSpeed;
    }

    @Override
    public void setInertiaMaxSpeed(double inertiaMaxSpeed) {
        this.inertiaMaxSpeed = inertiaMaxSpeed;
    }

    @Override
    public double getEaseLinearity() {
        return this.easeLinearity;
    }

    @Override
    public void setEaseLinearity(double easeLinearity) {
        this.easeLinearity = easeLinearity;
    }

    @Override
    public boolean isWorldCopyJump() {
        return this.worldCopyJump;
    }

    @Override
    public void setWorldCopyJump(boolean worldCopyJump) {
        this.worldCopyJump = worldCopyJump;
    }

    @Override
    public double getMaxBoundsViscosity() {
        return this.maxBoundsViscosity;
    }

    @Override
    public void setMaxBoundsViscosity(double maxBoundsViscosity) {
        this.maxBoundsViscosity = maxBoundsViscosity;
    }

    @Override
    public boolean isScrollWheelZoom() {
        return scrollWheelZoom;
    }

    @Override
    public void setScrollWheelZoom(boolean scrollWheelZoom) {
        this.scrollWheelZoom = scrollWheelZoom;
    }

    @Override
    public double getWheelDebounceTime() {
        return this.wheelDebounceTime;
    }

    @Override
    public void setWheelDebounceTime(double wheelDebounceTime) {
        this.wheelDebounceTime = wheelDebounceTime;
    }

    @Override
    public double getWheelPxPerZoomLevel() {
        return this.wheelPxPerZoomLevel;
    }

    @Override
    public void setWheelPxPerZoomLevel(double wheelPxPerZoomLevel) {
        this.wheelPxPerZoomLevel = wheelPxPerZoomLevel;
    }
}
