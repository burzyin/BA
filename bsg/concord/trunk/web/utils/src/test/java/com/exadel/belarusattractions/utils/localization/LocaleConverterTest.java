package com.exadel.belarusattractions.utils.localization;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:46 AM, 3/20/13
 */

public class LocaleConverterTest {

    @Test
    public void testParseLocaleWithoutCountry() throws Exception {
        String localeParam = "en";
        Locale expectedLocale = LocaleConverter.parseLocale(localeParam);
        Assert.assertNotNull(expectedLocale);

        Locale actualLocale = new Locale("en");
        Assert.assertEquals(expectedLocale, actualLocale);
    }

    @Test
    public void testParseLocaleWithCountry() throws Exception {
        String localeParam = "en_US";
        Locale expectedLocale = LocaleConverter.parseLocale(localeParam);
        Assert.assertNotNull(expectedLocale);

        Locale actualLocale = new Locale("en", "US");
        Assert.assertEquals(expectedLocale, actualLocale);
    }

    @Test(expected = LocaleException.class)
    public void testParseLocaleWrongLocale() throws Exception {
        String localeParam = "WRONG_WRONG";
        LocaleConverter.parseLocale(localeParam);
    }
}
