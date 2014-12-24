package com.exadel.bsgdemo.near;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import net.londatiga.android.QuickAction.OnActionItemClickListener;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract.QuickContact;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.exadel.bsgdemo.MediaContentProvider;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;

public class NearListFragment extends android.support.v4.app.Fragment  implements
LoaderManager.LoaderCallbacks<Cursor>{
	private ListView nearList;
	private static final int LOADER_ID = 0x01;
	private CursorAdapter adapter;
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		nearList = (ListView) inflater.inflate(R.layout.near_list_fragment_layout, null);
		
		//nearList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new String[]{"1","1","1","1","1","1","1","1","1","1"}));
		//Cursor c = getActivity().getContentResolver().query(MediaContentProvider.PLACES_URI, null, null, null, null);
		//getActivity().startManagingCursor(c);
		getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
		//nearList.setAdapter(new PlacesAdapter(getActivity(), c));
		/*nearList.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if(convertView == null){
					convertView = LayoutInflater.from(getActivity()).inflate(R.layout.near_list_item, null);
				}
				return convertView;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return position;
			}
			
			@Override
			public int getCount() {
				return 15;
			}
		});*/
		return nearList;
	}
	private class PlacesAdapter extends CursorAdapter{

		public PlacesAdapter(Context context, Cursor c) {
			super(context, c);
		}

		private final String imagesPath = Environment.getExternalStorageDirectory() + "/BSGDemo/"; 
		@Override
		public void bindView(View arg0, Context arg1, Cursor arg2) {
			Place p = new Place(arg2);
			((TextView)arg0.findViewById(R.id.paces_item_title)).setText(p.getTitle());
			((ImageView)arg0.findViewById(R.id.icon)).setImageBitmap(BitmapFactory.decodeFile(imagesPath + p.getIconPath()));
			//((Button)arg0.findViewById(R.id.places_item_position)).setText(p.getId());
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
			View rootView = LayoutInflater.from(arg0).inflate(R.layout.near_list_item, null);
			//bindView(rootView, arg0, arg1);
			return rootView;
		}
	}
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		 return new CursorLoader(getActivity(), MediaContentProvider.PLACES_URI, null, null, null, null);
	}
	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		adapter = new PlacesAdapter(getActivity(), arg1);
		nearList.setAdapter(adapter);
		nearList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class); 
				target.putExtra("id", arg3);
				startActivity(target);
			}
		});
		registerForContextMenu(nearList);
//		nearList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class); 
//				target.putExtra("id", arg3);
//				startActivity(target);
//				return true;
//			}
//		});
		//adapter.swapCursor(arg1);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
//		menu.clear();
//		for (int i = 0; i < contextMenuItems.length; i++) {
//			menu.add(contextMenuItems[i]);
//		}
		super.onCreateContextMenu(menu, v, menuInfo);
		
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		final long id = info.id;
		int pos = info.position;
		
		QuickAction qa = new QuickAction(getActivity());
		
		//qa.setContentView(R.layout.quick_action_layout);
		for (int i = 0; i < contextMenuItems.length; i++) {
			ActionItem ai = new ActionItem();
			ai.setTitle(contextMenuItems[i]);
			switch (i) {
			case 0:
				ai.setIcon(getResources().getDrawable(R.drawable.i));
				break;
			case 1:
				ai.setIcon(getResources().getDrawable(R.drawable.gallery));
				break;
			case 2:
				ai.setIcon(getResources().getDrawable(R.drawable.history));
				break;
			case 3:
				ai.setIcon(getResources().getDrawable(R.drawable.marker));
				break;
			case 4:
				ai.setIcon(getResources().getDrawable(R.drawable.secrets));
				break;
			case 5:
				ai.setIcon(getResources().getDrawable(R.drawable.audio_guide));
				break;
			default:
				break;
			}
			
			qa.addActionItem(ai);
		}
		qa.setOnActionItemClickListener(new OnActionItemClickListener() {
			
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {
				Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class); 
				target.putExtra("id", id);
				target.putExtra("action", pos);
				startActivity(target);
			}
		});
		
		View target = ((ListView)v).getChildAt(pos);
		qa.show(target==null?v:target);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int menuItemIndex = info.position;
		Cursor c = ((CursorAdapter)nearList.getAdapter()).getCursor();
		c.moveToPosition(menuItemIndex);
		
		long realId = c.getLong(c.getColumnIndex("_id"));
		Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class); 
		target.putExtra("id", realId);
		int pos = getItemID(item.getTitle().toString());
		target.putExtra("action", pos);
		startActivity(target);
		return super.onContextItemSelected(item);
	}
	
	public static final String[] contextMenuItems = new String[]{"Информация", "Галерея", "История", "Маршруты", "Секреты", "аудио гид"}; 
	private int getItemID(String title){
		for (int i = 0; i < contextMenuItems.length; i++) {
			if(title.equalsIgnoreCase(contextMenuItems[i]))
				return i;
		}
		return -1;
	}
	
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		 adapter.swapCursor(null);
	}
}