
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

import java.util.function.Supplier;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Circle;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.CircleMarker;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Path;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Polygon;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Polyline;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Rectangle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Type of paths")
@Route(value = "path/types", layout = LeafletDemoApp.class)
public class TypeOfPathsExample extends ExampleContainer {

    private static final long serialVersionUID = 6407154243967502379L;

    @Override
    protected void initMap(Div mapContainer) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();

        FormLayout sidebar = new FormLayout();
        sidebar.setHeightFull();
        sidebar.setWidth("400px");

        MapOptions options = new DefaultMapOptions();
        options.setCenter(latlng(47.070121823, 19.2041015625));
        options.setZoom(7);
        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        sidebar.add(createButton(this::polyline, leafletMap));
        sidebar.add(createButton(this::polygon, leafletMap));
        sidebar.add(createButton(this::rectangle, leafletMap));
        sidebar.add(createButton(this::circle, leafletMap));
        sidebar.add(createButton(this::circleMarker, leafletMap));

        layout.add(leafletMap, sidebar);
        mapContainer.add(layout);
    }

    private Button createButton(Supplier<Path> suplier, LeafletMap leafletMap) {
        Path path = suplier.get();
        Button btn = new Button(path.getClass().getSimpleName());
        btn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btn.addClickListener((event) -> {
            leafletMap.removeAllLayers();
            path.bindTooltip("Hi, I'am a " + path.getClass().getSimpleName());
            path.addTo(leafletMap);

        });
        return btn;
    }

    public Polyline polyline() {
        return new Polyline(latlng(47.2, 18.3), latlng(47.3, 17.42));
    }

    public Polygon polygon() {
        Polygon polygon = new Polygon(latlng(47.0, 17.3), latlng(47.3, 18.42), latlng(47.3, 18.82), latlng(47.5, 17.82),
                latlng(47.0, 17.3));
        polygon.setFillColor("red");
        polygon.setColor("red");
        polygon.setWeight(1.5);
        return polygon;
    }

    public Rectangle rectangle() {
        Rectangle rectangle = new Rectangle(latlng(45.5, 18.3), latlng(46.3, 18.92));
        rectangle.setFillColor("green");
        rectangle.setColor("green");
        rectangle.setWeight(1);
        return rectangle;
    }

    public Circle circle() {
        Circle circle = new Circle(latlng(47.070121823, 19.2041015625), 100_000); //radius in meters
        circle.setFillColor("yellow");
        circle.setColor("yellow");
        circle.setDashArray("5, 10");
        return circle;
    }

    public CircleMarker circleMarker() {
        return new CircleMarker(latlng(47.070121823, 19.2041015625), 100); // radius in pixels
    }
}
