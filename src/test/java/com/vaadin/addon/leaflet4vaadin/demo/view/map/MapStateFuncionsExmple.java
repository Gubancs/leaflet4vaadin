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
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Map state functions")
@Route(value = "map/statefunctions", layout = LeafletDemoApp.class)
public class MapStateFuncionsExmple extends ExampleContainer {

    private LeafletMap leafletMap;
    private TextField centerTextField;
    private TextField zoomTextField;
    private TextField sizeTextField;
    private TextArea boundsTextArea;
    private TextArea pixelBoundsTextArea;

    @Override
    protected void initDemo() {
        final MapOptions options = new DefaultMapOptions();
        options.setCenter(new LatLng(47.070121823, 19.204101562500004));
        options.setZoom(7);
        leafletMap = new LeafletMap(options);
        leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");

        leafletMap.whenReady((event) -> refreshState());
        leafletMap.onMoveEnd((event) -> refreshState());
        leafletMap.onZoomEnd((event) -> refreshState());

        addToContent(leafletMap);

        centerTextField = new TextField("Center");
        centerTextField.setWidthFull();
        centerTextField.setReadOnly(true);

        zoomTextField = new TextField("Zoom");
        zoomTextField.setWidthFull();
        zoomTextField.setReadOnly(true);

        sizeTextField = new TextField("Size");
        sizeTextField.setWidthFull();
        sizeTextField.setReadOnly(true);

        boundsTextArea = new TextArea("Bounds");
        boundsTextArea.setWidthFull();
        boundsTextArea.setReadOnly(true);

        pixelBoundsTextArea = new TextArea("Pixel bounds");
        pixelBoundsTextArea.setWidthFull();
        pixelBoundsTextArea.setReadOnly(true);

        addToSidebar(centerTextField, zoomTextField, sizeTextField, boundsTextArea, pixelBoundsTextArea);
    }

    private void refreshState() {
        if (leafletMap.isReady()) {
            leafletMap.getCenter().thenAccept((center) -> {
                centerTextField.setValue(center.toString());
            });
            leafletMap.getZoom().thenAccept((zoom) -> {
                zoomTextField.setValue(zoom.toString());
            });
            leafletMap.getSize().thenAccept((size) -> {
                sizeTextField.setValue(size.toString());
            });
            leafletMap.getBounds().thenAccept((bounds) -> {
                boundsTextArea.setValue(bounds.toString());
            });
            leafletMap.getPixelBounds().thenAccept((pixelBounds) -> {
                pixelBoundsTextArea.setValue(pixelBounds.toString());
            });
        }
    }

}
