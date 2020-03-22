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

package com.vaadin.addon.leaflet4vaadin.layer.vectors;

import java.util.Arrays;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * A class for drawing polyline overlays on a map. Extends Path.
 * 
 */
public class Polyline extends Path {

    private static final long serialVersionUID = -2430760430165501877L;
    private double smoothFactor = 1.0;
    private boolean noClip;
    private final List<LatLng> latlngs;

    public Polyline(LatLng... latlngs) {
        this(Arrays.asList(latlngs));
    }

    public Polyline(List<LatLng> latlngs) {
        this.latlngs = latlngs;
    }

    /**
     * Returns true if the Polyline has no LatLngs.
     * 
     * @return true if it has no coordinates
     */
    public boolean isEmpty() {
        return this.latlngs == null || this.latlngs.size() == 0;
    }

    /**
     * Returns the LatLngBounds of the path.
     * 
     * @return the bounds of the polyline
     */
    public LatLngBounds getBounds() {
        return new LatLngBounds(this.latlngs);
    }

    public double getSmoothFactor() {
        return smoothFactor;
    }

    /**
     * How much to simplify the polyline on each zoom level. More means better
     * performance and smoother look, and less means more accurate representation.
     * 
     * @param smoothFactor how much to simplify the polyline on each zoom level
     */
    public void setSmoothFactor(double smoothFactor) {
        this.smoothFactor = smoothFactor;
    }

    public boolean isNoClip() {
        return noClip;
    }

    /**
     * Disable polyline clipping.
     * 
     * @param noClip set it to true to disable polyline clipping
     */
    public void setNoClip(boolean noClip) {
        this.noClip = noClip;
    }

    public List<LatLng> getLatlngs() {
        return latlngs;
    }

}
