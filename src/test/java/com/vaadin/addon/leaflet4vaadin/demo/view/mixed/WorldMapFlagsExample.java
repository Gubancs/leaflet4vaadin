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

package com.vaadin.addon.leaflet4vaadin.demo.view.mixed;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.demo.utils.GeoJsonUtils;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.MouseEventType;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSON;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSONOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;

@PageTitle("World map flags")
@Route(value = "mixed/word-map-flags", layout = LeafletDemoApp.class)
public class WorldMapFlagsExample extends ExampleContainer {

	@Override
	protected void initDemo() {

		MapOptions mapOptions = new DefaultMapOptions();
		mapOptions.setCenter(new LatLng(47.070121823, 19.2041015625));
		mapOptions.setZoom(3);
		LeafletMap leafletMap = new LeafletMap(mapOptions);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		FeatureCollection featureCollection = GeoJsonUtils.loadFeatureCollection("json/countries.geo.json");

		GeoJSONOptions options = new GeoJSONOptions();
		options.style(this::styleCountries);
		options.filter(this::filterCountries);
		options.onEachFeature(this::onEachCountries);
		GeoJSON geoJSON = new GeoJSON(featureCollection, options);
		geoJSON.addTo(leafletMap);

		addToContent(leafletMap);
	}

	public boolean filterCountries(GeoJsonObject geoJson) {
		Feature feature = GeoJSON.asFeature(geoJson);
		String country = (String) feature.getProperties().get("name");
		return country.contains("n");
	}

	public PathOptions styleCountries(Feature feature) {
		String country = (String) feature.getProperties().get("name");
		if ("Hungary".equals(country)) {
			PathOptions pathOptions = new PathOptions();
			pathOptions.setColor("red");
			pathOptions.setFillColor("red");
			return pathOptions;

		} else {
			return new PathOptions();
		}
	}

	public void onEachCountries(Feature feature, Layer layer) {
		layer.on(MouseEventType.click, (event) -> {
			Notification.show("The selected country is: " + feature.getProperties().get("name"), 3000,
					Position.TOP_CENTER);
		});
	}
}
