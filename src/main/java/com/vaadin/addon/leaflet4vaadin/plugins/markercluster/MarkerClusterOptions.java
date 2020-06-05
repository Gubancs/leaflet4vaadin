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

package com.vaadin.addon.leaflet4vaadin.plugins.markercluster;

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;

/**
 * Possible {@link MarkerClusterGroup} options
 * 
 * <ul>
 * <li><b>minOpacity</b> -</li>
 * </ul>
 * *
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-05-25
 * @version 1.0
 * @see MarkerClusterGroup
 */
public class MarkerClusterOptions implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6006827958359411349L;

    private int maxClusterRadius = 80;
    private String clusterPane = "markerPane";

    private boolean spiderfyOnMaxZoom = true;
    private boolean showCoverageOnHover = true;
    private boolean zoomToBoundsOnClick = true;
    private boolean singleMarkerMode = false;

    private Integer disableClusteringAtZoom = null;
    private boolean removeOutsideVisibleBounds = true;
    private boolean animate = true;
    private boolean animateAddingMarkers = false;
    private double spiderfyDistanceMultiplier = 1;
    private PathOptions spiderLegPolylineOptions;

    private boolean chunkedLoading = false;
    private int chunkInterval = 200;
    private int chunkDelay = 50;
    private PathOptions polygonOptions;

    public int getMaxClusterRadius() {
        return maxClusterRadius;
    }

    public void setMaxClusterRadius(int maxClusterRadius) {
        this.maxClusterRadius = maxClusterRadius;
    }

    public String getClusterPane() {
        return clusterPane;
    }

    public void setClusterPane(String clusterPane) {
        this.clusterPane = clusterPane;
    }

    public boolean isSpiderfyOnMaxZoom() {
        return spiderfyOnMaxZoom;
    }

    public void setSpiderfyOnMaxZoom(boolean spiderfyOnMaxZoom) {
        this.spiderfyOnMaxZoom = spiderfyOnMaxZoom;
    }

    public boolean isShowCoverageOnHover() {
        return showCoverageOnHover;
    }

    public void setShowCoverageOnHover(boolean showCoverageOnHover) {
        this.showCoverageOnHover = showCoverageOnHover;
    }

    public boolean isZoomToBoundsOnClick() {
        return zoomToBoundsOnClick;
    }

    public void setZoomToBoundsOnClick(boolean zoomToBoundsOnClick) {
        this.zoomToBoundsOnClick = zoomToBoundsOnClick;
    }

    public boolean isSingleMarkerMode() {
        return singleMarkerMode;
    }

    public void setSingleMarkerMode(boolean singleMarkerMode) {
        this.singleMarkerMode = singleMarkerMode;
    }

    public Integer getDisableClusteringAtZoom() {
        return disableClusteringAtZoom;
    }

    public void setDisableClusteringAtZoom(Integer disableClusteringAtZoom) {
        this.disableClusteringAtZoom = disableClusteringAtZoom;
    }

    public boolean isRemoveOutsideVisibleBounds() {
        return removeOutsideVisibleBounds;
    }

    public void setRemoveOutsideVisibleBounds(boolean removeOutsideVisibleBounds) {
        this.removeOutsideVisibleBounds = removeOutsideVisibleBounds;
    }

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public boolean isAnimateAddingMarkers() {
        return animateAddingMarkers;
    }

    public void setAnimateAddingMarkers(boolean animateAddingMarkers) {
        this.animateAddingMarkers = animateAddingMarkers;
    }

    public double getSpiderfyDistanceMultiplier() {
        return spiderfyDistanceMultiplier;
    }

    public void setSpiderfyDistanceMultiplier(double spiderfyDistanceMultiplier) {
        this.spiderfyDistanceMultiplier = spiderfyDistanceMultiplier;
    }

    public PathOptions getSpiderLegPolylineOptions() {
        return spiderLegPolylineOptions;
    }

    public void setSpiderLegPolylineOptions(PathOptions spiderLegPolylineOptions) {
        this.spiderLegPolylineOptions = spiderLegPolylineOptions;
    }

    public boolean isChunkedLoading() {
        return chunkedLoading;
    }

    public void setChunkedLoading(boolean chunkedLoading) {
        this.chunkedLoading = chunkedLoading;
    }

    public int getChunkInterval() {
        return chunkInterval;
    }

    public void setChunkInterval(int chunkInterval) {
        this.chunkInterval = chunkInterval;
    }

    public int getChunkDelay() {
        return chunkDelay;
    }

    public void setChunkDelay(int chunkDelay) {
        this.chunkDelay = chunkDelay;
    }

    public PathOptions getPolygonOptions() {
        return polygonOptions;
    }

    public void setPolygonOptions(PathOptions polygonOptions) {
        this.polygonOptions = polygonOptions;
    }

}
