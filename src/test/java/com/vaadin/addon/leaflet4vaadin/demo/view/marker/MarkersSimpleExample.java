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
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Markers simple")
@Route(value = "marker/simple", layout = LeafletDemoApp.class)
public class MarkersSimpleExample extends ExampleContainer {

	private static final long serialVersionUID = -2604214754789085086L;

	@Override
	protected void initMap(Div mapContainer) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSizeFull();

		FormLayout sidebar = new FormLayout();
		sidebar.setHeightFull();
		sidebar.setWidth("400px");
		TextField latitude = new TextField();
		TextField longitude = new TextField();
		longitude.setWidthFull();
		latitude.setWidthFull();

		sidebar.addFormItem(latitude, "Latitude");
		sidebar.addFormItem(longitude, "Longitude");

		MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.2041015625));
		options.setZoom(7);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		Marker marker = new Marker(options.getCenter());
		marker.setDraggable(true);
		marker.bindPopup("Hey, drag me if you want");
		marker.onClick((e) -> {
			Notification.show("You click the marker.", 3000, Position.TOP_CENTER);
		});
		Binder<Marker> binder = new Binder<>(Marker.class);
		binder.forField(latitude).bind((m) -> String.valueOf(m.getLatLng().getLat()), null);
		binder.forField(longitude).bind((m) -> String.valueOf(m.getLatLng().getLng()), null);
		binder.readBean(marker);

		marker.onMove((e) -> {
			binder.readBean(marker);
		});
		leafletMap.addLayer(marker);


		layout.add(leafletMap, sidebar);
		mapContainer.add(layout);
	}
}
