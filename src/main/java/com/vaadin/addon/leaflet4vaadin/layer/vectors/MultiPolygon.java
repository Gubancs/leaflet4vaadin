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

import java.util.ArrayList;

import com.vaadin.addon.leaflet4vaadin.layer.vectors.structure.GeometryStructure;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.structure.MultiLatLngArray;
import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * * A class for drawing multi polygon overlays on a map.
 * 
 */
public class MultiPolygon extends Polygon {

    private static final long serialVersionUID = 2133788010825025903L;

    private MultiPolygonStructure latlngs;

    public MultiPolygon(MultiPolygonStructure latlngs) {
        this.latlngs = latlngs;
    }

    @Override
    public String getLeafletType() {
        return Polygon.class.getSimpleName();
    }

    @Override
    public MultiPolygonStructure getLatlngs() {
        return latlngs;
    }

    public static class MultiPolygonStructure extends ArrayList<MultiLatLngArray> implements GeometryStructure {

        private static final long serialVersionUID = -8597806785233271725L;

        @Override
        public LatLngBounds getBounds() {
            LatLngBounds bounds = new LatLngBounds();
            forEach((latlng) -> bounds.extend(latlng.getBounds()));
            return bounds;
        }
    }

}
