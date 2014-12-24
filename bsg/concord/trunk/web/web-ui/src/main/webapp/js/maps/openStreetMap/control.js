function initialize(id, mapSettings, urls) {
    var options = {
            elt: document.getElementById(id),
            zoom: mapSettings.initZoom,
            latLng: {lat: mapSettings.initLatitude, lng: mapSettings.initLongitude},
            mtype: mapSettings.initMapType,
            zoomOnDoubleClick: mapSettings.initZoomOnDoubleClick
        },
        sc = new MQA.ShapeCollection();

    getAllPOI(urls, sc);
    window.map = new MQA.TileMap(options);
    map.addShapeCollection(sc);
    loadModules(map);
}

function getAllPOI(urls, sc) {
    $.getJSON(urls.sightsRequest, function (data) {
        $.each(data, function (i, properties) {
            sc.add(createPOI(properties));
        });
    });
}

function createPOI(properties) {
    var poi = new MQA.Poi({lat: properties.latitude, lng: properties.longitude});
    poi.setIcon(new MQA.Icon("http://open.mapquestapi.com/staticmap/geticon?uri=poi-red_1.png", 20, 29));
    poi.setAltIcon(new MQA.Icon("http://open.mapquestapi.com/staticmap/geticon?uri=poi-green_1.png", 20, 29));
    poi.setRolloverContent(properties.title);
    poi.setInfoContentHTML(properties.title + '<br>' + properties.prompt + '<br>' + '<a href="' + properties.detailedInformationUrl + '">Detailed information!</a>' + '<br>' + "<br><br><img src='" + properties.iconUrl + "'>");

    MQA.EventManager.addListener(poi, 'mouseover', function (evt) {
        evt.srcObject.setAltStateFlag(true);
        preloadImage(properties.iconUrl);
    });
    MQA.EventManager.addListener(poi, 'mouseout', function (evt) {
        evt.srcObject.setAltStateFlag(false);
    });

    return poi;
}

function preloadImage(img) {
    (new Image()).src = img;
}

function loadModules(map) {
    MQA.withModule('largezoom', 'geolocationcontrol', 'mousewheel', function () {

        map.addControl(
            new MQA.LargeZoom(),
            new MQA.MapCornerPlacement(MQA.MapCorner.TOP_LEFT, new MQA.Size(5, 5))
        );

        map.addControl(
            new MQA.GeolocationControl(),
            new MQA.MapCornerPlacement(MQA.MapCorner.TOP_RIGHT, new MQA.Size(10, 50))
        );

        map.enableMouseWheelZoom();
    });
}