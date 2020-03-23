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

package com.vaadin.addon.leaflet4vaadin.layer.groups;

import java.io.Serializable;
import java.util.function.Predicate;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

import org.geojson.Feature;
import org.geojson.GeoJsonObject;
import org.geojson.LngLatAlt;

/**
 * GeoJSON methods
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-22
 * @version 1.0
 */
public class GeoJSONOptions implements Serializable {

    private static final long serialVersionUID = -873141420455203067L;
    private boolean markersInheritOptions = false;
    private transient Predicate<GeoJsonObject> filter;
    private transient StyleHandler styleHandler;
    private transient PointToLayerHandler pointToLayerHandler;
    private transient OnEachFeatureHandler onEachFeatureHandler;
    private transient CoordsToLatLngHandler coordsToLatLngHandler;

    public GeoJSONOptions() {
    }

    /**
     * @return the coordsToLatLngHandler
     */
    CoordsToLatLngHandler coordsToLatLngHandler() {
        return coordsToLatLngHandler;
    }

    /**
     * A Function that will be used for converting GeoJSON coordinates to LatLngs.
     * The default is the coordsToLatLng static method.
     * 
     * @param coordsToLatLngHandler the coordsToLatLngHandler to set
     */
    public void setCoordsToLatLngHandler(CoordsToLatLngHandler coordsToLatLngHandler) {
        this.coordsToLatLngHandler = coordsToLatLngHandler;
    }

    /**
     * @return the markersInheritOptions
     */
    boolean isMarkersInheritOptions() {
        return markersInheritOptions;
    }

    /**
     * Whether default Markers for "Point" type Features inherit from group options.
     * 
     * @param markersInheritOptions the markersInheritOptions to set
     */
    public void setMarkersInheritOptions(boolean markersInheritOptions) {
        this.markersInheritOptions = markersInheritOptions;
    }

    /**
     * @return the styleHandler
     */
    StyleHandler style() {
        return styleHandler;
    }

    /**
     * A Function defining the Path options for styling GeoJSON lines and polygons,
     * called internally when data is added. The default value is to not override
     * any defaults:
     * 
     * @param styleHandler the styleHandler to set
     */
    public void style(StyleHandler styleHandler) {
        this.styleHandler = styleHandler;
    }

    /**
     * A Function defining how GeoJSON points spawn Leaflet layers. It is internally
     * called when data is added, passing the GeoJSON point feature and its LatLng.
     * The default is to spawn a default Marker:
     * 
     * @return the pointToLayerHandler
     */
    PointToLayerHandler pointToLayer() {
        return pointToLayerHandler;
    }

    /**
     * @param pointToLayerHandler the pointToLayerHandler to set
     */
    public void pointToLayer(PointToLayerHandler pointToLayerHandler) {
        this.pointToLayerHandler = pointToLayerHandler;
    }

    /**
     * @return the filter
     */
    Predicate<GeoJsonObject> filter() {
        return filter;
    }

    /**
     * A Function that will be used to decide whether to include a feature or not.
     * The default is to include all features: Note: dynamically changing the filter
     * option will have effect only on newly added data. It will not re-evaluate
     * already included features.
     * 
     * @param filter the filter to set
     */
    public void filter(Predicate<GeoJsonObject> filter) {
        this.filter = filter;
    }

    /**
     * @return the onEachFeatureHandler
     */
    OnEachFeatureHandler onEachFeature() {
        return onEachFeatureHandler;
    }

    /**
     * A Function that will be called once for each created Feature, after it has
     * been created and styled. Useful for attaching events and popups to features.
     * The default is to do nothing with the newly created layers:
     * 
     * @param onEachFeatureHandler the onEachFeatureHandler to set
     */
    public void onEachFeature(OnEachFeatureHandler onEachFeatureHandler) {
        this.onEachFeatureHandler = onEachFeatureHandler;
    }

    @FunctionalInterface
    public static interface PointToLayerHandler {
        Layer pointToLayer(GeoJsonObject geoJson, LatLng latLng);
    }

    @FunctionalInterface
    public static interface OnEachFeatureHandler {
        void onEachFeature(Feature feature, Layer layer);
    }

    @FunctionalInterface
    public static interface StyleHandler {
        PathOptions style(Feature feature);
    }

    /**
     * Callback function that will be used for converting GeoJSON coordinates to
     * LatLngs.
     */
    @FunctionalInterface
    public static interface CoordsToLatLngHandler {
        LatLng convert(LngLatAlt coordinate);
    }
}
