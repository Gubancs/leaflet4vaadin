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

import org.geojson.GeoJsonObject;

public class GeoJSON extends FeatureGroup {

    /**
     *
     */
    private static final long serialVersionUID = -276591822504800118L;
    private boolean markersInheritOptions = false;
    private GeoJsonObject geoJson;

    public GeoJSON(GeoJsonObject geoJson) {
        this.geoJson = geoJson;
    }

    /**
     * @return the markersInheritOptions
     */
    public boolean isMarkersInheritOptions() {
        return markersInheritOptions;
    }

    /**
     * Whether default Markers for "Point" type Features inherit from group options.
     * 
     * @param markersInheritOptions the markersInheritOptions to set
     */
    public void setMarkersInheritOptions(boolean markersInheritOptions) {
        this.markersInheritOptions = markersInheritOptions;
    }

    /**
     * @return the geoJson
     */
    public GeoJsonObject getGeoJson() {
        return geoJson;
    }
}
