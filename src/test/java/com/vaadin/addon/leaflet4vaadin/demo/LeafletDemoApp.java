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

import com.github.appreciated.app.layout.annotations.Caption;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts.LeftResponsiveHybrid;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.vaadin.addon.leaflet4vaadin.demo.components.ExampleContainer;
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
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class LeafletDemoApp extends AppLayoutRouterLayout<LeftResponsiveHybrid> {

	private static final long serialVersionUID = -9119767347112138141L;

	public LeafletDemoApp() {
		HorizontalLayout actions = new HorizontalLayout();
		Button directoryButton = new Button("Vaddin directory", new Icon(VaadinIcon.VAADIN_H));
		directoryButton.addClickListener(
				(e) -> UI.getCurrent().getPage().setLocation("https://vaadin.com/directory/component/leaflet4vaadin"));
		directoryButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
		Button githubButton = new Button("Github");
		githubButton.addClickListener(
				(e) -> UI.getCurrent().getPage().setLocation("https://github.com/Gubancs/leaflet4vaadin"));
		githubButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		actions.add(directoryButton, githubButton);

		Image image = new Image("https://leafletjs.com/docs/images/logo.png", "icon");
		image.setHeight("var(--app-layout-menu-button-height)");
		image.getStyle().set("margin", "var(--app-layout-space-s)");
		image.addClickListener((e) -> UI.getCurrent().getPage().setLocation("https://leafletjs.com/"));

		init(AppLayoutBuilder.get(LeftResponsiveHybrid.class).withTitle("Vaadin 14 - Leaflet examples")
				.withAppBar(AppBarBuilder.get().add(actions).build()).withIconComponent(image)
				.withAppMenu(LeftAppMenuBuilder.get()
						.add(LeftSubMenuBuilder.get("Map", new Icon(VaadinIcon.GLOBE))
								.add(menuItem(MapEventsExample.class)).add(menuItem(MapDarkThemeExample.class))
								.add(menuItem(MapPollListenerExample.class)).add(menuItem(MapFunctionsExample.class))
								.add(menuItem(MapGeolocationExample.class))
								.add(menuItem(MapConversionMethodsExample.class))
								.build())
						.add(LeftSubMenuBuilder.get("Markers", new Icon(VaadinIcon.MAP_MARKER))
								.add(menuItem(MarkersSimpleExample.class)).add(menuItem(MarkersWithEventsExample.class))
								.add(menuItem(MarkersAddAndRemoveExample.class))
								.add(menuItem(MarkersChangingIconExample.class))
								.add(menuItem(MarkersGroupExample.class)).add(menuItem(MarkersEventsExample.class))
								.add(menuItem(MarkersRemoveOnClickExample.class))
								.add(menuItem(MarkerMethodCallExample.class)).build())
						.add(LeftSubMenuBuilder.get("Paths", new Icon(VaadinIcon.PUZZLE_PIECE))
								.add(menuItem(PathSimpleExample.class)).add(menuItem(TypeOfPathsExample.class))
								.add(menuItem(Paths3000Example.class)).add(menuItem(PathsEventPropagationExample.class))
								.add(menuItem(FlyToPolygonBoundsExample.class)).build())
						.add(LeftSubMenuBuilder.get("Controls", new Icon(VaadinIcon.CROSSHAIRS))
								.add(menuItem(RemoveDefaultControlsExample.class))
								.add(menuItem(ControlPositionExample.class)).add(menuItem(ScaleControlExample.class))
								.build())
						.add(LeftSubMenuBuilder.get("Layers", new Icon(VaadinIcon.GRID_BIG))
								.add(menuItem(TileLayerExample.class)).add(menuItem(MultipleBaseLayersExample.class))
								.add(menuItem(GeoJSONLayerExample.class)).build())
						.add(LeftSubMenuBuilder.get("Mixin", new Icon(VaadinIcon.EYE))
								.add(menuItem(WorldMapFlagsExample.class)).build())
						.add(LeftSubMenuBuilder.get("Plugins", new Icon(VaadinIcon.PLUG))
								.add(menuItem(FullScreenPluginExample.class)).build())
						.withStickyFooter().build())
				.build());
	}

	public LeftNavigationItem menuItem(Class<? extends ExampleContainer> view) {
		return new LeftNavigationItem(view.getAnnotation(Caption.class).value(), new Icon("lumo", "space"), view);
	}

}
