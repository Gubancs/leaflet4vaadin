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

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.ModelEncoder;

public abstract class LeafletControl implements Serializable {

	private static final long serialVersionUID = -2562236800599480357L;

	public static enum ControlPosition {
		topleft, topright, bottomleft, bottomright;
	}

	private final String leafletType;
	private ControlPosition position = ControlPosition.topright;

	public LeafletControl(String leafletType) {
		this.leafletType = leafletType;
	}

	public ControlPosition getPosition() {
		return position;
	}

	public String getLeafletType() {
		return leafletType;
	}

	@Encode(value = ControlPositionModelEncoder.class)
	public void setPosition(ControlPosition position) {
		this.position = position;
	}

	/**
	 * Adds the control to the given map.
	 * 
	 * @param leafletMap add this control to the given leaflet map
	 */
	public void addTo(LeafletMap leafletMap) {
		leafletMap.addControl(this);
	}

	public static class ControlPositionModelEncoder implements ModelEncoder<ControlPosition, String> {

		/**
		 *
		 */
		private static final long serialVersionUID = -2149055629014677547L;

		@Override
		public String encode(ControlPosition value) {
			return value.name();
		}

		@Override
		public ControlPosition decode(String value) {
			return ControlPosition.valueOf(value);
		}

	}

}
