package com.exadel.belarusattractions.internationalization;

import com.exadel.belarusattractions.utils.localization.LocaleConverter;
import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Locale;

/**
 * Developer: Yan Khonskiy
 * Created: 12:23 PM, 12/18/12
 */
public class LocaleValidator {

    @Autowired
    private SupportedLocales supportedLocales;

    @Autowired
    @Qualifier(value = "locale_en")
    private Locale defaultLocale;

    public void setSupportedLocales(SupportedLocales supportedLocales) {
        this.supportedLocales = supportedLocales;
    }

    public void setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public SupportedLocales getSupportedLocales() {
        return supportedLocales;
    }

    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    public boolean isLocaleSupported(Locale locale) {
        return supportedLocales.isLocaleSupported(new Locale(locale.getLanguage()));
    }

    public Locale validateLocale(String localeParam) throws LocaleException {
        Locale locale = LocaleConverter.parseLocale(localeParam);
        if (supportedLocales.isLocaleSupported(locale)) {
            return supportedLocales.getSupportedLocale(locale);
        }
        throw new LocaleException(locale + " is not supported");
    }

    public String getSupportedLocalesPattern() {
        List<Locale> locales = supportedLocales.getLocales();
        if (locales.size() < 1) {
            throw new RuntimeException("This app does not support locales. At least one locale should be supported.");
        }
        StringBuilder localePattern = new StringBuilder("");

        for (Locale locale : locales) {
            if (localePattern.length() == 0) {
                localePattern.append(locale.getLanguage());
            }
            else {
                localePattern.append("|").append(locale.getLanguage());
            }
        }
        return localePattern.toString();
    }
}