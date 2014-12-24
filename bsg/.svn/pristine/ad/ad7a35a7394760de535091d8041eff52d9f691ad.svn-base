package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.internationalization.LocaleValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

/**
 * This controller processes requests
 * from the navigation bar.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 11:55 AM, 11/9/12
 */

@Controller
public class NavigationBarController extends AbstractController {

    private final org.apache.log4j.Logger log = Logger.getLogger(getClass());

    @Autowired
    private LocaleValidator localeValidator;

    @Override
    public void setLocaleValidator(LocaleValidator localeValidator) {
        this.localeValidator = localeValidator;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePageView(Model model) {
        log.info("Home page view.");

        Map<String,Object> modelMap = model.asMap();

        ModelAndView modelAndView = new ModelAndView("pages/home");
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView aboutPageView(Model model) {
        log.info("About page view.");

        Map<String, Object> modelMap = model.asMap();

        ModelAndView modelAndView = new ModelAndView("pages/about");
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @RequestMapping(value = "/changeLocale", method = RequestMethod.POST)
    public ModelAndView changeLocale(
            @RequestParam(value = "locale") Locale locale,
            @RequestParam(value = "backUrl") String backUrl,
            HttpServletRequest request) throws Exception {
        log.info("Change locale request.");

        String language = locale.getLanguage();
        String resultLocale = localeValidator.validateLocale(language).getLanguage();
        String resultBackUrl = backUrl;

        if ((backUrl == null) || (backUrl.equals("")) || (backUrl.equals("${backUrl}"))) {
            resultBackUrl = "";
        }

        String redirectUrl = "http://www." + resultLocale + ".app.by:" + request.getServerPort() + resultBackUrl;
        RedirectView view = new RedirectView(redirectUrl);
        view.setExposeModelAttributes(false);
        return new ModelAndView(view);
    }
}