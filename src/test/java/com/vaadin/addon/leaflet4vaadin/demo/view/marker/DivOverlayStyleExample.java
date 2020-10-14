package com.vaadin.addon.leaflet4vaadin.demo.view.marker;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.demo.LeafletDemoApp;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.DefaultMapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.map.options.MapOptions;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.layer.ui.popup.Popup;
import com.vaadin.addon.leaflet4vaadin.layer.ui.tooltip.Tooltip;
import com.vaadin.addon.leaflet4vaadin.types.DivIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.vaadin.addon.leaflet4vaadin.types.LatLng.latlng;
import static java.util.stream.IntStream.range;


@PageTitle("DivOverlay styling")
@Route(value = "marker/div-overlay-styling", layout = LeafletDemoApp.class)
public class DivOverlayStyleExample extends ExampleContainer {

    @Override
    protected void initDemo() {

        MapOptions options = new DefaultMapOptions();
        options.setCenter(latlng(47.070121823, 19.2041015625));
        options.setZoom(7);
        LeafletMap leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        Marker marker = new Marker(options.getCenter());
        Popup popup = new Popup("Hi, I have custom CSS class, just press F12 and inspect the DOM to make sure.");
        popup.setClassName("custom-popup-css");
        popup.setCloseOnClick(true);
        popup.setCloseOnEscapeKey(true);
        marker.bindPopup(popup);

        marker.addTo(leafletMap);

        addToContent(leafletMap);
    }
}
