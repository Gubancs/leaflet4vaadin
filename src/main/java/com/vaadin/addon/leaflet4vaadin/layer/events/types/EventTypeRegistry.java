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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;

public class EventTypeRegistry {

    private static final Map<String, LeafletEventType> eventMap = new HashMap<>();

    static {
        register(DragEventType.class);
        register(Layer.Events.class);
        register(MapEventType.class);
        register(MouseEventType.class);
        register(PopupEventType.class);
        register(TooltipEventType.class);
    }

    static <T extends Enum<T> & LeafletEventType> void register(Class<T> eventType) {
        EnumSet.allOf(eventType).forEach(e -> eventMap.put(e.getLeafletEvent(), e));
    }

    public static LeafletEventType valueOf(String stringValue) {
        return eventMap.get(stringValue);
    }

}
