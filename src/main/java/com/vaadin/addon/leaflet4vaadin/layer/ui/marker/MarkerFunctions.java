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

package com.vaadin.addon.leaflet4vaadin.layer.ui.marker;

import java.util.concurrent.CompletableFuture;

import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;
import com.vaadin.addon.leaflet4vaadin.types.Icon;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

/**
 * In addition to shared layer methods like addTo() and remove() and popup
 * methods like bindPopup() you can also use the following methods:
 */
public interface MarkerFunctions extends ExecutableFunctions {

    /**
     * Returns the current geographical position of the marker.
     * 
     * @return the current geographical position of the marker
     */
    default CompletableFuture<LatLng> callLatLng() {
        return call("getLatLng", LatLng.class);
    }

    /**
     * Changes the marker position to the given point.
     * 
     * @param latLng the new position of the marker
     */
    default void setLatLng(LatLng latLng) {
        executeJs("setLatLng", latLng);
    }

    /**
     * Changes the zIndex offset of the marker.
     * 
     * @param offset the zIndex offset value
     */
    default void setZIndexOffset(int offset) {
        executeJs("setZIndexOffset", offset);
    }

    /**
     * Returns the current icon used by the marker
     * 
     * @param icon the current icon used by the marker
     */
    Icon getIcon();

    /**
     * Changes the marker icon.
     * 
     * @param icon the new icon options
     */
    default void setIcon(Icon icon) {
        executeJs("setIcon", icon);
    }

    /**
     * Changes the opacity of the marker.
     * 
     * 
     * @param icon the new opacity of the marker
     */
    default void setOpacity(double opacity) {
        executeJs("setOpacity", opacity);
    }
}