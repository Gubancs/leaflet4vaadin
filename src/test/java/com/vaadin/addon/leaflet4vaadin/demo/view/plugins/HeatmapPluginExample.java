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

package com.vaadin.addon.leaflet4vaadin.demo.view.plugins;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.plugins.HeatLayer;
import com.vaadin.addon.leaflet4vaadin.plugins.HeatLayerOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Heatmap example")
@Route(value = "plugins/heatmap", layout = LeafletDemoApp.class)
public class HeatmapPluginExample extends ExampleContainer {

    private Binder<HeatLayerOptions> binder;
    private HeatLayer heatLayer;

    @Override
    protected void initDemo() {
        MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.204101562500004));
        options.setZoom(7);

        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        heatLayer = new HeatLayer();
        heatLayer.setLatLngs(randomLatLngs());
        heatLayer.addTo(leafletMap);
        leafletMap.onMouseMove((e) -> {
            e.getLatLng().setAltitude(0.1);
            heatLayer.addLatLng(e.getLatLng());
        });
        addToContent(leafletMap);
        
        binder = new Binder<>(HeatLayerOptions.class);
        createFormControls();
        binder.setBean(new HeatLayerOptions());

        binder.addValueChangeListener((event) -> heatLayer.setOptions(binder.getBean()));
        binder.addStatusChangeListener((event) -> heatLayer.setOptions(binder.getBean()));
    }

    private void createFormControls() {
        // Stroke control
        FormLayout form = new FormLayout();

     //  the minimum opacity the heat will start at
        NumberField minOpacity = new NumberField();
        minOpacity.setHasControls(true);
        minOpacity.setMin(0);
        minOpacity.setMax(1);
        minOpacity.setStep(0.1);
        minOpacity.setWidthFull();
        minOpacity.setValueChangeMode(ValueChangeMode.EAGER);
        form.addFormItem(minOpacity, "Minimum opacity");
        binder.forField(minOpacity).bind("minOpacity");
        
        // maximum point intensity,
        NumberField max = new NumberField();
        max.setHasControls(true);
        max.setMin(0);
        max.setMax(1);
        max.setStep(0.1);
        max.setWidthFull();
        max.setValueChangeMode(ValueChangeMode.EAGER);
        form.addFormItem(max, "Maximum point intensity");
        binder.forField(max).bind("max");
        
     // radius of each "point" of the heatmap,
        NumberField radius = new NumberField();
        radius.setHasControls(true);
        radius.setMin(1);
        radius.setMax(100);
        radius.setStep(5);
        radius.setWidthFull();
        radius.setValueChangeMode(ValueChangeMode.EAGER);
        form.addFormItem(radius, "Radius of each point");
        binder.forField(radius).bind("radius");
        
        // amount of blur
        NumberField blur = new NumberField();
        blur.setHasControls(true);
        blur.setMin(0);
        blur.setMax(100);
        blur.setStep(5);
        blur.setWidthFull();
        blur.setValueChangeMode(ValueChangeMode.EAGER);
        form.addFormItem(blur, "Amount of blur");
        binder.forField(blur).bind("blur");

        Button reset = new Button("Reset to defaults");
        reset.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        reset.addClickListener((event) -> {
            binder.setBean(new HeatLayerOptions());
        });
        form.add(reset);
        
        Button clear = new Button("Clear the map");
        clear.addThemeVariants(ButtonVariant.LUMO_ERROR);
        clear.addClickListener((event) -> heatLayer.setLatLngs(new ArrayList<>()));
        form.add(clear);
        
        addToSidebar(form);
    }
    

    private static List<LatLng> randomLatLngs() {
        List<LatLng> heatmapData = new ArrayList<>();
        for (int i = 0; i++ < 100;) {
            double altitude = Math.random();
            double lat = (Math.random() * 4) + 45;
            double lng = (Math.random() * 7) + 16;
            heatmapData.add(LatLng.latlng(lat, lng, altitude));
        }
        return heatmapData;
    }

}
