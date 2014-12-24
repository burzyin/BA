/**
 *
 * Developer: Paulau Aliaksandr
 * Created: 10:30 AM, 12/26/12
 */

Array.prototype.remove = function (from, to) {
    var rest = this.slice((to || from) + 1 || this.length);
    this.length = from < 0 ? this.length + from : from;
    return this.push.apply(this, rest);
};

function postToUrl(path, key, params, method) {
    method = method || "post";

    var form = $(document.createElement("form"))
        .attr({"method": method, "action": path});

    $.each(params, function (i, val) {
        $(document.createElement("input"))
            .attr({ "type": "hidden", "name": key, "value": val })
            .appendTo(form);
    });

    form.appendTo(document.body).submit();
}

function initButtons() {
    $("input:button, button").button();
}

function initLocaleSelect() {
    var selectLocale = $("select#locale");
    selectLocale.val(locale.slice(0, 2));
    selectLocale.change(function () {
        selectLocale.parent().submit();
    });

    initLocaleSelectMenu(selectLocale);
}

function initLocaleSelectMenu(select) {
    select.selectmenu({
        style: 'popup',
        width: 150,
        icons: [
            {find: '.en'},
            {find: '.be'},
            {find: '.ru'},
            {find: '.de'}
        ]
    });

    $("#locale").find("> .flag").each(function () {
        initFlagIcon($(this).val());
    });
}

function initFlagIcon(flag) {
    $("." + flag + " .ui-selectmenu-item-icon").css('background', "url(http://www.app.by/img/" + flag + ".png) 0 0 no-repeat");
}


function initAdSlides() {
    var adSlides = $('#adSlides');
    adSlides.slides({
        preload: true,
        generateNextPrev: false,
        generatePagination: false,
        play: 5000,
        pause: 5000,
        hoverPause: true,
        randomize: true,
        container: 'adContainer',
        slideEasing: "easeInBack",
        slideSpeed: 700
    });
    //$(".slides > .prev > img").attr('src', "http://www.app.by/img/arrow-left.png");
    //$(".slides > .next > img").attr('src', "http://www.app.by/img/arrow-right.png");
    adSlides.find("> .prev").find("> img").attr('src', "http://www.app.by/img/arrow-left.png");
    adSlides.find("> .next").find("> img").attr('src', "http://www.app.by/img/arrow-right.png");
}