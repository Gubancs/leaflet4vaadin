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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.LeafletObject;
import com.vaadin.addon.leaflet4vaadin.layer.events.Evented;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.PopupEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.TooltipEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.PopupEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.TooltipEventType;
import com.vaadin.addon.leaflet4vaadin.layer.groups.LayerGroup;
import com.vaadin.addon.leaflet4vaadin.layer.ui.popup.Popup;
import com.vaadin.addon.leaflet4vaadin.layer.ui.tooltip.Tooltip;

/**
 * A set of methods from the Layer base class that all Leaflet layers use.
 * Inherits all methods, options and events from L.Evented.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-02-06
 * @version 1.0
 */
public abstract class Layer extends LeafletObject implements Evented, LayerPopupFunctions, LayerTooltipFunctions {

    private static final long serialVersionUID = -1803411573095089760L;

    public static enum LayerEventType implements LeafletEventType {
        add, remove;
    }

    private transient final Map<LeafletEventType, Set<LeafletEventListener>> eventListeners = new HashMap<>();

    public static final String DEFAULT_PANE = "overlayPane";
    private String pane = DEFAULT_PANE;
    private String attribution;
    private Popup popup;
    private Tooltip tooltip;
    private List<String> events = new ArrayList<>();

    protected Layer() {
    }

    @Override
    public void unbindTooltip() {
        LayerTooltipFunctions.super.unbindTooltip();
        this.tooltip = null;
    }

    @Override
    public void unbindPopup() {
        LayerPopupFunctions.super.unbindPopup();
        this.popup = null;
    }

    /**
     * Fired after the layer is added to a map
     * 
     * @param listener
     *            the event listener
     */
    public void onAdd(LeafletEventListener<LeafletEvent> listener) {
        on(LayerEventType.add, listener);
    }

    /**
     * Fired after the layer is removed from a map
     * 
     * @param listener
     *            the event listener
     */
    public void onRemove(LeafletEventListener<LeafletEvent> listener) {
        on(LayerEventType.remove, listener);
    }

    /**
     * Fired when a tooltip bound to this layer is opened.
     * 
     * @param listener
     *            the event listener
     */
    public void onTooltipOpen(LeafletEventListener<TooltipEvent> listener) {
        on(TooltipEventType.tooltipopen, listener);
    }

    /**
     * Fired when a tooltip bound to this layer is closed.
     * 
     * @param listener
     *            the event listener
     */
    public void onTooltipClose(LeafletEventListener<TooltipEvent> listener) {
        on(TooltipEventType.tooltipclose, listener);
    }

    /**
     * Fired when a popup bound to this layer is opened
     * 
     * @param listener
     *            the event listener
     */
    public void onPopupOpen(LeafletEventListener<PopupEvent> listener) {
        on(PopupEventType.popupopen, listener);
    }

    /**
     * Fired when a popup bound to this layer is closed
     * 
     * @param listener
     *            the event listener
     */
    public void onPopupClose(LeafletEventListener<PopupEvent> listener) {
        on(PopupEventType.popupclose, listener);
    }

    public <T extends LeafletEvent> void fireEvent(T leafletEvent) {
        Optional<LeafletEventType> event = eventListeners.keySet().stream().filter(type -> type.equals(leafletEvent.getType())).findFirst();

        if (event.isPresent()) {
            eventListeners.get(event.get()).forEach(listener -> listener.handleEvent(leafletEvent));
        }
    }

    /**
     * Adds the layer to the given layer group
     * 
     * @param layerGroup
     *            the layer group
     */
    public void addTo(LayerGroup layerGroup) {
        setParent(layerGroup);
        layerGroup.addLayer(this);
    }

    /**
     * Adds the layer to the given map
     * 
     * @param leafletMap
     *            the leaflet map
     */
    public void addTo(LeafletMap leafletMap) {
        setParent(leafletMap);
        leafletMap.addLayer(this);
    }

    public List<String> getEvents() {
        return this.events;
    }

    public String getAttribution() {
        return this.attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getPane() {
        return this.pane;
    }

    public void setPane(String pane) {
        this.pane = pane;
    }

    @Override
    public Popup getPopup() {
        return this.popup;
    }

    public void bindPopup(String popupContent) {
        bindPopup(new Popup(popupContent));
    }

    public void bindPopup(Popup popup) {
        popup.setParent(this);
        this.popup = popup;
    }

    public Tooltip getTooltip() {
        return this.tooltip;
    }

    public void bindTooltip(String tooltipContent) {
        bindTooltip(new Tooltip(tooltipContent));
    }

    public void bindTooltip(Tooltip tooltip) {
        tooltip.setParent(this);
        this.tooltip = tooltip;
    }

    public <T> void set(Supplier<CompletableFuture<T>> futureResult, Consumer<T> handler) {
        if (futureResult.get() != null) {
            futureResult.get().thenAccept((result) -> handler.accept(result));
        }
    }

    @Override
    public <T extends LeafletEvent> void addEventListener(LeafletEventType eventType, LeafletEventListener<T> listener) {
        if (!events.contains(eventType.getLeafletEvent())) {
            events.add(eventType.getLeafletEvent());
        }
        Set<LeafletEventListener> listeners = eventListeners.get(eventType);
        if (listeners == null) {
            listeners = new HashSet<>();
            eventListeners.putIfAbsent(eventType, listeners);
        }
        listeners.add(listener);
    }

    @Override
    public void clearAllEventListeners() {
        this.eventListeners.clear();
        this.events.clear();
        executeJs(this, "clearAllEventListeners");
    }

    @Override
    public boolean hasEventListeners(LeafletEventType eventType) {
        Set<LeafletEventListener> listeners = this.eventListeners.get(eventType);
        return listeners != null && listeners.size() > 0;
    }

    @Override
    public void removeEventListener(LeafletEventType eventType) {
        this.eventListeners.remove(eventType);
        this.events.remove(eventType.getLeafletEvent());
        executeJs(this, "removeEventListener", eventType.getLeafletEvent());
    }
   
    /**
     * Removes the layer from the map it is currently active on
     */
    public void remove() {
        if (getParent() instanceof LeafletMap) {
            LeafletMap map = (LeafletMap) getParent();
            map.removeLayer(this);
        } else if (getParent() instanceof LayerGroup) {
            LayerGroup parentLayerGroup = (LayerGroup) getParent();
            parentLayerGroup.removeLayer(this);
        }
    }

}
