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

package com.vaadin.addon.leaflet4vaadin.layer.ui.marker;

import com.vaadin.addon.leaflet4vaadin.layer.InteractiveLayer;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsDragEvents;
import com.vaadin.addon.leaflet4vaadin.types.Icon;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;
import com.vaadin.addon.leaflet4vaadin.types.Point;

/**
 * L.Marker is used to display clickable/draggable icons on the map. Extends
 * InteractiveLayer.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-02-06
 * @version 1.0
 */
public class Marker extends InteractiveLayer implements SupportsDragEvents, MarkerFunctions {

	private static final long serialVersionUID = 5997712572773708479L;
	private Icon icon = Icon.DEFAULT_ICON;
	private LatLng latLng;
	private String title = "";
	private String alt = "";
	private double opacity = 1.0;
	private int zIndexOffset = 0;
	private boolean keyboard = true;
	private boolean draggable = false;
	private boolean autoPan = false;
	private Point autoPanPadding = Point.of(50, 50);
	private boolean riseOnHover = false;
	private int riseOffset = 250;
	private int autoPanSpeed = 10;

	public Marker() {
		super();
		setBubblingMouseEvents(false);
		setPane("markerPane");
	}

	/**
	 * Instantiates a Marker object given a geographical point and optionally an
	 * options object.
	 * 
	 * @param latLng the geographical point
	 */
	public Marker(LatLng latLng) {
		this();
		this.latLng = latLng;
	}

	public LatLng getLatLng() {
		if (isInitialized()) {
			MarkerFunctions.super.callLatLng().thenAccept((result) -> this.latLng = result);
		}
		return latLng;
	}

	@Override
	public void setLatLng(LatLng latLng) {
		if (isInitialized()) {
			MarkerFunctions.super.setLatLng(latLng);
		}
		this.latLng = latLng;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		if (isInitialized()) {
			MarkerFunctions.super.setOpacity(opacity);
		}
		this.opacity = opacity;
	}

	public Icon getIcon() {
		return icon;
	}

	@Override
	public void setIcon(Icon icon) {
		if (isInitialized()) {
			MarkerFunctions.super.setIcon(icon);
		}
		this.icon = icon;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	public boolean isAutoPan() {
		return autoPan;
	}

	public void setAutoPan(boolean autoPan) {
		this.autoPan = autoPan;
	}

	public int getAutoPanSpeed() {
		return autoPanSpeed;
	}

	public void setAutoPanSpeed(int autoPanSpeed) {
		this.autoPanSpeed = autoPanSpeed;
	}

	public boolean isKeyboard() {
		return keyboard;
	}

	public void setKeyboard(boolean keyboard) {
		this.keyboard = keyboard;
	}

	public int getzIndexOffset() {
		return zIndexOffset;
	}

	public void setzIndexOffset(int zIndexOffset) {
		if (isInitialized()) {
			MarkerFunctions.super.setZIndexOffset(zIndexOffset);
		}
		this.zIndexOffset = zIndexOffset;
	}

	public boolean isRiseOnHover() {
		return riseOnHover;
	}

	public void setRiseOnHover(boolean riseOnHover) {
		this.riseOnHover = riseOnHover;
	}

	public int getRiseOffset() {
		return riseOffset;
	}

	public void setRiseOffset(int riseOffset) {
		this.riseOffset = riseOffset;
	}

	public Point getAutoPanPadding() {
		return autoPanPadding;
	}

	public void setAutoPanPadding(Point autoPanPadding) {
		this.autoPanPadding = autoPanPadding;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

}
