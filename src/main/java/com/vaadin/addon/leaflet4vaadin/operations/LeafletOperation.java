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

package com.vaadin.addon.leaflet4vaadin.operations;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.addon.leaflet4vaadin.layer.Layer;

/**
 * {@link LeafletOperation} class contains all pieces of information is required
 * to perform function invocations on client-side e.g. flyTo() on map.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-03-11
 * @version 1.0
 */
public class LeafletOperation implements Serializable {

    private static final long serialVersionUID = -7752937288867492721L;
    private String layerId;
    private String functionName;
    private Serializable[] arguments;
    private final ObjectMapper mapper = new ObjectMapper();

    public LeafletOperation(Layer layer, String functionName, Serializable... arguments) {
        this.layerId = layer.getUuid();
        this.functionName = functionName;
        this.arguments = arguments;
    }

    /**
     * @return the layerId
     */
    public String getLayerId() {
        return layerId;
    }

    /**
     * @return the functionName
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * @return the arguments
     */
    public String getArguments() {
        try {
            return mapper.writeValueAsString(arguments);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert arguments to JSON.", e);
        }
    }

}
