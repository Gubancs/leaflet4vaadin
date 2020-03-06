# leaflet4vaadin
**Leaflet4Vaadin** provides a Java API for **Leaflet**, which is a popular map implementation, similar to Google Maps.  Leaflet is lightweight and shines on mobile devices. Also it has an extensive set of add-ons, of which many are available for Vaadin too.
With this Vaadin add-on you can use LeafletJS with the server side Java you know best.

## Core Features:
- Provides initial options
- Handles map and layer events on server side such as ('click','zoom','move','resize' etc..)
- Supports vector layers
- Supports vaadin themes
- Supports map methods
- Supports tooltips and popups
## Example usage

```java
MapOptions options = new DefaultMapOptions();
options.setCenter(new LatLng(47.070121823, 19.204101562500004));
options.setZoom(7);
LeafletMap leafletMap = new LeafletMap(options );
leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
add(leafletMap);
```
