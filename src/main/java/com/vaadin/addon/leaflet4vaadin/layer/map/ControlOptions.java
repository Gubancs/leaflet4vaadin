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

package com.vaadin.addon.leaflet4vaadin.layer.map;

import java.io.Serializable;

public interface ControlOptions extends Serializable {

	boolean isAttributionControl();

	/**
	 * Whether a attribution control is added to the map by default.
	 * 
	 * @param attributionControl set false if yuo want to hide the default
	 *                           attribution control
	 */
	void setAttributionControl(boolean attributionControl);

	boolean isZoomControl();

	/**
	 * Whether a zoom control is added to the map by default.
	 * 
	 * @param zoomControl set false if yuo want to hide the default zoom control
	 */
	void setZoomControl(boolean zoomControl);
}
