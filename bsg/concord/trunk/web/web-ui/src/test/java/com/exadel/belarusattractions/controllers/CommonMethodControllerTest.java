package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.AbstractTestClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.Cookie;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:54 AM, 3/20/13
 */

public class CommonMethodControllerTest extends AbstractTestClass {

    private MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
    }

    @Test
    public void testGetScheme() throws Exception {
        String scheme = CommonMethodController.getScheme(request);
        String actual = "http";
        Assert.assertEquals(scheme, actual);
    }

    @Test
    public void testGetDomain() throws Exception {
        String domain = CommonMethodController.getDomain(request);
        String actual = "localhost";
        Assert.assertEquals(domain, actual);
    }

    @Test
    public void testCreateSessionIdCookie() throws Exception {
        request.getSession().setAttribute("id", 123);
        Cookie cookie = CommonMethodController.createSessionIdCookie(request);
        Assert.assertNotNull(cookie);

        String name = cookie.getName();
        String actual = "JSESSIONID";
        Assert.assertEquals(name, actual);
    }
}
