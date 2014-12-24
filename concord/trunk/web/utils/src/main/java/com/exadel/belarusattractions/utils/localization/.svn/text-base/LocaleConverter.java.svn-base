package com.exadel.belarusattractions.utils.localization;

import org.apache.commons.lang.LocaleUtils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility methods for Locale.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 6:38 PM, 12/14/12
 */

public class LocaleConverter {

    /**
     * Parses a String to get locale.
     * This String must contains language param and
     * (optional) country param.
     *
     * @param localeParam - a String to be parsed.
     * @return available Locale.
     * @throws LocaleException will be thrown if the localeParam
     *                         contains wrong locale (there is no such locale.)
     */
    public static Locale parseLocale(String localeParam) throws LocaleException {

        Pattern pattern = Pattern.compile("^([a-zA-Z]{2,3})([_-]([a-zA-Z]+))?$");
        Matcher matcher = pattern.matcher(localeParam);

        if (matcher.find()) {
            String language = matcher.group(1);
            String country = matcher.group(3);
            Locale locale = new Locale(language);

            if (LocaleUtils.isAvailableLocale(locale)) {
                return (country == null) ? locale : new Locale(language, country);
            }
        }

        throw new LocaleException("Such locale " + localeParam + " does not exist.");
    }
}