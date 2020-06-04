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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Paths event propagation")
@Route(value = "path/events", layout = LeafletDemoApp.class)
public class PathsEventPropagationExample extends ExampleContainer {

    private static final long serialVersionUID = 5677974062708487480L;

    @Override
	protected void initDemo() {

		MapOptions options = new DefaultMapOptions();
		options.setCenter(latlng(47.070121823, 19.2041015625));
		options.setZoom(7);
		options.setPreferCanvas(true);
		LeafletMap leafletMap = new LeafletMap(options);
		leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

		for (int i = 0; i < 100; i++) {
			int radius = (int) (Math.random() * 20);
			double lat = (Math.random() * 10) + 40;
			double lon = (Math.random() * 10) + 10;
			String fillColor = "rgb(" + (int) (Math.random() * 255) + "," + (int) (Math.random() * 255) + ","
					+ (int) (Math.random() * 255) + ")";
			CircleMarker circleMarker = new CircleMarker(latlng(lat, lon), radius);
			circleMarker.setWeight(0);
			circleMarker.setFillColor(fillColor);
			circleMarker.setFillOpacity(Math.random());
			circleMarker.bindTooltip("<strong>Radius: </strong>" + circleMarker.getRadius()
					+ "<br/><strong>Opacity: </strong>" + circleMarker.getOpacity()
					+ "<br/><strong>Fill color: </strong>" + circleMarker.getFillColor()
					+ "<br/><strong>Latitude: </strong>" + circleMarker.getLatlng().getLat()
					+ "<br/><strong>Longitude: </strong>" + circleMarker.getLatlng().getLng());
			circleMarker.addTo(leafletMap);
		}

		addToContent(leafletMap);

	}

}
