package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.dto.map.MapType;
import com.exadel.belarusattractions.dto.map.properties.MapProperties;
import com.exadel.belarusattractions.dto.routes.Route;
import com.exadel.belarusattractions.dto.sights.Sight;
import com.exadel.belarusattractions.services.MapService;
import com.exadel.belarusattractions.services.RoutesService;
import com.exadel.belarusattractions.services.SightsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * SightDescriptionController.
 * Processes /description/{code} requests.
 * Where {code} - short code of the sight.
 * <p/>
 * Developer: Paulau Aliaksandr
 * Created: 5:05 AM, 11/29/12
 */

@Controller
public class SightDescriptionController extends AbstractController {

    private final org.apache.log4j.Logger log = Logger.getLogger(getClass());

    @Autowired
    private SightsService sightsService;

    @Autowired
    private RoutesService routesService;

    @Autowired
    private MapService mapService;

    @RequestMapping(value = "/sights/description/{code}", method = RequestMethod.GET)
    public ModelAndView descriptionView(@PathVariable String code, Model model) {
        log.info("Description page view. Request code: " + code);

        Map<String,Object> modelMap = model.asMap();
        Locale locale = (Locale) modelMap.get("locale");
        Sight sight = sightsService.getSightByCode(code, locale);
        List<Route> relatedRoutes = routesService.getRelatedRoutes(sight.getId());
        MapProperties mapProperties = mapService.getMapProperties(MapType.GOOGLE_MAP);

        ModelAndView modelAndView = new ModelAndView("pages/sights/sightDescription");
        modelAndView.addObject("sight", sight);
        modelAndView.addObject("routes", relatedRoutes);
        modelAndView.addObject("properties", mapProperties);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }
}