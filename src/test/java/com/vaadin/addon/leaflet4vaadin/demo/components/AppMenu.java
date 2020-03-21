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

import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.DetailsVariant;

@CssImport(value = "styles/demo-menu.css")
public class AppMenu extends Accordion {

    private static final long serialVersionUID = 6428270908323350266L;

    private AppMenu() {
        getElement().getClassList().add("app-menu");

    }

    public void setActivePath(String path) {
        getChildren().forEach((child) -> {
            AccordionPanel panel = (AccordionPanel) child;
            AppMenuItem menuItem = (AppMenuItem) panel.getContent().findFirst().get();

            panel.getElement().getClassList().remove("active");
            menuItem.getSubMenuItems().forEach(item -> item.removeThemeVariants(ButtonVariant.LUMO_PRIMARY));
            Optional<Button> submenu = menuItem.getSubMenuItems().filter(item -> item.getElement() != null)
                    .filter((item) -> path.equals(item.getElement().getAttribute("path"))).findFirst();
            if (submenu.isPresent()) {
                panel.getElement().getClassList().add("active");
                submenu.get().addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            }
        });
    }

    public void addMenuItem(Component summary, AppMenuItem appMenuItem) {
        AccordionPanel menuPanel = new AccordionPanel(summary, appMenuItem);
        menuPanel.getElement().getClassList().add("app-menu-item-panel");
        menuPanel.addThemeVariants(DetailsVariant.FILLED, DetailsVariant.REVERSE);
        add(menuPanel);
    }

    public static AppMenu create() {
        return new AppMenu();
    }

}