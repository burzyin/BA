package com.exadel.belarusattractions.internationalization;

import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;

/**
 * List of supported locales.
 *
 * Developer: Yan Khonskiy
 * Created: 7:50 PM, 12/14/12
 */

public class SupportedLocales {

    @Autowired
    private List<Locale> locales;

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    @PostConstruct
    public void validateLocales() throws LocaleException {
        for (Locale locale : locales) {
            if (!LocaleUtils.isAvailableLocale(locale)) {
                throw new LocaleException("Such locale " + locale + " does not exist.");
            }
        }
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public Boolean isLocaleSupported(Locale locale) {
        for (Locale supportedLocale : locales) {
            if (supportedLocale.getLanguage().equals(locale.getLanguage())) {
                return true;
            }
        }
        return false;
    }

    public Locale getSupportedLocale(Locale locale) {
        for (Locale supportedLocale : locales) {
            if (supportedLocale.getLanguage().equals(locale.getLanguage())) {
                return supportedLocale;
            }
        }
        return new Locale("");
    }

    public String toString() {
        return "Amount of supported locales is " + locales.size();
    }
}