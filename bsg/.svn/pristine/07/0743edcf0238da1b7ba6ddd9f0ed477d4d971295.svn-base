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
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.database.PlaceTable;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;
import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import net.londatiga.android.QuickAction.OnActionItemClickListener;

public class NearListFragment extends android.support.v4.app.Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 0x01;
    private ListView mNearList;
    private CursorAdapter mCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mNearList = (ListView) inflater.inflate(R.layout.near_list_fragment_layout, null);

        //mNearList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new String[]{"1","1","1","1","1","1","1","1","1","1"}));
        //Cursor c = getActivity().getContentResolver().query(PlaceContentProvider.PLACES_URI, null, null, null, null);
        //getActivity().startManagingCursor(c);
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        //mNearList.setAdapter(new PlacesAdapter(getActivity(), c));
        /*mNearList.setAdapter(new BaseAdapter() {

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
        return mNearList;
    }

    private class PlacesAdapter extends CursorAdapter {

        private final String imagesPath = Environment.getExternalStorageDirectory() + "/BSGDemo/";

        public PlacesAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            Place p = new Place(cursor);
            ((TextView) view.findViewById(R.id.guide_item_title)).setText(p.getTitle());
            ((ImageView) view.findViewById(R.id.place_icon)).setImageBitmap(BitmapFactory.decodeFile(imagesPath + p.getIconPath()));
            //((Button)arg0.findViewById(R.id.places_item_position)).setText(p.getId());
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View rootView = LayoutInflater.from(context).inflate(R.layout.near_list_item, null);
            //bindView(rootView, arg0, arg1);
            return rootView;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
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
        mCursorAdapter = new PlacesAdapter(getActivity(), cursor);
        mNearList.setAdapter(mCursorAdapter);
        mNearList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long id) {
                Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class);
                target.putExtra("id", id);
                startActivity(target);
            }
        });
        registerForContextMenu(mNearList);
//		mNearList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mCursorAdapter.swapCursor(null);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
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

        View target = ((ListView) v).getChildAt(pos);
        qa.show(target == null ? v : target);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = info.position;
        Cursor c = ((CursorAdapter) mNearList.getAdapter()).getCursor();
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

    private int getItemID(String title) {
        for (int i = 0; i < contextMenuItems.length; i++) {
            if (title.equalsIgnoreCase(contextMenuItems[i]))
                return i;
        }
        return -1;
    }
}