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

package com.vaadin.addon.leaflet4vaadin.demo.view.controls;

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;
import static java.util.stream.IntStream.range;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.controls.LayersControl;
import com.vaadin.addon.leaflet4vaadin.controls.LayersControlOptions;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.groups.LayerGroup;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.raster.TileLayer;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.Icon;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Layers control example")
@Route(value = "controls/layerscontrol", layout = LeafletDemoApp.class)
public class LayersControlExample extends ExampleContainer {

    private static Icon CUSTOM_ICON = new Icon("images/marker-icon-demo.png", 41);
    
    @Override
    protected void initDemo() {

        MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.2041015625));
        options.setZoom(7);
        options.setAttributionControl(false);
        options.setZoomControl(false);
        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        // Initialize the layers control

        LayersControlOptions layersControlOptions = new LayersControlOptions();
        layersControlOptions.setCollapsed(false);
        LayersControl layersControl = new LayersControl(layersControlOptions);
        layersControl.addTo(leafletMap);

        // Add base layers

        TileLayer openStreetmap = new TileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
        openStreetmap.setSubdomains("1");
        layersControl.addBaseLayer(openStreetmap, "OpenStreetmap default");

        TileLayer wikimedia = new TileLayer("https://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png");
        wikimedia.setAttribution("Wikimedia Maps");
        layersControl.addBaseLayer(wikimedia, "Wikimedia Maps");

        // Add overlays
        
        LayerGroup layerGroup = createRandomMarkers(Icon.DEFAULT_ICON, 50);
        layerGroup.addTo(leafletMap);
        layersControl.addOverlay(layerGroup, "Simple markers");
        
        LayerGroup customGroup = createRandomMarkers(CUSTOM_ICON, 100);
        customGroup.addTo(leafletMap);
        layersControl.addOverlay(customGroup, "Custom markers");

        addToContent(leafletMap);
    }

    private LayerGroup createRandomMarkers(Icon icon, int limit) {
        LayerGroup layerGroup = new LayerGroup();
        range(0, limit).forEach((i) -> {
            double lat = (Math.random() * 4) + 45;
            double lon = (Math.random() * 7) + 16;
            Marker marker = new Marker(latlng(lat, lon));
            marker.setIcon(icon);
            marker.bindPopup("Hi, My location is:</br> latitude: " + lat + "</br>longitude: " + lon);
            marker.addTo(layerGroup);
        });
        return layerGroup;
    }

}
