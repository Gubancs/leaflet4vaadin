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

package com.vaadin.addon.leaflet4vaadin.layer.ui.popup;

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.types.Point;

public class Popup implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4544720725678791374L;
	private String content;
	private Integer maxWidth = 300;
	private Integer minWidht = 50;
	private Integer maxHeight;
	private boolean autoPan = true;
	private Point autoPanPaddingTopLeft;
	private Point autoPanPaddingBottomRight;
	private Point autoPanPadding = Point.of(5, 5);
	private boolean keepInView;
	private boolean closeButton = true;
	private boolean autoClose = true;
	private boolean closeOnEscapeKey = true;
	private boolean closeOnClick = true;
	private String className = "";

	public Popup(String content) {
		this.content = content;
	}

	public Integer getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}

	public Integer getMinWidht() {
		return minWidht;
	}

	public void setMinWidht(Integer minWidht) {
		this.minWidht = minWidht;
	}

	public Integer getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}

	public boolean isAutoPan() {
		return autoPan;
	}

	public void setAutoPan(boolean autoPan) {
		this.autoPan = autoPan;
	}

	public Point getAutoPanPaddingTopLeft() {
		return autoPanPaddingTopLeft;
	}

	public void setAutoPanPaddingTopLeft(Point autoPanPaddingTopLeft) {
		this.autoPanPaddingTopLeft = autoPanPaddingTopLeft;
	}

	public Point getAutoPanPaddingBottomRight() {
		return autoPanPaddingBottomRight;
	}

	public void setAutoPanPaddingBottomRight(Point autoPanPaddingBottomRight) {
		this.autoPanPaddingBottomRight = autoPanPaddingBottomRight;
	}

	public Point getAutoPanPadding() {
		return autoPanPadding;
	}

	public void setAutoPanPadding(Point autoPanPadding) {
		this.autoPanPadding = autoPanPadding;
	}

	public boolean isKeepInView() {
		return keepInView;
	}

	public void setKeepInView(boolean keepInView) {
		this.keepInView = keepInView;
	}

	public boolean isCloseButton() {
		return closeButton;
	}

	public void setCloseButton(boolean closeButton) {
		this.closeButton = closeButton;
	}

	public boolean isAutoClose() {
		return autoClose;
	}

	public void setAutoClose(boolean autoClose) {
		this.autoClose = autoClose;
	}

	public boolean isCloseOnEscapeKey() {
		return closeOnEscapeKey;
	}

	public void setCloseOnEscapeKey(boolean closeOnEscapeKey) {
		this.closeOnEscapeKey = closeOnEscapeKey;
	}

	public boolean isCloseOnClick() {
		return closeOnClick;
	}

	public void setCloseOnClick(boolean closeOnClick) {
		this.closeOnClick = closeOnClick;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
