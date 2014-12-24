package com.exadel.belarusattractions.internationalization;

import com.exadel.belarusattractions.AbstractTestClass;
import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:54 AM, 3/18/13
 */

public class SupportedLocalesTest extends AbstractTestClass {

    @Autowired
    private SupportedLocales supportedLocales;

    @Test
    public void testValidateLocales() throws LocaleException {
        supportedLocales.validateLocales();
    }

    @Test
    public void testIsLocaleSupported() throws Exception {
        Locale supportedLocale = new Locale("en");
        Assert.assertTrue(supportedLocales.isLocaleSupported(supportedLocale));
    }

    @Test
    public void testIsLocaleSupportedWrongLocale() throws Exception {
        Locale supportedLocale = new Locale("WRONG");
        Assert.assertFalse(supportedLocales.isLocaleSupported(supportedLocale));
    }

}
