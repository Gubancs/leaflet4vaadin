package com.vaadin.addon.leaflet4vaadin.plugins.fullscreen;

import com.vaadin.addon.leaflet4vaadin.LeafletMap;
import com.vaadin.addon.leaflet4vaadin.controls.LeafletControl;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.EventTypeRegistry;
import com.vaadin.addon.leaflet4vaadin.layer.events.types.LeafletEventType;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * <h3>Leaflet.Control.FullScreen</h3>
 * 
 * Web component: <a href=
 * "https://www.npmjs.com/package/leaflet.fullscreen">https://www.npmjs.com/package/leaflet.fullscreen</a><br>
 * <br>
 * 
 * Simple plugin for Leaflet that adds fullscreen button to your maps.<br>
 * <br>
 * 
 * 
 * If your map have a zoomControl the fullscreen button will be added at the
 * bottom of this one.<br>
 * <br>
 * 
 * If your map doesn't have a zoomContron the fullscreen button will be added to
 * topleft corner of the map (same as the zoomcontrol).<br>
 * <br>
 * 
 * If you want to use the plugin on a map embedded in an iframe, don't forget to
 * set allowfullscreen attribute on your iframe.<br>
 * <br>
 * 
 * <h3>FullScreen options</h3>
 * <ul>
 * <li><b>position</b> - change the position of the button can be topleft,
 * topright, bottomright or bottomleft, defaut topleft</li>
 * <li><b>title</b> - change the title of the button, default Full Screen</li>
 * <li><b>titleCancel</b> - change the title of the button when fullscreen is
 * on, default Exit Full Screen</li>
 * <li><b>content</b> - change the content of the button, can be HTML, default
 * null</li>
 * <li><b>forceSeparateButton</b> - force seperate button to detach from zoom
 * buttons, default false</li>
 * <li><b>forcePseudoFullscreen</b> - force use of pseudo full screen even if
 * full screen API is available, default false</li>
 * <li><b>fullscreenElement</b> - Dom element to render in full screen, false by
 * default, fallback to map._container</li>
 * </ul>
 * 
 * *
 * <h3>FullScreen events</h3>
 * <ul>
 * <li><b>enterFullscreen</b> - event is fired when entering fullscreen</li>
 * <li><b>exitFullscreen</b> - events is fired when exiting fullscreen</li>
 * </ul>
 * 
 * <h3>Basic Usage</h3>
 * 
 * <pre>
 * FullScreenControl fullScreenControl = new FullScreenControl();
 * fullScreenControl.addTo(leafletMap);
 * </pre>
 * 
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-05-26
 * @version 1.0
 * @see LeafletControl
 */
@NpmPackage(value = "leaflet.fullscreen", version = "1.6.0")
@JsModule("leaflet.fullscreen/Control.FullScreen.js")
@CssImport(value = "leaflet.fullscreen/Control.FullScreen.css", themeFor = "leaflet-map")
public class FullScreenControl extends LeafletControl {

    private static final long serialVersionUID = 5799278525370536625L;
    private String title = "Full Screen";
    private String titleCancel = "Exit Full Screen";
    private String content = null;
    private boolean forceSeparateButton = false;
    private boolean forcePseudoFullscreen = false;
    private boolean fullscreenElement = false;

    public static enum FullScreenEventType implements LeafletEventType {
        enterFullscreen, exitFullscreen;

        static {
            EventTypeRegistry.register(FullScreenEventType.class);
        }
    }

    public FullScreenControl() {
        super("fullscreen");
        setPosition(ControlPosition.topleft);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleCancel() {
        return titleCancel;
    }

    public void setTitleCancel(String titleCancel) {
        this.titleCancel = titleCancel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isForceSeparateButton() {
        return forceSeparateButton;
    }

    public void setForceSeparateButton(boolean forceSeparateButton) {
        this.forceSeparateButton = forceSeparateButton;
    }

    public boolean isForcePseudoFullscreen() {
        return forcePseudoFullscreen;
    }

    public void setForcePseudoFullscreen(boolean forcePseudoFullscreen) {
        this.forcePseudoFullscreen = forcePseudoFullscreen;
    }

    public boolean isFullscreenElement() {
        return fullscreenElement;
    }

    public void setFullscreenElement(boolean fullscreenElement) {
        this.fullscreenElement = fullscreenElement;
    }

    public static WithFullScreenControl wrap(LeafletMap leafletMap) {
        return new LeafletMapFullScreenAdapter(leafletMap);
    }

}
