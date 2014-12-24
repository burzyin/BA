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

import com.exadel.bsgdemo.MediaContentProvider;
import com.exadel.bsgdemo.data.Place;

public class EditRouteDialogFragment extends android.support.v4.app.DialogFragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		Cursor c = getActivity().getContentResolver().query(MediaContentProvider.PLACES_URI, null, null, null, null);
		final ListView lv = new ListView(getActivity());
		lv.setAdapter(new mAdapter(getActivity(), c));
		//c.close();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Uri uri = ContentUris.withAppendedId(MediaContentProvider.PLACES_URI, arg3);
				Cursor c = getActivity().getContentResolver().query(uri, null, null, null, null);
				if(c.moveToFirst()){
					Place p = new Place(c);
					if(!RouteManager.getInstance().cointains(p))
						RouteManager.getInstance().addPointToCurRoute(p);
					else
						RouteManager.getInstance().removeItem(p);
					((CursorAdapter)lv.getAdapter()).notifyDataSetChanged();
				}
				c.close();
			}
		});
		return lv;
	}
	
	private class mAdapter extends CursorAdapter{

		public mAdapter(Context context, Cursor c) {
			super(context, c);
		}

		@Override
		public void bindView(View arg0, Context arg1, Cursor arg2) {
			Place p = new Place(arg2);
			TextView tv = (TextView) arg0;
			tv.setTextSize(20);
			tv.setText(p.getTitle());
			if(RouteManager.getInstance().cointains(p))
				tv.setTextColor(Color.GREEN);
			else
				tv.setTextColor(Color.BLACK);
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
			TextView tv = new TextView(getActivity());
			return tv;
		}
		
	} 
}
