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

package com.vaadin.addon.leaflet4vaadin.demo;

import com.vaadin.addon.leaflet4vaadin.demo.view.controls.ControlPositionExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.controls.RemoveDefaultControlsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.controls.ScaleControlExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.GeoJSONLayerExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.MultipleBaseLayersExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.TileLayerExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapConversionMethodsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapDarkThemeExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapEventsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapFunctionsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapGeolocationExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapPollListenerExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkerMethodCallExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkersAddAndRemoveExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkersChangingIconExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkersEventsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkersGroupExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkersRemoveOnClickExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkersSimpleExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.marker.MarkersWithEventsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.mixed.WorldMapFlagsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.path.FlyToPolygonBoundsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.path.PathSimpleExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.path.Paths3000Example;
import com.vaadin.addon.leaflet4vaadin.demo.view.path.PathsEventPropagationExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.path.TypeOfPathsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.plugins.FullScreenPluginExample;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class LeafletDemoApp extends AppLayout {

	private static final long serialVersionUID = -9119767347112138141L;

	public LeafletDemoApp() {
		addToNavbar(true, new DrawerToggle());

		// Leaflet Icon
		Image image = new Image("https://leafletjs.com/docs/images/logo.png", "icon");
		image.setHeight("30px");
		image.getStyle().set("margin", "10px");
		image.addClickListener((e) -> UI.getCurrent().getPage().setLocation("https://leafletjs.com/"));
		addToNavbar(image);

		// External links in navbar
		initActionButtons();

		// Application menubar
		initializeDemoMenu();
	}

	private void initActionButtons() {
		HorizontalLayout actions = new HorizontalLayout();
		actions.setPadding(true);
		actions.setWidthFull();
		actions.setJustifyContentMode(JustifyContentMode.END);

		// Vaadin directory button
		Button directoryButton = new Button("Vaddin directory", new Icon(VaadinIcon.VAADIN_H));
		directoryButton.addClickListener(
				(e) -> UI.getCurrent().getPage().setLocation("https://vaadin.com/directory/component/leaflet4vaadin"));
		directoryButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

		// Github button
		Button githubButton = new Button("Github");
		githubButton.addClickListener(
				(e) -> UI.getCurrent().getPage().setLocation("https://github.com/Gubancs/leaflet4vaadin"));
		githubButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		actions.add(directoryButton, githubButton);

		addToNavbar(actions);
		
	}

	private void initializeDemoMenu() {
		Accordion accordion = new Accordion();

		// Map examples
		MenuItem.create("Map").addSubMenu(MapEventsExample.class).addSubMenu(MapDarkThemeExample.class)
				.addSubMenu(MapPollListenerExample.class).addSubMenu(MapGeolocationExample.class)
				.addSubMenu(MapFunctionsExample.class).addSubMenu(MapConversionMethodsExample.class).addTo(accordion);

		// Marker examples
		MenuItem.create("Markers").addSubMenu(MarkersSimpleExample.class).addSubMenu(MarkersEventsExample.class)
				.addSubMenu(MarkersWithEventsExample.class).addSubMenu(MarkersGroupExample.class)
				.addSubMenu(MarkersAddAndRemoveExample.class).addSubMenu(MarkersChangingIconExample.class)
				.addSubMenu(MarkersRemoveOnClickExample.class).addSubMenu(MarkerMethodCallExample.class)
				.addTo(accordion);

		// Layers examples
		MenuItem.create("Layers").addSubMenu(TileLayerExample.class).addSubMenu(MultipleBaseLayersExample.class)
				.addSubMenu(GeoJSONLayerExample.class).addTo(accordion);

		// Paths examples
		MenuItem.create("Paths").addSubMenu(PathSimpleExample.class).addSubMenu(TypeOfPathsExample.class)
				.addSubMenu(PathsEventPropagationExample.class).addSubMenu(FlyToPolygonBoundsExample.class)
				.addSubMenu(Paths3000Example.class).addTo(accordion);

		// Controls examples
		MenuItem.create("Controls").addSubMenu(RemoveDefaultControlsExample.class)
				.addSubMenu(ControlPositionExample.class).addSubMenu(ScaleControlExample.class).addTo(accordion);

		// Mixins examples
		MenuItem.create("Mixin").addSubMenu(WorldMapFlagsExample.class).addTo(accordion);

		// Plugins examples
		MenuItem.create("Plugins").addSubMenu(FullScreenPluginExample.class).addTo(accordion);

		addToDrawer(accordion);
	}

	private static class MenuItem extends VerticalLayout {

		private static final long serialVersionUID = 6428270908323350266L;
		private final String title;

		public MenuItem(String title) {
			this.title = title;
			setSpacing(false);
			setPadding(false);
		}

		public AccordionPanel addTo(Accordion accordion) {
			AccordionPanel menuPanel = new AccordionPanel(title, this);
			menuPanel.addThemeVariants(DetailsVariant.FILLED, DetailsVariant.REVERSE);
			accordion.add(menuPanel);
			return menuPanel;
		}

		public MenuItem addSubMenu(Class<? extends Component> navigationTarget) {
			Button menuButton = new Button(navigationTarget.getAnnotation(PageTitle.class).value());
			menuButton.setWidthFull();
			menuButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
			menuButton.addClickListener((event) -> UI.getCurrent().navigate(navigationTarget));
			add(menuButton);
			return this;
		}

		public static MenuItem create(String title) {
			return new MenuItem(title);
		}

	}
}
