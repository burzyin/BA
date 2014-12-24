package com.exadel.belarusattractions.configuration;

import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * MapConfigurationFactory
 * builds map view depending on mapType parameter.
 *
 * Developer: Yan Khonskiy
 * Created: 8:17 AM, 11/27/12
 */
public interface ConfigurationFactory {

    public ModelAndView getDefaultPage(Locale locale);

    public ModelAndView getDefaultPage(Long [] sightsId, Locale locale);

    public ModelAndView getDefaultPage(Long [] sightsId, int route, Locale locale);
}