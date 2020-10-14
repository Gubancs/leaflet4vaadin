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

package com.vaadin.addon.leaflet4vaadin.layer.ui;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.Point;

import java.util.concurrent.CompletableFuture;

/**
 * Base model for L.Popup and L.Tooltip. Inherit from it for custom popup like plugins.
 *
 * @author <strong>Gabor Kokeny</strong> Email:
 * <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @version 1.0
 * @see com.vaadin.addon.leaflet4vaadin.layer.ui.popup.Popup
 * @see com.vaadin.addon.leaflet4vaadin.layer.ui.tooltip.Tooltip
 * @since 2020-10-01
 */
public abstract class DivOverlay extends Layer {

    private String content;
    private String className = "";
    private Point offset = Point.of(0, 7);

    protected DivOverlay(String content) {
        this.content = content;
    }

    /**
     * A custom CSS class name to assign to the overlay.
     *
     * @return the CSS class name to assign to the overlay.
     */
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * The offset of the overlay position. Useful to control the anchor
     * of the overlay when opening it on some overlays.
     *
     * @return the offset of the overlay position
     */
    public Point getOffset() {
        return offset;
    }

    public void setOffset(Point offset) {
        this.offset = offset;
    }


    /**
     * Returns the geographical point of popup.
     *
     * @return the geographical point of popup.
     */
    public CompletableFuture<LatLng> getLatLng() {
        return call("getLatLng", LatLng.class);
    }

    /**
     * Sets the geographical point where the overlay will open.
     *
     * @param latLng the geographical point where the overlay will open
     */
    public void setLatLng(LatLng latLng) {
        executeJs("setLatLng", latLng);
    }


    /**
     * Returns the content of the overlay.
     *
     * @return the content of the overlay.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Sets the HTML content of the overlay. If a function is passed the source layer will be passed to the function.
     *
     * @param content the content of the overlay
     */
    public void setContent(String content) {
        this.content = content;
        executeJs("setContent", content);
    }
}
