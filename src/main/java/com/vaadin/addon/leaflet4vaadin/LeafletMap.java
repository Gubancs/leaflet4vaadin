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

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.addon.leaflet4vaadin.controls.LeafletControl;
import com.vaadin.addon.leaflet4vaadin.layer.Identifiable;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.DragEndEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.DragEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.ErrorEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEventListener;
import com.vaadin.addon.leaflet4vaadin.layer.events.LocationEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.MouseEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.MoveEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.PopupEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.TooltipEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.ZoomAnimEvent;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsLocationEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsMapEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsMouseEvents;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.DragEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.EventTypeRegistry;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LocationEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.MapEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.MouseEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.PopupEventType;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.TooltipEventType;
import com.vaadin.addon.leaflet4vaadin.layer.groups.LayerGroup;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.GeolocationFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.MapFunctions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.operations.LeafletOperation;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;
import com.vaadin.addon.leaflet4vaadin.types.Point;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.internal.JsonSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag("leaflet-map")
@NpmPackage(value = "leaflet", version = "1.6.0")
@JsModule("./leaflet-map.js")
@JsModule("leaflet/dist/leaflet-src.js")
@CssImport(value = "leaflet/dist/leaflet.css", id = "leaflet-css")
@CssImport(value = "./styles/leaflet-lumo-theme.css", id = "lumo-leaflet-map")
public final class LeafletMap extends PolymerTemplate<LeafletModel> implements MapFunctions, GeolocationFunctions,
		SupportsMouseEvents, SupportsMapEvents, SupportsLocationEvents, HasSize, HasTheme {

	private static final long serialVersionUID = 3789693345308589828L;

	private final Logger logger = LoggerFactory.getLogger(LeafletMap.class);

	private static class MapLayer extends LayerGroup
			implements SupportsMouseEvents, SupportsMapEvents, SupportsLocationEvents {
	}

	private final MapLayer mapLayer = new MapLayer();

	public LeafletMap() {
		this(new DefaultMapOptions());
	}

	public LeafletMap(MapOptions mapOptions) {
		setId("template");
		getModel().setMapOptions(mapOptions);
		setSizeFull();
	}

	@EventHandler
	private void onBaseEventHandler(@ModelItem("event.target.options.uuid") String layerId,
			@EventData("event.type") String event, @ModelItem("event.target.options") Layer target) {
		LeafletEventType eventType = EventTypeRegistry.valueOf(event);
		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		if (logger.isInfoEnabled()) {
			logger.info("Event fired on client side:");
			logger.info(" -- type: {}", event);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- target: {}", target);
			logger.info(" -- eventType: {}", eventType);
			logger.info(" -- layer: {}", layer);
		}
		LeafletEvent leafletEvent = new LeafletEvent(layer, eventType);
		layer.fireEvent(leafletEvent);
	}

	@EventHandler
	private void onMouseEventEventHandler(@ModelItem("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.latlng.lat") Double latitude,
			@EventData("event.latlng.lng") Double longitude, @EventData("event.layerPoint.x") int layerPointX,
			@EventData("event.layerPoint.y") int layerPointY, @EventData("event.containerPoint.x") int containerPointX,
			@EventData("event.containerPoint.y") int containerPointY, @ModelItem("event.target.options") Layer target) {

		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		LatLng latLng = new LatLng(latitude, longitude);
		Point layerPoint = Point.of(layerPointX, layerPointY);
		Point containerPoint = Point.of(containerPointX, containerPointY);
		if (logger.isInfoEnabled()) {
			logger.info("MouseEvent fired on client side: ");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- target: {}", target);
			logger.info(" -- latLng: {}", latLng);
			logger.info(" -- layerPoint: {}", layerPoint);
			logger.info(" -- containerPoint: {}", containerPoint);
		}
		MouseEvent leafletEvent = new MouseEvent(layer, MouseEventType.valueOf(eventType), latLng, layerPoint,
				containerPoint);
		layer.fireEvent(leafletEvent);
	}

	@EventHandler
	private void onMoveEventHandler(@ModelItem("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.latlng.lat") Double newLat,
			@EventData("event.latlng.lng") Double newLng, @EventData("event.oldLatLng.lat") Double oldLat,
			@EventData("event.oldLatLng.lng") Double oldLng, @ModelItem("event.target.options") Layer target) {

		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		LatLng latLng = new LatLng(newLat, newLng);
		LatLng oldLatLng = new LatLng(oldLat, oldLng);
		if (logger.isInfoEnabled()) {
			logger.info("MoveEvent fired on client side: ");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- latLng: {}", latLng);
			logger.info(" -- oldLatLng: {}", oldLatLng);
			logger.info(" -- target: {}", target);
			logger.info(" -- layer: {}", layer);
		}
		MoveEvent leafletEvent = new MoveEvent(layer, DragEventType.valueOf(eventType), oldLatLng, latLng);
		layer.fireEvent(leafletEvent);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onKeyboardEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("KeyBoard fired on client side: {} - {}", eventType, layerId);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onLocationEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.latlng.lat") Double latitude,
			@EventData("event.latlng.lng") Double longitude, @EventData("event.accuracy") Double accuracy,
			@EventData("event.altitude") Double altitude, @EventData("event.altitudeAccuracy") Double altitudeAccuracy,
			@EventData("event.heading") Double heading, @EventData("event.speed") Double speed,
			@EventData("event.timestamp") Double timestamp) {

		LatLng latlng = new LatLng(latitude, longitude);
		if (logger.isInfoEnabled()) {
			logger.info("LocationEvent fired on client side:");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- latlng.latitude: {}", latlng.getLat());
			logger.info(" -- latlng.longitude: {}", latlng.getLon());
			logger.info(" -- accuracy: {}", accuracy);
			logger.info(" -- altitude: {}", altitude);
			logger.info(" -- altitudeAccuracy: {}", altitudeAccuracy);
			logger.info(" -- heading: {}", heading);
			logger.info(" -- speed: {}", speed);
			logger.info(" -- timestamp: {}", timestamp);
		}
		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		LocationEvent event = new LocationEvent(layer, LocationEventType.valueOf(eventType), latlng, null, accuracy,
				altitude, altitudeAccuracy, heading, speed, timestamp);
		layer.fireEvent(event);
	}

	@EventHandler
	private void onErrorEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String event, @EventData("event.message") String message,
			@EventData("event.code") int code) {
		LeafletEventType eventType = EventTypeRegistry.valueOf(event);
		if (logger.isInfoEnabled()) {
			logger.info("ErrorEvent fired on client side:");
			logger.info(" -- eventType: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- message: {}", message);
			logger.info(" -- code: {}", code);
		}
		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		ErrorEvent errorEvent = new ErrorEvent(layer, eventType, message, code);
		layer.fireEvent(errorEvent);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onLayersControlEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("LayersControlEvent fired on client side: {} - {}", eventType, layerId);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onTileEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("TileEvent fired on client side: {} - {}", eventType, layerId);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onTileErrorEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("TileErrorEvent fired on client side: {} - {}", eventType, layerId);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onResizeEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("ResizeEvent fired on client side: {} - {}", eventType, layerId);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onGeoJSONEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.type") String eventType) {
		logger.info("GeoJSONEvent fired on client side: {} - {}", eventType, layerId);
	}

	@EventHandler
	private void onPopupEventHandler(@ModelItem("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @ModelItem("event.target.options") Layer target) {
		if (logger.isInfoEnabled()) {
			logger.info("PopupEvent fired on client side:");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- target: {}", target.getJson());
		}
		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		LeafletEvent event = new PopupEvent(layer, PopupEventType.valueOf(eventType), null);
		layer.fireEvent(event);
	}

	@EventHandler
	private void onTooltipEventHandler(@ModelItem("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @ModelItem("event.target.options") Layer target) {
		if (logger.isInfoEnabled()) {
			logger.info("TooltipEvent fired on client side:");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- target: {}", target.getJson());
		}
		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		LeafletEvent event = new TooltipEvent(layer, TooltipEventType.valueOf(eventType), null);
		layer.fireEvent(event);
	}

	/**
	 * The layer that was added or removed.
	 */
	@EventHandler
	private void onDragEventHandler(@ModelItem("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.latlng.lat") Double newLat,
			@EventData("event.latlng.lng") Double newLng, @EventData("event.oldLatLng.lat") Double oldLat,
			@EventData("event.oldLatLng.lng") Double oldLng, @ModelItem("event.target.options") Layer target) {

		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		LatLng latLng = new LatLng(newLat, newLng);
		LatLng oldLatLng = new LatLng(oldLat, oldLng);
		if (logger.isInfoEnabled()) {
			logger.info("DragEvent fired on client side: ");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- latLng: {}", latLng);
			logger.info(" -- oldLatLng: {}", oldLatLng);
			logger.info(" -- target: {}", target);
			logger.info(" -- layer: {}", layer);
		}
		DragEvent leafletEvent = new DragEvent(layer, DragEventType.valueOf(eventType), oldLatLng, latLng);
		layer.fireEvent(leafletEvent);
	}

	/**
	 * The layer that was added or removed.
	 * 
	 * @param distance
	 */
	@EventHandler
	private void onDragEndEventHandler(@ModelItem("event.target.options.uuid") String layerId,
			@EventData("event.type") String eventType, @EventData("event.distance") double distance,
			@ModelItem("event.target.options") Layer target) {
		if (logger.isInfoEnabled()) {
			logger.info("DragEndEvent fired on client side:");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- distance: {}", distance);
			logger.info(" -- target: {}", target.getJson());
		}
		Layer layer = this.mapLayer.findLayer(layerId).orElse(this.mapLayer);
		LeafletEvent event = new DragEndEvent(layer, DragEventType.valueOf(eventType), distance);
		layer.fireEvent(event);
	}

	/**
	 * Fired at least once per zoom animation. For continuous zoom, like pinch
	 * zooming, fired once per frame during zoom.
	 */
	@EventHandler
	private void onZoomAnimEventHandler(@ModelItem("target.options.uuid") String layerId,
			@EventData("event.zoom") int zoom, @EventData("event.center.lat") Double latitude,
			@EventData("event.center.lng") Double longitude, @EventData("event.type") String eventType,
			@ModelItem("event.target.options") Layer target) {

		LatLng center = new LatLng(latitude, longitude);
		if (logger.isInfoEnabled()) {
			logger.info("ZoomAnimEvent fired on client side:");
			logger.info(" -- type: {}", eventType);
			logger.info(" -- uuid: {}", layerId);
			logger.info(" -- zoom: {}", zoom);
			logger.info(" -- center: {}", center);
			logger.info(" -- target: {}", target);
			logger.info(" -- layer: {}", this.mapLayer);
		}
		LeafletEvent event = new ZoomAnimEvent(this.mapLayer, MapEventType.valueOf(eventType), center, zoom, false);
		this.mapLayer.fireEvent(event);
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

	@Override
	public <T extends LeafletEvent> void addEventListener(LeafletEventType eventType,
			LeafletEventListener<T> listener) {
		this.mapLayer.addEventListener(eventType, listener);
		this.getModel().getEvents().add(eventType);
	}

	public void removeAllLayers() {
		getModel().getLayers().clear();
		this.mapLayer.getLayers().clear();
	}

	/**
	 * Sets a map view that contains the given geographical bounds with the maximum
	 * zoom level possible.
	 * 
	 * @param bounds the geographical bounds
	 */
	public void fitBounds(LatLngBounds bounds) {
		getModel().getMapOptions().setBounds(bounds);
	}

	/**
	 * Returns the geographical bounds visible in the current map view
	 * 
	 * @return the geographical bounds of the map
	 */
	public LatLngBounds getBounds() {
		return getModel().getMapOptions().getBounds();
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
		logger.info("Execute leaflet function: {}", functionName);
		LeafletOperation leafletOperation = new LeafletOperation(target, functionName, arguments);
		getElement().callJsFunction("callLeafletFunction", JsonSerializer.toJson(leafletOperation));
	}

	@Override
	public <T extends Serializable> T call(Identifiable target, String functionName, Class<T> resultType,
			Serializable... arguments) {
		logger.info("Call leaflet function: {}", functionName);
		long took = System.currentTimeMillis();
		LeafletOperation leafletOperation = new LeafletOperation(target, functionName, arguments);
		PendingJavaScriptResult javascriptResult = getElement().callJsFunction("callLeafletFunction",
				JsonSerializer.toJson(leafletOperation));

		CompletableFuture<T> completableFuture = javascriptResult.toCompletableFuture(resultType);

		try {
			T result = completableFuture.get();
			logger.info(" -- result: {}", result);
			logger.info(" -- took: {}", (System.currentTimeMillis() - took) + " ms");
			return result;
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Failed to handle javascript result", e);
		}
	}

	@Override
	public String getUuid() {
		return getModel().getMapOptions().getUuid();
	}

}