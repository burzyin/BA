package com.exadel.bsgdemo.near.grid.adapters;


import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;

import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.near.grid.imageviews.ScaleImageView;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:25 AM, 12/17/13
 */

public class StaggeredGridAdapter extends SimpleGridAdapter {

    public StaggeredGridAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    protected View initializeRootView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.near_grid_item_staggered, null);
    }

    @Override
    protected void initializeImage(View view, Place place) {
        ScaleImageView imageView = (ScaleImageView) view.getTag(R.id.place_icon);
        assert imageView != null;
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagesPath + place.getIconPath()));
    }
}