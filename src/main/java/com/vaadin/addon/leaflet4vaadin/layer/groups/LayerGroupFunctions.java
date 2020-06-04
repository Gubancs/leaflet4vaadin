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

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;

/**
 * LayerGroup methods
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-23
 * @version 1.0
 */
public interface LayerGroupFunctions extends ExecutableFunctions {

    /**
     * Adds the given layer to the group.
     * 
     * @param layer the layer to be added to this layer group
     */
    default void addLayer(Layer layer) {
        executeJs("addLayer", layer);
    }

    /**
     * Removes the given layer from the group.
     * 
     * @param layer the layer to be removed from this layer group
     */
    default void removeLayer(Layer layer) {
        executeJs("removeLayer", layer);
    }

    /**
     * Removes the layer with the given internal ID from the group.
     * 
     * @param layerId the internal ID of the layer should remove
     */
    default void removeLayer(String layerId) {
        executeJs("removeLayer", layerId);
    }

    /**
     * Returns true if the given layer is currently added to the group.
     * 
     * @param layer layer to be check
     * @return true if the given layer is currently added to the group.
     */
    boolean hasLayer(Layer layer);

    /**
     * Returns true if the given internal ID is currently added to the group.
     * 
     * @param layerId layer to be check
     * @return true if the given internal ID is currently added to the group.
     */
    boolean hasLayer(String layerId);

    /**
     * Removes all the layers from the group.
     */
    default void clearLayers() {
        executeJs("clearLayers");
    }
}
