package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.LocalePatternFormatter;
import com.exadel.belarusattractions.internationalization.LocaleValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Developer: Yan Khonskiy
 * Created: 4:37 PM, 2/4/13
 */

public class LocaleHandlerInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private LocaleValidator localeValidator;

    public void setLocaleValidator(LocaleValidator localeValidator) {
        this.localeValidator = localeValidator;
    }

    /**
     * Parses locale from url.
     * If there is not selected locale, default locale will be redirected.
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return the result of handling: true or false accordingly to is locale supported or unsupported
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle.");

        Pattern pattern = LocalePatternFormatter.formPattern(localeValidator);
        Matcher matcher = pattern.matcher(request.getRequestURL().toString());

        if ((matcher.find()) && (matcher.group(2) != null)) {
            Locale locale = localeValidator.validateLocale(matcher.group(2));
            setViewLocale(request, response, new Locale(locale.getLanguage()));
        } else {
            Locale requestLocale = resolveLocaleFromRequest(request);
            String redirectUrl = "http://www." + requestLocale.getLanguage() + ".app.by:" + request.getServerPort() + request.getRequestURI();
            response.sendRedirect(redirectUrl);
            return false;
        }
        return true;
    }

    /**
     * Sets view locale.
     * If locale from request is supported,
     * it will be set, otherwise default locale will be set.
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param locale
     */
    private void setViewLocale(HttpServletRequest request,
                               HttpServletResponse response,
                               Locale locale) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response, locale);
    }

    /**
     * Resolves locale from request.
     * If applications supports locale from request,
     * it will be returned, otherwise default locale will be returned.
     *
     * @param request current HTTP request
     * @return resolved locale.
     */
    private Locale resolveLocaleFromRequest(HttpServletRequest request) {
        Locale requestLocale = request.getLocale();
        if (requestLocale == null) {
            return localeValidator.getDefaultLocale();
        }
        if (!localeValidator.isLocaleSupported(requestLocale)) {
            requestLocale = localeValidator.getDefaultLocale();
        }
        return requestLocale;
    }
}