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
 * Represents a point with x and y coordinates in pixels.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-07
 * @version 1.0
 */
public class Point implements LeafletClass {

	private static final long serialVersionUID = -4978055088391693282L;
	private Double x;
	private Double y;

	public Point(){
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the cartesian distance between the current and the given points.
	 * 
	 * @param target a valid point
	 * @return the distance between the current and the given point
	 */
	public double distanceTo(Point target) {
		if (target == null) {
			return -1;
		}
		double dx = target.x - this.x;
		double dy = target.y - this.y;

		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Returns `true` if both coordinates of the given point are less than the
	 * corresponding current point coordinates (in absolute values).
	 * 
	 * @param point a valid point
	 * @return true if both coordiantes of the given point are less than current
	 *         point coordinates
	 */
	public boolean contains(Point point) {
		if (point == null) {
			return false;
		}
		return Math.abs(point.x) <= Math.abs(this.x) && Math.abs(point.y) <= Math.abs(this.y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public static Point of(double x, double y) {
		return new Point(x, y);
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}
