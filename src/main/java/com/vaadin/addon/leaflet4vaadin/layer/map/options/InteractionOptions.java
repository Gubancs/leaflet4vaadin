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

package com.vaadin.addon.leaflet4vaadin.layer.map.options;

import java.io.Serializable;

/**
 * Leaflet map interaction options
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-14
 * @version 1.0
 */
public interface InteractionOptions extends Serializable {

    boolean isClosePopupOnClick();

    /**
     * Set it to false if you don't want popups to close when user clicks the map.
     * 
     * @param closePopupOnClick
     */
    void setClosePopupOnClick(boolean closePopupOnClick);

    double getZoomSnap();

    /**
     * Forces the map's zoom level to always be a multiple of this, particularly
     * right after a fitBounds() or a pinch-zoom. By default, the zoom level snaps
     * to the nearest integer; lower values (e.g. 0.5 or 0.1) allow for greater
     * granularity. A value of 0 means the zoom level will not be snapped after
     * fitBounds or a pinch-zoom.
     * 
     * @param zoomSnap
     */
    void setZoomSnap(double zoomSnap);

    double getZoomDelta();

    /**
     * Controls how much the map's zoom level will change after a zoomIn(),
     * zoomOut(), pressing + or - on the keyboard, or using the zoom controls.
     * Values smaller than 1 (e.g. 0.5) allow for greater granularity.
     * 
     * @param zoomDelta
     */
    void setZoomDelta(double zoomDelta);

    boolean isTrackResize();

    /**
     * Whether the map automatically handles browser window resize to update itself.
     * 
     * @param trackResize
     */
    void setTrackResize(boolean trackResize);

    boolean isBoxZoom();

    /**
     * Whether the map can be zoomed to a rectangular area specified by dragging the
     * mouse while pressing the shift key.
     * 
     * @param trackResize
     */
    void setBoxZoom(boolean boxZoom);

    boolean isDoubleClickZoom();

    /**
     * Whether the map can be zoomed in by double clicking on it and zoomed out by
     * double clicking while holding shift. If passed 'center', double-click zoom
     * will zoom to the center of the view regardless of where the mouse was.
     * 
     * @param doubleClickZoom
     */
    void setDoubleClickZoom(boolean doubleClickZoom);

    boolean isDragging();

    /**
     * Whether the map be draggable with mouse/touch or not.
     * 
     * @param dragging
     */
    void setDragging(boolean dragging);
}
