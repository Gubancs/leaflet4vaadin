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

package com.vaadin.addon.leaflet4vaadin.layer.vectors.structure;

import com.vaadin.addon.leaflet4vaadin.types.LatLngBounds;

/**
 * Core interface to geometry structures
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-23
 * @version 1.0
 */
public interface GeometryStructure {

    /**
     * Returns true if the geometry structure has no LatLngs.
     * 
     * @return return true if this geometry structure has no coordinates
     *         otherwise return false.
     */
    boolean isEmpty();

    /**
     * Calculate the boundary of this geometry structure
     * 
     * @return the {@link LatLngBounds} of the geometry
     */
    LatLngBounds getBounds();
}
