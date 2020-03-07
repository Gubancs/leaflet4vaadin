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

import com.vaadin.addon.leaflet4vaadin.types.LatLng;

/**
 * A class for drawing circle overlays on a map. Extends CircleMarker. It's an
 * approximation and starts to diverge from a real circle closer to poles (due
 * to projection distortion).
 */
public class Circle extends CircleMarker {
    /**
     *
     */
    private static final long serialVersionUID = -9214599807982996954L;

    public Circle(LatLng latlng) {
        super(latlng);
    }

    public Circle(LatLng latlng, int radius) {
        super(latlng, radius);
    }

}
