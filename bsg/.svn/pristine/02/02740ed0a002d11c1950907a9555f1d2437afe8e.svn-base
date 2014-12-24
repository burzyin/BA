package com.exadel.belarusattractions;

import com.exadel.belarusattractions.internationalization.LocaleValidator;

import java.util.regex.Pattern;

/**
 * Util class containing formPattern method.
 *
 * Developer: Yan Khonskiy
 * Created: 12:53 PM, 2/5/13
 */
public class LocalePatternFormatter {

    private LocalePatternFormatter() {}

    /**
     * Forms pattern containing all supported locales.
     * @param localeValidator validator that contains supported locales.
     * @return reg. expr. pattern.
     */
    public static Pattern formPattern(LocaleValidator localeValidator) {
        return Pattern.compile("((" + localeValidator.getSupportedLocalesPattern() + ")[.])?[a][p][p]");
    }
}
