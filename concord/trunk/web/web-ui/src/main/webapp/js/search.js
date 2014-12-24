/**
 *
 * Developer: Paulau Aliaksandr
 * Created: 11:53 AM, 1/31/13
 */

function initSearchDeleteButton(field, button) {
    $(field).keyup(function () {
        $(button).fadeIn();
        if ($.trim($(field).val()) == "") {
            $(button).fadeOut();
        }
    });

    $(button).click(function () {
        $(field).val("");
        $(this).hide();
    });
}

function initSearchPlaceholder (field, message) {
    $(field).attr('placeholder', message);
}