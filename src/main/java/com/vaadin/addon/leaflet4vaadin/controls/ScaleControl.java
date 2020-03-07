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

package com.vaadin.addon.leaflet4vaadin.controls;

/**
 * A simple scale control that shows the scale of the current center of screen
 * in metric (m/km) and imperial (mi/ft) systems. Extends Control.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-07
 * @version 1.0
 */
public class ScaleControl extends LeafletControl {

    private static final long serialVersionUID = -3415081890756100166L;
    private int maxWidth = 100;
    private boolean metric = true;
    private boolean imperial = true;
    private boolean updateWhenIdle = false;

    public ScaleControl() {
        super("scale");
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    /**
     * Maximum width of the control in pixels. The width is set dynamically to show
     * round values (e.g. 100, 200, 500).
     * 
     * @param maxWidth the maximum width of the control in pixels
     */
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public boolean isMetric() {
        return metric;
    }

    /**
     * Whether to show the metric scale line (m/km).
     * 
     * @param metric whether to show the metric scale line
     */
    public void setMetric(boolean metric) {
        this.metric = metric;
    }

    public boolean isImperial() {
        return imperial;
    }

    /**
     * Whether to show the imperial scale line (mi/ft).
     * 
     * @param imperial whether to show the imperial scale line
     */
    public void setImperial(boolean imperial) {
        this.imperial = imperial;
    }

    public boolean isUpdateWhenIdle() {
        return updateWhenIdle;
    }

    /**
     * If true, the control is updated on moveend, otherwise it's always up-to-date
     * (updated on move).
     * 
     * @param updateWhenIdle If true, the control is updated on moveend
     */
    public void setUpdateWhenIdle(boolean updateWhenIdle) {
        this.updateWhenIdle = updateWhenIdle;
    }

}
