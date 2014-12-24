package com.exadel.belarusattractions.common;

import com.exadel.belarusattractions.utils.json.JsonUtils;

/**
 * Declare methods toJsString() and toJson
 * to be called from velocity pages.
 *
 * Developer: Yan Khonskiy
 * Created: 10:42 AM, 11/23/12
 */
public abstract class AbstractUIObject {

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }

    /**
     * This method is used in googleMapPage.vm.
     * @return escaped json formatted String.
     */
    public String toJsString() {
        return JsonUtils.toJsString(this);
    }
}