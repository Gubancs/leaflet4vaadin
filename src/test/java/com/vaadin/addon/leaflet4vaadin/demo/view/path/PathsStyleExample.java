
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
import com.vaadin.addon.leaflet4vaadin.layer.vectors.CircleMarker;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Change styles")
@Route(value = "path/style", layout = LeafletDemoApp.class)
public class PathsStyleExample extends ExampleContainer {

	private Binder<PathOptions> binder;

	@Override
	protected void initDemo() {
		MapOptions options = new DefaultMapOptions();
		options.setCenter(latlng(47.070121823, 19.2041015625));
		options.setZoom(7);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		binder = new Binder<>(PathOptions.class);
		createFormControls(binder);

		binder.setBean(new PathOptions());

		CircleMarker marker = new CircleMarker(latlng(47.070121823, 19.2041015625), 100);
		marker.addTo(leafletMap);
		binder.addValueChangeListener((event) -> marker.setStyle(binder.getBean()));
		binder.addStatusChangeListener((event) -> marker.setStyle(binder.getBean()));

		addToContent(leafletMap);
	}

	private void createFormControls(Binder<PathOptions> binder) {
		// Stroke control
		FormLayout form = new FormLayout();
		Checkbox checkbox = new Checkbox();
		form.addFormItem(checkbox, "Stroke");
		binder.forField(checkbox).bind("stroke");

		Select<String> strokeColor = new Select<>("red", "blue", "white", "green", "yellow", "gray", "black");
		strokeColor.setWidthFull();
		form.addFormItem(strokeColor, "Stroke color");
		binder.forField(strokeColor).bind("color");

		// Weight control
		NumberField weight = new NumberField();
		weight.setHasControls(true);
		weight.setMin(0);
		weight.setMax(20);
		weight.setWidthFull();
		form.addFormItem(weight, "Stroke weight");
		binder.forField(weight).bind("weight");

		// Fill control
		Checkbox fill = new Checkbox();
		form.addFormItem(fill, "Fill");
		binder.forField(fill).bind("fill");

		Select<String> fillColor = new Select<>("red", "blue", "white", "green", "yellow", "gray", "black");
		fillColor.setWidthFull();
		form.addFormItem(fillColor, "Fill color");
		binder.forField(fillColor).bind("fillColor");

		// Fill opacity control
		NumberField fillOpacity = new NumberField();
		fillOpacity.setHasControls(true);
		fillOpacity.setMin(0);
		fillOpacity.setMax(100);
		fillOpacity.setWidthFull();
		form.addFormItem(fillOpacity, "Fill opacity");
		binder.forField(fillOpacity).bind((o) -> 1 / o.getFillOpacity(), (o, v) -> o.setFillOpacity(v / 100));

		Button reset = new Button("Reset to defaults");
		reset.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		reset.addClickListener((event) -> {
			binder.setBean(new PathOptions());
		});
		form.add(reset);

		addToSidebar(form);
	}

}
