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
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.TileEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * Generic class for handling a tiled grid of HTML elements. This is the base
 * class for all tile layers.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-06
 * @version 1.0
 */
public class GridLayer extends Layer {

    private static final long serialVersionUID = -228368014425536804L;

    public static enum Events implements LeafletEventType {
        load, tileunload, tileloadstart, tileerror, tileload, loading;
    }

    private int tileSize = 256;
    private double opacity = 1.0;
    private boolean updateWhenIdle = true;
    private boolean updateWhenZooming = true;
    private int updateInterval = 200;
    private int zIndex = 1;
    private LatLngBounds bounds;
    private Integer minZoom = 0;
    private Integer maxZoom = 18;
    // private Integer maxNativeZoom;
    // private Integer minNativeZoom;
    private boolean noWrap = false;
    private String className;
    private int keepBuffer = 2;

    public GridLayer() {
        super();
        setPane("tilePane");
    }

    /**
     * Fired when the grid layer starts loading tiles.
     * 
     * @param listener the event listener
     */
    public void onLoading(LeafletEventListener<LeafletEvent> listener) {
        on(Events.loading, listener);
    }

    /**
     * Fired when a tile is removed (e.g. when a tile goes off the screen).
     * 
     * @param listener the event listener
     */
    public void onTileUnload(LeafletEventListener<TileEvent> listener) {
        on(Events.tileunload, listener);
    }

    /**
     * Fired when a tile is requested and starts loading.
     * 
     * @param listener the event listener
     */
    public void onTileLoadStart(LeafletEventListener<TileEvent> listener) {
        on(Events.tileloadstart, listener);
    }

    /**
     * Fired when there is an error loading a tile.
     * 
     * @param listener the event listener
     */
    public void onTileError(LeafletEventListener<TileEvent> listener) {
        on(Events.tileerror, listener);
    }

    /**
     * Fired when a tile loads.
     * 
     * @param listener the event listener
     */
    public void onTileLoad(LeafletEventListener<TileEvent> listener) {
        on(Events.tileload, listener);
    }

    /**
     * Fired when the grid layer loaded all visible tiles.
     * 
     * @param listener the event listener
     */
    public void onLoad(LeafletEventListener<LeafletEvent> listener) {
        on(Events.load, listener);
    }

    /**
     * @return the tileSize
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Width and height of tiles in the grid.
     * 
     * @param tileSize the tileSize to set
     */
    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * @return the opacity
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * Opacity of the tiles.
     * 
     * @param opacity the opacity to set
     */
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    /**
     * @return the updateWhenIdle
     */
    public boolean isUpdateWhenIdle() {
        return updateWhenIdle;
    }

    /**
     * Load new tiles only when panning ends. true by default on mobile browsers, in
     * order to avoid too many requests and keep smooth navigation. false otherwise
     * in order to display new tiles during panning, since it is easy to pan outside
     * the keepBuffer option in desktop browsers.
     *
     * @param updateWhenIdle the updateWhenIdle to set
     */
    public void setUpdateWhenIdle(boolean updateWhenIdle) {
        this.updateWhenIdle = updateWhenIdle;
    }

    /**
     * @return the updateWhenZooming
     */
    public boolean isUpdateWhenZooming() {
        return updateWhenZooming;
    }

    /**
     * By default, a smooth zoom animation (during a touch zoom or a flyTo()) will
     * update grid layers every integer zoom level. Setting this option to false
     * will update the grid layer only when the smooth animation ends.
     *
     * @param updateWhenZooming the updateWhenZooming to set
     */
    public void setUpdateWhenZooming(boolean updateWhenZooming) {
        this.updateWhenZooming = updateWhenZooming;
    }

    /**
     * @return the updateInterval
     */
    public int getUpdateInterval() {
        return updateInterval;
    }

    /**
     * Tiles will not update more than once every updateInterval milliseconds when
     * panning.
     *
     * @param updateInterval the updateInterval to set
     */
    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    /**
     * @return the zIndex
     */
    public int getzIndex() {
        return zIndex;
    }

    /**
     * The explicit zIndex of the tile layer.
     *
     * @param zIndex the zIndex to set
     */
    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * @return the bounds
     */
    public LatLngBounds getBounds() {
        return bounds;
    }

    /**
     * If set, tiles will only be loaded inside the set LatLngBounds.
     *
     * @param bounds the bounds to set
     */
    public void setBounds(LatLngBounds bounds) {
        this.bounds = bounds;
    }

    /**
     * @return the minZoom
     */
    public Integer getMinZoom() {
        return minZoom;
    }

    /**
     * The minimum zoom level down to which this layer will be displayed
     * (inclusive).
     *
     * @param minZoom the minZoom to set
     */
    public void setMinZoom(Integer minZoom) {
        this.minZoom = minZoom;
    }

    /**
     * @return the maxZoom
     */
    public Integer getMaxZoom() {
        return maxZoom;
    }

    /**
     * The maximum zoom level up to which this layer will be displayed (inclusive).
     *
     * @param maxZoom the maxZoom to set
     */
    public void setMaxZoom(Integer maxZoom) {
        this.maxZoom = maxZoom;
    }

    // /**
    // * @return the maxNativeZoom
    // */
    // public Integer getMaxNativeZoom() {
    // return maxNativeZoom;
    // }

    // /**
    // * Maximum zoom number the tile source has available. If it is specified, the
    // * tiles on all zoom levels higher than maxNativeZoom will be loaded from
    // * maxNativeZoom level and auto-scaled.
    // *
    // * @param maxNativeZoom the maxNativeZoom to set
    // */
    // public void setMaxNativeZoom(Integer maxNativeZoom) {
    // this.maxNativeZoom = maxNativeZoom;
    // }

    // /**
    // * @return the minNativeZoom
    // */
    // public Integer getMinNativeZoom() {
    // return minNativeZoom;
    // }

    // /**
    // * Minimum zoom number the tile source has available. If it is specified, the
    // * tiles on all zoom levels lower than minNativeZoom will be loaded from
    // * minNativeZoom level and auto-scaled.
    // *
    // * @param minNativeZoom the minNativeZoom to set
    // */
    // public void setMinNativeZoom(Integer minNativeZoom) {
    // this.minNativeZoom = minNativeZoom;
    // }

    /**
     * @return the noWrap
     */
    public boolean isNoWrap() {
        return noWrap;
    }

    /**
     * Whether the layer is wrapped around the antimeridian. If true, the GridLayer
     * will only be displayed once at low zoom levels. Has no effect when the map
     * CRS doesn't wrap around. Can be used in combination with bounds to prevent
     * requesting tiles outside the CRS limits.
     *
     * @param noWrap the noWrap to set
     */
    public void setNoWrap(boolean noWrap) {
        this.noWrap = noWrap;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * A custom class name to assign to the tile layer. Empty by default.
     *
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the keepBuffer
     */
    public int getKeepBuffer() {
        return keepBuffer;
    }

    /**
     * When panning the map, keep this many rows and columns of tiles before
     * unloading them.
     *
     * @param keepBuffer the keepBuffer to set
     */
    public void setKeepBuffer(int keepBuffer) {
        this.keepBuffer = keepBuffer;
    }
}
