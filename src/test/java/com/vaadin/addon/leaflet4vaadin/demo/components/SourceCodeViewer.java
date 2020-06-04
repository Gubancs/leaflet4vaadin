
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

package com.vaadin.addon.leaflet4vaadin.demo.components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class SourceCodeViewer extends VerticalLayout {

	private final String urlSpec;

	private String varibleColor = "#deeaef";
	private String numberColor = "#23a263";
	private String annotationColor = "#fff";
	private String stringColor = "#f5986b";
	private String keywordColor = "#2bcdff";
	private String typeColor = "#508dff";
	private String methodColor = "#d2d081";
	private String staticVariableColor = "#41a6ff";

	protected SourceCodeViewer(String urlSpec) {
		super();
		this.urlSpec = urlSpec;
		setPadding(false);
		setSpacing(false);
		setHeightFull();

		HorizontalLayout header = new HorizontalLayout();
		header.setWidthFull();
		header.setPadding(true);
		header.getStyle().set("background", "rgb(57, 57, 57)");
		Anchor sourceUrl = new Anchor(urlSpec, urlSpec);
		sourceUrl.getStyle().set("color", "#fff");
		header.add(sourceUrl);

		// Code layout
		VerticalLayout codeLayout = new VerticalLayout();
		codeLayout.setSpacing(false);
		codeLayout.setPadding(true);
		codeLayout.setSizeFull();
		codeLayout.getStyle().set("background", "rgb(52, 52, 52)");
		codeLayout.getStyle().set("box-shadow", "rgb(33, 33, 33) 0px 0px 12px inset");
		codeLayout.getStyle().set("overflow", "auto");

		Pre code = new Pre();
		code.getStyle().set("margin", "0px");
		code.getStyle().set("background", "transparent");
		code.getStyle().set("color", "rgb(197, 197, 197)");
		code.getStyle().set("font-size", "14px");
		code.getStyle().set("-webkit-font-smoothing", "antialiased");

		try {
			code.getElement().setProperty("innerHTML", getSourceCode());
		} catch (Exception e) {
			getElement().setProperty("innerHTML", "Unable to get source code from GitHub. :(");
			LoggerFactory.getLogger(getClass()).error(e.getMessage()); 
		}
		codeLayout.add(code);

		add(header, codeLayout);
	}

	private String getSourceCode() throws Exception {
		URL url = new URL(urlSpec);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null) {

			if (inputLine.startsWith("import ") || inputLine.startsWith("// ") || inputLine.startsWith("package ")) {
				continue;
			}
			inputLine = highlightJava(inputLine);
			response.append(inputLine);
			response.append("\n");
		}

		in.close();

		return response.toString().trim();
	}

	private String highlightJava(String inputLine) {
		inputLine = highlightVariables(inputLine, varibleColor);
		inputLine = highlightNumbers(inputLine, numberColor);
		inputLine = highlightStaticVariables(inputLine, staticVariableColor);
		inputLine = highlightAnnotations(inputLine, annotationColor);
		inputLine = highlightStrings(inputLine, stringColor);
		inputLine = highlightKeywords(inputLine, keywordColor, "public", "private", "protected", "final", "new",
				"return", "continue", "extends", "implements", "null", "throws", "throw", "static", "void", "abstract",
				"instanceof", "volatile", "transient", "synchronized", "strictfp", "package", "native", "interface",
				"goto", "enum", "const", "assert");

		inputLine = highlightBlockKeywords(inputLine, keywordColor, "if", "else", "else if", "while", "for", "try",
				"case", "break", "default", "catch", "switch", "finally", "do");

		inputLine = highlightInstanceKeywords(inputLine, keywordColor, "true", "false", "class", "this", "super");

		inputLine = highlightTypes(inputLine, typeColor, "long", "int", "short", "double", "float", "byte", "char",
				"String");
		inputLine = highlightMethodInvocations(inputLine, methodColor);
		inputLine = highlightMethodReferences(inputLine, methodColor);
		inputLine = highlightMethods(inputLine, methodColor);
		return inputLine;
	}

	private String highlightBlockKeywords(String code, String color, String... keywords) {
		return highlightKeywords(code, color, keywords);
	}

	private String highlightTypes(String code, String color, String... primitiveTypes) {
		return highlightKeywords(code, color, primitiveTypes);
	}

	private String highlightKeywords(String code, String color, String... keywords) {
		for (String keyword : keywords) {
			code = code.replaceAll("(\\s*)(" + keyword + ")(\\s+)",
					"$1<strong style='color:" + color + "'>$2</strong>$3");
		}
		return code;
	}

	private String highlightNumbers(String code, String color) {
		return code.replaceAll("([-+]?\\d+[\\.,]?[\\d]*[lL]?)", "<strong style='color:" + color + "'>$1</strong>");
	}

	private String highlightAnnotations(String code, String color) {
		return code.replaceAll("(@[\\w]+)", "<strong style='color:" + color + "'>$1</strong>");
	}

	private String highlightInstanceKeywords(String code, String color, String... keywords) {
		for (String keyword : keywords) {
			code = code.replaceAll("(" + keyword + ")(\\s|[:|\\.|\\)]+)",
					"<strong style='color:" + color + "'>$1</strong>$2");
		}
		return code;
	}

	private String highlightStrings(String code, String color) {
		return code.replaceAll("\"(\\.|[^\"]*)\"", "<strong style='color:" + color + "'>\"$1\"</strong>");
	}

	private String highlightMethods(String code, String color) {
		return code.replaceAll("(\\s+)([a-z]\\w*)(\\()", "$1<strong style='color:" + color + "'>$2</strong>$3");
	}

	private String highlightMethodInvocations(String code, String color) {
		return code.replaceAll("(\\.)([a-z]\\w*)(\\()", "$1<strong style='color:" + color + "'>$2</strong>$3");
	}

	private String highlightMethodReferences(String code, String color) {
		return code.replaceAll("(::)([a-z]\\w*)([\\),;])", "$1<strong style='color:" + color + "'>$2</strong>$3");
	}

	private String highlightVariables(String code, String color) {
		return code.replaceAll("([\\s\\(\\)]+)([a-z]\\w+)(\\s?[=\\.,;\\)])",
				"$1<strong style='color:" + color + "'>$2</strong>$3");
	}

	private String highlightStaticVariables(String code, String color) {
		return code.replaceAll("([\\s\\.\\()])([A-Z_]+)([\\)\\(\\s=])",
				"$1<strong style='color:" + color + "'>$2</strong>$3");
	}

}
