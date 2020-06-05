package com.vaadin.addon.leaflet4vaadin.types;

import java.io.Serializable;

public interface BasicType extends Serializable {

    default String getLeafletType() {
        return getClass().getSimpleName();
    }

}
