package com.exadel.bsgdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:25 AM, 10/8/13
 */

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private Integer[] mImages;
    private int mCount;
    private int mItemLayout;


    public ImageAdapter(Context mContext, int mItemLayout, Integer[] mImages) {
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
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View inflatedView = layoutInflater.inflate(mItemLayout, null);

        ImageView imageView = (ImageView) inflatedView.findViewById(R.id.gallery_item_image);
        imageView.setImageResource(mImages[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        TextView textView = (TextView) inflatedView.findViewById(R.id.gallery_item_title);
        textView.setText("Gallery image");

        container.addView(inflatedView);
        return inflatedView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}