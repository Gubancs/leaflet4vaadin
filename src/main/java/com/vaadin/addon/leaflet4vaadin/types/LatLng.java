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

package com.vaadin.addon.leaflet4vaadin.types;

/**
 * Represents a geographical point with a certain latitude and longitude.
 * 
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-21
 * @version 1.0
 */
public class LatLng implements LeafletClass {
    private static final long serialVersionUID = 8519525431224154852L;
    private double lng;
    private double lat;
    private double altitude;

    public LatLng() {
    }

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LatLng(double lat, double lng, double altitude) {
        this(lat, lng);
        this.altitude = altitude;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return lat + "," + lng;
    }

    public static LatLng latlng(double lat, double lng) {
        return new LatLng(lat, lng);
    }

    public static LatLng latlng(double lat, double lng, double altitude) {
        return new LatLng(lat, lng, altitude);
    }

}
