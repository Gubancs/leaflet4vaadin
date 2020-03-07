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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;

/**
 * Used to group several layers and handle them as one. If you add it to the
 * map, any layers added or removed from the group will be added/removed on the
 * map as well. Extends Layer.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-02-06
 * @version 1.0
 */
public class LayerGroup extends Layer {

	/**
	 *
	 */
	private static final long serialVersionUID = 439247482151898231L;
	private List<Layer> layers = new ArrayList<>();

	public LayerGroup(Layer... layers) {
		this(Arrays.asList(layers));
	}

	public LayerGroup(List<Layer> layers) {
		super();
		this.layers = new ArrayList<>(layers);
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void addLayer(Layer layer) {
		this.getLayers().add(layer);
	}

	public void removeLayer(Layer layer) {
		findChildLayer(layer.getUuid()).ifPresent(l -> getLayers().remove(l));
	}

	@Override
	public Optional<Layer> findLayer(String layerId) {
		Optional<Layer> layer = super.findLayer(layerId);
		if (layer.isPresent()) {
			return layer;
		}
		return findChildLayer(layerId);
	}

	private Optional<Layer> findChildLayer(String layerId) {
		return layers.stream().map(l -> l.findLayer(layerId)).filter(Optional::isPresent).findFirst()
				.orElse(Optional.empty());
	}

}
