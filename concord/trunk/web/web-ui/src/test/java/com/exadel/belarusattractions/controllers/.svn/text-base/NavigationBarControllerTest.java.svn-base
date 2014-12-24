package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.AbstractTestClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

/**
 * Developer: Paulau Aliaksandr
 * Created: 9:34 AM, 3/18/13
 */

public class NavigationBarControllerTest extends AbstractTestClass {

    @Autowired
    private NavigationBarController navigationBarController;

    private Locale locale;
    private MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        this.request =  new MockHttpServletRequest();
        this.locale = new Locale("en");
    }

    @Test
    public void testHomePageView() throws Exception {
        ExtendedModelMap model = new ExtendedModelMap();
        ModelAndView modelAndView = navigationBarController.homePageView(model);
        Assert.assertNotNull(modelAndView);

        String viewName = modelAndView.getViewName();
        String actual = "pages/home";
        Assert.assertEquals(viewName, actual);
    }

    @Test
    public void testChangeLocale() throws Exception {
        String backUrl = "/backurl";

        ModelAndView modelAndView = navigationBarController.changeLocale(locale, backUrl, request);
        Assert.assertNotNull(modelAndView);

        RedirectView view = (RedirectView) modelAndView.getView();
        String actual = "http://www." + locale.getLanguage() + ".app.by:" + request.getServerPort() + backUrl;
        Assert.assertEquals(view.getUrl(), actual);
    }

    @Test
    public void testChangeLocaleArgumentNull() throws Exception {
        String backUrl = null;

        ModelAndView modelAndView = navigationBarController.changeLocale(locale, backUrl, request);
        Assert.assertNotNull(modelAndView);

        RedirectView view = (RedirectView) modelAndView.getView();
        String actual = "http://www." + locale.getLanguage() + ".app.by:" + request.getServerPort();
        Assert.assertEquals(view.getUrl(), actual);
    }

    @Test
    public void testAboutPageView() throws Exception {
        ExtendedModelMap model = new ExtendedModelMap();
        ModelAndView modelAndView = navigationBarController.aboutPageView(model);
        Assert.assertNotNull(modelAndView);

        String viewName = modelAndView.getViewName();
        String actual = "pages/about";
        Assert.assertEquals(viewName, actual);
    }
}
