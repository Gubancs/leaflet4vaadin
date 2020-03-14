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

import com.github.appreciated.app.layout.annotations.Caption;
import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.CircleMarker;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Caption("Map poll listener")
@Route(value = "map/polling", layout = LeafletDemoApp.class)
public class MapPollListenerExample extends ExampleContainer {

    @Override
    protected void initMap(Div mapContainer) {

        MapOptions options = new DefaultMapOptions();
        options.setCenter(latlng(47.070121823, 19.2041015625));
        options.setZoom(7);
        options.setPreferCanvas(true);
        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
        refresh(leafletMap);
        UI.getCurrent().setPollInterval(3000);
        UI.getCurrent().addPollListener((pollEvent) -> refresh(leafletMap));

        mapContainer.add(leafletMap);
    }

    private void refresh(LeafletMap leafletMap) {
        leafletMap.removeAllLayers();
        for (int i = 0; i < 50; i++) {
            int radius = (int) (Math.random() * 10);
            double lat = (Math.random() * 4) + 45;
            double lon = (Math.random() * 7) + 16;
            CircleMarker circleMarker = new CircleMarker(latlng(lat, lon), radius);
            circleMarker.setWeight(0);
            circleMarker.setFillOpacity(Math.random());
            circleMarker.addTo(leafletMap);
        }
    }

}
