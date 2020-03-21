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

/**
 * Represents a rectangular area in pixel coordinates.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-21
 * @version 1.0
 */
public class Bounds implements LeafletClass {
    private static final long serialVersionUID = 181166727523469617L;
    private Point max = new Point();
    private Point min = new Point();

    public Bounds() {
        max.setX(Double.MAX_VALUE);
        max.setY(Double.MAX_VALUE);
        min.setX(Double.NEGATIVE_INFINITY);
        min.setY(Double.NEGATIVE_INFINITY);
    }

    public Bounds(Point... point) {
        this();
        extend(point);
    }

    /**
     * Returns the bottom-left point of the bounds.
     * 
     * @return the bottom-left point of the bounds.
     */
    public Point getBottomLeft() {
        return new Point(this.min.getX(), this.max.getY());
    }

    /**
     * Returns the bottom-left point of the bounds.
     * 
     * @return the bottom-left point of the bounds.
     */
    public Point getTopRight() {
        return new Point(this.max.getX(), this.min.getY());
    }

    /**
     * Returns the top-left point of the bounds
     * 
     * @return the top-left point of the bounds
     */
    public Point getTopLeft() {
        return this.min;
    }

    /**
     * Returns the bottom-right point of the bounds
     * 
     * @return the bottom-right point of the bounds
     */
    public Point getBottomRight() {
        return this.max;
    }

    public void extend(Point... points) {
        for (Point p : points) {
            extend(p.getX(), p.getY());
        }
    }

    public void extend(double x, double y) {
        max.setX(max(max.getX(), x));
        max.setY(max(max.getY(), y));
        min.setX(min(min.getX(), x));
        min.setY(min(min.getY(), y));
    }

    public Point getCenter() {
        double x = (this.min.getX() + this.max.getY()) / 2;
        double y = (this.min.getX() + this.max.getY()) / 2;
        return new Point(x, y);
    }

}
