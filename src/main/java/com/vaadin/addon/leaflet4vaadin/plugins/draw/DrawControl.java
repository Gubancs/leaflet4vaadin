package com.vaadin.addon.leaflet4vaadin.plugins.draw;

import com.vaadin.addon.leaflet4vaadin.controls.LeafletControl;
import com.vaadin.addon.leaflet4vaadin.layer.groups.FeatureGroup;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

/**
 * <h3>Leaflet.Draw</h3>
 * 
 * Web component: <a href=
 * "https://www.npmjs.com/package/leaflet-draw">https://www.npmjs.com/package/leaflet-draw</a></br>
 * </br>
 * </br>
 * 
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-06-05
 * @version 1.0
 */
@NpmPackage(value = "leaflet-draw", version = "1.0.4")
@JsModule("leaflet-draw/dist/leaflet.draw-src.js")
@CssImport(value = "leaflet-draw/dist/leaflet.draw-src.css", themeFor = "leaflet-map")
public class DrawControl extends LeafletControl {

    private static final long serialVersionUID = 4115779806912447282L;

    private DrawOptions draw;

    private EditPolyOptions edit;

    public DrawControl(FeatureGroup featureGroup) {
        super("Draw");
        setPosition(ControlPosition.topleft);
        draw = new DrawOptions();
        edit = new EditPolyOptions(featureGroup);
    }

    /**
     * The options used to configure the draw toolbar.
     * 
     * @return return the options used to configure the draw toolbar.
     * 
     */
    public DrawOptions getDraw() {
        return draw;
    }

    /**
     * The options used to configure the edit toolbar.
     * 
     * @return return the options used to configure the edit toolbar.
     */
    public EditPolyOptions getEdit() {
        return edit;
    }

    public void disableMarkerDraw() {
        getDraw().setMarker(null);
    }

    public void disableCirclemarkerDraw() {
        getDraw().setCirclemarker(null);
    }

}
