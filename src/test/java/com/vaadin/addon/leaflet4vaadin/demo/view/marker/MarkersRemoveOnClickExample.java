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

package com.vaadin.addon.leaflet4vaadin.demo.view.marker;

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;
import static java.util.stream.IntStream.range;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Remove on click")
@Route(value = "marker/remove-on-click", layout = LeafletDemoApp.class)
public class MarkersRemoveOnClickExample extends ExampleContainer {

	private static final long serialVersionUID = -4737660367386714966L;

	@Override
	protected void initMap(Div mapContainer) {

		MapOptions options = new DefaultMapOptions();
		options.setCenter(latlng(47.070121823, 19.2041015625));
		options.setZoom(7);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		range(0, 10).forEach((i) -> {
			double lat = (Math.random() * 4) + 45;
			double lon = (Math.random() * 7) + 16;
			Marker marker = new Marker(latlng(lat, lon));
			marker.bindTooltip("Click me to remove from map");
			marker.onClick((e) -> {
				leafletMap.removeLayer(e.getTarget());
				Notification.show("Marker removed successfully", 1000, Position.TOP_CENTER);
			});
			marker.addTo(leafletMap);
		});

		mapContainer.add(leafletMap);
	}
}
