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

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Bounds implements LeafletClass {
    private static final long serialVersionUID = 181166727523469617L;
    private double southWestLng;
    private double southWestLat;
    private double northEastLng;
    private double northEastLat;

    public Bounds() {
        setSouthWestLat(Double.MAX_VALUE);
        setSouthWestLon(Double.MAX_VALUE);
        setNorthEastLat(Double.NEGATIVE_INFINITY);
        setNorthEastLon(Double.NEGATIVE_INFINITY);
    }

    public Bounds(Point... point) {
        this();
        extend(point);
    }

    public double getSouthWestLon() {
        return southWestLng;
    }

    public void setSouthWestLon(double southWestLng) {
        this.southWestLng = southWestLng;
    }

    public double getSouthWestLat() {
        return southWestLat;
    }

    public void setSouthWestLat(double southWestLat) {
        this.southWestLat = southWestLat;
    }

    public double getNorthEastLon() {
        return northEastLng;
    }

    public void setNorthEastLon(double northEastLng) {
        this.northEastLng = northEastLng;
    }

    public double getNorthEastLat() {
        return northEastLat;
    }

    public void setNorthEastLat(double northEastLat) {
        this.northEastLat = northEastLat;
    }

    public void extend(Point... points) {
        for (Point p : points) {
            extend(p.getX(), p.getY());
        }
    }

    public void extend(double latitude, double longitude) {
        setNorthEastLat(max(getNorthEastLat(), latitude));
        setNorthEastLon(max(getNorthEastLon(), longitude));
        setSouthWestLat(min(getSouthWestLat(), latitude));
        setSouthWestLon(min(getSouthWestLon(), longitude));
    }

    public Point getCenter() {
        double x = (getNorthEastLat() + getSouthWestLat()) / 2;
        double y = (getNorthEastLon() + getSouthWestLon()) / 2;
        return Point.of(x, y);
    }

}
