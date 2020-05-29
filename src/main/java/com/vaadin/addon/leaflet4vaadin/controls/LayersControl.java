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

package com.vaadin.addon.leaflet4vaadin.controls;

import java.util.Collections;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.EventTypeRegistry;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;

/**
 * The layers control gives users the ability to switch between different base
 * layers and switch overlays on/off (check out the detailed example). Extends
 * Control. </br>
 * </br>
 * The baseLayers and overlays parameters are object literals with layer names
 * as keys and Layer objects as values. </br>
 * </br>
 * The layer names can contain HTML, which allows you to add additional styling
 * to the items.
 * 
 * 
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-05-29
 * @version 1.0
 */
public class LayersControl extends LeafletControl {

    private static final long serialVersionUID = -7779809624116362068L;

    @LeafletArgument
    private final List<Layer> baseLayers;

    @LeafletArgument
    private final List<Layer> overlays;

    private final LayersControlOptions options;

    public static enum LayerControlEventType implements LeafletEventType {
        baselayerchange, overlayadd, overlayremove, layeradd, layerremove;

        static {
            EventTypeRegistry.register(LayerControlEventType.class);
        }
    }

    
    public LayersControl() {
        this(new LayersControlOptions());
    }

    public LayersControl(LayersControlOptions options) {
        this(Collections.emptyList(), Collections.emptyList(), options);
    }

    public LayersControl(List<Layer> baseLayers, List<Layer> overlays) {
        this(baseLayers, overlays, new LayersControlOptions());
    }

    public LayersControl(List<Layer> baseLayers, List<Layer> overlays, LayersControlOptions options) {
        super("layers");
        this.baseLayers = baseLayers;
        this.overlays = overlays;
        this.options = options;
    }

    /**
     * Fired when the base layer is changed through the layer control.
     * 
     * 
     * @param listener
     *            the listener to call when the event occurs, not {@code null}
     */
    public void onBaseLayerChange(LeafletEventListener<LayersControlEvent> listener) {
        leafletMap.on(LayerControlEventType.baselayerchange, listener);
    }

    /**
     * Fired when an overlay is selected through the layer control.
     * 
     * @param listener
     *            the listener to call when the event occurs, not {@code null}
     */
    public void onOverlayAdd(LeafletEventListener<LayersControlEvent> listener) {
        leafletMap.on(LayerControlEventType.overlayadd, listener);
    }

    /**
     * Fired when an overlay is selected through the layer control.
     * 
     * @param listener
     *            the listener to call when the event occurs, not {@code null}
     */
    public void onOverlayRemove(LeafletEventListener<LayersControlEvent> listener) {
        leafletMap.on(LayerControlEventType.overlayremove, listener);
    }

    /**
     * Fired when a new layer is added to the map.
     * 
     * @param listener
     *            the listener to call when the event occurs, not {@code null}
     */
    public void onLayerAdd(LeafletEventListener<LayersControlEvent> listener) {
        leafletMap.on(LayerControlEventType.layeradd, listener);
    }

    /**
     * Fired when some layer is removed from the map
     * 
     * @param listener
     *            the listener to call when the event occurs, not {@code null}
     */
    public void onLayerRemove(LeafletEventListener<LayersControlEvent> listener) {
        leafletMap.on(LayerControlEventType.layerremove, listener);
    }

    /**
     * Expand the control container if collapsed.
     */
    public void expand() {
        execute(this, "expand");
    }

    /**
     * Collapse the control container if expanded.
     */
    public void collapse() {
        execute(this, "collapse");
    }

    /**
     * Adds a base layer (radio button entry) with the given name to the
     * control.
     */
    public void addBaseLayer(Layer layer, String name) {
        execute(this, "addBaseLayer", layer, name);
    }

    /**
     * Adds an overlay (checkbox entry) with the given name to the control.
     */
    public void addOverlay(Layer layer, String name) {
        execute(this, "addOverlay", layer, name);
    }

    /**
     * Remove the given layer from the control.
     */
    public void removeLayer(Layer layer) {
        execute(this, "removeLayer", layer);
    }

    public boolean isCollapsed() {
        return options.isCollapsed();
    }

    public boolean isAutoZIndex() {
        return options.isAutoZIndex();
    }

    public boolean isHideSingleBase() {
        return options.isHideSingleBase();
    }

    public boolean isSortLayers() {
        return options.isSortLayers();
    }

    public List<Layer> getBaseLayers() {
        return baseLayers;
    }

    public List<Layer> getOverlays() {
        return overlays;
    }
}
