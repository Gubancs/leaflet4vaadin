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
 * Some of the geolocation methods for Map take in an options parameter.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-11
 * @version 1.0
 */
public class LocateOptions implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2147827616216491426L;
    private boolean watch = false;
    private boolean setView = false;
    private int maxZoom = Integer.MAX_VALUE;
    private int timeout = 10000;
    private int maximumAge = 0;
    private boolean enableHighAccuracy = false;

    /**
     * @return the watch
     */
    public boolean isWatch() {
        return watch;
    }

    /**
     * If true, starts continuous watching of location changes (instead of detecting
     * it once) using W3C watchPosition method. You can later stop watching using
     * map.stopLocate() method.
     * 
     * @param watch the watch to set
     */
    public void setWatch(boolean watch) {
        this.watch = watch;
    }

    /**
     * @return the setView
     */
    public boolean isSetView() {
        return setView;
    }

    /**
     * If true, automatically sets the map view to the user location with respect to
     * detection accuracy, or to world view if geolocation failed.
     * 
     * @param setView the setView to set
     */
    public void setSetView(boolean setView) {
        this.setView = setView;
    }

    /**
     * @return the maxZoom
     */
    public int getMaxZoom() {
        return maxZoom;
    }

    /**
     * The maximum zoom for automatic view setting when using setView option.
     * 
     * @param maxZoom the maxZoom to set
     */
    public void setMaxZoom(int maxZoom) {
        this.maxZoom = maxZoom;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Number of milliseconds to wait for a response from geolocation before firing
     * a locationerror event.
     * 
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * @return the maximumAge
     */
    public int getMaximumAge() {
        return maximumAge;
    }

    /**
     * Maximum age of detected location. If less than this amount of milliseconds
     * passed since last geolocation response, locate will return a cached location.
     * 
     * @param maximumAge the maximumAge to set
     */
    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    /**
     * @return the enableHighAccuracy
     */
    public boolean isEnableHighAccuracy() {
        return enableHighAccuracy;
    }

    /**
     * Enables high accuracy, see description in the W3C spec.
     * 
     * @param enableHighAccuracy the enableHighAccuracy to set
     */
    public void setEnableHighAccuracy(boolean enableHighAccuracy) {
        this.enableHighAccuracy = enableHighAccuracy;
    }
}
