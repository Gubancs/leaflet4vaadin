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

package com.vaadin.addon.leaflet4vaadin.controls;

/**
 * A basic zoom control with two buttons (zoom in and zoom out). It is put on
 * the map by default unless you set its zoomControl option to false. Extends
 * Control.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-06
 * @version 1.0
 */
public class ZoomControl extends LeafletControl {

	private static final long serialVersionUID = -1579783907685084701L;

	private String zoomInText = "+";
	private String zoomInTitle = "Zoom in";
	private String zoomOutText = "&#x2212";
	private String zoomOutTitle = "Zoom out";

	public ZoomControl() {
		super("zoom");
	}

	public String getZoomInText() {
		return zoomInText;
	}

	public void setZoomInText(String zoomInText) {
		this.zoomInText = zoomInText;
	}

	public String getZoomInTitle() {
		return zoomInTitle;
	}

	public void setZoomInTitle(String zoomInTitle) {
		this.zoomInTitle = zoomInTitle;
	}

	public String getZoomOutText() {
		return zoomOutText;
	}

	public void setZoomOutText(String zoomOutText) {
		this.zoomOutText = zoomOutText;
	}

	public String getZoomOutTitle() {
		return zoomOutTitle;
	}

	public void setZoomOutTitle(String zoomOutTitle) {
		this.zoomOutTitle = zoomOutTitle;
	}

}
