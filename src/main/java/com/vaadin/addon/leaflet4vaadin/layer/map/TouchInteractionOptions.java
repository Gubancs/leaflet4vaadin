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

/**
 * Touch interaction options
 */
public interface TouchInteractionOptions {

    default boolean isTap() {
        return true;
    }

    /**
     * Enables mobile hacks for supporting instant taps (fixing 200ms click delay on
     * iOS/Android) and touch holds (fired as contextmenu events).
     * 
     * @param tap set to true if you want to enable the hack
     */
    void setTap(boolean tap);

    default int getTapTolerance() {
        return 15;
    }

    /**
     * The max number of pixels a user can shift his finger during touch for it to
     * be considered a valid tap.
     * 
     * @param tapTolerance The max number of pixels a user can shift his finger
     *                     during touch
     */
    void setTaptolerance(int tapTolerance);

    String getTouchZoom();

    /**
     * Whether the map can be zoomed by touch-dragging with two fingers. If passed
     * 'center', it will zoom to the center of the view regardless of where the
     * touch events (fingers) were. Enabled for touch-capable web browsers except
     * for old Androids.
     * 
     * @param touchZoom Whether the map can be zoomed by touch-dragging with two
     *                  fingers
     */
    void setTouchZoom(String touchZoom);

    default boolean isBounceAtZoomLimits() {
        return true;
    }

    /**
     * Set it to false if you don't want the map to zoom beyond min/max zoom and
     * then bounce back when pinch-zooming.
     * 
     * @param bounceAtZoomLimits Set it to false if you don't want the map to zoom
     */
    void setBounceAtZoomLimits(boolean bounceAtZoomLimits);
}
