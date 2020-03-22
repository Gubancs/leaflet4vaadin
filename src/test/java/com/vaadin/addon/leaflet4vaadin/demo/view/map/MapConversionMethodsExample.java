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
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Map conversion methods")
@Route(value = "map/conversion", layout = LeafletDemoApp.class)
public class MapConversionMethodsExample extends ExampleContainer {
	private static final long serialVersionUID = -3659860383467926963L;

	private TextField latitude;
	private TextField longitude;
	private TextField locationX;
	private TextField locationY;

	private LeafletMap leafletMap;

	@Override
	protected void initDemo() {
		FormLayout form = new FormLayout();
		latitude = createTextField(form, "Latitude");
		longitude = createTextField(form, "Longitude");
		locationX = createTextField(form, "Location X (pixels):");
		locationY = createTextField(form, "Location Y (pixels):");
		addToSidebar(form);

		MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.2041015625));
		options.setZoom(7);
		leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		Marker marker = new Marker(options.getCenter());
		marker.bindTooltip("Just click on map to change my location.");
		marker.addTo(leafletMap);

		leafletMap.whenReady((event) -> {
			updateMarkerPosition(marker, options.getCenter());
		});

		leafletMap.onClick((event) -> {
			updateMarkerPosition(marker, event.getLatLng());
		});

		addToContent(leafletMap);
	}

	private void updateMarkerPosition(Marker marker, LatLng latLng) {
		latitude.setValue(String.valueOf(latLng.getLat()));
		longitude.setValue(String.valueOf(latLng.getLng()));

		leafletMap.latLngToContainerPoint(latLng).thenAccept((result) -> {
			locationX.setValue(String.valueOf(result.getX()));
			locationY.setValue(String.valueOf(result.getY()));
		});

		marker.setLatLng(latLng);
	}

	private static TextField createTextField(FormLayout form, String label) {
		TextField textField = new TextField();
		textField.setReadOnly(true);
		textField.setWidthFull();
		form.addFormItem(textField, label);
		return textField;
	}

}
