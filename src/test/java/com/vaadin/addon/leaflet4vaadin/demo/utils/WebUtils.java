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

package com.vaadin.addon.leaflet4vaadin.demo.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;

public class WebUtils {

    public static URL getBaseUrl() {
        return getUrl("");
    }

    public static URL getUrl(String path) {
        Objects.requireNonNull(path, "Path cannot be null");
        String urlSpec = path;
        try {
            VaadinRequest vaadinRequest = VaadinService.getCurrentRequest();
            HttpServletRequest req = ((VaadinServletRequest) vaadinRequest).getHttpServletRequest();
            StringBuffer requestUrl = req.getRequestURL();
            String baseUrl = requestUrl.substring(0, requestUrl.length() - req.getRequestURI().length());
            urlSpec = baseUrl + (path.startsWith("/") ? path : "/" + path);
            return new URL(urlSpec);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create URL from spec: " + urlSpec, e);
        }
    }

}