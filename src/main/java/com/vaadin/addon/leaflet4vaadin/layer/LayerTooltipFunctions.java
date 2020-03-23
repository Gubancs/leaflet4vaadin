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

package com.vaadin.addon.leaflet4vaadin.layer;

import java.util.concurrent.CompletableFuture;

import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.tooltip.Tooltip;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

/**
 * A set of tooltip methods available on each layer
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-23
 * @version 1.0
 */
public interface LayerTooltipFunctions extends ExecutableFunctions {

    /**
     * Binds a tooltip to the layer with the passed content
     * 
     * @param tooltipContent the new tooltip content
     */
    default void bindTooltip(String tooltipContent) {
        execute("bindTooltip", tooltipContent);
    }

    /**
     * Binds a tooltip to the layer with the passed content
     * 
     * @param tooltip the new tooltip content
     */
    default void bindTooltip(Tooltip tooltip) {
        execute("bindTooltip", tooltip);
    }

    /**
     * Removes the tooltip previously bound with bindTooltip.
     */
    default void unbindTooltip() {
        execute("unbindTooltip");
    }

    /**
     * Closes the tooltip bound to this layer if it is open.
     */
    default void closeTooltip() {
        execute("closeTooltip");
    }

    /**
     * Opens the bound tooltip
     * 
     * @param latLng the tooltip location
     */
    default void openTooltip() {
        execute("closeTooltip");
    }

    /**
     * Opens the bound tooltip at the specified latlng or at the default tooltip
     * anchor if no latlng is passed.
     * 
     * @param latLng the tooltip location
     */
    default void openTooltip(LatLng latLng) {
        execute("closeTooltip", latLng);
    }

    /**
     * Opens or closes the tooltip bound to this layer depending on its current
     * state.
     */
    default void toggleTooltip() {
        execute("toggleTooltip");
    }

    /**
     * Sets the content of the tooltip bound to this layer.
     * 
     * @param tooltipContent the new tooltip content
     */
    default void setTooltipContent(String tooltipContent) {
        execute("setTooltipContent", tooltipContent);
    }

    /**
     * Sets the content of the tooltip bound to this layer.
     * 
     * @param tooltip the new tooltip content
     */
    default void setTooltipContent(Tooltip tooltip) {
        execute("setTooltipContent", tooltip);
    }

    /**
     * Returns true if the tooltip bound to this layer is currently open.
     * 
     * @return returns true if the is currently open
     */
    default CompletableFuture<Boolean> isTooltipOpen() {
        return call("isTooltipOpen", Boolean.class);
    }

    /**
     * Returns the tooltip bound to this layer.
     * 
     * @return the tooltip bound to this layer.
     */
    Tooltip getTooltip();

}
