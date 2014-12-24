package com.exadel.belarusattractions.controllers;

import com.exadel.belarusattractions.dto.routes.Route;
import com.exadel.belarusattractions.services.RoutesService;
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
import java.util.Map;

/**
 * Routes controller.
 * Processes routes requests.
 * </p>
 * Developer: Paulau Aliaksandr
 * Created: 12:36 PM, 1/3/13
 */
@Controller
public class RoutesController extends AbstractController {

    private final org.apache.log4j.Logger log = Logger.getLogger(getClass());

    @Autowired
    private RoutesService routesService;

    @ResponseBody
    @RequestMapping(value = "/routes/routes-list", method = RequestMethod.GET)
    public String getRoutes(Model model) {
        log.info("Routes list request.");

        List<Route> routes = routesService.getRoutes();
        return JsonUtils.toJson(routes);
    }

    /**
     *
     * @param model Model with properties such as locale.
     * @return modelAndView
     */
    @RequestMapping(value = "/routes/routes-list-page", method = RequestMethod.GET)
    public ModelAndView routesListView(Model model) {
        log.info("Routes list page view.");

        Map<String,Object> modelMap = model.asMap();

        ModelAndView modelAndView = new ModelAndView("pages/routes/routesList");
        modelAndView.addObject("routesList", routesService.getRoutes());
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }
}