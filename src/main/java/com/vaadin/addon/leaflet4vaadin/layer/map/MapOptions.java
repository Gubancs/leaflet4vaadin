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

package com.vaadin.addon.leaflet4vaadin.layer.map;

import com.vaadin.addon.leaflet4vaadin.layer.Identifiable;

public interface MapOptions extends MapStateOptions, AnimationOptions, ControlOptions, Identifiable {

    boolean isPreferCanvas();

    /**
     * Whether Paths should be rendered on a Canvas renderer. By default, all Paths
     * are rendered in a SVG renderer.
     * 
     * @param preferCanvas whether Paths should be rendered on a Canvas renderer
     */
    void setPreferCanvas(boolean preferCanvas);

}
