var colors = {active: "FF0000", passive: "38A9FF", neutral: "8DB81D"};
var startMarker;
var wayPoints = [];
var directionsDisplay;

function initGoogleMap(mapPlaceHolderId, routePlaceholderId, routeWrapperId, mapSettings, sights, route) {
    var mapOptions = getMapOptions(mapSettings, sights),
        mapPlaceHolder = $("#" + mapPlaceHolderId)[0],
        routePlaceHolder = $("#" + routePlaceholderId)[0],
        routeWrapper = $("#" + routeWrapperId)[0],
        map = new google.maps.Map(mapPlaceHolder, mapOptions),
        infoWindow = new google.maps.InfoWindow({content: ''});

    directionsDisplay = initDirectionsDisplay(map, routePlaceHolder);

    addMarkers(map, sights, infoWindow);

    initStartMarker(map);

    if (document.location.href.split('description').length === 1) {
        addStartMarkerListener(map);
    }

    if ((route === 1) && (sights.length >= 2)) {
        calcRoute(routeWrapper, sights);
    }
}

function addMarkers(map, sights, infoWindow) {
    for (var i = 0; i < sights.length; i++) {
        addMarker(map, sights[i], infoWindow);
    }
}

function addMarker(map, val, infoWindow) {
    var marker = initMarker(map, val, colors.passive);
    if (document.location.href.split('description').length === 1) {
        addMarkerListener(map, marker, val, infoWindow);
    }
}

function addMarkerListener(map, marker, val, infoWindow) {

    google.maps.event.addListener(marker, 'mouseover', function () {
        infoWindow.setContent("<div class='infoWindow'>" +
            "<h4>" + val.name + "</h4>" +
            "<br/>" +
            "<img src='" + val.pictogramUrl + "'>" +
            "<p>" + val.shortDescription + "</p>" +
            "<a href='" + contextPath + servletPath + "/sights/description/" + val.code + "'>Detailed Information</a>" +
            "</div>"
        );
        infoWindow.open(map, marker);
    });

    google.maps.event.addListener(marker, 'mouseout', function () {
        setTimeout(function () {
            infoWindow.close();
        }, 10000);

    });

    function removeWayPoint(position) {
        $.each(wayPoints, function (i, val) {
            if (val.location === position) {
                wayPoints.remove(i);
                return false;
            }
        });
    }

    google.maps.event.addListener(marker, 'click', function () {
        var currentColor = getIconColor(marker);
        if (currentColor === colors.active) {
            marker.setIcon(createIcon(colors.passive));
            removeWayPoint(marker.position);
        } else {
            marker.setIcon(createIcon(colors.active));
            wayPoints.push({location: marker.position, stopover: true})
        }
    });
}

function addStartMarkerListener(map) {
    google.maps.event.addListener(map, 'click', function (event) {
        addStartMarker(event.latLng);
    });
}

function addStartMarker(location) {
    if (!startMarker.position) {
        startMarker.setPosition(location);
        wayPoints.unshift({location: location});
    }
    else {
        startMarker.setPosition(location);
        wayPoints[0].location = startMarker.position;
    }
}

function initStartMarker(map) {
    startMarker = new google.maps.Marker({
        map: map,
        icon: createIcon(colors.neutral)
    });
}

function initMarker(map, val, color) {
    return new google.maps.Marker({
        title: val.title,
        position: new google.maps.LatLng(val.latitude, val.longitude),
        map: map,
        icon: createIcon(color)
    });
}

function calcRoute(routeWrapper, sights) {

    $(routeWrapper).removeAttr('hidden');

    if (sights !== undefined) {
        wayPoints = initWayPoints(sights);
    }

    var route = initRouteWithWayPoints(wayPoints);

    initDirectionsService(route, directionsDisplay);
}

function initDirectionsService(route, directionsDisplay) {
    var directionsService = new google.maps.DirectionsService();
    directionsService.route(route, function (response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
    });
}

function initRouteWithWayPoints(wayPoints) {
    return {
        origin: wayPoints[0].location,
        destination: wayPoints[wayPoints.length - 1].location,
        waypoints: wayPoints.slice(1, -1),
        optimizeWaypoints: true,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };
}

function initDirectionsDisplay(map, routePlaceHolder) {
    var directionsDisplay = new google.maps.DirectionsRenderer();
    directionsDisplay.setMap(map);
    directionsDisplay.setPanel(routePlaceHolder);
    return directionsDisplay;
}

function initWayPoints(sights) {
    var wayPoints = [];
    $.each(sights, function (i, val) {
        wayPoints.push({
                location: new google.maps.LatLng(val.latitude, val.longitude),
                stopover: true
            }
        );
    });
    return wayPoints;
}

function createIcon(color) {
    return new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + color,
        new google.maps.Size(21, 34),
        new google.maps.Point(0, 0),
        new google.maps.Point(10, 34));
}

function getIconColor(marker) {
    return marker.icon.url.slice(-6);
}

function getMapOptions(mapSettings, sights) {
    var center;
    var zoom;
    if (sights.length === 1) {
        center = new google.maps.LatLng(sights[0].latitude, sights[0].longitude);
        zoom = mapSettings.initZoom + 2;
    } else {
        center = new google.maps.LatLng(mapSettings.initLatitude, mapSettings.initLongitude);
        zoom = mapSettings.initZoom;
    }
    return initMapOptions(center, zoom);
}

function initMapOptions(center, zoom) {
    return {
        center: center,
        zoom: zoom,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
}

function findCurrentLocation() {
    navigator.geolocation.getCurrentPosition(function (position) {
        addStartMarker(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
    });
}