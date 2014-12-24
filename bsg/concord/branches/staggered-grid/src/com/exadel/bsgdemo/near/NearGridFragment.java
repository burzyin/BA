package com.exadel.bsgdemo.near;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.database.PlaceTable;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;

public class NearGridFragment extends android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 0x02;
    private GridView mGridView;
    private PlacesGridAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.near_grid_fragment_layout, null);
        mGridView = (GridView) view.findViewById(R.id.grid_view);

        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        return view;
    }

    private class PlacesGridAdapter extends CursorAdapter {
        private final String imagesPath = Environment.getExternalStorageDirectory() + "/BSGDemo/";

        public PlacesGridAdapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Place place = new Place(cursor);
            TextView textView = (TextView) view.findViewById(R.id.place_title);
            textView.setText(place.getTitle());

            ImageView imageView = (ImageView) view.findViewById(R.id.place_icon);
            imageView.setImageBitmap(BitmapFactory.decodeFile(imagesPath + place.getIconPath()));
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View rootView = LayoutInflater.from(context).inflate(R.layout.near_grid_item, null);
            //bindView(rootView, arg0, arg1);
            return rootView;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                PlaceTable.COLUMN_ID,
                PlaceTable.COLUMN_TITLE,
                PlaceTable.COLUMN_ICON,
                PlaceTable.COLUMN_LONGITUDE,
                PlaceTable.COLUMN_LATITUDE
        };
        return new CursorLoader(getActivity(), PlaceContentProvider.PLACES_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter = new PlacesGridAdapter(getActivity(), cursor);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class);
                target.putExtra("id", id);
                startActivity(target);
            }
        });
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);
    }
}