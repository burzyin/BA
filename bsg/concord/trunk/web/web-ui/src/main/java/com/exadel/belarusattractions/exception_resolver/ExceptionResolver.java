package com.exadel.belarusattractions.exception_resolver;

import com.exadel.belarusattractions.internationalization.LocaleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Developer: Yan Khonskiy
 * Created: 3:45 PM, 2/1/13
 */

public class ExceptionResolver extends SimpleMappingExceptionResolver {

    @Autowired
    private LocaleValidator localeValidator;

    public void setLocaleValidator(LocaleValidator localeValidator) {
        this.localeValidator = localeValidator;
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object handler,
                                              Exception exception) {

        Locale defaultLocale = localeValidator.getDefaultLocale();

        ModelAndView modelAndView = super.doResolveException(request, response, handler, exception);
        modelAndView.addObject("locale", defaultLocale);
        return modelAndView;
    }
}
