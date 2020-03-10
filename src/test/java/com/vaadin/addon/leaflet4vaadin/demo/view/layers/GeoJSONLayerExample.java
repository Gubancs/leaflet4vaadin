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

import com.github.appreciated.app.layout.annotations.Caption;
import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSON;
import com.vaadin.addon.leaflet4vaadin.layer.map.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.MapOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;

@Caption("GeoJSON layer")
@Route(value = "layers/geojson", layout = LeafletDemoApp.class)
public class GeoJSONLayerExample extends ExampleContainer {

	@Override
	protected void initMap(final Div mapContainer) {

		final MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.204101562500004));
		options.setZoom(7);
		options.setPreferCanvas(true);
		final LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		GeometryFactory factory = new GeometryFactory();
		Geometry point = factory.createPoint(new Coordinate(17.80, 47.5));
		Geometry line = factory
				.createLineString(new Coordinate[] { new Coordinate(17.80, 47.5), new Coordinate(17.20, 47.8) });
		Geometry polygon = factory.createPolygon(new Coordinate[] { new Coordinate(19.20, 47.0),
				new Coordinate(19.20, 47.25), new Coordinate(18.50, 47.3), new Coordinate(19.20, 47.0) });
		Geometry[] geometries = { point, line, polygon };
		GeometryCollection gc = factory.createGeometryCollection(geometries);
		GeoJSON geoJSON = new GeoJSON(gc);
		geoJSON.addTo(leafletMap);

		mapContainer.add(leafletMap);
	}

}
