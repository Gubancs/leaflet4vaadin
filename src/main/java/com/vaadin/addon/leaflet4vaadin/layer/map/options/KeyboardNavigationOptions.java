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

package com.vaadin.addon.leaflet4vaadin.layer.map.options;

import java.io.Serializable;

/**
 * Leaflet map keyboard navigation options
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-14
 * @version 1.0
 */
public interface KeyboardNavigationOptions extends Serializable {

    boolean isKeyboard();

    /**
     * Makes the map focusable and allows users to navigate the map with keyboard
     * arrows and +/- keys.
     * 
     * @param keyboard set true to enable keyboard navigation
     */
    void setKeyboard(boolean keyboard);

    double getKeyboardPanDelta();

    /**
     * Amount of pixels to pan when pressing an arrow key.
     * 
     * @param keyboardPanDelta amount of pixels
     */
    void setKeyboardPanDelta(double keyboardPanDelta);
}
