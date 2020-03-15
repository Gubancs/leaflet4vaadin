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

package com.vaadin.addon.leaflet4vaadin.layer.ui.tooltip;

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.types.Point;

public class Tooltip implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8082088105777353049L;
	private String content;
	private String pane = "tooltipPane";
	private Point offset = Point.of(0, 0);
	private String direction = "auto";
	private boolean permanent;
	private boolean sticky;
	private boolean interactive;
	private double opacity = 0.9;

	public Tooltip(String content) {
		this.content = content;
	}

	public String getPane() {
		return pane;
	}

	public void setPane(String pane) {
		this.pane = pane;
	}

	public Point getOffset() {
		return offset;
	}

	public void setOffset(Point offset) {
		this.offset = offset;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public boolean isPermanent() {
		return permanent;
	}

	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}

	public boolean isSticky() {
		return sticky;
	}

	public void setSticky(boolean sticky) {
		this.sticky = sticky;
	}

	public boolean isInteractive() {
		return interactive;
	}

	public void setInteractive(boolean interactive) {
		this.interactive = interactive;
	}

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Tooltip [content=" + content + ", direction=" + direction + ", interactive=" + interactive + ", offset="
				+ offset + ", opacity=" + opacity + ", pane=" + pane + ", permanent=" + permanent + ", sticky=" + sticky
				+ "]";
	}

}
