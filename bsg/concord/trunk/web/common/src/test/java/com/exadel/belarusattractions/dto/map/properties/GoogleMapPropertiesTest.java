package com.exadel.belarusattractions.dto.map.properties;

import com.exadel.belarusattractions.common.AbstractUIObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


/**
* Developer: Yan Khonskiy
* Created: 5:35 AM, 11/27/12
*/
public class GoogleMapPropertiesTest {

    @Test
    public void testToString() {
        AbstractUIObject abstractUIObject = initGoogleMapProperties_0();
        String json = abstractUIObject.toString();
        System.out.println(json);
        assertNotNull(json);

        abstractUIObject = initGoogleMapProperties_1();
        json = abstractUIObject.toString();
        System.out.println(json);
        assertNotNull(json);
    }

    @Test
    public void testToJsString() {
        AbstractUIObject abstractUIObject = initGoogleMapProperties_0();
        String json = abstractUIObject.toJsString();
        System.out.println(json);
        assertNotNull(json);

        abstractUIObject = initGoogleMapProperties_1();
        json = abstractUIObject.toJsString();
        System.out.println(json);
        assertNotNull(json);
    }

    private AbstractUIObject initGoogleMapProperties_0() {
        GoogleMapProperties googleMapProperties = new GoogleMapProperties();
        googleMapProperties.setInitLatitude(10D);
        googleMapProperties.setInitLongitude(20D);
        googleMapProperties.setInitZoom(10);
        googleMapProperties.setKey("abc");
        return googleMapProperties;
    }

    private AbstractUIObject initGoogleMapProperties_1() {
        GoogleMapProperties googleMapProperties = new GoogleMapProperties();
        googleMapProperties.setInitLatitude(20D);
        googleMapProperties.setInitLongitude(20D);
        googleMapProperties.setInitZoom(null);
        googleMapProperties.setKey(null);
        return googleMapProperties;
    }
}