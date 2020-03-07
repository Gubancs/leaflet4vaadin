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

package com.vaadin.addon.leaflet4vaadin.layer;

import com.vaadin.addon.leaflet4vaadin.layer.events.supports.SupportsMouseEvents;

/**
 * Some Layers can be made interactive - when the user interacts with such a
 * layer, mouse events like click and mouseover can be handled. Use the event
 * handling methods to handle these events.
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-02-06
 * @version 1.0
 */
public abstract class InteractiveLayer extends Layer implements SupportsMouseEvents {

	private static final long serialVersionUID = -8240959432268467723L;
	private boolean interactive = true;
	private boolean bubblingMouseEvents = true;

	protected InteractiveLayer() {
	}

	public boolean isInteractive() {
		return interactive;
	}

	public void setInteractive(final boolean interactive) {
		this.interactive = interactive;
	}

	public boolean isBubblingMouseEvents() {
		return bubblingMouseEvents;
	}

	public void setBubblingMouseEvents(final boolean bubblingMouseEvents) {
		this.bubblingMouseEvents = bubblingMouseEvents;
	}
}
