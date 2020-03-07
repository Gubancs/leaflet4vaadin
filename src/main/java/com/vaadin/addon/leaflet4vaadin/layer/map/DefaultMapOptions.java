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

package com.vaadin.addon.leaflet4vaadin.layer.map;

import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

public class DefaultMapOptions implements MapOptions {

    private static final long serialVersionUID = -3495449596566756164L;
    private LatLng center;
    private int zoom;
    private Integer minZoom = 0;
    private Integer maxZoom = 20;
    private boolean zoomAnimation = true;
    private LatLngBounds bounds;
    private boolean preferCanvas = false;

    private boolean attributionControl = true;
    private boolean zoomControl = true;

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
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

}
