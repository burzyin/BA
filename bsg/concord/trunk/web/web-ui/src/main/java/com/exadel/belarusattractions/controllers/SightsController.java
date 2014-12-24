package com.exadel.belarusattractions.controllers;


import com.exadel.belarusattractions.dto.sights.Sight;
import com.exadel.belarusattractions.services.SightsService;
import com.exadel.belarusattractions.utils.json.JsonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * SightsController.
 * Gets sights in ModelAndView.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 2:45 AM, 11/26/12
 */
@Controller
public class SightsController extends AbstractController {

    private final org.apache.log4j.Logger log = Logger.getLogger(getClass());

    @Autowired
    private SightsService sightsService;

    @ResponseBody
    @RequestMapping(value = "/sights/sights-list", method = RequestMethod.GET)
    public String getSights(Model model) {
        log.info("Sights list request.");

        Locale locale = (Locale) model.asMap().get("locale");
        List<Sight> sightList = sightsService.getSights(locale);
        return JsonUtils.toJson(sightList);
    }

    @RequestMapping(value = "/sights/sights-list-page", method = RequestMethod.GET)
    public ModelAndView sightsListView(Model model) {
        log.info("Sights list page view.");

        Map<String, Object> modelMap = model.asMap();
        Locale locale = (Locale) modelMap.get("locale");
        List<Sight> sightsList = sightsService.getSights(locale);

        ModelAndView modelAndView = new ModelAndView("pages/sights/sightsList");
        modelAndView.addObject("sightsList", sightsList);
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }
}