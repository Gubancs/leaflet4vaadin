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

package com.vaadin.addon.leaflet4vaadin.layer.events;

import com.vaadin.addon.leaflet4vaadin.layer.Layer;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.PopupEventType;
import com.vaadin.addon.leaflet4vaadin.layer.ui.popup.Popup;

public class PopupEvent extends LeafletEvent {

    private Popup popup;

    public PopupEvent(Layer layer, PopupEventType eventType, Popup popup) {
        super(layer, eventType);
        this.popup = popup;
    }

    /**
     * @return the popup
     */
    public Popup getPopup() {
        return popup;
    }

    @Override
    public String toString() {
        return "PopupEvent [type=" + super.getType() + ", popup=" + popup + "]";
    }

}
