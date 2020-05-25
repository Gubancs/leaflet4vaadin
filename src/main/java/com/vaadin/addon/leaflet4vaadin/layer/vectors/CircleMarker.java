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

package com.vaadin.addon.leaflet4vaadin.layer.vectors;

import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

/**
 * A circle of a fixed size with radius specified in pixels. Extends Path.
 */
public class CircleMarker extends Path {

    /**
     *
     */
    private static final long serialVersionUID = 8294051897899342825L;

    @LeafletArgument
    private final LatLng latlng;
    private final double radius;

    /**
     * Instantiates a circle marker object given a geographical point
     * 
     * @param latlng geographical point
     */
    public CircleMarker(LatLng latlng) {
        this(latlng, 10);
    }

    /**
     * Instantiates a circle marker object given a geographical point and a custom
     * radius in pixels
     * 
     * @param latlng geographical point
     * @param radius radius in pixels
     */
    public CircleMarker(LatLng latlng, double radius) {
        this.latlng = latlng;
        this.radius = radius;
    }

    /**
     * @return the latlng
     */
    public LatLng getLatlng() {
        return latlng;
    }

    /**
     * Radius of the circle marker, in pixels
     * 
     * @return the radius in pixels
     */
    public double getRadius() {
        return radius;
    }

}
