package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.AbstractTestClass;
import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Locale;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:54 AM, 3/20/13
 */

public class AbstractControllerTest extends AbstractTestClass {

    @Autowired
    private AbstractController abstractController;

    private MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
    }

    @Test
    public void testResolveLocaleFromUrl() throws LocaleException {
        request.setServerName("be.app.by");
        Locale expectedLocale = abstractController.resolveLocaleFromUrl(request);
        Assert.assertNotNull(expectedLocale);

        Locale actualLocale = new Locale("be");
        Assert.assertEquals(expectedLocale, actualLocale);
    }

    @Test
    public void testResolveLocaleFromUrlWrongLocale() throws LocaleException {
        request.setServerName("WRONG.app.by");
        Locale expectedLocale = abstractController.resolveLocaleFromUrl(request);
        Assert.assertNotNull(expectedLocale);

        Locale actualLocale = new Locale("en");
        Assert.assertEquals(expectedLocale, actualLocale);
    }

    @Test
    public void testResolveBackUrl() throws Exception {
        request.setContextPath("contextPath:");
        request.setServletPath("servletPath:");
        request.setPathInfo("pathInfo");
        String backurl = abstractController.resolveBackUrl(request);
        Assert.assertNotNull(backurl);

        String actual = "contextPath:servletPath:pathInfo";
        Assert.assertEquals(backurl, actual);
    }
}
