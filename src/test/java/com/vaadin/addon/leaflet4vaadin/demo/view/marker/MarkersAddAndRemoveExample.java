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

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Add/remove markers easily")
@Route(value = "marker/add-and-remove-markers", layout = LeafletDemoApp.class)
public class MarkersAddAndRemoveExample extends ExampleContainer {

	@Override
	protected void initDemo() {

		Button addButton = new Button("Add markers");
		addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		addButton.setEnabled(false);
		Button removeButton = new Button("Remove markers");
		removeButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
		addToSidebar(addButton, removeButton);

		MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.2041015625));
		options.setZoom(7);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		addMarkers(leafletMap);

		removeButton.addClickListener((event) -> {
//FIXME			leafletMap.removeAllLayers();
			removeButton.setEnabled(false);
			addButton.setEnabled(true);
		});
		addButton.addClickListener((event) -> {
			this.addMarkers(leafletMap);
			removeButton.setEnabled(true);
			addButton.setEnabled(false);
		});

		addToContent(leafletMap);
	}

	private void addMarkers(LeafletMap leafletMap) {
		Marker draggableMarker = new Marker(new LatLng(47.070121823, 19.2041015625));
		draggableMarker.setDraggable(true);
		draggableMarker.bindPopup("Hey, drag me if you want");
		draggableMarker.addTo(leafletMap);

		Marker staticMarker = new Marker(new LatLng(46.470121823, 18.3041015625));
		staticMarker.bindPopup("Hey, I'm a static marker");
		staticMarker.addTo(leafletMap);

		Notification.show("Your markers has been added to map.", 3000, Position.TOP_CENTER);
	}

}
