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

package com.vaadin.addon.leaflet4vaadin.layer.events;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LocationEventType;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * Represents the leaflet LocationEvent
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-11
 * @version 1.0
 */
public class LocationEvent extends LeafletEvent {

    private final LatLng latlng;
    private final LatLngBounds bounds;
    private final Double accuracy;
    private final Double altitude;
    private final Double altitudeAccuracy;
    private final Double heading;
    private final Double speed;
    private final Double timestamp;

    public LocationEvent(Layer layer, LocationEventType eventType, LatLng latlng, LatLngBounds bounds, Double accuracy,
            Double altitude, Double altitudeAccuracy, Double heading, Double speed, Double timestamp) {
        super(layer, eventType);
        this.latlng = latlng;
        this.bounds = bounds;
        this.accuracy = accuracy;
        this.altitude = altitude;
        this.altitudeAccuracy = altitudeAccuracy;
        this.heading = heading;
        this.speed = speed;
        this.timestamp = timestamp;
    }

    /**
     * Detected geographical location of the user.
     * 
     * @return the latlng
     */
    public LatLng getLatlng() {
        return latlng;
    }

    /**
     * Geographical bounds of the area user is located in (with respect to the
     * accuracy of location).
     * 
     * @return the bounds
     */
    public LatLngBounds getBounds() {
        return bounds;
    }

    /**
     * Accuracy of location in meters.
     * 
     * @return the accuracy
     */
    public Double getAccuracy() {
        return accuracy;
    }

    /**
     * Height of the position above the WGS84 ellipsoid in meters.
     * 
     * @return the altitude
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * Accuracy of altitude in meters.
     * 
     * @return the altitudeAccuracy
     */
    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    /**
     * The direction of travel in degrees counting clockwise from true North.
     * 
     * @return the heading
     */
    public Double getHeading() {
        return heading;
    }

    /**
     * Current velocity in meters per second.
     * 
     * @return the speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * The time when the position was acquired.
     * 
     * @return the timestamp
     */
    public Double getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "LocationEvent [type=" + super.getType() + ", accuracy=" + accuracy + ", altitude=" + altitude
                + ", altitudeAccuracy=" + altitudeAccuracy + ", bounds=" + bounds + ", heading=" + heading + ", latlng="
                + latlng + ", speed=" + speed + ", timestamp=" + timestamp + "]";
    }
}
