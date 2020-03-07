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

package com.vaadin.addon.leaflet4vaadin.layer.events.types;

public enum TileEventType implements LeafletEventType {

    /**
     * Fired when the grid layer starts loading tiles.
     */
    loading,

    /**
     * Fired when a tile is removed (e.g. when a tile goes off the screen).
     */
    tileunload,

    /**
     * Fired when a tile is removed (e.g. when a tile goes off the screen).
     */
    tileloadstart,

    /**
     * Fired when there is an error loading a tile.
     */
    tileerror,

    /**
     * Fired when a tile loads.
     */
    tileload,

    /**
     * Fired when the grid layer loaded all visible tiles.
     */
    load;

    @Override
	public String getLeafletEvent() {
		return name();
	}
}
