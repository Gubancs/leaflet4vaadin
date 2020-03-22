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

package com.vaadin.addon.leaflet4vaadin.demo.view.layers;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.raster.TileLayer;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Simple tile layer")
@Route(value = "layers/tilelayer", layout = LeafletDemoApp.class)
public class TileLayerExample extends ExampleContainer {

	private static final long serialVersionUID = -875948840999507377L;

	@Override
	protected void initDemo() {

		final MapOptions options = new DefaultMapOptions();
		options.setCenter(new LatLng(47.070121823, 19.204101562500004));
		options.setZoom(7);
		final LeafletMap leafletMap = new LeafletMap(options);

		TileLayer wikimedia = new TileLayer("https://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png");
		wikimedia.setAttribution("Wikimedia Maps");
		wikimedia.setName("Wikimedia Maps");
		wikimedia.addTo(leafletMap);

		addToContent(leafletMap);
	}

}
