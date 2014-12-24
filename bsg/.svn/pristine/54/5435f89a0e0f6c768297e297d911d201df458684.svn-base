package com.exadel.bsgdemo.generators;

import com.exadel.bsgdemo.data.Profile;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:19 AM, 10/16/13
 */

@Deprecated
public class ProfileGenerator {

    private Profile profile;

    public ProfileGenerator() {
        long id = (long) (Math.random()*10000);
        int visited = (int) (Math.random()*100);
        int liked = (int) (Math.random()*100);
        int recommended = (int) (Math.random()*100);

        profile = new Profile(id, null, null, visited, liked, recommended);
    }

    public Profile getProfile() {
        return profile;
    }
}
