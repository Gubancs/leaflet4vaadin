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

package com.vaadin.addon.leaflet4vaadin.layer.map.functions;

import com.vaadin.addon.leaflet4vaadin.options.LocateOptions;

public interface GeolocationFunctions extends ExecutableFunctions {

    /**
     * Tries to locate the user using the Geolocation API, firing a locationfound
     * event with location data on success or a locationerror event on failure, and
     * optionally sets the map view to the user's location with respect to detection
     * accuracy (or to the world view if geolocation failed). Note that, if your
     * page doesn't use HTTPS, this method will fail in modern browsers (Chrome 50
     * and newer)
     */
    default void locate() {
        executeJs("locate");
    }

    /**
     * Tries to locate the user using the Geolocation API, firing a locationfound
     * event with location data on success or a locationerror event on failure, and
     * optionally sets the map view to the user's location with respect to detection
     * accuracy (or to the world view if geolocation failed). Note that, if your
     * page doesn't use HTTPS, this method will fail in modern browsers (Chrome 50
     * and newer) See Locate options for more details.
     * 
     * @param options the locate options
     */
    default void locate(LocateOptions options) {
        executeJs("locate", options);
    }

    /**
     * Stops watching location previously initiated by map.locate({watch: true}) and
     * aborts resetting the map view if map.locate was called with {setView: true}.
     */
    default void stopLocate() {
        executeJs("stopLocate");
    }
}