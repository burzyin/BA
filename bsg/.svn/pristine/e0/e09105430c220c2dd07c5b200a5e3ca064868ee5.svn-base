package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.LocalePatternFormatter;
import com.exadel.belarusattractions.internationalization.LocaleValidator;
import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AbstractController.
 * Forms default model.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 1:52 AM, 11/26/12
 */

@Controller
public class AbstractController {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private LocaleValidator localeValidator;

    public void setLocaleValidator(LocaleValidator localeValidator) {
        this.localeValidator = localeValidator;
    }

    /**
     * Resolves locale from url and puts it into the model.
     *
     *
     * @param request current HTTP request
     * @return locale from request if supported, default locale otherwise
     */
    @ModelAttribute(value = "locale")
    protected Locale resolveLocaleFromUrl(HttpServletRequest request) throws LocaleException {
        log.info("@ModelAttribute: resolveLocaleFromUrl.");

        Pattern pattern = LocalePatternFormatter.formPattern(localeValidator);
        Matcher matcher = pattern.matcher(request.getRequestURL().toString());
        Locale finalLocale;

        if ((matcher.find()) && (matcher.group(2) != null)) {
            finalLocale = localeValidator.validateLocale(matcher.group(2));
        } else {
            finalLocale = localeValidator.getDefaultLocale();
        }
        return finalLocale;
    }

    /**
     * Puts back url into model.
     *
     * @param request current HTTP request
     * @return the back url
     */
    @ModelAttribute(value = "backUrl")
    protected String resolveBackUrl(HttpServletRequest request) {
        log.info("@ModelAttribute: resolveBackUrl.");
        return getRequestUrlWithGeneralLocale(request);
    }

    /**
     * This methods forms url to send it on view.
     *
     * @param request current HTTP request
     * @return the request url with general locale
     */
    private String getRequestUrlWithGeneralLocale(HttpServletRequest request) {
        return request.getContextPath() + request.getServletPath() + request.getPathInfo();
    }
}