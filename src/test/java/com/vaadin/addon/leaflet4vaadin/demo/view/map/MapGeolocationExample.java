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

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Circle;
import com.vaadin.addon.leaflet4vaadin.options.LocateOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Locate user")
@Route(value = "map/locate-user", layout = LeafletDemoApp.class)
public class MapGeolocationExample extends ExampleContainer {

    private static final long serialVersionUID = -3956839422711312002L;

    private Marker userLocation;
    private Circle circle;

    @Override
    protected void initDemo() {

        Button locateButton = new Button("Find my location");

        // leaflet map
        final MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.204101562500004));
        options.setZoom(7);
        final LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        leafletMap.onLocationError((event) -> {
            Notification.show("Unable to locate your location: " + event.getMessage(), 2000, Position.TOP_CENTER);
            locateButton.setEnabled(true);
        });

        leafletMap.onLocationFound((event) -> {
            userLocation = new Marker(event.getLatlng());
            userLocation.bindTooltip("Your approximate location. Accuracy: " + event.getAccuracy() + " meters.");
            userLocation.addTo(leafletMap);
            circle = new Circle(event.getLatlng(), event.getAccuracy());
            circle.addTo(leafletMap);
            locateButton.setEnabled(true);
        });

        // toolbar
        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setWidthFull();
        locateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        locateButton.addClickListener((event) -> {
            if (userLocation != null) {
                leafletMap.removeLayer(userLocation);
            }
            if (circle != null) {
                leafletMap.removeLayer(circle);
            }
            locateButton.setEnabled(false);
            LocateOptions locateOptions = new LocateOptions();
            locateOptions.setSetView(true);
            locateOptions.setEnableHighAccuracy(true);
            leafletMap.locate(locateOptions);
        });
        toolbar.add(locateButton);

        addToContent(locateButton, leafletMap);
    }

}
