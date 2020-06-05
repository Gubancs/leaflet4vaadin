package com.vaadin.addon.leaflet4vaadin.controls;

import java.io.Serializable;

public class LayersControlOptions implements Serializable {

    private static final long serialVersionUID = 1218151748533368492L;
    private boolean collapsed = true;
    private boolean autoZIndex = true;
    private boolean hideSingleBase = false;
    private boolean sortLayers = false;

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
    }

    public boolean isAutoZIndex() {
        return autoZIndex;
    }

    public void setAutoZIndex(boolean autoZIndex) {
        this.autoZIndex = autoZIndex;
    }

    public boolean isHideSingleBase() {
        return hideSingleBase;
    }

    public void setHideSingleBase(boolean hideSingleBase) {
        this.hideSingleBase = hideSingleBase;
    }

    public boolean isSortLayers() {
        return sortLayers;
    }

    public void setSortLayers(boolean sortLayers) {
        this.sortLayers = sortLayers;
    }

}
