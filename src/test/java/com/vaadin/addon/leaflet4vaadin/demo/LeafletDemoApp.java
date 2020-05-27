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

import com.vaadin.addon.leaflet4vaadin.demo.components.AppMenu;
import com.vaadin.addon.leaflet4vaadin.demo.components.AppMenuItem;
import com.vaadin.addon.leaflet4vaadin.demo.view.controls.ControlPositionExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.controls.RemoveDefaultControlsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.controls.ScaleControlExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.GeoJSONEventsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.GeoJSONFilterExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.GeoJSONLayerExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.GeoJSONStyleExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.MultipleBaseLayersExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.layers.TileLayerExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapConversionMethodsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapDarkThemeExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapEventsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapFunctionsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapGeolocationExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.map.MapMaxBoundsExample;
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
import com.vaadin.addon.leaflet4vaadin.demo.view.path.PathsStyleExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.path.TypeOfPathsExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.plugins.FullScreenPluginExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.plugins.HeatmapPluginExample;
import com.vaadin.addon.leaflet4vaadin.demo.view.plugins.MarkerClusterPluginExample;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@CssImport(value = "styles/demo-applayout.css", themeFor = "vaadin-app-layout")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class LeafletDemoApp extends AppLayout implements AfterNavigationObserver {

	private static final long serialVersionUID = -9119767347112138141L;

	private AppMenu appMenu = AppMenu.create();

	public LeafletDemoApp() {
		DrawerToggle drawerToggle = new DrawerToggle();
		drawerToggle.setIcon(new Icon(VaadinIcon.MENU));
		addToNavbar(true, drawerToggle);

		// Leaflet Icon
		Image image = new Image("https://leafletjs.com/docs/images/logo.png", "icon");
		image.setHeight("30px");
		image.getStyle().set("margin", "10px");
		image.addClickListener((e) -> UI.getCurrent().getPage().setLocation("https://leafletjs.com/"));
		addToNavbar(image);

		// App title
		Label appTitle = new Label("Vaadin - Leaflet examples");
		appTitle.setWidthFull();
		appTitle.getStyle().set("font-size", "20px");
		appTitle.getStyle().set("margin-left", "10px");
		appTitle.getStyle().set("font-weight", "300");
		addToNavbar(appTitle);

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

		HtmlContainer donate = new Div();
		String donateForm = "<form action=\"https://www.paypal.com/cgi-bin/webscr\" "
				+ "method=\"post\" target=\"_top\" style=\"display:flex; align-items:center; height: 100%; \">"
				+ "<input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\" />"
				+ "<input type=\"hidden\" name=\"hosted_button_id\" value=\"8M9BEK428U6AW\" />"
				+ "<button type=\"submit\" style=\"border: none;font-size: 16px;color:#083469;font-weight:600;border:1px #e89740 solid; "
				+ "box-shadow:0px 0px 5px #c1c1c1;padding: 8px 20px;background:linear-gradient(#f8e8a4,#f3a726);cursor: pointer;\" "
				+ "title=\"PayPal - The safer, easier way to pay online!\">Donate</button>" + "</form>";
		donate.getElement().setProperty("innerHTML", donateForm);

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
		actions.add(directoryButton, githubButton, donate);

		addToNavbar(actions);
	}

	private void initializeDemoMenu() {

		// Map examples
		AppMenuItem.create("Map", new Icon(VaadinIcon.GLOBE)).addSubMenu(MapEventsExample.class)
				.addSubMenu(MapMaxBoundsExample.class).addSubMenu(MapDarkThemeExample.class)
				.addSubMenu(MapPollListenerExample.class).addSubMenu(MapGeolocationExample.class)
				.addSubMenu(MapFunctionsExample.class).addSubMenu(MapConversionMethodsExample.class).addTo(appMenu);

		// Marker examples
		AppMenuItem.create("Markers", new Icon(VaadinIcon.MAP_MARKER)).addSubMenu(MarkersSimpleExample.class)
				.addSubMenu(MarkersEventsExample.class).addSubMenu(MarkersWithEventsExample.class)
				.addSubMenu(MarkersGroupExample.class).addSubMenu(MarkersAddAndRemoveExample.class)
				.addSubMenu(MarkersChangingIconExample.class).addSubMenu(MarkersRemoveOnClickExample.class)
				.addSubMenu(MarkerMethodCallExample.class).addTo(appMenu);

		// Layers examples
		AppMenuItem.create("Layers", new Icon(VaadinIcon.GRID_SMALL)).addSubMenu(TileLayerExample.class)
				.addSubMenu(MultipleBaseLayersExample.class).addSubMenu(GeoJSONLayerExample.class)
				.addSubMenu(GeoJSONFilterExample.class).addSubMenu(GeoJSONStyleExample.class)
				.addSubMenu(GeoJSONEventsExample.class).addTo(appMenu);

		// Paths examples
		AppMenuItem.create("Paths", new Icon(VaadinIcon.FILL)).addSubMenu(PathSimpleExample.class)
				.addSubMenu(TypeOfPathsExample.class).addSubMenu(PathsEventPropagationExample.class)
				.addSubMenu(FlyToPolygonBoundsExample.class).addSubMenu(Paths3000Example.class)
				.addSubMenu(PathsStyleExample.class).addTo(appMenu);

		// Controls examples
		AppMenuItem.create("Controls", new Icon(VaadinIcon.ARROWS)).addSubMenu(RemoveDefaultControlsExample.class)
				.addSubMenu(ControlPositionExample.class).addSubMenu(ScaleControlExample.class).addTo(appMenu);

		// Mixins examples
		AppMenuItem.create("Mixin", new Icon(VaadinIcon.SHIELD)).addSubMenu(WorldMapFlagsExample.class).addTo(appMenu);

		// Plugins examples
		AppMenuItem.create("Plugins", new Icon(VaadinIcon.PLUG)).addSubMenu(FullScreenPluginExample.class)
		        .addSubMenu(HeatmapPluginExample.class)
		        .addSubMenu(MarkerClusterPluginExample.class)
				.addTo(appMenu);

		addToDrawer(appMenu);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		appMenu.setActivePath(event.getLocation().getPath());
	}
}
