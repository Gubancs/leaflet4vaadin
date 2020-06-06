
![Java CI with Maven](https://github.com/Gubancs/leaflet4vaadin/workflows/Java%20CI%20with%20Maven/badge.svg)
![Vaadin Directory](https://github.com/Gubancs/leaflet4vaadin/workflows/Vaadin%20Directory/badge.svg)

[![Published on vaadin.com/directory](https://img.shields.io/badge/Vaadin%20Directory-published-blue.svg?colorB=00b4f0)](https://vaadin.com/directory/component/leaflet4vaadin)
[![Star on vaadin.com/directory](https://img.shields.io/vaadin-directory/star/leaflet4vaadin.svg)](https://vaadin.com/directory/component/leaflet4vaadin)
[![Rating on vaadin.com/directory](https://img.shields.io/vaadin-directory/rating/leaflet4vaadin.svg)](https://vaadin.com/directory/component/leaflet4vaadin)
[![Rating count on vaadin.com/directory](https://img.shields.io/vaadin-directory/rc/leaflet4vaadin.svg)](https://vaadin.com/directory/component/leaflet4vaadin)
[![Latest version on vaadin.com/directory](https://img.shields.io/vaadin-directory/v/leaflet4vaadin.svg)](https://vaadin.com/directory/component/leaflet4vaadin)
[![Latest release date on vaadin.com/directory](https://img.shields.io/vaadin-directory/rd/leaflet4vaadin.svg)](https://vaadin.com/directory/component/leaflet4vaadin)

# leaflet4vaadin

- FRAMEWORK SUPPORT: **Polymer 3.0+Vaadin platform 14+**
- Leaflet: https://leafletjs.com/
- Vaadin Add-on directory:
https://vaadin.com/directory/component/leaflet4vaadin/
- Live demo: https://leaflet4vaadin.herokuapp.com/
- Source Code Example with Vaadin Flow V15: https://github.com/Gubancs/leaflet4vaadin-demo
- Feel free to donate: https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=8M9BEK428U6AW&source=url

<img src="https://raw.githubusercontent.com/Gubancs/leaflet4vaadin/master/demo.png" alt="Lichess homepage" title="Lichess comes with light and dark theme, this screenshot shows both." />

**Leaflet4Vaadin** provides a Java API for **Leaflet**, which is a popular map implementation, similar to Google Maps.  Leaflet is lightweight and shines on mobile devices. Also it has an extensive set of add-ons, of which many are available for Vaadin too.
With this Vaadin add-on you can use LeafletJS with the server side Java you know best, and you can easily extend it or just implement your custom plugins and integrate them.

## Core Features:
- map configuration
- interactive layers
- map and layer events
- markers
- tooltip and popup binding
- layer groups
- vector layers
- dark theme
- map controls (zoom, layers, scale)
- map state functions
- tile layers
- GeoJSON support
- supports Leaflet plugins (eg.: fullscreen, heatmap, markercluster)


## Future improvements
- i18n support
- leaflet draw integration
- design and performance improvements
- use Typescript on client-side instead of JS
- supports parameters in custom events
- selenium ui tests
- more examples
- bugfixes
- documentation

# Using with maven

```xml
<dependency>
   <groupId>com.vaadin</groupId>
   <artifactId>leafletf4vaadin</artifactId>
   <version>0.3.0-beta</version>
</dependency>

<repository>
   <id>vaadin-addons</id>
   <url>https://maven.vaadin.com/vaadin-addons</url>
</repository>
```
Copy the above dependencies to your Maven pom.xml If you have any issues installing, please contact me.

## Example usage

```java
MapOptions options = new DefaultMapOptions();
options.setCenter(new LatLng(47.070121823, 19.204101562500004));
options.setZoom(7);
LeafletMap leafletMap = new LeafletMap(options );
leafletMap.setBaseUrl("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
add(leafletMap);
```

## Development instructions

Starting the test/demo server:
1. Run `mvn jetty:run`.
2. Open http://localhost:8080 in the browser.


## Contact

Name: Gabor Kokeny

Email: kokeny19@gmail.com
