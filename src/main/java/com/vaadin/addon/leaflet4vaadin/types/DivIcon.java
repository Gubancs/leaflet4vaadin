package com.vaadin.addon.leaflet4vaadin.types;

/**
 * Represents a lightweight icon for markers that uses a simple <div> element
 * instead of an image. Inherits from Icon but ignores the iconUrl and shadow
 * options. </br>
 * </br>
 * By default, it has a 'leaflet-div-icon' CSS class and is styled as a little
 * white square with a shadow.
 * 
 * 
 * <h3>Usage example</h3>
 * 
 * <pre>
 * Marker marker = new Marker(new LatLng(47.1, 18.4));
 * marker.setIcon(new DivIcon("my-div-icon"));
 * marker.addTo(leafletMap);
 * </pre>
 * 
 * 
 * 
 * @author <strong>Gabor Kokeny</strong> Email:
 *         <a href='mailto=kokeny19@gmail.com'>kokeny19@gmail.com</a>
 * @since 2020-05-29
 * @version 1.0
 */
public class DivIcon extends Icon {

    private static final long serialVersionUID = 5260221030669197125L;
    private static final String CSS_CLASS = "leaflet-div-icon";
    private String html;
    private Point bgPos = Point.of(0, 0);

    public DivIcon() {
        this(CSS_CLASS);
    }

    public DivIcon(String className) {
        super();
        setClassName(className);
    }

    public DivIcon(String className, int size) {
        super();
        setClassName(className);
        setIconSize(Point.of(size, size));
        setIconAnchor(Point.of(size / 2, size / 2));
    }


    public String getHtml() {
        return html;
    }

    /**
     * Sets the custom HTML code to put inside the div element, empty by
     * default. Alternatively, an instance of HTMLElement.
     * 
     * @param html
     *            custom HTML code to put inside the div
     */
    public void setHtml(String html) {
        this.html = html;
    }

    public Point getBgPos() {
        return bgPos;
    }

    /**
     * Sets the Optional relative position of the background, in pixels
     *
     * @param bgPos
     *            the position of the background to be used
     */
    public void setBgPos(Point bgPos) {
        this.bgPos = bgPos;
    }
}
