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

public class LatLng implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 8519525431224154852L;
	private double lon;
	private double lat;

	public LatLng() {
	}

	public LatLng(double lat, double lon) {
		this.lon = lon;
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return lat + "," + lon;
	}

	public static LatLng latlng(double lat, double lon) {
		return new LatLng(lat, lon);
	}
}
