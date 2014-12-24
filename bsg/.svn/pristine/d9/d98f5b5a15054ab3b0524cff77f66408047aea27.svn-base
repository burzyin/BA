/**
 *
 * Developer: Paulau Aliaksandr
 * Created: 10:25 AM, 4/3/13
 */

function connectFacebookSDK (locale) {
    var js, id = 'facebook-jssdk';
    if (document.getElementById(id)) {
        return;
    }
    js = document.createElement('script');
    js.id = id;
    js.async = true;
    js.src = "//connect.facebook.net/" + locale + "/all.js";
    document.getElementsByTagName('head')[0].appendChild(js);
}

window.fbAsyncInit = function () {
    FB.init({
        appId: '318115051647965', // App ID from the App Dashboard
        status: true, // check the login status upon init?
        cookie: true, // set sessions cookies to allow your server to access the session?
        xfbml: true  // parse XFBML tags on this page?
    });

    FB.Event.subscribe('auth.login', function () {
        var dialog = $("#dialog");

        dialog.find("> p").text('Welcome!  Fetching your information.... ');
        dialog.dialog({
            title: "Facebook login",
            draggable: false,
            resizable: false,
            modal: true,
            hide: "fade"
        });
        FB.api('/me', function (response) {
            setTimeout(function () {
                dialog.find("> p").text("Welcome, " + response.name + '.');
            }, 1000);
        });
        setTimeout(function () {
            dialog.dialog("close");
        }, 2000);
    });

    FB.Event.subscribe('auth.logout', function () {
        var dialog = $("#dialog");

        dialog.find("> p").text('Good bye!');
        dialog.dialog({
            title: "Facebook logout",
            draggable: false,
            resizable: false,
            modal: true,
            hide: "fade"
        });

        setTimeout(function () {
            dialog.dialog("close");
        }, 2000);
    });


    FB.getLoginStatus(function (response) {
        if (response.status === 'connected') {
            console.log('connected');
            testAPI();
        } else if (response.status === 'not_authorized') {
            console.log('not_authorized');

        } else {
            console.log('not_logged_in');

        }
    });
};

function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function (response) {
        console.log('Good to see you, ' + response.name + '.');
    });
}
