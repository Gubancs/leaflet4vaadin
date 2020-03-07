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

package com.vaadin.addon.leaflet4vaadin.controls;

/**
 * The attribution control allows you to display attribution data in a small
 * text box on a map. It is put on the map by default unless you set its
 * attributionControl option to false, and it fetches attribution texts from
 * layers with the getAttribution method automatically. Extends Control.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-07
 * @version 1.0
 */
public class AttributionControl extends LeafletControl {

    private static final long serialVersionUID = 9098765502642340035L;
    private String prefix = "Leaflet";

    public AttributionControl() {
        super("attribution");
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * The HTML text shown before the attributions. Pass false to disable.
     * 
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
