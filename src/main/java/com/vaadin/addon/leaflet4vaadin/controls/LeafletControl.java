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
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.layer.Identifiable;
import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;
import com.vaadin.addon.leaflet4vaadin.types.LeafletClass;
import com.vaadin.flow.templatemodel.Encode;
import com.vaadin.flow.templatemodel.ModelEncoder;

public abstract class LeafletControl implements LeafletClass, ExecutableFunctions {

	private static final long serialVersionUID = -2562236800599480357L;

	public static enum ControlPosition {
		topleft, topright, bottomleft, bottomright;
	}

	private final String uuid;
	private final String controlType;
	private ControlPosition position = ControlPosition.topright;
	private ExecutableFunctions functionDelegate;

	public LeafletControl(String controlType) {
		this.controlType = controlType;
		this.uuid = UUID.randomUUID().toString();
	}

	@Override
	public String getUuid() {
		return uuid;
	}

	public ControlPosition getPosition() {
		return position;
	}

	@Override
	public String getLeafletType() {
		return controlType;
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
		this.functionDelegate = leafletMap;
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

	@Override
	public void execute(Identifiable target, String functionName, Serializable... arguments) {
		if (functionDelegate != null) {
			functionDelegate.execute(target, functionName, arguments);
		} else {
			throw new RuntimeException("Unable to execute leaflet function " + functionName);
		}
	}

	@Override
	public <T extends Serializable> CompletableFuture<T> call(Identifiable target, String functionName,
			Class<T> resultType, Serializable... arguments) {
		if (functionDelegate != null) {
			return functionDelegate.call(target, functionName, resultType, arguments);
		} else {
			throw new RuntimeException("Unable to call leaflet function " + functionName);
		}
	}

}
