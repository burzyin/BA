package com.exadel.bsgdemo.newroute;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.data.Place;

public class EditRouteDialogFragment extends android.support.v4.app.DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Cursor cursor = getActivity().getContentResolver().query(PlaceContentProvider.PLACES_URI, null, null, null, null);
        final ListView listView = new ListView(getActivity());
        listView.setAdapter(new RouteAdapter(getActivity(), cursor));
        //cursor.close();
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = ContentUris.withAppendedId(PlaceContentProvider.PLACES_URI, l);
                assert uri != null;
                Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
                assert cursor != null;
                if (cursor.moveToFirst()) {
                    Place p = new Place(cursor);
                    if (!RouteManager.getInstance().contains(p))
                        RouteManager.getInstance().addPointToCurRoute(p);
                    else
                        RouteManager.getInstance().removeItem(p);
                    ((CursorAdapter) listView.getAdapter()).notifyDataSetChanged();
                }
                cursor.close();
            }
        });
        return listView;
    }

    private class RouteAdapter extends CursorAdapter {

        public RouteAdapter(Context context, Cursor cursor) {
            super(context, cursor);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Place place = new Place(cursor);
            TextView textView = (TextView) view;
            textView.setTextSize(20);
            textView.setText(place.getTitle());
            if (RouteManager.getInstance().contains(place))
                textView.setTextColor(Color.GREEN);
            else
                textView.setTextColor(Color.BLACK);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return new TextView(getActivity());
        }
    }
}
