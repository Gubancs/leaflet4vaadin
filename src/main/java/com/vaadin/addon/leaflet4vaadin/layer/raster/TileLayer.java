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

package com.vaadin.addon.leaflet4vaadin.layer.raster;

import com.vaadin.addon.leaflet4vaadin.layer.groups.GridLayer;

/**
 * Used to load and display tile layers on the map. Note that most tile servers
 * require attribution, which you can set under Layer. Extends GridLayer.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-06
 * @version 1.0
 */
public class TileLayer extends GridLayer {

    /**
     *
     */
    private static final long serialVersionUID = 7333804905034746145L;
    private String urlTemplate;
    private String[] subdomains = { "a", "b", "c" };
    private String errorTileUrl;
    private int zoomOffset = 0;
    private boolean tms = false;
    private boolean zoomReverse = false;
    private boolean detectRetina = false;
    private String crossOrigin;

    /**
     * Instantiates a tile layer object given a URL template and optionally an
     * options object.
     * 
     * @param urlTemplate the URL template of this tile layer
     */
    public TileLayer(String urlTemplate) {
        super();
        this.urlTemplate = urlTemplate;
    }

    /**
     * @return the subdomains
     */
    public String[] getSubdomains() {
        return subdomains;
    }

    /**
     * Subdomains of the tile service. Can be passed in the form of one string
     * (where each letter is a subdomain name) or an array of strings.
     * 
     * @param subdomains the subdomains to set
     */
    public void setSubdomains(String... subdomains) {
        this.subdomains = subdomains;
    }

    /**
     * @return the errorTileUrl
     */
    public String getErrorTileUrl() {
        return errorTileUrl;
    }

    /**
     * URL to the tile image to show in place of the tile that failed to load.
     * 
     * @param errorTileUrl the errorTileUrl to set
     */
    public void setErrorTileUrl(String errorTileUrl) {
        this.errorTileUrl = errorTileUrl;
    }

    /**
     * @return the zoomOffset
     */
    public int getZoomOffset() {
        return zoomOffset;
    }

    /**
     * The zoom number used in tile URLs will be offset with this value.
     * 
     * @param zoomOffset the zoomOffset to set
     */
    public void setZoomOffset(int zoomOffset) {
        this.zoomOffset = zoomOffset;
    }

    /**
     * @return the tms
     */
    public boolean isTms() {
        return tms;
    }

    /**
     * If true, inverses Y axis numbering for tiles (turn this on for TMS services).
     * 
     * @param tms the tms to set
     */
    public void setTms(boolean tms) {
        this.tms = tms;
    }

    /**
     * @return the zoomReverse
     */
    public boolean isZoomReverse() {
        return zoomReverse;
    }

    /**
     * If set to true, the zoom number used in tile URLs will be reversed (maxZoom -
     * zoom instead of zoom)
     * 
     * @param zoomReverse the zoomReverse to set
     */
    public void setZoomReverse(boolean zoomReverse) {
        this.zoomReverse = zoomReverse;
    }

    /**
     * @return the detectRetina
     */
    public boolean isDetectRetina() {
        return detectRetina;
    }

    /**
     * If true and user is on a retina display, it will request four tiles of half
     * the specified size and a bigger zoom level in place of one to utilize the
     * high resolution.
     * 
     * @param detectRetina the detectRetina to set
     */
    public void setDetectRetina(boolean detectRetina) {
        this.detectRetina = detectRetina;
    }

    /**
     * @return the crossOrigin
     */
    public String getCrossOrigin() {
        return crossOrigin;
    }

    /**
     * Whether the crossOrigin attribute will be added to the tiles. If a String is
     * provided, all tiles will have their crossOrigin attribute set to the String
     * provided. This is needed if you want to access tile pixel data. Refer to CORS
     * Settings for valid String values.
     * 
     * @param crossOrigin the crossOrigin to set
     */
    public void setCrossOrigin(String crossOrigin) {
        this.crossOrigin = crossOrigin;
    }

    /**
     * @return the urlTemplate
     */
    public String getUrlTemplate() {
        return urlTemplate;
    }
}
