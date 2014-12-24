package com.exadel.belarusattractions.internationalization;

import com.exadel.belarusattractions.AbstractTestClass;
import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:53 AM, 3/18/13
 */

public class LocaleValidatorTest extends AbstractTestClass {

    @Autowired
    private LocaleValidator localeValidator;

    @Test
    public void testGetSupportedLocales() throws Exception {
        SupportedLocales supportedLocales = localeValidator.getSupportedLocales();
        Assert.assertNotNull(supportedLocales);
        for (Locale locale : supportedLocales.getLocales()) {
            Assert.assertNotNull(locale);
        }
    }

    @Test
    public void testGetDefaultLocale() throws Exception {
        Locale expectedLocale = localeValidator.getDefaultLocale();
        Assert.assertNotNull(expectedLocale);

        Locale actualLocale = new Locale("en");
        Assert.assertEquals(expectedLocale, actualLocale);
    }

    @Test
    public void testIsLocaleSupported() throws Exception {
        Locale supportedLocale = new Locale("en");
        Assert.assertTrue(localeValidator.isLocaleSupported(supportedLocale));
    }

    @Test
    public void testIsLocaleSupportedWrongLocale() throws Exception {
        Locale notSupportedLocale = new Locale("WRONG");
        Assert.assertFalse(localeValidator.isLocaleSupported(notSupportedLocale));
    }

    @Test
    public void testValidateLocale() throws LocaleException {
        String localeParam = "en_US";
        Locale locale = localeValidator.validateLocale(localeParam);
        Assert.assertNotNull(locale);
    }

    @Test(expected = LocaleException.class)
    public void testValidateLocaleWrongLocale() throws LocaleException {
        String localeParam = "WRONG_WRONG";
        localeValidator.validateLocale(localeParam);
    }

    @Test
    public void testGetSupportedLocalesPattern() throws Exception {
        String localesPattern = localeValidator.getSupportedLocalesPattern();
        Assert.assertNotNull(localesPattern);
    }
}
