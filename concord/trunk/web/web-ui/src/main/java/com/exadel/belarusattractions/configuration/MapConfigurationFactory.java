package com.exadel.belarusattractions.configuration;


import com.exadel.belarusattractions.dto.sights.Sight;
import com.exadel.belarusattractions.services.MapService;
import com.exadel.belarusattractions.services.SightsService;
import com.exadel.belarusattractions.utils.json.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

/**
 * An object of MapConfigurationFactory
 * forms a ModelAndView depending on MapType.
 * <p/>
 * Developer: Yan Khonskiy
 * Created: 4:01 AM, 11/26/12                                       `
 */
public class MapConfigurationFactory implements ConfigurationFactory {

    @Autowired
    private MapService mapService;

    @Autowired
    private SightsService sightsService;

    public void setMapService(MapService mapService) {
        this.mapService = mapService;
    }

    public void setSightsService(SightsService sightsService) {
        this.sightsService = sightsService;
    }

    @Override
    public ModelAndView getDefaultPage(Locale locale) {
        List<Sight> sights = sightsService.getSights(locale);

        ModelAndView modelAndView = formDefaultModelAndView();
        modelAndView.addObject("sights", JsonUtils.toJsString(sights));
        return modelAndView;
    }

    @Override
    public ModelAndView getDefaultPage(Long[] sightsId, Locale locale) {
        List<Sight> selectedSights = sightsService.getSelectedSights(sightsId, locale);

        ModelAndView modelAndView = formDefaultModelAndView();
        modelAndView.addObject("sights", JsonUtils.toJsString(selectedSights));
        return modelAndView;
    }

    @Override
    public ModelAndView getDefaultPage(Long[] sightsId, int route, Locale locale) {
        List<Sight> selectedSights = sightsService.getSelectedSights(sightsId, locale);

        ModelAndView modelAndView = formModelAndView();
        modelAndView.addObject("sights", JsonUtils.toJsString(selectedSights));
        modelAndView.addObject("route", route);
        return modelAndView;
    }

    private ModelAndView formModelAndView() {
        String typeCode = mapService.getDefaultMapType().getTypeCode();
        ModelAndView modelAndView = new ModelAndView("pages/maps/" + typeCode + "/defaultView");
        modelAndView.addObject("properties", mapService.getMapProperties());
        return modelAndView;
    }

    private ModelAndView formDefaultModelAndView() {
        ModelAndView modelAndView = formModelAndView();
        modelAndView.addObject("route", 0);
        return modelAndView;
    }
}