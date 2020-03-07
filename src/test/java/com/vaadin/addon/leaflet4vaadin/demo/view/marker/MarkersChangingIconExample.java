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

import com.github.appreciated.app.layout.annotations.Caption;
import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Caption("Changing the marker icons")
@Route(value = "marker/changing-the-icons", layout = LeafletDemoApp.class)
public class MarkersChangingIconExample extends ExampleContainer {

    @Override
    protected void initMap(Div mapContainer) {

        MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.2041015625));
        options.setZoom(7);
        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        Marker marker = new Marker(new LatLng(46.470121823, 18.3041015625));
        marker.bindTooltip("Hey, I'm a draggable marker with custom icon");
        marker.setDraggable(true);
        com.vaadin.addon.leaflet4vaadin.types.Icon icon = new com.vaadin.addon.leaflet4vaadin.types.Icon(
                "images/marker-icon-demo.png", 41);
        marker.setIcon(icon);
        marker.addTo(leafletMap);

        mapContainer.add(leafletMap);
    }

}
