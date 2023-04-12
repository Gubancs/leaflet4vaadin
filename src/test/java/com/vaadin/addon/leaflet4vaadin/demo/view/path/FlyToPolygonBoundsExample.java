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
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Polygon;
import com.vaadin.addon.leaflet4vaadin.options.FitBoundsOptions;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Fly to bounds")
@Route(value = "path/fly-to-bounds", layout = LeafletDemoApp.class)
public class FlyToPolygonBoundsExample extends ExampleContainer {

    private static final long serialVersionUID = -1011084083592421402L;

    private LeafletMap leafletMap;
    private Polygon polygon;

    @Override
    protected void initDemo() {

        MapOptions options = new DefaultMapOptions();
        options.setCenter(latlng(47.070121823, 19.2041015625));
        options.setZoom(7);
        options.setPreferCanvas(true);
        leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        polygon = new Polygon(latlng(47.0, 17.3), latlng(47.3, 18.42), latlng(47.3, 18.82), latlng(47.5, 17.82), latlng(47.0, 17.3));
        polygon.onClick((event) -> leafletMap.flyToBounds(polygon.getBounds()));
        polygon.addTo(leafletMap);

        addToContent(leafletMap);

        createFormControls();
    }

    private void createFormControls() {
        IntegerField maxZoom = new IntegerField("Max zoom:");
        maxZoom.setStepButtonsVisible(true);
        maxZoom.setValue(6);
        maxZoom.setWidthFull();

        Button flyToBounds = new Button("Fly to bounds");
        flyToBounds.addClickListener((event) -> {
            FitBoundsOptions fitBoundsOptions = new FitBoundsOptions();
            fitBoundsOptions.setMaxZoom(maxZoom.getValue());
            leafletMap.flyToBounds(polygon.getBounds(), fitBoundsOptions);
        });

        Button fitToBounds = new Button("Fit to bounds");
        fitToBounds.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        fitToBounds.addClickListener((event) -> {
            FitBoundsOptions fitBoundsOptions = new FitBoundsOptions();
            fitBoundsOptions.setMaxZoom(maxZoom.getValue());
            leafletMap.fitBounds(polygon.getBounds(), fitBoundsOptions);
        });

        addToSidebar(maxZoom, flyToBounds, fitToBounds);
    }

}
