package com.vaadin.addon.leaflet4vaadin.demo.view.map;

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Map in dialog")
@Route(value = "map/dialog", layout = LeafletDemoApp.class)
public class MapDialogExample extends ExampleContainer {

    @Override
    protected void initDemo() {
        Button openDialog = new Button("Open dialog");
        openDialog.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        openDialog.addClickListener((event) -> {
            Dialog dialog = new Dialog();
            dialog.setWidth("600px");
            dialog.setHeight("400px");

            //Initialize map
            
            MapOptions options = new DefaultMapOptions();
            options.setCenter(latlng(47.070121823, 19.2041015625));
            options.setZoom(7);
            options.setPreferCanvas(true);
            
            LeafletMap leafletMap = new LeafletMap(options);
            leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
            dialog.add(leafletMap);
            
            dialog.open();
        });
        addToContent(openDialog);
    }

}
