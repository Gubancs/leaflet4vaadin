
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

/*
* Internationalization object for customizing the component UI texts.
*/
package com.vaadin.addon.leaflet4vaadin.i18n;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import com.vaadin.flow.internal.JsonSerializer;

import org.apache.commons.io.IOUtils;

import elemental.json.JsonFactory;
import elemental.json.JsonValue;
import elemental.json.impl.JreJsonFactory;

public class LeafletI18n implements Serializable {

    private static final JsonValue DEFAULT_I18N;

    static {
        try {
            final JsonFactory JSON_FACTORY = new JreJsonFactory();
            DEFAULT_I18N = JSON_FACTORY.parse(
                    IOUtils.toString(LeafletI18n.class.getResource("leaflet-i18n.json"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot find the default i18n configuration");
        }
    }

    private String zoomIn;
    private String zoomOut;

    /**
     * @return a new instance with the default messages
     */
    public static LeafletI18n createDefault() {
        return JsonSerializer.toObject(LeafletI18n.class, DEFAULT_I18N);
    }

}