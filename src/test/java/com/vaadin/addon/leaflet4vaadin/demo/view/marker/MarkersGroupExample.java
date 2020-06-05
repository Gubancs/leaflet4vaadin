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
import com.vaadin.addon.leaflet4vaadin.controls.LayersControl;
import com.vaadin.addon.leaflet4vaadin.controls.LayersControlOptions;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.groups.LayerGroup;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Marker group")
@Route(value = "marker/group", layout = LeafletDemoApp.class)
public class MarkersGroupExample extends ExampleContainer {

    private LeafletMap leafletMap;
	private LayersControl layersControl;
	private LayerGroup defaultGroupLayer;
	private LayerGroup customGroupLayer;

	@Override
	protected void initDemo() {
		Button defaultGroup = new Button("Default markers");
		defaultGroup.addThemeVariants(ButtonVariant.LUMO_ERROR);
		defaultGroup.addClickListener((event) -> {
			if (defaultGroupLayer == null) {
				defaultGroupLayer = createDefaultMarkerGroup();
			} else {
                layersControl.removeLayer(defaultGroupLayer);
                leafletMap.removeLayer(defaultGroupLayer);
                defaultGroupLayer = null;
			}
		});
		Button customGroup = new Button("Custom markers");
		customGroup.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		customGroup.addClickListener((event) -> {
			if (customGroupLayer == null) {
				customGroupLayer = createCustomMarkerGroup();
			} else {
				layersControl.removeLayer(customGroupLayer);
				leafletMap.removeLayer(customGroupLayer);
				customGroupLayer = null;
			}
		});
		addToSidebar(defaultGroup, customGroup);

		MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.2041015625));
		options.setZoom(7);
		leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		//Initialize layers control with customized options
		LayersControlOptions layersControlOptions = new LayersControlOptions();
		layersControlOptions.setCollapsed(false);
		layersControl = new LayersControl(layersControlOptions);
        layersControl.addTo(leafletMap);
		
        //Initialize layers and add them to the Map and the LayersControl
		customGroupLayer = createCustomMarkerGroup();
		defaultGroupLayer = createDefaultMarkerGroup();
		

		addToContent(leafletMap);
	}

	public LayerGroup createDefaultMarkerGroup() {
		Marker m1 = new Marker(new LatLng(46.470121823, 18.3041015625));
		Marker m2 = new Marker(new LatLng(46.320121823, 18.0041015625));
		LayerGroup group = new LayerGroup(m1, m2);
		group.addTo(leafletMap);
		layersControl.addOverlay(group, "Default markers");
		return group;
	}

	public LayerGroup createCustomMarkerGroup() {
		com.vaadin.addon.leaflet4vaadin.types.Icon icon = new com.vaadin.addon.leaflet4vaadin.types.Icon(
				"images/marker-icon-demo.png", 41);
		Marker m3 = new Marker(new LatLng(46.270121823, 18.8041015625));
		m3.setIcon(icon);
		Marker m4 = new Marker(new LatLng(46.320121823, 19.2041015625));
		m4.setIcon(icon);
		LayerGroup group = new LayerGroup(m3, m4);
        group.addTo(leafletMap);
		layersControl.addOverlay(group, "Custom markers");
		return group;
	}

}
