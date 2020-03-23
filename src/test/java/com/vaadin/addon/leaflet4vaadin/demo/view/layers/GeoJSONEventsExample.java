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

package com.vaadin.addon.leaflet4vaadin.demo.view.layers;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.demo.utils.GeoJsonUtils;
import com.vaadin.addon.leaflet4vaadin.layer.InteractiveLayer;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSON;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSONOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Path;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.geojson.Feature;
import org.geojson.FeatureCollection;

@PageTitle("GeoJSON events")
@Route(value = "layers/geojson-events", layout = LeafletDemoApp.class)
public class GeoJSONEventsExample extends ExampleContainer {

	static PathOptions defaultStyle = new PathOptions("blue", 0.5);
	static PathOptions hoverStyle = new PathOptions("red", 0.5);

	@Override
	protected void initDemo() {

		MapOptions mapOptions = new DefaultMapOptions();
		mapOptions.setCenter(new LatLng(47.070121823, 19.2041015625));
		mapOptions.setZoom(3);
		LeafletMap leafletMap = new LeafletMap(mapOptions);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		FeatureCollection featureCollection = GeoJsonUtils.loadFeatureCollection("json/countries.geo.json");

		GeoJSONOptions options = new GeoJSONOptions();
		options.onEachFeature(this::onEachCountries);

		GeoJSON geoJSON = new GeoJSON(featureCollection, options);
		geoJSON.setStyle(defaultStyle);
		geoJSON.addTo(leafletMap);

		addToContent(leafletMap);
	}

	public void onEachCountries(Feature feature, Layer layer) {
		InteractiveLayer interactiveLayer = (InteractiveLayer) layer;
		interactiveLayer.onClick((event) -> {
			Notification.show("You selected the following country: " + feature.getProperties().get("name"), 3000,
					Position.TOP_CENTER);
		});
		interactiveLayer.onMouseOver((event) -> {
			Path layerWithStyle = (Path) layer;
			layerWithStyle.setStyle(hoverStyle);
			layerWithStyle.bringToFront();
		});
		interactiveLayer.onMouseOut((event) -> {
			Path layerWithStyle = (Path) layer;
			layerWithStyle.setStyle(defaultStyle);
		});
	}
}
