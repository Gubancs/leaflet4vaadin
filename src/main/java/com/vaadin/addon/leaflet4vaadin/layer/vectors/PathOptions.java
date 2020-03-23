package com.vaadin.addon.leaflet4vaadin.layer.vectors;

import java.io.Serializable;

/**
 * Contains options shared between vector overlays (Polygon, Polyline, Circle).
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * 
 * @since 2020-03-22
 * @version 1.0
 */
public class PathOptions implements Serializable {

	private static final long serialVersionUID = -7714457256248051646L;
	private boolean stroke = true;
	private String color = "#3388ff";
	private double weight = 3;
	private double opacity = 1.0;
	private String lineCap = "round";
	private String lineJoin = "round";
	private String dashArray;
	private String dashOffset;
	private boolean fill = true;
	private String fillColor = "#3388ff";
	private double fillOpacity = 0.2;
	private String fillRule = "evenodd";
	private String className;

	public PathOptions() {
	}

	public PathOptions(String color) {
		this.color = color;
		this.fillColor = color;
	}

	public PathOptions(String color, double weight) {
		this.color = color;
		this.fillColor = color;
		this.weight = weight;
	}

	public boolean isStroke() {
		return stroke;
	}

	/**
	 * Whether to draw stroke along the path. Set it to false to disable borders on
	 * polygons or circles.
	 * 
	 * @param stroke Set it to false to disable borders on polygons or circles.
	 */
	public void setStroke(boolean stroke) {
		this.stroke = stroke;
	}

	public String getColor() {
		return color;
	}

	/**
	 * Stroke color
	 * 
	 * @param color the color of the stroke
	 */
	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return weight;
	}

	/**
	 * Stroke width in pixels
	 * 
	 * @param weight the weight of the path
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getOpacity() {
		return opacity;
	}

	/**
	 * Stroke opacity
	 * 
	 * @param opacity the opacity of the path
	 */
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	public String getLineCap() {
		return lineCap;
	}

	/**
	 * A string that defines shape to be used at the end of the stroke.
	 * 
	 * @param lineCap shape to be used at the end of the stroke
	 */
	public void setLineCap(String lineCap) {
		this.lineCap = lineCap;
	}

	public String getLineJoin() {
		return lineJoin;
	}

	/**
	 * A string that defines shape to be used at the corners of the stroke.
	 * 
	 * @param lineJoin shape to be used at the corners of the stroke
	 */
	public void setLineJoin(String lineJoin) {
		this.lineJoin = lineJoin;
	}

	public String getDashArray() {
		return dashArray;
	}

	/**
	 * A string that defines the stroke dash pattern. Doesn't work on Canvas-powered
	 * layers in some old browsers.
	 * 
	 * @param dashArray efines the stroke dash pattern
	 */
	public void setDashArray(String dashArray) {
		this.dashArray = dashArray;
	}

	public String getDashOffset() {
		return dashOffset;
	}

	/**
	 * A string that defines the distance into the dash pattern to start the dash.
	 * Doesn't work on Canvas-powered layers in some old browsers.
	 * 
	 * @param dashOffset defines the distance into the dash pattern to start the
	 *                   dash
	 */
	public void setDashOffset(String dashOffset) {
		this.dashOffset = dashOffset;
	}

	public boolean isFill() {
		return fill;
	}

	/**
	 * Whether to fill the path with color. Set it to false to disable filling on
	 * polygons or circles.
	 * 
	 * @param fill Set it to false to disable filling on polygons or circles
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public String getFillColor() {
		return fillColor;
	}

	/**
	 * Fill color. Defaults to the value of the color option
	 * 
	 * @param fillColor the fill color of the path
	 */
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	public double getFillOpacity() {
		return fillOpacity;
	}

	/**
	 * Fill opacity.
	 * 
	 * @param fillOpacity the fill opacity (0 between 1)
	 */
	public void setFillOpacity(double fillOpacity) {
		this.fillOpacity = fillOpacity;
	}

	public String getFillRule() {
		return fillRule;
	}

	/**
	 * A string that defines how the inside of a shape is determined.
	 * 
	 * @param fillRule the string that defines how the inside of a shape is
	 *                 determined
	 */
	public void setFillRule(String fillRule) {
		this.fillRule = fillRule;
	}

	public String getClassName() {
		return className;
	}

	/**
	 * Custom class name set on an element. Only for SVG renderer.
	 * 
	 * @param className the class name set on an element
	 */
	public void setClassName(String className) {
		this.className = className;
	}

}
