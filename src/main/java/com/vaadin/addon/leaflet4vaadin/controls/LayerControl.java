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

package com.vaadin.addon.leaflet4vaadin.controls;

public class LayerControl extends LeafletControl {

	private static final long serialVersionUID = -7779809624116362068L;
	private boolean collapsed = false;
	private boolean autoZIndex = true;
	private boolean hideSingleBase;
	private boolean sortLayers;

	public LayerControl() {
		super("layers");
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public boolean isAutoZIndex() {
		return autoZIndex;
	}

	public void setAutoZIndex(boolean autoZIndex) {
		this.autoZIndex = autoZIndex;
	}

	public boolean isHideSingleBase() {
		return hideSingleBase;
	}

	public void setHideSingleBase(boolean hideSingleBase) {
		this.hideSingleBase = hideSingleBase;
	}

	public boolean isSortLayers() {
		return sortLayers;
	}

	public void setSortLayers(boolean sortLayers) {
		this.sortLayers = sortLayers;
	}

	/**
	 * Expand the control container if collapsed.
	 */
	public void expand() {
		execute(this, "expand");
	}

	/**
	 * Collapse the control container if expanded.
	 */
	public void collapse() {
		execute(this, "collapse");
	}
}
