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

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.layer.events.Evented;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.PopupEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.TooltipEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.PopupEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.TooltipEventType;
import com.vaadin.addon.leaflet4vaadin.layer.groups.LayerGroup;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.popup.Popup;
import com.vaadin.addon.leaflet4vaadin.layer.ui.tooltip.Tooltip;
import com.vaadin.addon.leaflet4vaadin.types.LeafletClass;

/**
 * A set of methods from the Layer base class that all Leaflet layers use.
 * Inherits all methods, options and events from L.Evented.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-02-06
 * @version 1.0
 */
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(value = { "json" })
public abstract class Layer
		implements Evented, LeafletClass, LayerFunctions, LayerPopupFunctions, LayerTooltipFunctions {

	private static final long serialVersionUID = -1803411573095089760L;

	public static enum LayerEventType implements LeafletEventType {
		add, remove;
	}

	private transient final Map<LeafletEventType, Set<LeafletEventListener>> eventListeners = new HashMap<>();

	private transient final ObjectMapper objectMapper = new ObjectMapper();

	public static final String DEFAULT_PANE = "overlayPane";
	private String uuid;
	private String json;
	private String pane = DEFAULT_PANE;
	private String attribution;
	private Popup popup;
	private Tooltip tooltip;
	private List<String> events = new ArrayList<>();
	private ExecutableFunctions functionDelegate;

	protected Layer() {
		this.uuid = UUID.randomUUID().toString();
		configureObjectMapper(objectMapper);
	}

	protected void configureObjectMapper(final ObjectMapper objectMapper) {
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
	 * @param listener the event listener
	 */
	public void onAdd(LeafletEventListener<LeafletEvent> listener) {
		on(LayerEventType.add, listener);
	}

	/**
	 * Fired after the layer is removed from a map
	 * 
	 * @param listener the event listener
	 */
	public void onRemove(LeafletEventListener<LeafletEvent> listener) {
		on(LayerEventType.remove, listener);
	}

	/**
	 * Fired when a tooltip bound to this layer is opened.
	 * 
	 * @param listener the event listener
	 */
	public void onTooltipOpen(LeafletEventListener<TooltipEvent> listener) {
		on(TooltipEventType.tooltipopen, listener);
	}

	/**
	 * Fired when a tooltip bound to this layer is closed.
	 * 
	 * @param listener the event listener
	 */
	public void onTooltipClose(LeafletEventListener<TooltipEvent> listener) {
		on(TooltipEventType.tooltipclose, listener);
	}

	/**
	 * Fired when a popup bound to this layer is opened
	 * 
	 * @param listener the event listener
	 */
	public void onPopupOpen(LeafletEventListener<PopupEvent> listener) {
		on(PopupEventType.popupopen, listener);
	}

	/**
	 * Fired when a popup bound to this layer is closed
	 * 
	 * @param listener the event listener
	 */
	public void onPopupClose(LeafletEventListener<PopupEvent> listener) {
		on(PopupEventType.popupclose, listener);
	}

	public <T extends LeafletEvent> void fireEvent(T leafletEvent) {
		Optional<LeafletEventType> event = eventListeners.keySet().stream()
				.filter(type -> type.equals(leafletEvent.getType())).findFirst();

		if (event.isPresent()) {
			eventListeners.get(event.get()).forEach(listener -> listener.handleEvent(leafletEvent));
		}
	}

	/**
	 * Adds the layer to the given layer group
	 * 
	 * @param layerGroup the layer group
	 */
	public void addTo(LayerGroup layerGroup) {
		this.functionDelegate = layerGroup;
		layerGroup.addLayer(this);
	}

	/**
	 * Adds the layer to the given map
	 * 
	 * @param leafletMap the leaflet map
	 */
	public void addTo(LeafletMap leafletMap) {
		this.functionDelegate = leafletMap;
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

	public String getUuid() {
		return uuid;
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

	public void bindPopup(Popup popupOptions) {
		this.popup = popupOptions;
	}

	public Tooltip getTooltip() {
		return this.tooltip;
	}

	public void bindTooltip(String tooltipContent) {
		bindTooltip(new Tooltip(tooltipContent));
	}

	public void bindTooltip(Tooltip tooltipOptions) {
		this.tooltip = tooltipOptions;
	}

	public String getJson() {
		if (this.json == null) {
			try {
				this.json = objectMapper.writeValueAsString(this);
				return this.json;
			} catch (IOException e) {
				throw new RuntimeException("Unable to convert Layer into JSON type", e);
			}
		}
		return this.json;
	}

	/**
	 * @param json the json to set
	 */
	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public void execute(Identifiable target, String functionName, Serializable... arguments) {
		if (functionDelegate instanceof ExecutableFunctions) {
			functionDelegate.execute(target, functionName, arguments);
		}
	}

	@Override
	public <T extends Serializable> CompletableFuture<T> call(Identifiable target, String functionName,
			Class<T> resultType, Serializable... arguments) {
		if (functionDelegate instanceof ExecutableFunctions) {
			return functionDelegate.call(target, functionName, resultType, arguments);
		} else {
			return null;
		}
	}

	public <T> void set(Supplier<CompletableFuture<T>> futureResult, Consumer<T> handler) {
		if (futureResult.get() != null) {
			futureResult.get().thenAccept((result) -> handler.accept(result));
		}
	}

	@Override
	public <T extends LeafletEvent> void addEventListener(LeafletEventType eventType,
			LeafletEventListener<T> listener) {
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
		execute(this, "clearAllEventListeners");
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
		execute(this, "removeEventListener", eventType.getLeafletEvent());
	}

	public List<String> getConstructorArgumentNames() {
		return findLeafletArguments(getClass()).stream().map(Field::getName).distinct().collect(Collectors.toList());
	}

	static List<Field> findLeafletArguments(Class<?> layer) {
		List<Field> fields = Arrays.asList(layer.getDeclaredFields()).stream()
				.filter(f -> f.isAnnotationPresent(LeafletArgument.class)).collect(Collectors.toList());
		if (layer.getSuperclass() != null) {
			fields.addAll(findLeafletArguments(layer.getSuperclass()));
		}
		return fields;
	}
}
