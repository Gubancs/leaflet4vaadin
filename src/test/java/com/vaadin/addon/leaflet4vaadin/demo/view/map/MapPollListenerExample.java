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

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.CircleMarker;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Map poll listener")
@Route(value = "map/polling", layout = LeafletDemoApp.class)
public class MapPollListenerExample extends ExampleContainer {

	private static final Integer DEFAULT_POLL_INTERVAL = 3000;

	private Integer radius;
	private Integer circleCount;

	@Override
	protected void initDemo() {

		MapOptions options = new DefaultMapOptions();
		options.setCenter(latlng(47.070121823, 19.2041015625));
		options.setZoom(7);
		options.setPreferCanvas(true);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		this.radius = 10;
		this.circleCount = 50;

		refresh(leafletMap);
		
		createFormControls();

		UI.getCurrent().setPollInterval(DEFAULT_POLL_INTERVAL);
		UI.getCurrent().addPollListener((pollEvent) -> refresh(leafletMap));

		addToContent(leafletMap);
	}

	private void refresh(LeafletMap leafletMap) {
		leafletMap.removeAllLayers();
		for (int i = 0; i < circleCount; i++) {
			int radius = (int) (Math.random() * this.radius);
			double lat = (Math.random() * 4) + 45;
			double lon = (Math.random() * 7) + 16;
			CircleMarker circleMarker = new CircleMarker(latlng(lat, lon), radius);
			circleMarker.setWeight(0);
			circleMarker.setFillOpacity(Math.random());
			circleMarker.addTo(leafletMap);
		}
	}

	private void createFormControls() {
		FormLayout form = new FormLayout();
		Checkbox enablePolling = new Checkbox();
		enablePolling.setValue(true);
		enablePolling.addValueChangeListener((event) -> {
			if (event.getValue()) {
				UI.getCurrent().setPollInterval(DEFAULT_POLL_INTERVAL);
			} else {
				UI.getCurrent().setPollInterval(-1);
			}
		});
		form.addFormItem(enablePolling, "Enable/disable polling");

		NumberField weight = new NumberField();
		weight.setValue(DEFAULT_POLL_INTERVAL.doubleValue());
		weight.setHasControls(true);
		weight.setMin(300);
		weight.setMax(5000);
		weight.setWidthFull();
		weight.addValueChangeListener((event) -> UI.getCurrent().setPollInterval(event.getValue().intValue()));
		form.addFormItem(weight, "Poll interval");

		NumberField radius = new NumberField();
		radius.setValue(this.radius.doubleValue());
		radius.setHasControls(true);
		radius.setMin(1);
		radius.setMax(100);
		radius.setWidthFull();
		radius.addValueChangeListener((event) -> this.radius = event.getValue().intValue());
		form.addFormItem(radius, "Max radius");

		NumberField circleCount = new NumberField();
		circleCount.setValue(this.circleCount.doubleValue());
		circleCount.setHasControls(true);
		circleCount.setMin(2);
		circleCount.setMax(300);
		circleCount.setWidthFull();
		circleCount.addValueChangeListener((event) -> this.circleCount = event.getValue().intValue());
		form.addFormItem(circleCount, "Number of circles");

		addToSidebar(form);
	}

}
