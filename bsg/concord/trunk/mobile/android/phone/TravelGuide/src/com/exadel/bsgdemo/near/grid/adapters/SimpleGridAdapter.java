package com.exadel.bsgdemo.near.grid.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.near.grid.imageviews.SquareImageView;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:09 PM, 12/22/13
 */
public class SimpleGridAdapter extends CursorAdapter {

    protected final String imagesPath = Environment.getExternalStorageDirectory() + "/BSGDemo/";

    public SimpleGridAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Place place = new Place(cursor);
        initializeText(view, place);
        initializeImage(view, place);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View rootView = initializeRootView(context);
        assert rootView != null;
        rootView.setTag(R.id.place_title, rootView.findViewById(R.id.place_title));
        rootView.setTag(R.id.place_icon, rootView.findViewById(R.id.place_icon));
        return rootView;
    }

    protected View initializeRootView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.near_grid_item_simple, null);
    }

    protected void initializeImage(View view, Place place) {
        SquareImageView imageView = (SquareImageView) view.getTag(R.id.place_icon);
        assert imageView != null;
        imageView.setImageBitmap(BitmapFactory.decodeFile(imagesPath + place.getIconPath()));
    }

    private void initializeText(View view, Place place) {
        TextView textView = (TextView) view.getTag(R.id.place_title);
        assert textView != null;
        textView.setText(place.getTitle());
    }
}