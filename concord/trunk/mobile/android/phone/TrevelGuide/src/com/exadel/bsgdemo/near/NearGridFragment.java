package com.exadel.bsgdemo.near;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.exadel.bsgdemo.MediaContentProvider;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;

public class NearGridFragment extends android.support.v4.app.Fragment implements
LoaderManager.LoaderCallbacks<Cursor>{
	private static final int LOADER_ID = 0x02;
	private ListView gv;
	private PlacesGridAdapter adapter;
	public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.near_grid_fragment_layout, null);
		gv = (ListView) v.findViewById(R.id.grid_view);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				startActivity(new Intent(getActivity(), PlaceDescriptionActivity.class));
			}
		});
		
		getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
		return v;
	}
	
	private class PlacesGridAdapter extends CursorAdapter{
		private static final int IMAGE_ID = 111;
		private static final int TITLE_ID = 222;
		public PlacesGridAdapter(Context context, Cursor c) {
			super(context, c);
		}

		@Override
		public void bindView(View arg0, Context arg1, Cursor arg2) {
			Place p = new Place(arg2);
			((TextView)arg0.findViewById(TITLE_ID)).setText(p.getTitle());
			if(p.getIconPath().equalsIgnoreCase("cheliuskintsev_park"))
				((ImageView)arg0.findViewById(IMAGE_ID)).setImageResource(R.drawable.cheliuskintsev_park);
			else if(p.getIconPath().equalsIgnoreCase("gorkogo_park"))
				((ImageView)arg0.findViewById(IMAGE_ID)).setImageResource(R.drawable.gorkogo_park);
			else if(p.getIconPath().equalsIgnoreCase("independence_square"))
				((ImageView)arg0.findViewById(IMAGE_ID)).setImageResource(R.drawable.independence_square);
			else 
				((ImageView)arg0.findViewById(IMAGE_ID)).setImageResource(R.drawable.botanic_garden);
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
			arg2 = new LinearLayout(getActivity());
			((LinearLayout)arg2).setOrientation(LinearLayout.VERTICAL);
			ImageView iv = new ImageView(getActivity()){
				protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
					setMeasuredDimension(widthMeasureSpec, widthMeasureSpec/4*3);
				};
			};
			iv.setId(IMAGE_ID);
			
			TextView tv = new TextView(getActivity());
			tv.setId(TITLE_ID);
			tv.setTextColor(Color.GREEN);
			
			((LinearLayout)arg2).addView(iv);
			((LinearLayout)arg2).addView(tv);
			return arg2;
		}
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		 return new CursorLoader(getActivity(), MediaContentProvider.PLACES_URI, null, null, null, null);
	}
	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		adapter = new PlacesGridAdapter(getActivity(), arg1);
		gv.setAdapter(adapter);
		//adapter.swapCursor(arg1);
	}
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		 adapter.swapCursor(null);
	}
}
