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

import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

public class AppMenuItem extends VerticalLayout {

    private static final long serialVersionUID = 6428270908323350266L;
    private final String title;
    private final Icon icon;

    public AppMenuItem(String title, Icon icon) {
        this.title = title;
        this.icon = icon;
        addClassName("app-menu-item");
        setSpacing(false);
        setPadding(false);
    }

    public void addTo(AppMenu appMenu) {
        HorizontalLayout summary = new HorizontalLayout();
        summary.addClassName("app-menu-item-summary");
        summary.add(icon);
        summary.add(new Label(title));
        appMenu.addMenuItem(summary, this);
    }

    public AppMenuItem addSubMenu(Class<? extends Component> navigationTarget) {
        Button menuButton = new Button(navigationTarget.getAnnotation(PageTitle.class).value());
        menuButton.addClassName("app-submenu-item");
        menuButton.setWidthFull();
        menuButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        menuButton.addClickListener((event) -> UI.getCurrent().navigate(navigationTarget));
        menuButton.getElement().setAttribute("path", navigationTarget.getAnnotation(Route.class).value());
        add(menuButton);
        return this;
    }

    public Stream<Button> getSubMenuItems() {
        return getChildren().map((child) -> (Button) child);
    }

    public static AppMenuItem create(String title, Icon icon) {
        return new AppMenuItem(title, icon);
    }

}