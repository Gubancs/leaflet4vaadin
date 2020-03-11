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

package com.vaadin.addon.leaflet4vaadin.options;

import java.io.Serializable;

/**
 * Some of the Map methods which modify the center of the map take in an options
 * parameter.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-11
 * @version 1.0
 */
public class PanOptions implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2219400051885154626L;
    private boolean animate = true;
    private double duration = 0.25;
    private double easeLinearity = 0.25;
    private boolean noMoveStart;

    /**
     * @return the animate
     */
    public boolean isAnimate() {
        return animate;
    }

    /**
     * If true, panning will always be animated if possible. If false, it will not
     * animate panning, either resetting the map view if panning more than a screen
     * away, or just setting a new offset for the map pane (except for panBy which
     * always does the latter).
     * 
     * @param animate the animate to set
     */
    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    /**
     * @return the duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Duration of animated panning, in seconds.
     * 
     * @param duration the duration to set
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * @return the easeLinearity
     */
    public double getEaseLinearity() {
        return easeLinearity;
    }

    /**
     * The curvature factor of panning animation easing (third parameter of the
     * Cubic Bezier curve). 1.0 means linear animation, and the smaller this number,
     * the more bowed the curve.
     * 
     * @param easeLinearity the easeLinearity to set
     */
    public void setEaseLinearity(double easeLinearity) {
        this.easeLinearity = easeLinearity;
    }

    /**
     * @return the noMoveStart
     */
    public boolean isNoMoveStart() {
        return noMoveStart;
    }

    /**
     * If true, panning won't fire movestart event on start (used internally for
     * panning inertia).
     * 
     * @param noMoveStart the noMoveStart to set
     */
    public void setNoMoveStart(boolean noMoveStart) {
        this.noMoveStart = noMoveStart;
    }
}
