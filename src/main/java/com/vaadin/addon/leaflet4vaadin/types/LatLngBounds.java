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

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a rectangular geographical area on a map.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-21
 * @version 1.0
 */
public class LatLngBounds implements LeafletClass {
	private static final long serialVersionUID = -7421430446913242834L;

	@JsonProperty("_southWest")
	private LatLng southWest = new LatLng();
	@JsonProperty("_northEast")
	private LatLng northEast = new LatLng();

	public LatLngBounds() {
	}

	public LatLngBounds(LatLng... latlngs) {
		this(Arrays.asList(latlngs));
	}

	public LatLngBounds(List<LatLng> latlngs) {
		this(latlngs.get(0));
		latlngs.forEach(p -> extend(p));
	}

	public LatLngBounds(LatLng latlng) {
		southWest.setLat(latlng.getLat());
		southWest.setLng(latlng.getLng());
		northEast.setLat(latlng.getLat());
		northEast.setLng(latlng.getLng());
	}

	/**
	 * @param southWest the southWest to set
	 */
	public void setSouthWest(LatLng southWest) {
		this.southWest = southWest;
	}

	/**
	 * @param northEast the northEast to set
	 */
	public void setNorthEast(LatLng northEast) {
		this.northEast = northEast;
	}

	/**
	 * Returns the south-west point of the bounds.
	 * 
	 * @return the south-west point of the bounds.
	 */
	public LatLng getSouthWest() {
		return this.southWest;
	}

	/**
	 * Returns the south-east point of the bounds.
	 * 
	 * @return the south-east point of the bounds
	 */
	@JsonIgnore
	public LatLng getSouthEast() {
		return new LatLng(this.getSouth(), this.getEast());
	}

	/**
	 * Returns the north-east point of the bounds.
	 * 
	 * @return the north-east point of the bounds.
	 */
	public LatLng getNorthEast() {
		return this.northEast;
	}

	/**
	 * Returns the north-west point of the bounds.
	 * 
	 * @return Returns the north-west point of the bounds
	 */
	@JsonIgnore
	public LatLng getNorthWest() {
		return new LatLng(this.getNorth(), this.getWest());
	}

	/**
	 * Returns the west longitude of the bounds
	 * 
	 * @return the west longitude of the bounds
	 */
	@JsonIgnore
	public double getWest() {
		return this.southWest.getLng();
	}

	/**
	 * Returns the south latitude of the bounds
	 * 
	 * @return the south latitude of the bounds
	 */
	@JsonIgnore
	public double getSouth() {
		return this.southWest.getLat();
	}

	/**
	 * Returns the east longitude of the bounds
	 * 
	 * @return the east longitude of the bounds
	 */
	@JsonIgnore
	public double getEast() {
		return this.northEast.getLng();
	}

	/**
	 * Returns the north latitude of the bounds
	 * 
	 * @return the north latitude of the bounds
	 */
	@JsonIgnore
	public double getNorth() {
		return this.northEast.getLat();
	}

	/**
	 * Returns the center point of the bounds.
	 * 
	 * @return the center point of the bounds.
	 */
	@JsonIgnore
	public LatLng getCenter() {
		double lat = (this.southWest.getLat() + this.northEast.getLat()) / 2;
		double lon = (this.southWest.getLng() + this.northEast.getLng()) / 2;
		return new LatLng(lat, lon);
	}

	public void extend(LatLng... latlngs) {
		extend(Arrays.asList(latlngs));
	}

	public void extend(List<LatLng> latlngs) {
		for (LatLng latlng : latlngs) {
			northEast.setLat(Math.max(northEast.getLat(), latlng.getLat()));
			northEast.setLng(Math.max(northEast.getLng(), latlng.getLng()));
			southWest.setLat(Math.min(southWest.getLat(), latlng.getLat()));
			southWest.setLng(Math.min(southWest.getLng(), latlng.getLng()));
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * Extend the bounds to contain the given bounds
	 * 
	 * @param bounds the bounds
	 */
	public void extend(LatLngBounds bounds) {
		northEast.setLat(Math.max(northEast.getLat(), bounds.getSouthWest().getLat()));
		northEast.setLng(Math.max(northEast.getLng(), bounds.getSouthWest().getLng()));
		southWest.setLat(Math.min(southWest.getLat(), bounds.getNorthEast().getLat()));
		southWest.setLng(Math.min(southWest.getLng(), bounds.getNorthEast().getLng()));
	}
}
