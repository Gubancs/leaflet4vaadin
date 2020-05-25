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

package com.vaadin.addon.leaflet4vaadin.layer.vectors;

import java.util.Arrays;
import java.util.List;

import com.vaadin.addon.leaflet4vaadin.annotations.LeafletArgument;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.structure.GeometryStructure;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.structure.LatLngArray;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.structure.MultiLatLngArray;
import com.vaadin.addon.leaflet4vaadin.types.LatLng;

/**
 * A class for drawing polygon overlays on a map. Extends Polyline. Note that
 * points you pass when creating a polygon shouldn't have an additional last
 * point equal to the first one — it's better to filter out such points.
 */
public class Polygon extends Polyline {

    private static final long serialVersionUID = -128072866378031092L;

	@LeafletArgument
    private final MultiLatLngArray latlngs;

    public Polygon(LatLng... latLngs) {
        this(Arrays.asList(latLngs));
    }

    public Polygon(List<LatLng> exteriorLatlngs) {
        latlngs = new MultiLatLngArray();
        latlngs.add(new LatLngArray(exteriorLatlngs));
    }

    public Polygon(List<LatLng> exteriorLatlngs, MultiLatLngArray interiorRings) {
        latlngs = new MultiLatLngArray();
        latlngs.add(new LatLngArray(exteriorLatlngs));
        latlngs.addAll(interiorRings);
    }

    @Override
    public GeometryStructure getLatlngs() {
        return this.latlngs;
    }

}
