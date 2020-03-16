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

import com.github.appreciated.app.layout.annotations.Caption;
import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Caption("Map conversion methods")
@Route(value = "map/conversion", layout = LeafletDemoApp.class)
public class MapConversionMethodsExample extends ExampleContainer {

	private static final long serialVersionUID = -3659860383467926963L;

	TextField latitude;
	TextField longitude;
	TextField locationX;
	TextField locationY;

	private LeafletMap leafletMap;

	@Override
	protected void initMap(final Div mapContainer) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();

		FormLayout sidebar = new FormLayout();
		sidebar.setHeightFull();
		sidebar.setWidth("400px");

		latitude = new TextField();
		longitude = new TextField();
		locationX = new TextField();
		locationY = new TextField();

		latitude.setReadOnly(true);
		longitude.setReadOnly(true);
		locationX.setReadOnly(true);
		locationY.setReadOnly(true);

		latitude.setWidthFull();
		longitude.setWidthFull();
		locationX.setWidthFull();
		locationY.setWidthFull();

		sidebar.addFormItem(latitude, "Latitude");
		sidebar.addFormItem(longitude, "Longitude");
		sidebar.addFormItem(locationX, "Location X (pixels): ");
		sidebar.addFormItem(locationY, "Location Y (pixels):");

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

		layout.add(leafletMap, sidebar);
		mapContainer.add(layout);
	}

	private void updateMarkerPosition(Marker marker, LatLng latLng) {
		latitude.setValue(String.valueOf(latLng.getLat()));
		longitude.setValue(String.valueOf(latLng.getLon()));

		leafletMap.latLngToContainerPoint(latLng).thenAccept((result) -> {
			locationX.setValue(String.valueOf(result.getX()));
			locationY.setValue(String.valueOf(result.getY()));
		});

		marker.setLatLng(latLng);
	}

}
