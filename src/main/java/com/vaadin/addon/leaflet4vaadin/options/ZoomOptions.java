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
 * Some of the Map methods which modify the zoom level take in an options
 * parameter.x
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-11
 * @version 1.0
 */
public class ZoomOptions implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7822771192377907792L;

    private boolean animate = true;

    /**
     * @return the animate
     */
    public boolean isAnimate() {
        return animate;
    }

    /**
     * If not specified, zoom animation will happen if the zoom origin is inside the
     * current view. If true, the map will attempt animating zoom disregarding where
     * zoom origin is. Setting false will make it always reset the view completely
     * without animation.
     * 
     * @param animate the animate to set
     */
    public void setAnimate(boolean animate) {
        this.animate = animate;
    }
}
