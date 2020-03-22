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

package com.vaadin.addon.leaflet4vaadin.layer.vectors;

import com.vaadin.addon.leaflet4vaadin.layer.InteractiveLayer;

/**
 * An abstract class that contains options and constants shared between vector
 * overlays (Polygon, Polyline, Circle). Do not use it directly. Extends
 * InteractiveLayer
 */
public abstract class Path extends InteractiveLayer implements PathFunctions {

	/**
	 *
	 */
	private static final long serialVersionUID = 8625609400908426994L;

	private final PathOptions pathOptions = new PathOptions();

	public boolean isStroke() {
		return pathOptions.isStroke();
	}

	/**
	 * Whether to draw stroke along the path. Set it to false to disable borders on
	 * polygons or circles.
	 * 
	 * @param stroke Set it to false to disable borders on polygons or circles.
	 */
	public void setStroke(boolean stroke) {
		this.pathOptions.setStroke(stroke);
	}

	public String getColor() {
		return pathOptions.getColor();
	}

	/**
	 * Stroke color
	 * 
	 * @param color the color of the stroke
	 */
	public void setColor(String color) {
		this.pathOptions.setColor(color);
	}

	public double getWeight() {
		return pathOptions.getWeight();
	}

	/**
	 * Stroke width in pixels
	 * 
	 * @param weight the weight of the path
	 */
	public void setWeight(double weight) {
		this.pathOptions.setWeight(weight);
	}

	public double getOpacity() {
		return pathOptions.getOpacity();
	}

	/**
	 * Stroke opacity
	 * 
	 * @param opacity the opacity of the path
	 */
	public void setOpacity(double opacity) {
		this.pathOptions.setOpacity(opacity);
	}

	public String getLineCap() {
		return pathOptions.getLineCap();
	}

	/**
	 * A string that defines shape to be used at the end of the stroke.
	 * 
	 * @param lineCap shape to be used at the end of the stroke
	 */
	public void setLineCap(String lineCap) {
		this.pathOptions.setLineCap(lineCap);
	}

	public String getLineJoin() {
		return pathOptions.getLineJoin();
	}

	/**
	 * A string that defines shape to be used at the corners of the stroke.
	 * 
	 * @param lineJoin shape to be used at the corners of the stroke
	 */
	public void setLineJoin(String lineJoin) {
		this.pathOptions.setLineJoin(lineJoin);
	}

	public String getDashArray() {
		return pathOptions.getDashArray();
	}

	/**
	 * A string that defines the stroke dash pattern. Doesn't work on Canvas-powered
	 * layers in some old browsers.
	 * 
	 * @param dashArray efines the stroke dash pattern
	 */
	public void setDashArray(String dashArray) {
		this.pathOptions.setDashArray(dashArray);
	}

	public String getDashOffset() {
		return pathOptions.getDashOffset();
	}

	/**
	 * A string that defines the distance into the dash pattern to start the dash.
	 * Doesn't work on Canvas-powered layers in some old browsers.
	 * 
	 * @param dashOffset defines the distance into the dash pattern to start the
	 *                   dash
	 */
	public void setDashOffset(String dashOffset) {
		this.pathOptions.setDashOffset(dashOffset);
	}

	public boolean isFill() {
		return pathOptions.isFill();
	}

	/**
	 * Whether to fill the path with color. Set it to false to disable filling on
	 * polygons or circles.
	 * 
	 * @param fill Set it to false to disable filling on polygons or circles
	 */
	public void setFill(boolean fill) {
		this.pathOptions.setFill(fill);
	}

	public String getFillColor() {
		return pathOptions.getFillColor();
	}

	/**
	 * Fill color. Defaults to the value of the color option
	 * 
	 * @param fillColor the fill color of the path
	 */
	public void setFillColor(String fillColor) {
		this.pathOptions.setFillColor(fillColor);
	}

	public double getFillOpacity() {
		return pathOptions.getFillOpacity();
	}

	/**
	 * Fill opacity.
	 * 
	 * @param fillOpacity the fill opacity (0 between 1)
	 */
	public void setFillOpacity(double fillOpacity) {
		this.pathOptions.setFillOpacity(fillOpacity);
	}

	public String getFillRule() {
		return pathOptions.getFillRule();
	}

	/**
	 * A string that defines how the inside of a shape is determined.
	 * 
	 * @param fillRule the string that defines how the inside of a shape is
	 *                 determined
	 */
	public void setFillRule(String fillRule) {
		this.pathOptions.setFillRule(fillRule);
	}

	public String getClassName() {
		return pathOptions.getClassName();
	}

	/**
	 * Custom class name set on an element. Only for SVG renderer.
	 * 
	 * @param className the class name set on an element
	 */
	public void setClassName(String className) {
		this.pathOptions.setClassName(className);
	}

}
