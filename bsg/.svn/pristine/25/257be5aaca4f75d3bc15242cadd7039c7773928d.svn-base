function initGallery() {
    $(".gallery").find("img").hover(
        function () {
            $(this).attr('class', 'active');
            $('#poiPhoto').attr({
                'alt': $(this).attr('alt'),
                'src': $(this).attr('src')
            });
        },
        function () {
            $(this).attr('class', '');
        }
    );
}

function initRoutes(routes) {
    if (routes.length === 0) {
        $("#poiRoutesList").css('display', "none");
    }
    $("a[name='route']").click(function () {
        var count = $(this).attr('count');
        postToUrl(contextPath + servletPath + "/map/view-route-page", "sightsId", routes[count].wayPoints);
    });
}