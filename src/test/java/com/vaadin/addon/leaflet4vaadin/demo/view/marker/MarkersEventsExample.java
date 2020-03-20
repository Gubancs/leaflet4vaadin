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
import com.vaadin.addon.leaflet4vaadin.layer.events.LeafletEvent;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Marker events")
@Route(value = "marker/events", layout = LeafletDemoApp.class)
public class MarkersEventsExample extends ExampleContainer {

	private static final long serialVersionUID = -9185254069257875170L;
	
	private Label eventLabel;

	@Override
	protected void initMap(Div mapContainer) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		FormLayout toolbar = new FormLayout();
		toolbar.setWidthFull();
		eventLabel = new Label();
		eventLabel.getStyle().set("font-weight", "bold");
		toolbar.add(eventLabel);

		MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.2041015625));
		options.setZoom(7);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		Marker marker = new Marker(new LatLng(46.470121823, 18.3041015625));
		marker.setDraggable(true);
		marker.bindPopup("Hi, I'am an interactive marker!");
		marker.bindTooltip("hey, drag me if you want.");
		marker.onClick(this::logEvent);
		marker.onAdd(this::logEvent);
		marker.onDoubleClick(this::logEvent);
		marker.onPopupOpen(this::logEvent);
		marker.onPopupClose(this::logEvent);
		marker.onTooltipOpen(this::logEvent);
		marker.onTooltipClose(this::logEvent);
		marker.onDragStart(this::logEvent);
		marker.onDragEnd(this::logEvent);
		marker.onDrag(this::logEvent);
		marker.onContextMenuOpened(this::logEvent);
		marker.onMouseDown(this::logEvent);
		marker.onMouseUp(this::logEvent);
		marker.addTo(leafletMap);

		layout.add(toolbar, leafletMap);
		mapContainer.add(layout);
	}

	protected void logEvent(LeafletEvent leafletEvent) {
		this.eventLabel.setText("'" + leafletEvent.getType().name() + "' event caught in listener.");
	}
}
