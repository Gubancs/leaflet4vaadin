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

package com.vaadin.addon.leaflet4vaadin.layer.groups;

import java.util.concurrent.CompletableFuture;

import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * FeatureGroup methods
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-22
 * @version 1.0
 */
public interface FeatureGroupFunctions extends ExecutableFunctions {

    /**
     * Sets the given path options to each layer of the group that has a setStyle
     * method.
     * 
     * @param geoJsonObject
     */
    default void setStyle(PathOptions pathOptions) {
        executeJs("setStyle", pathOptions);
    }

    /**
     * Brings the layer group to the top of all other layers
     */
    default void bringToFront() {
        executeJs("bringToFront");
    }

    /**
     * Brings the layer group to the back of all other layers
     */
    default void bringToBack() {
        executeJs("bringToBack");
    }

    /**
     * Returns the LatLngBounds of the Feature Group (created from bounds and
     * coordinates of its children).
     * 
     * @return the LatLngBounds of the Feature Group
     */
    default CompletableFuture<LatLngBounds> getBounds() {
        return call("getBounds", LatLngBounds.class);
    }
}
