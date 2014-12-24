package com.exadel.belarusattractions.exception_resolver;

import com.exadel.belarusattractions.AbstractTestClass;
import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.Map;

/**
 * Developer: Paulau Aliaksandr
 * Created: 9:31 AM, 3/20/13
 */

public class ExceptionResolverTest extends AbstractTestClass {

    @Autowired
    private ExceptionResolver exceptionResolver;

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
    public void testDoResolveException() throws Exception {
        ModelAndView modelAndView = exceptionResolver.doResolveException(request, response, handler, new LocaleException("Wrong locale."));
        Assert.assertNotNull(modelAndView);

        Map<String,Object> model = modelAndView.getModel();
        Object expectedLocale = model.get("locale");
        Locale actualLocale = new Locale("en");
        Assert.assertEquals(expectedLocale, actualLocale);

        String viewName = modelAndView.getViewName();
        String actualName = "pages/error/locale-does-not-exist";
        Assert.assertEquals(viewName, actualName);
    }
}
