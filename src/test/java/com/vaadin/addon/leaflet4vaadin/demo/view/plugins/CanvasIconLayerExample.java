package com.vaadin.addon.leaflet4vaadin.demo.view.plugins;

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.plugins.canvasiconlayer.CanvasIconLayer;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Canvas icon layer")
@Route(value = "plugins/canvasiconlayer", layout = LeafletDemoApp.class)
public class CanvasIconLayerExample extends ExampleContainer {

    private CanvasIconLayer canvasIconLayer;

    @Override
    protected void initDemo() {

        MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.204101562500004));
        options.setZoom(7);
        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        Anchor pluginRepository = new Anchor();
        pluginRepository.setHref("https://github.com/eJuke/Leaflet.Canvas-Markers");
        pluginRepository.setText("Leaflet.Canvas-Markers plugin: https://github.com/eJuke/Leaflet.Canvas-Markers");
        pluginRepository.setTarget("_blank");
        
        addToContent(pluginRepository, leafletMap);

        canvasIconLayer = new CanvasIconLayer();
        

        IntegerField count = new IntegerField();
        count.setValue(10);
        count.setWidthFull();
        count.setStepButtonsVisible(true);

        Button refreshButton = new Button("Refresh markers");
        refreshButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        refreshButton.setDisableOnClick(true);
        refreshButton.addClickListener((event) -> {
            canvasIconLayer.clearLayers();
            addRandomMarkers(count.getValue());
            canvasIconLayer.redraw();
            refreshButton.setEnabled(true);
        });
        
        addToSidebar(count, refreshButton);

        canvasIconLayer.onAdd((e)-> {
            addRandomMarkers(count.getValue());
        });
        canvasIconLayer.addTo(leafletMap);
    }


    private void addRandomMarkers(int count) {
        List<Marker> markers = new ArrayList<>();
        range(0, count).forEach((i) -> {
            double lat = (Math.random() * 4) + 45;
            double lon = (Math.random() * 7) + 16;
            Marker marker = new Marker(latlng(lat, lon));
            marker.bindPopup("Index: " + i);
            markers.add(marker);
        });
        canvasIconLayer.addMarkers(markers);
    }

}
