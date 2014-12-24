package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.configuration.ConfigurationFactory;
import com.exadel.belarusattractions.utils.localization.LocaleException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * MapController.
 * Processes "/view-map-page" requests.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 1:52 AM, 11/26/12
 */
@Controller
public class MapController extends AbstractController {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    @Qualifier("mapConfigurationFactory")
    private ConfigurationFactory factory;

    @RequestMapping(value = "/map/view-map-page-all-sights", method = RequestMethod.GET)
    public ModelAndView viewMapPage(Model model) {
        log.info("Map with all sights page view.");

        Map<String,Object> modelMap = model.asMap();
        Locale locale = (Locale) modelMap.get("locale");

        ModelAndView modelAndView = factory.getDefaultPage(locale);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @RequestMapping(value = "/map/view-map-page-selected-sights", method = RequestMethod.POST)
    public ModelAndView viewSelectedSights(@RequestParam(value = "sightsId") Long[] sightsId,
                                           Model model,
                                           HttpServletRequest request,
                                           HttpServletResponse response,
                                           HttpSession session) {
        log.info("Map with selected sights page view. Request method: POST");

        Map<String, Object> modelMap = model.asMap();
        Locale locale = (Locale) modelMap.get("locale");

        session.setAttribute("sightsId", sightsId);
        response.addCookie(CommonMethodController.createSessionIdCookie(request));

        ModelAndView modelAndView = factory.getDefaultPage(sightsId, locale);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @RequestMapping(value = "/map/view-map-page-selected-sights", method = RequestMethod.GET)
    public ModelAndView viewSelectedSightsGet(Model model,
                                              HttpSession session,
                                              HttpServletRequest request) throws IOException {
        log.info("Map with selected sights page view. Request method: GET");

        if (session.getAttribute("sightsId") == null) {
            RedirectView view = new RedirectView(request.getContextPath() + request.getServletPath() + "/map/view-map-page-all-sights");
            view.setExposeModelAttributes(false);
            return new ModelAndView(view);
        }

        Long[] sightsId = (Long[]) session.getAttribute("sightsId");
        Map<String, Object> modelMap = model.asMap();
        Locale locale = (Locale) modelMap.get("locale");

        ModelAndView modelAndView = factory.getDefaultPage(sightsId, locale);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @RequestMapping(value = "/map/selected-sights/{id}", method = RequestMethod.GET)
    public ModelAndView viewSightOnMap(@PathVariable String id,
                                       Model model) {
        log.info("View selected sight on map with id: " + id);

        Long[] sightsId = {Long.parseLong(id)};
        Map<String, Object> modelMap = model.asMap();
        Locale locale = (Locale) modelMap.get("locale");

        ModelAndView modelAndView = factory.getDefaultPage(sightsId, locale);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @RequestMapping(value = "/map/view-route-page", method = RequestMethod.POST)
    public ModelAndView viewRoutePage(@RequestParam(value = "sightsId") Long[] sightsId,
                                      Model model,
                                      HttpSession session,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws LocaleException {
        log.info("View route on map page. Request method: POST.");

        Locale locale = (Locale) model.asMap().get("locale");

        session.setAttribute("sightsId", sightsId);
        response.addCookie(CommonMethodController.createSessionIdCookie(request));

        ModelAndView modelAndView = factory.getDefaultPage(sightsId, 1, locale);
        modelAndView.addAllObjects(model.asMap());
        return modelAndView;
    }

    @RequestMapping(value = "/map/view-route-page", method = RequestMethod.GET)
    public ModelAndView viewRoutePageGet(HttpServletRequest request,
                                         Model model,
                                         HttpSession session) throws LocaleException {
        log.info("View route on map page. Request method: GET.");

        if (session.getAttribute("sightsId") == null) {
            RedirectView view = new RedirectView(request.getContextPath() + request.getServletPath() + "/map/view-map-page-all-sights");
            view.setExposeModelAttributes(false);
            return new ModelAndView(view);
        }

        Long[] sightsId = (Long[]) session.getAttribute("sightsId");
        Locale locale = (Locale) model.asMap().get("locale");

        ModelAndView modelAndView = factory.getDefaultPage(sightsId, 1, locale);
        modelAndView.addAllObjects(model.asMap());
        return modelAndView;
    }
}