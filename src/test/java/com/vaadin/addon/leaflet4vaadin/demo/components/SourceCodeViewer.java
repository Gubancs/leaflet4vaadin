
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

import com.vaadin.flow.component.html.Pre;

public class SourceCodeViewer extends Pre {

	private final String urlSpec;

	protected SourceCodeViewer(String urlSpec) {
		super();
		this.urlSpec = urlSpec;
		getStyle().set("padding", "30px");
		getStyle().set("background", "rgb(37, 37, 37)");
		getStyle().set("color", "rgb(197, 197, 197)");
		getStyle().set("font-size", "14px");
		getStyle().set("-webkit-font-smoothing", "antialiased");

		try {
			getElement().setProperty("innerHTML", getSourceCode());
		} catch (Exception e) {
			getElement().setProperty("innerHTML", "Unable to get source code from GitHub. :(");
			e.printStackTrace();
		}
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
		inputLine = highlightNumbers(inputLine, "#23a263");
		inputLine = highlightAnnotations(inputLine, "white");
		inputLine = highlightStrings(inputLine, "#f5986b");
		inputLine = highlightKeywords(inputLine, "#2b88ff", "public", "private", "protected", "final", "new", "return",
				"continue", "if", "while", "for", "this", "super", "class", "extends", "implements", "null", "throws",
				"throw", "try", "catch", "static", "long", "int", "short", "double", "float", "byte", "void",
				"abstract", "false", "true");
		return inputLine;
	}

	private String highlightKeywords(String code, String color, String... keywords) {
		for (String keyword : keywords) {
			code = code.replaceAll(keyword, "<strong style='color:" + color + "'>" + keyword + "</strong>");
		}
		return code;
	}

	private String highlightNumbers(String code, String color) {
		return highlight(code, "(-?[0-9]+.?[0-9]+)", color);
	}

	private String highlightAnnotations(String code, String color) {
		return highlight(code, "(@[\\w]+)", color);
	}

	private String highlightStrings(String code, String color) {
		return code.replaceAll("\"(\\.|[^\"]*)\"", "<strong style='color:" + color + "'>\"$1\"</strong>");
	}

	private String highlight(String code, String regex, String color) {
		return code.replaceAll(regex, "<strong style='color:" + color + "'>$1</strong>");
	}

}
