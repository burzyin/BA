package com.exadel.bsgdemo.place.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.near.grid.imageviews.SquareImageView;

public class GalleryGridAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mImages;
    private int mCount;
    private int mItemLayout;

    public GalleryGridAdapter(Context mContext, int mItemLayout, Integer[] mImages) {
        this.mContext = mContext;
        this.mImages = mImages;
        this.mItemLayout = mItemLayout;
        this.mCount = mImages.length;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Object getItem(int i) {
        return mImages[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflatedView = view;
        if (inflatedView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            inflatedView = layoutInflater.inflate(R.layout.place_gallery_grid_item, null);
        }
        assert inflatedView != null;
        SquareImageView imageView = (SquareImageView) inflatedView.findViewById(R.id.grid_image);
        imageView.setImageResource(mImages[i]);

        return inflatedView;
    }
}