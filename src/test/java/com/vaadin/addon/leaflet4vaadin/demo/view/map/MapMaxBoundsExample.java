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

package com.vaadin.addon.leaflet4vaadin.demo.view.map;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Map max bounds")
@Route(value = "map/max-bounds", layout = LeafletDemoApp.class)
public class MapMaxBoundsExample extends ExampleContainer {

	private static final long serialVersionUID = 6608207938871245065L;

	@Override
	protected void initMap(final Div mapContainer) {

		final MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.204101562500004));
		options.setZoom(7);
		final LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		leafletMap.whenReady((e) -> {
			leafletMap.getBounds().thenAccept((bounds) -> {
				System.out.println("Current bounds: " + bounds);
				leafletMap.setMaxBounds(bounds);
			});
		});

		mapContainer.add(leafletMap);
	}

}
