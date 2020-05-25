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

package com.vaadin.addon.leaflet4vaadin;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.addon.leaflet4vaadin.controls.LeafletControl;
import com.vaadin.addon.leaflet4vaadin.layer.Identifiable;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.DragEndEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.ErrorEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.KeyboardEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LayerEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.LocationEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.MouseEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.MoveEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.PopupEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.ResizeEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.TileErrorEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.TileEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.TooltipEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.ZoomAnimEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsKeyboardEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsLayerEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsLocationEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsMapEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsMouseEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.EventTypeRegistry;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.KeyboardEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LayerEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LocationEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.MouseEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.PopupEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.TileEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.TooltipEventType;
import com.vaadin.addon.leaflet4vaadin.layer.groups.LayerGroup;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.GeolocationFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.MapConversionFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.MapGetStateFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.MapModifyStateFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.operations.LeafletOperation;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.Point;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.component.page.PendingJavaScriptResult.JavaScriptException;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.internal.JsonSerializer;
import com.vaadin.flow.shared.Registration;

@Tag("leaflet-map")
@NpmPackage(value = "leaflet", version = "1.6.0")
@JsModule("./leaflet-map.js")
@JsModule("leaflet/dist/leaflet-src.js")
@CssImport(value = "leaflet/dist/leaflet.css", id = "leaflet-css")
@CssImport(value = "./styles/leaflet-lumo-theme.css", id = "lumo-leaflet-map")
public final class LeafletMap extends PolymerTemplate<LeafletModel> implements MapModifyStateFunctions,
		MapGetStateFunctions, GeolocationFunctions, MapConversionFunctions, SupportsMouseEvents, SupportsMapEvents,
		SupportsLocationEvents, SupportsKeyboardEvents, SupportsLayerEvents, HasSize, HasTheme {

	private static final long serialVersionUID = 3789693345308589828L;

	private final Logger logger = LoggerFactory.getLogger(LeafletMap.class);

	private static class MapLayer extends LayerGroup {

		/**
		 *
		 */
		private static final long serialVersionUID = -3205153902141978918L;
	}

	private final MapLayer mapLayer = new MapLayer();

	private boolean ready = false;

	public LeafletMap() {
		this(new DefaultMapOptions());
	}

	public LeafletMap(MapOptions mapOptions) {
		setId("template");
		getModel().setMapOptions(mapOptions);
		setSizeFull();
	}

	/**
	 * Generic event handler
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see LeafletEvent
	 */
	@EventHandler
	private void onBaseEventHandler(@EventData("event.target.options.uuid") String layerId,
			@EventData("event.type") String event) {
		Layer layer = findLayer(layerId);
		LeafletEvent leafletEvent = new LeafletEvent(layer, EventTypeRegistry.valueOf(event));
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when any mouse event fired on the layer
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see MoveEvent
	 * @see MouseEventType
	 */
	@EventHandler
	private void onMouseEventEventHandler(@EventData("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.latlng.lat") Double latitude,
			@EventData("event.latlng.lng") Double longitude, @EventData("event.layerPoint.x") int layerPointX,
			@EventData("event.layerPoint.y") int layerPointY, @EventData("event.containerPoint.x") int containerPointX,
			@EventData("event.containerPoint.y") int containerPointY) {

		Layer layer = findLayer(layerId);
		LatLng latLng = new LatLng(latitude, longitude);
		Point layerPoint = Point.of(layerPointX, layerPointY);
		Point containerPoint = Point.of(containerPointX, containerPointY);

		LeafletEvent leafletEvent = new MouseEvent(layer, MouseEventType.valueOf(eventType), latLng, layerPoint,
				containerPoint);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when the layer is moved via setLatLng or by dragging.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see MoveEvent
	 * @see DragEventType
	 */
	@EventHandler
	private void onMoveEventHandler(@EventData("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.latlng.lat") Double newLat,
			@EventData("event.latlng.lng") Double newLng, @EventData("event.oldLatLng.lat") Double oldLat,
			@EventData("event.oldLatLng.lng") Double oldLng) {

		Layer layer = findLayer(layerId);
		LatLng latLng = new LatLng(newLat, newLng);
		LatLng oldLatLng = new LatLng(oldLat, oldLng);

		LeafletEvent leafletEvent = new MoveEvent(layer, DragEventType.valueOf(eventType), oldLatLng, latLng);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when the user presses a key from the keyboard while the map is focused
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see KeyboardEvent
	 * @see KeyboardEventType
	 */
	@EventHandler
	private void onKeyboardEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.originalEvent.key") String key,
			@EventData("event.originalEvent.code") String code, @EventData("event.originalEvent.keyCode") int keyCode,
			@EventData("event.originalEvent.shiftKey") boolean shiftKey,
			@EventData("event.originalEvent.altKey") boolean altKey,
			@EventData("event.originalEvent.ctrlKey") boolean ctrlKey) {
		Layer layer = findLayer(layerId);
		LeafletEvent leafletEvent = new KeyboardEvent(layer, KeyboardEventType.valueOf(eventType), key, code, keyCode,
				shiftKey, altKey, ctrlKey);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when geolocation (using the locate method) went successfully.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see LocationEvent
	 * @see LocationEventType
	 */
	@EventHandler
	private void onLocationEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.latlng.lat") Double latitude,
			@EventData("event.latlng.lng") Double longitude, @EventData("event.accuracy") Double accuracy,
			@EventData("event.altitude") Double altitude, @EventData("event.altitudeAccuracy") Double altitudeAccuracy,
			@EventData("event.heading") Double heading, @EventData("event.speed") Double speed,
			@EventData("event.timestamp") Double timestamp) {
		LatLng latlng = new LatLng(latitude, longitude);
		Layer layer = findLayer(layerId);
		LeafletEvent leafletEvent = new LocationEvent(layer, LocationEventType.valueOf(eventType), latlng, null,
				accuracy, altitude, altitudeAccuracy, heading, speed, timestamp);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when geolocation (using the locate method) failed.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see ErrorEvent
	 */
	@EventHandler
	private void onErrorEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String event, @EventData("event.message") String message,
			@EventData("event.code") int code) {
		Layer layer = findLayer(layerId);
		ErrorEvent errorEvent = new ErrorEvent(layer, EventTypeRegistry.valueOf(event), message, code);
		fireEvent(layer, errorEvent);
	}

	/**
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 */
	@EventHandler
	private void onLayersControlEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("LayersControlEvent fired on client side: {} - {}", eventType, layerId);
		// TODO not implemented yet
	}

	/**
	 * Fired when a new layer is added to the map, or removed from the map.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see LayerEvent
	 * @see LayerEventType
	 */
	@EventHandler
	private void onLayerEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		Layer layer = findLayer(layerId);
		LeafletEvent leafletEvent = new LayerEvent(layer, LayerEventType.valueOf(eventType), null);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when a tile event fired on map
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see TileEvent
	 * @see TileEventType
	 */
	@EventHandler
	private void onTileEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.coords.x") Double coordsX,
			@EventData("event.coords.y") Double coordsY) {
		Layer layer = findLayer(layerId);
		Point coords = Point.of(coordsX, coordsY);
		LeafletEvent leafletEvent = new TileEvent(layer, TileEventType.valueOf(eventType), coords);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when there is an error loading a tile.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see TileErrorEvent
	 * @see TileEventType
	 */
	@EventHandler
	private void onTileErrorEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.coords.x") Double coordsX,
			@EventData("event.coords.y") Double coordsY) {
		Layer layer = findLayer(layerId);
		Point coords = Point.of(coordsX, coordsY);
		LeafletEvent leafletEvent = new TileErrorEvent(layer, TileEventType.valueOf(eventType), coords);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when the map is resized.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see ResizeEvent
	 * @see MapEventType
	 */
	@EventHandler
	private void onResizeEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.oldSize.x") Double oldSizeX,
			@EventData("event.oldSize.y") Double oldSizeY, @EventData("event.newSize.x") Double newSizeX,
			@EventData("event.newSize.y") Double newSizeY) {
		Layer layer = findLayer(layerId);
		LeafletEvent leafletEvent = new ResizeEvent(layer, Point.of(oldSizeX, oldSizeY), Point.of(newSizeX, newSizeY));
		fireEvent(layer, leafletEvent);
	}

	/**
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 */
	@EventHandler
	private void onGeoJSONEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("GeoJSONEvent fired on client side: {} - {}", eventType, layerId);
		// TODO not implemented yet
	}

	/**
	 * Fired when a popup is opened or closed in the map
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see PopupEvent
	 * @see PopupEventType
	 */
	@EventHandler
	private void onPopupEventHandler(@EventData("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		Layer layer = findLayer(layerId);
		LeafletEvent leafletEvent = new PopupEvent(layer, PopupEventType.valueOf(eventType), null);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when a tooltip is opened or closed in the map.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see TooltipEvent
	 * @see TooltipEventType
	 */
	@EventHandler
	private void onTooltipEventHandler(@EventData("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		Layer layer = findLayer(layerId);
		LeafletEvent leafletEvent = new TooltipEvent(layer, TooltipEventType.valueOf(eventType), null);
		fireEvent(layer, leafletEvent);
	}

	/**
	 * Fired when the user stops dragging the layer.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see DragEndEvent
	 * @see DragEventType
	 */
	@EventHandler
	private void onDragEndEventHandler(@EventData("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.distance") double distance) {
		Layer layer = findLayer(layerId);
		LeafletEvent event = new DragEndEvent(layer, DragEventType.valueOf(eventType), distance);
		fireEvent(layer, event);
	}

	/**
	 * Fired at least once per zoom animation. For continuous zoom, like pinch
	 * zooming, fired once per frame during zoom.
	 * 
	 * @param layerId   the id of the layer where the event occurred
	 * @param eventType the type of the occurred event
	 * @see ZoomAnimEvent
	 * @see MapEventType
	 */
	@EventHandler
	private void onZoomAnimEventHandler(@EventData("target.options.uuid") String layerId,
			@EventData("event.zoom") int zoom, @EventData("event.center.lat") Double latitude,
			@EventData("event.center.lng") Double longitude, @EventData("event.type") String eventType) {
		LatLng center = new LatLng(latitude, longitude);
		LeafletEvent event = new ZoomAnimEvent(this.mapLayer, MapEventType.valueOf(eventType), center, zoom, false);
		fireEvent(this.mapLayer, event);
	}

	/**
	 * fire the given leaflet event
	 * 
	 * @param layerId   the layer where the event occurred
	 * @param eventType the event object to be propagate
	 * @see LeafletEvent
	 */
	private void fireEvent(Layer layer, LeafletEvent event) {
		logger.info("Leaflet event fired on client side: {}", event.getType());
		logger.info("Event data: {}", event);
		layer.fireEvent(event);
	}

	/**
	 * Returns the layer with the given internal ID.
	 * 
	 * @param layerId the id of the layer to be looking for
	 * @return the layer with the given internal ID
	 */
	public Layer getLayer(String layerId) {
		return this.mapLayer.getLayer(layerId).orElse(this.mapLayer);
	}

	public Layer findLayer(String layerId) {
		return this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
	}

	/**
	 * Adds the given layer to the map
	 * 
	 * @param layer the layer to add
	 */
	public void addLayer(Layer layer) {
		logger.debug("add layer: {}", layer);
		this.mapLayer.addLayer(layer);
		getModel().getLayers().add(layer);
	}

	/**
	 * Removes the given layer from the map.
	 * 
	 * @param layer the layer to remove
	 */
	public void removeLayer(Layer layer) {
		logger.debug("remove layer: {}", layer.getUuid());
		Optional<Layer> layerToRemove = getModel().getLayers().stream().filter(l -> l.getUuid().equals(layer.getUuid()))
				.findFirst();
		if (layerToRemove.isPresent()) {
			getModel().getLayers().remove(layerToRemove.get());
			this.mapLayer.removeLayer(layerToRemove.get());
		} else {
			logger.warn("Unable to remove layer because layer not found with uuid: " + layer.getUuid());
		}
	}

	public void setBaseUrl(String baseUrl) {
		getModel().setBaseUrl(baseUrl);
	}

	/**
	 * Adds the given control to the map
	 * 
	 * @param leafletControl the control to add
	 */
	public void addControl(LeafletControl leafletControl) {
		getModel().getControls().add(leafletControl);
	}

	/**
	 * Removes the given control from the map
	 * 
	 * @param leafletControl the control to remove
	 */
	public void removeControl(LeafletControl leafletControl) {
		getModel().getControls().remove(leafletControl);
	}

	public void removeAllLayers() {
		getModel().getLayers().clear();
		this.mapLayer.getLayers().clear();
	}

	/**
	 * Fired when the map gets initialized with a view (center and zoom) and at
	 * least one layer, or immediately if it's already initialized.
	 * 
	 * @param listener the listener to call when the event occurs, not {@code null}
	 * @return a handle that can be used for removing the listener
	 */
	public Registration whenReady(ComponentEventListener<MapReadyEvent> listener) {
		return ComponentUtil.addListener(this, MapReadyEvent.class, listener);
	}

	/**
	 * Fired when the map gets initialized on client side
	 */
	@EventHandler
	private void onMapReadyEventHandler() {
		logger.info("Leaflet map gets initialized on client side.");
		this.ready = true;
		fireEvent(new MapReadyEvent(this));
	}

	/**
	 * Adds theme variants to the map component.
	 *
	 * @param variants theme variants to add
	 */
	public void addThemeVariants(LeafletMapVariant... variants) {
		getThemeNames().addAll(Stream.of(variants).map(LeafletMapVariant::getVariantName).collect(Collectors.toList()));
	}

	@Override
	public void execute(Identifiable target, String functionName, Serializable... arguments) {
		if (ready) {
			logger.info("Execute leaflet function: {}", functionName);
			LeafletOperation leafletOperation = new LeafletOperation(target, functionName, arguments);
			getElement().callJsFunction("callLeafletFunction", JsonSerializer.toJson(leafletOperation));
		}
	}

	@Override
	public <T extends Serializable> CompletableFuture<T> call(Identifiable target, String functionName,
			Class<T> resultType, Serializable... arguments) {
		if (ready) {
			logger.info("Call leaflet function: {}", functionName);
			LeafletOperation leafletOperation = new LeafletOperation(target, functionName, arguments);
			PendingJavaScriptResult javascriptResult = getElement().callJsFunction("callLeafletFunction",
					JsonSerializer.toJson(leafletOperation));

			CompletableFuture<T> completableFuture = new CompletableFuture<>();
			javascriptResult.then(value -> {
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					T result = objectMapper.readValue(value.toString(), resultType);
					completableFuture.complete(result);
				} catch (IOException e) {
					throw new RuntimeException("Failed to parse javascript result", e);
				}
			}, errorValue -> {
				JavaScriptException exception = new JavaScriptException(errorValue);
				completableFuture.completeExceptionally(exception);
			});
			return completableFuture;
		} else {
			return null;
		}
	}

	@Override
	public <T extends LeafletEvent> void addEventListener(LeafletEventType eventType,
			LeafletEventListener<T> listener) {
		this.mapLayer.addEventListener(eventType, listener);
		this.getModel().getEvents().add(eventType);
	}

	@Override
	public String getUuid() {
		return getModel().getMapOptions().getUuid();
	}

	@Override
	public void removeEventListener(LeafletEventType eventType) {
		this.mapLayer.removeEventListener(eventType);
		this.getModel().getEvents().remove(eventType);
	}

	@Override
	public void clearAllEventListeners() {
		this.mapLayer.clearAllEventListeners();
		this.getModel().getEvents().clear();
	}

	@Override
	public boolean hasEventListeners(LeafletEventType eventType) {
		return this.mapLayer.hasEventListeners(eventType);
	}

	/**
	 * @return the ready
	 */
	public boolean isReady() {
		return ready;
	}

	/**
	 * Map event which fired when map gets initialized on client side
	 * 
	 * @author <strong>Gabor Kokeny</strong> Email:
	 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
	 * @since 2020-03-16
	 * @version 1.0
	 */
	public static final class MapReadyEvent extends ComponentEvent<LeafletMap> {

		private static final long serialVersionUID = 412791990495078838L;

		public MapReadyEvent(LeafletMap source) {
			super(source, true);
		}

	}
}