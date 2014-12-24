package com.exadel.bsgdemo.place;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exadel.bsgdemo.ImageAdapter;
import com.exadel.bsgdemo.PagerContainer;
import com.exadel.bsgdemo.R;

public class PlaceGalleryFragment extends Fragment {
    private PagerContainer mPagerContainer;

    private final Integer[] images = {R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.place_gallery_fragment_view, null);

        assert v != null;
        mPagerContainer = (PagerContainer) v.findViewById(R.id.screen_pager_container);

        ViewPager viewPager = mPagerContainer.getViewPager();
        ImageAdapter imageAdapter = new ImageAdapter(getActivity(), R.layout.place_gallery_item_layout, images);
        viewPager.setAdapter(imageAdapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        viewPager.setOffscreenPageLimit(imageAdapter.getCount());
        //A little space between pages
        viewPager.setPageMargin(1);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        viewPager.setClipChildren(false);
        return v;
    }
}