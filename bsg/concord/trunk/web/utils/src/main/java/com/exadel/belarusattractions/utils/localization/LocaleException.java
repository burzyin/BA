package com.exadel.belarusattractions.utils.localization;

import java.text.ParseException;

/**
 * LocaleException informs
 * that to create locale is impossible.
 *
 * Developer: Yan Khonskiy
 * Created: 5:42 PM, 12/18/12
 */
public class LocaleException extends ParseException {

    public LocaleException(String message) {
        super(message, 0);
    }
}