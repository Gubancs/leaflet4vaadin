package com.vaadin.addon.leaflet4vaadin.plugins.draw;

import java.io.Serializable;

import com.vaadin.addon.leaflet4vaadin.layer.groups.FeatureGroup;
import com.vaadin.addon.leaflet4vaadin.layer.vectors.PathOptions;

public class EditPolyOptions implements Serializable {

    private static final long serialVersionUID = -5568092216457984625L;

    private FeatureGroup featureGroup;
    private EditHandlerOptions edit;
    private boolean allowIntersection = true;

    public EditPolyOptions(FeatureGroup featureGroup) {
        this.featureGroup = featureGroup;
        edit = new EditHandlerOptions();
    }

    public FeatureGroup getFeatureGroup() {
        return featureGroup;
    }

    public EditHandlerOptions getEdit() {
        return edit;
    }

    public void setEdit(EditHandlerOptions edit) {
        this.edit = edit;
    }

    public boolean isAllowIntersection() {
        return allowIntersection;
    }

    public void setAllowIntersection(boolean allowIntersection) {
        this.allowIntersection = allowIntersection;
    }

    static class EditHandlerOptions implements Serializable {

        private static final long serialVersionUID = -8930648056547952011L;

        private PathOptions selectedPathOptions = new PathOptions();

        public PathOptions getSelectedPathOptions() {
            return selectedPathOptions;
        }

        public void setSelectedPathOptions(PathOptions selectedPathOptions) {
            this.selectedPathOptions = selectedPathOptions;
        }
    }

    // static class EditShapeOptions implements Serializable {
    // private Icon moveIcon = new DivIcon("leaflet-div-icon
    // leaflet-editing-icon leaflet-edit-move", 8);
    // private Icon resizeIcon = new DivIcon("leaflet-div-icon
    // leaflet-editing-icon leaflet-edit-resize", 8);
    // private Icon touchMoveIcon = new DivIcon("leaflet-div-icon
    // leaflet-editing-icon leaflet-edit-move leaflet-touch-icon", 20);
    // private Icon touchResizeIcon = new DivIcon("leaflet-div-icon
    // leaflet-editing-icon leaflet-edit-resize leaflet-touch-icon", 20);
    // }
}
