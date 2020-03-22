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

package com.vaadin.addon.leaflet4vaadin.demo.view.path;

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Polyline;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Path simple")
@Route(value = "path/simple", layout = LeafletDemoApp.class)
public class PathSimpleExample extends ExampleContainer {

	@Override
	protected void initDemo() {

		MapOptions options = new DefaultMapOptions();
		options.setCenter(latlng(47.07, 19.20));
		options.setZoom(7);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		Polyline polyline = new Polyline(latlng(47.2, 18.3), latlng(47.3, 17.42));
		polyline.addTo(leafletMap);

		addToContent(leafletMap);

		leafletMap.whenReady((event) -> {
			leafletMap.fitBounds(polyline.getBounds());
		});

	}
}
