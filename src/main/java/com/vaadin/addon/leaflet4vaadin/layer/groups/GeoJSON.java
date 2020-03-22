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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vaadin.addon.leaflet4vaadin.layer.HasStyle;
import com.vaadin.addon.leaflet4vaadin.layer.Identifiable;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSONOptions.OnEachFeatureHandler;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSONOptions.PointToLayerHandler;
import com.vaadin.addon.leaflet4vaadin.layer.groups.GeoJSONOptions.StyleHandler;
import com.vaadin.addon.leaflet4vaadin.layer.ui.marker.Marker;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Polygon;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.Polyline;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.GeometryCollection;
import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.geojson.MultiLineString;
import org.geojson.MultiPoint;
import org.geojson.MultiPolygon;
import org.geojson.Point;

/**
 * Represents a GeoJSON object or an array of GeoJSON objects. Allows you to
 * parse GeoJSON data and display it on the map. Extends FeatureGroup.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-22
 * @version 1.0
 */
public class GeoJSON extends FeatureGroup implements GeoJSONFunctions {

    private static final long serialVersionUID = -7574772572305688052L;

    private final GeoJsonObject geoJson;
    private final GeoJSONOptions options;
    private final Map<Identifiable, Feature> layerFeatureMap = new HashMap<>();

    public GeoJSON(GeoJsonObject geoJson) {
        this(geoJson, new GeoJSONOptions());
    }

    public GeoJSON(GeoJsonObject geoJson, GeoJSONOptions options) {
        this.geoJson = geoJson;
        this.options = options;
        this.addData(geoJson);
    }

    @Override
    public void addData(GeoJsonObject geoJsonObject) {
        if (geoJsonObject instanceof FeatureCollection) {
            FeatureCollection featureCollection = (FeatureCollection) geoJsonObject;
            featureCollection.getFeatures().forEach((feature) -> addData(feature));
            return;
        }

        if (options.filter() != null && !options.filter().test(geoJsonObject)) {
            return;
        }

        Layer layer = GeoJSON.geometryToLayer(geoJsonObject, options);
        if (layer == null) {
            return;
        }

        Feature feature = asFeature(geoJson);
        layerFeatureMap.put(layer, feature);

        if (options.style() != null) {
            setLayerStyle(layer, options.style());
        }

        if (options.onEachFeature() != null) {
            OnEachFeatureHandler handler = options.onEachFeature();
            handler.onEachFeature(feature, layer);
        }

        layer.addTo(this);
    }

    private void setLayerStyle(Layer layer, StyleHandler styleHandler) {
        if (layer instanceof HasStyle) {
            Feature feature = layerFeatureMap.get(layer);
            ((HasStyle) layer).setStyle(styleHandler.style(feature));
        }
    }

    /**
     * @return the geoJson
     */
    public GeoJsonObject getGeoJson() {
        return geoJson;
    }

    /**
     * Normalize GeoJSON geometries/features into GeoJSON features.
     * 
     * @param the geoJson to be converted to Feature
     */
    public static Feature asFeature(GeoJsonObject geoJson) {
        Feature feature = new Feature();
        feature.setGeometry(geoJson);
        return feature;
    }

    /**
     * Creates a Layer from a given GeoJsonObject object. Can use a custom
     * pointToLayer and/or coordsToLatLng functions if provided as options.
     * 
     * @param geoJsonObject the geojson to convert
     * @param options       a custom options
     * 
     * @see GeoJSONOptions
     */
    public static Layer geometryToLayer(GeoJsonObject geoJsonObject, GeoJSONOptions options) {
        Layer layer = null;

        if (geoJsonObject instanceof Feature) {
            Feature feature = (Feature) geoJsonObject;
            return geometryToLayer(feature.getGeometry(), options);
        } else if (geoJsonObject instanceof Point) {
            Point point = (Point) geoJsonObject;
            LngLatAlt coordinates = point.getCoordinates();
            LatLng latLng = new LatLng(coordinates.getLatitude(), coordinates.getLongitude());
            layer = pointToLayer(options.pointToLayer(), point, latLng);
        } else if (geoJsonObject instanceof MultiPoint) {
            MultiPoint multiPoint = (MultiPoint) geoJsonObject;
            FeatureGroup featureGroup = new FeatureGroup();
            multiPoint.getCoordinates().stream().map(GeoJSON::coordinateToLatLng)
                    .map((latLng) -> pointToLayer(options.pointToLayer(), multiPoint, latLng))
                    .forEach((l) -> l.addTo(featureGroup));
            layer = featureGroup;
        } else if (geoJsonObject instanceof LineString) {
            LineString lineString = (LineString) geoJsonObject;
            List<LatLng> latLngs = lineString.getCoordinates().stream().map(GeoJSON::coordinateToLatLng)
                    .collect(Collectors.toList());
            layer = new Polyline(latLngs);
        } else if (geoJsonObject instanceof MultiLineString) {
            // FIXME not implemented yet
        } else if (geoJsonObject instanceof org.geojson.Polygon) {
            org.geojson.Polygon polygon = (org.geojson.Polygon) geoJsonObject;
            List<LatLng> latLngs = polygon.getExteriorRing().stream().map(GeoJSON::coordinateToLatLng)
                    .collect(Collectors.toList());
            layer = new Polygon(latLngs);
        } else if (geoJsonObject instanceof MultiPolygon) {
            // FIXME not implemented yet
        } else if (geoJsonObject instanceof GeometryCollection) {
            FeatureGroup geometryFeatureGroup = new FeatureGroup();
            GeometryCollection geometryCollection = (GeometryCollection) geoJsonObject;
            geometryCollection.getGeometries().stream().map((geometry) -> geometryToLayer(geometry, options))
                    .forEach(geometryLayer -> geometryLayer.addTo(geometryFeatureGroup));
            layer = geometryFeatureGroup;
        }

        return layer;
    }

    public static List<LatLng> coordinateToLatLng(List<LngLatAlt> latAlt) {
        return latAlt.stream().map(GeoJSON::coordinateToLatLng).collect(Collectors.toList());
    }

    public static LatLng coordinateToLatLng(LngLatAlt latAlt) {
        return new LatLng(latAlt.getLatitude(), latAlt.getLongitude());
    }

    private static Layer pointToLayer(PointToLayerHandler pointToLayerHandler, GeoJsonObject geoJson, LatLng latLng) {
        if (pointToLayerHandler != null) {
            return pointToLayerHandler.pointToLayer(geoJson, latLng);
        } else {
            return new Marker(latLng); // TODO inherit marker options
        }
    }
}
