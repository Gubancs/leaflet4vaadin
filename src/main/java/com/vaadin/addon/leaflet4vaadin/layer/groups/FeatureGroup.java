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

package com.vaadin.addon.leaflet4vaadin.layer.groups;

import java.util.List;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsMouseEvents;

/**
 * Extended LayerGroup that makes it easier to do the same thing to all its
 * member layers
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-02-06
 * @version 1.0
 */
public class FeatureGroup extends LayerGroup implements SupportsMouseEvents {

	/**
	 *
	 */
	private static final long serialVersionUID = 4315847050612014255L;

	public FeatureGroup() {
		super();
	}

	public FeatureGroup(final Layer... layers) {
		super(layers);
	}

	public FeatureGroup(final List<Layer> layers) {
		super(layers);
	}

}
