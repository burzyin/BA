package com.exadel.bsgdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

public class GalleryActivity extends CommonActivity {

    PagerContainer mPagerContainer;

    private static final Integer[] IMAGES = { R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPagerContainer = (PagerContainer) findViewById(R.id.pager_container);

        ViewPager viewPager = mPagerContainer.getViewPager();
        ImageAdapter imageAdapter = new ImageAdapter(this, R.layout.place_gallery_item_layout, IMAGES);
        viewPager.setAdapter(imageAdapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        viewPager.setOffscreenPageLimit(imageAdapter.getCount());
        //A little space between pages
        viewPager.setPageMargin(1);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        viewPager.setClipChildren(false);

    }

    @SuppressWarnings("UnusedParameters")
    public void addButtonProcess(View view) {
        Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayout() {
        return R.layout.place_gallery_fragment_view;
    }
}