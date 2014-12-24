/**
 *
 * Developer: Paulau Aliaksandr
 * Created: 1:36 PM, 12/18/12
 */

function initSlideshow() {
    $('.slides').slides({
        autoHeight: true,
        preload: true,
        bigTarget: true,
        preloadImage: 'http://www.app.by/img/loading.gif',
        play: 4000,
        pause: 1500,
        hoverPause: true,
        effect: 'fade',
        fadeSpeed: 550,
        fadeEasing: 'easeInOutSine',
        generatePagination: true,
        generateNextPrev: false,
        animationStart: function () {
            $('.caption').animate({
                top: -55
            }, 100);
        },
        animationComplete: function () {
            $('.caption').animate({
                top: 0
            }, 200);
        },
        slidesLoaded: function () {
            $('.caption').animate({
                top: 0
            }, 200);
        }
    });

    //$(".slides > .prev > img").attr('src', "http://www.app.by/img/arrow-left.png");
    //$(".slides > .next > img").attr('src', "http://www.app.by/img/arrow-right.png");
    $(".pagination li a").css('background-image', "url(http://www.app.by/img/pagination.png)");
}

function initLinks() {
    $("a.route").attr('href', contextPath + servletPath + "/routes/routes-list-page");
    $("#about").click(function () {
        document.location.href = contextPath + servletPath + "/about";
    });
}