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

import com.github.appreciated.app.layout.annotations.Caption;
import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.raster.TileLayer;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Caption("Multiple base layers")
@Route(value = "layers/baselayers", layout = LeafletDemoApp.class)
public class MultipleBaseLayersExample extends ExampleContainer {

    /**
     *
     */
    private static final long serialVersionUID = -6643626349928766922L;

    @Override
    protected void initMap(final Div mapContainer) {

        final MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.204101562500004));
        options.setZoom(7);
        final LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        TileLayer openStreetmap = new TileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
        openStreetmap.setAttribution("OpenStreetmap");
        openStreetmap.setName("OpenStreetmap default");
        openStreetmap.setSubdomains("1");
        openStreetmap.addTo(leafletMap);

        TileLayer mapQuest = new TileLayer("http://{s}.basemaps.cartocdn.com/light_nolabels/{z}/{x}/{y}.png");
        mapQuest.setAttribution("Tiles courtesy of MapQuest");
        mapQuest.setName("Mapquest layer");
        mapQuest.setSubdomains("1");
        mapQuest.addTo(leafletMap);

        TileLayer wikimedia = new TileLayer("https://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png");
        wikimedia.setAttribution("Wikimedia Maps");
        wikimedia.setName("Wikimedia Maps");
        wikimedia.addTo(leafletMap);

        TileLayer openCycleMap = new TileLayer("http://tile.thunderforest.com/cycle/{z}/{x}/{y}.png");
        openCycleMap.setAttribution("OpenCycleMap");
        openCycleMap.setName("OpenCycleMap");
        openCycleMap.addTo(leafletMap);

        TileLayer grayscale = new TileLayer("https://tiles.wmflabs.org/bw-mapnik/{z}/{x}/{y}.png");
        grayscale.setAttribution("wmflabs OSM B&W mapnik map grayscale");
        grayscale.setName("Mapnik map grayscale");
        grayscale.addTo(leafletMap);

        mapContainer.add(leafletMap);
    }

}
