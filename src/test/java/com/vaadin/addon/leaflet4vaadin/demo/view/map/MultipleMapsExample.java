package com.vaadin.addon.leaflet4vaadin.demo.view.map;

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;
import static java.util.stream.IntStream.range;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.groups.LayerGroup;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.types.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Multiple maps")
@Route(value = "map/multiple", layout = LeafletDemoApp.class)
public class MultipleMapsExample extends ExampleContainer {

    private static Icon CUSTOM_ICON = new Icon("images/marker-icon-demo.png", 41);
    
    @Override
    protected void initDemo() {
        MapOptions options = new DefaultMapOptions();
        options.setCenter(latlng(47.070121823, 19.2041015625));
        options.setZoom(7);
        options.setPreferCanvas(true);

        LeafletMap leafletMapLeft = new LeafletMap(options);
        leafletMapLeft.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
        leafletMapLeft.addLayer(createRandomMarkers(Icon.DEFAULT_ICON, 30));

        LeafletMap leafletMapRight = new LeafletMap(options);
        leafletMapRight.setBaseUrl("https://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png");
        leafletMapRight.addLayer(createRandomMarkers(CUSTOM_ICON, 30));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.add(leafletMapLeft, leafletMapRight);
        addToContent(horizontalLayout);
    }

    private LayerGroup createRandomMarkers(Icon icon, int limit) {
        LayerGroup layerGroup = new LayerGroup();
        range(0, limit).forEach((i) -> {
            double lat = (Math.random() * 4) + 45;
            double lon = (Math.random() * 7) + 16;
            Marker marker = new Marker(latlng(lat, lon));
            marker.setIcon(icon);
            marker.addTo(layerGroup);
        });
        return layerGroup;
    }
}
