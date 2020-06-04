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

import com.vaadin.addon.leaflet4vaadin.layer.map.functions.ExecutableFunctions;

/**
 * Methods for vector layers
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-22
 * @version 1.1
 */
public interface PathFunctions extends ExecutableFunctions {

	/**
	 * Redraws the layer. Sometimes useful after you changed the coordinates that
	 * the path uses.
	 * 
	 */
	default void redraw() {
		executeJs("redraw");
	}

	/**
	 * Changes the appearance of a Path based on the options in the Path options
	 * object.
	 * 
	 * @param pathOptions the appearance to be use
	 */
	default void setStyle(PathOptions pathOptions) {
		executeJs("setStyle", pathOptions);
	}

	/**
	 * Brings the layer to the top of all path layers.
	 */
	default void bringToFront() {
		executeJs("bringToFront");
	}

	/**
	 * Brings the layer to the bottom of all path layers.
	 */
	default void bringToBack() {
		executeJs("bringToBack");
	}
}