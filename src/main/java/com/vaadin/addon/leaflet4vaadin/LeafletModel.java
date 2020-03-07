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

package com.vaadin.addon.leaflet4vaadin;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.controls.LeafletControl;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.addon.leaflet4vaadin.layer.map.MapOptions;
import com.vaadin.flow.templatemodel.TemplateModel;

public interface LeafletModel extends TemplateModel {

	MapOptions getMapOptions();

	void setMapOptions(MapOptions mapOptions);

	String getBaseUrl();

	void setBaseUrl(String baseUrl);

	default List<LeafletControl> getControls() {
		return new ArrayList<>();
	}

	default List<Layer> getLayers() {
		return new ArrayList<>();
	}

	default List<LeafletEventType> getEvents() {
		return new ArrayList<>();
	}
}
