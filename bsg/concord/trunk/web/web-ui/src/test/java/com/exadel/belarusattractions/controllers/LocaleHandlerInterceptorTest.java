package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.AbstractTestClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Locale;

/**
 * Developer: Paulau Aliaksandr
 * Created: 11:13 AM, 3/18/13
 */

public class LocaleHandlerInterceptorTest extends AbstractTestClass{

    @Autowired
    private LocaleHandlerInterceptor localeHandlerInterceptor;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private Object handler;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handler = new Object();
    }

    @Test
    public void testPreHandle() throws Exception {
        Locale locale = new Locale("en");
        request.setAttribute("locale", locale);
        Assert.assertFalse(localeHandlerInterceptor.preHandle(request, response, handler));

        String expected = response.getLocale().getLanguage();
        String actual = "en";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPreHandleWithoutLocale() throws Exception {
        Assert.assertFalse(localeHandlerInterceptor.preHandle(request, response, handler));

        String expected = response.getLocale().getLanguage();
        String actual = "en";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPreHandleWrongLocale() throws Exception {
        request.setAttribute("locale", new Locale("wrong"));
        Assert.assertFalse(localeHandlerInterceptor.preHandle(request, response, handler));

        String expected = response.getLocale().getLanguage();
        String actual = "en";
        Assert.assertEquals(expected, actual);
    }
}
