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
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSON;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSONOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.geojson.FeatureCollection;

@PageTitle("GeoJSON style")
@Route(value = "layers/geojson-style", layout = LeafletDemoApp.class)
public class GeoJSONStyleExample extends ExampleContainer {

	static final PathOptions defaultStyle = new PathOptions("gray", 0.5);
	static final PathOptions filteredStyle = new PathOptions("red", 0.5);

	@Override
	protected void initDemo() {

		final MapOptions mapOptions = new DefaultMapOptions();
		mapOptions.setCenter(new LatLng(47.070121823, 19.204101562500004));
		mapOptions.setZoom(3);
		mapOptions.setPreferCanvas(true);
		final LeafletMap leafletMap = new LeafletMap(mapOptions);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		FeatureCollection featureCollection = GeoJsonUtils.loadFeatureCollection("json/countries.geo.json");

		GeoJSONOptions options = new GeoJSONOptions();
		options.style((feature) -> {
			String countryName = (String) feature.getProperties().get("name");
			return countryName.startsWith("H") ? filteredStyle : defaultStyle;
		});

		GeoJSON geoJSON = new GeoJSON(featureCollection, options);
		geoJSON.addTo(leafletMap);

		addToContent(leafletMap);
	}

}
