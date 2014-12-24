function uncheckAll() {
    $('input:checkbox').removeAttr('checked');
    $('button').button('disable');
}

function submitForm(param) {
    var selectedSights = $("#selectedSights");

    if (param == 1) {
        selectedSights.attr('action', contextPath + servletPath + "/map/view-route-page");
    }
    else if (param == 2) {
        selectedSights.attr('action', contextPath + servletPath + "/map/view-map-page-selected-sights");
    }
    else if (param == 3) {
        alert("This feature does not work. In the future the user will be able to add their selected places.");
    }
    selectedSights.submit();
}

function handleChange() {
    var checked = $("input:checkbox:checked").length;

    if (checked >= 2) {
        $('#routeButton').button('enable');
        $("#wrapRouteButton").attr('title', '');
    }
    if (checked >= 1) {
        $('#mapButton').button('enable');
        $("#wrapMapButton").attr('title', '');
    }
    if (checked < 2) {
        $('#routeButton').button('disable');
        $("#wrapRouteButton").attr('title', "It will available if you choose more than 2 points");
    }
    if (checked < 1) {
        $('#mapButton').button('disable');
        $("#wrapMapButton").attr('title', "It will available if you choose more than 1 points");
    }
}

function initTooltip() {
    $("#wrapRouteButton").attr('title', "It will available if you choose more than 2 points");
    $("#wrapMapButton").attr('title', "It will available if you choose more than 1 points");
    $("#wrapGuideButton").attr('title', "It is not available yet");

    $(document).tooltip();
}

function bindActions() {
    $("#routeButton").click(function () {
        submitForm(1);
        return false;
    });

    $("#mapButton").click(function () {
        submitForm(2);
        return false;
    });

    $("#guideButton").click(function () {
        submitForm(3);
        return false;
    });

    $("input:checkbox").change(function () {
        handleChange();
    });
}