package com.exadel.bsgdemo.generators;

import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Guide;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:05 AM, 10/17/13
 */

@Deprecated
public class GuideGenerator {
    private List<Guide> guides;

    private int[] options = {R.drawable.buy, R.drawable.download, R.drawable.delete};

    public GuideGenerator() {
        guides = new ArrayList<Guide>();
        generateGuides();
    }

    private void generateGuides() {
        int SIZE = 20;
        String cost;
        for (int i = 0; i < SIZE; i++) {
            cost = "";
            int rand = ((int) (Math.random() * 10)) % 3;
            if (rand == 0) {
                cost = (int) (Math.random() * 10 + 1) + "$";
            } else {
                if (rand == 1) {
                    cost = "бесплатно";
                }
            }
            guides.add(createGuide(
                    (long) (i + 1),
                    "Guide" + i,
                    "Guide short description" + i,
                    HtmlContent.PICTURE,
                    cost,
                    options[rand],
                    "Minsk",
                    "iconPath"   // TODO: implement icon path

            ));
        }
    }

    private Guide createGuide(long id, String name, String shortDescription, String longDescription, String cost, int option, String place, String iconPath) {
        return new Guide(id, name, shortDescription, longDescription, cost, option, place, iconPath);
    }

    public List<Guide> getGuides() {
        return guides;
    }

    public Guide getGuideById(long id) {
        return guides.get((int) id);
    }
}
