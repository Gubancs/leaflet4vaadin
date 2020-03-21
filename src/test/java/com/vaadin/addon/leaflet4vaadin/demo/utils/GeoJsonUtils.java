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

package com.vaadin.addon.leaflet4vaadin.demo.utils;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.geojson.FeatureCollection;

public class GeoJsonUtils {

    public static FeatureCollection loadFeatureCollection(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = WebUtils.getUrl(path);
        try {
            return objectMapper.readValue(url, FeatureCollection.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse geojson from URL: " + url);
        }
    }

}