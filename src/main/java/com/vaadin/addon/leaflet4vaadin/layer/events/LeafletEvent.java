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

package com.vaadin.addon.leaflet4vaadin.layer.events;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The base event object. All other event objects contain these properties too.
 */
public class LeafletEvent {

	private final Layer target;

	private final LeafletEventType type;

	public LeafletEvent(Layer target, LeafletEventType type) {
		this.target = target;
		this.type = type;
	}

	/**
	 * The object that fired the event. For propagated events, the last object in
	 * the propagation chain that fired the event.
	 * 
	 * @return the target
	 */
	public Layer getTarget() {
		return target;
	}

	/**
	 * The event type (e.g. 'click').
	 * 
	 * @return the type
	 */
	public LeafletEventType getType() {
		return type;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
