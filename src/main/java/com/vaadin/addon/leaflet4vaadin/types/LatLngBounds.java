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

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LatLngBounds implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7421430446913242834L;
	private double southWestLng;
	private double southWestLat;
	private double northEastLng;
	private double northEastLat;

	public LatLngBounds(LatLng... points) {
		this(Arrays.asList(points));
	}

	public LatLngBounds(List<LatLng> points) {
		this(points.get(0));
		points.forEach(p -> extend(p));
	}

	public LatLngBounds(LatLng point) {
		setSouthWestLat(point.getLat());
		setSouthWestLon(point.getLon());
		setNorthEastLat(point.getLat());
		setNorthEastLon(point.getLon());
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

	public void extend(LatLng... points) {
		for (LatLng point : points) {
			setNorthEastLat(Math.max(getNorthEastLat(), point.getLat()));
			setNorthEastLon(Math.max(getNorthEastLon(), point.getLon()));
			setSouthWestLat(Math.min(getSouthWestLat(), point.getLat()));
			setSouthWestLon(Math.min(getSouthWestLon(), point.getLon()));
		}
	}

	public LatLng getCenter() {
		return new LatLng((getNorthEastLat() + getSouthWestLat()) / 2, (getNorthEastLon() + getSouthWestLon()) / 2);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
