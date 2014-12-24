package com.exadel.bsgdemo.place.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

import com.exadel.bsgdemo.ImageAdapter;
import com.exadel.bsgdemo.PagerContainer;
import com.exadel.bsgdemo.R;

public class PlaceGallery extends Fragment {
    private enum GalleryType {
        none, Grid, View, Slide
    }

    private GalleryType currentType = GalleryType.none;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_gallery_fragment_layout, null);
        ImageButton button1 = (ImageButton) view.findViewById(R.id.top_switcher_grid);
        ImageButton button2 = (ImageButton) view.findViewById(R.id.top_switcher_map);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                galleryChangeType(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                galleryChangeType(v);
            }
        });

        switchType(GalleryType.View);

        return view;
    }

    public void switchGrid() {
        GalleryType targetGrid = currentType == GalleryType.View ? GalleryType.Grid : GalleryType.View;
        switchType(targetGrid);
    }

    private void switchType(GalleryType fragment) {

        Fragment targetType = null;
        switch (fragment) {
            case View:
                targetType = new ViewFragment();
                currentType = GalleryType.View;
                break;
            case Grid:
                targetType = new GridFragment();
                currentType = GalleryType.Grid;
                break;
            default:
                break;
        }

        if (currentType.equals(GalleryType.none)) {
            if (targetType != null)
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.gallery_container, targetType,
                                "mGallery").commit();
        } else {
            if (targetType != null)
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.gallery_container, targetType,
                                "mGallery").commit();
        }
    }

    private class ViewFragment extends android.support.v4.app.Fragment {
        private PagerContainer mPagerContainer;

        private final Integer[] images = {R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal};

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

    private class GridFragment extends Fragment {
        private GridView mGridView;
        private GalleryGridAdapter mAdapter;

        private final Integer[] images = {R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal};

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.place_gallery_fragment_grid, null);

            assert view != null;
            mGridView = (GridView) view.findViewById(R.id.placeGridView);
            mAdapter = new GalleryGridAdapter(getActivity(), R.layout.place_gallery_grid_item, images);
            mGridView.setAdapter(mAdapter);
            return view;
        }
    }

    public void galleryChangeType(View v) {
        switch (v.getId()) {
            case R.id.top_switcher_grid:
                switchType(GalleryType.Grid);
                break;
            case R.id.top_switcher_map:
                switchType(GalleryType.View);
                break;
            default:
                break;
        }
    }
}