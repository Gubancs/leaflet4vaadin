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

import org.geojson.GeometryCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.geojson.Point;
import org.geojson.Polygon;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSON;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("GeoJSON layer")
@Route(value = "layers/geojson", layout = LeafletDemoApp.class)
public class GeoJSONLayerExample extends ExampleContainer {

	@Override
	protected void initDemo() {

		final MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.204101562500004));
		options.setZoom(7);
		options.setPreferCanvas(true);
		final LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		Point point = new Point(17.80, 47.5);
		LineString line = new LineString(new LngLatAlt(17.80, 47.5), new LngLatAlt(17.20, 47.8));
		Polygon polygon = new Polygon(new LngLatAlt(19.20, 47.0), new LngLatAlt(19.20, 47.25),
				new LngLatAlt(18.50, 47.3), new LngLatAlt(19.20, 47.0));

		GeometryCollection geoJson = new GeometryCollection();
		geoJson.add(point);
		geoJson.add(line);
		geoJson.add(polygon);

		GeoJSON geoJSON = new GeoJSON(geoJson);
		geoJSON.addTo(leafletMap);

		addToContent(leafletMap);
	}

}
