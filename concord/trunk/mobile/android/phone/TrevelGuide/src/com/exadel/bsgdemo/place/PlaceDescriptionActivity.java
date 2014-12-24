package com.exadel.bsgdemo.place;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.exadel.bsgdemo.MediaContentProvider;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;

public class PlaceDescriptionActivity extends FragmentActivity{
	private enum PlaceFragments{
		none, list, gallery, history, info, routes, secrets
	}
	private PlaceFragments curFragment = PlaceFragments.none;
	private Place curPlace;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		long id = getIntent().getLongExtra("id", -1);
		if(id > 0){
			Uri uri = ContentUris.withAppendedId(MediaContentProvider.PLACES_URI, id);
			Cursor c = getContentResolver().query(uri, null, null, null, null);
			if(c.moveToFirst())
				curPlace = new Place(c);
			c.close();
		
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.place_description_list_layout);
			switchFragment(PlaceFragments.list);
			((TextView)findViewById(R.id.page_title)).setText(curPlace.getTitle());
			if(getIntent().hasExtra("action")){
				int action = getIntent().getIntExtra("action", 2);
				switch (action) {
				case 0:
					switchFragment(PlaceFragments.info);
					break;
				case 1:
					switchFragment(PlaceFragments.gallery);
					break;
				case 2:
					switchFragment(PlaceFragments.history);
					break;
				case 3:
					switchFragment(PlaceFragments.routes);
					break;
				case 4:
					switchFragment(PlaceFragments.secrets);
					break;
//				case 5:
//					switchFragment(PlaceFragments.);
//					break;
				default:
					break;
				}
			}
		}else{
			Toast.makeText(getApplicationContext(), "Can't find place in database", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void switchFragment(PlaceFragments which){
		Fragment fr = null;
		switch (which) {
		case list:
			fr = new OptionsListFragment();
			break;
		case gallery:
			fr = new GalleryFragment();
			break;
		case history:
			fr = new HistoryFragment();
			break;
		case info:
			fr = new InfoFragment();
			break;
		default:
			return;
		}
		if(curFragment.equals(PlaceFragments.none))
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_wrapper, fr, "mFragment").commit();
		else
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_wrapper, fr, "mFragment").commit();
		curFragment = which;
		
		//getSupportFragmentManager().beginTransaction().replace(R.id.fragment_wrapper, fr).commit();
	}
	@Override
	public void onBackPressed() {
		if(curFragment.equals(PlaceFragments.list))
			this.finish();
		else
			switchFragment(PlaceFragments.list);
	}
	
	public void clickProcess(View v){
		switch (v.getId()) {
		case R.id.gallery_button:
			PlaceDescriptionActivity.this.switchFragment(PlaceFragments.gallery);
			break;
		case R.id.history_button:
			PlaceDescriptionActivity.this.switchFragment(PlaceFragments.history);
			break;
		case R.id.information_button:
			PlaceDescriptionActivity.this.switchFragment(PlaceFragments.info);
			break;
		case R.id.routes_button:
			PlaceDescriptionActivity.this.switchFragment(PlaceFragments.routes);
			break;
		case R.id.secrets_button:
			PlaceDescriptionActivity.this.switchFragment(PlaceFragments.secrets);
			break;
		default:
			break;
		}
	}
	
	public class OptionsListFragment extends Fragment{
		private final String[] options = {"Gallery", "History", "Info", "Routes", "Secrets"};
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.place_description_layout, null);
			int width = container.getWidth();
			int height = container.getHeight();
			//v.findViewById(R.id.description_buttons);
			ImageView iv = (ImageView) v.findViewById(R.id.place_description_image);
			iv.setImageBitmap(BitmapFactory.decodeFile(imagesPath + curPlace.getIconPath()));
			
//			LinearLayout ll = new LinearLayout(getActivity());
//			ll.setOrientation(LinearLayout.VERTICAL);
//			
//			ListView lv = new ListView(getActivity());
//			lv.setBackgroundResource(R.drawable.place_bg);
//			lv.setAdapter(new BaseAdapter(){
//
//				@Override
//				public int getCount() {
//					return options.length;
//				}
//
//				@Override
//				public Object getItem(int position) {
//					return position;
//				}
//
//				@Override
//				public long getItemId(int position) {
//					return position;
//				}
//
//				@Override
//				public View getView(int position, View convertView,
//						ViewGroup parent) {
//					TextView tv = new TextView(getActivity());
//					tv.setTextSize(25);
//					tv.setText(options[position]);
//					return tv;
//				}
//			});
//			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1,
//						int arg2, long arg3) {
//					switch (arg2) {
//					case 0:
//						PlaceDescriptionActivity.this.switchFragment(PlaceFragments.gallery);
//						break;
//					case 1:
//						PlaceDescriptionActivity.this.switchFragment(PlaceFragments.history);
//						break;
//					case 2:
//						PlaceDescriptionActivity.this.switchFragment(PlaceFragments.info);
//						break;
//					case 3:
//						PlaceDescriptionActivity.this.switchFragment(PlaceFragments.routes);
//						break;
//					case 4:
//						PlaceDescriptionActivity.this.switchFragment(PlaceFragments.secrets);
//						break;
//					default:
//						break;
//					}
//				}
//			});
//			ImageView iv = new ImageView(getActivity());
//			iv.setImageBitmap(BitmapFactory.decodeFile(imagesPath + curPlace.getIconPath()));
//			ll.addView(iv);
//			ll.addView(lv);
			return v;
		}
	}
	private final String imagesPath = Environment.getExternalStorageDirectory() + "/BSGDemo/";
	private class HistoryFragment extends Fragment{
		
		private short[] years = {1978, 1953, 1925};
		private String[] titles = {"Березинскому заповеднику был присвоен статус биосферного", "Популяция бобров достигла 600 особей", "Был организован Березинский заповедник"};
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			ListView lv = new ListView(getActivity());
			lv.setDividerHeight(20);
			lv.setPadding(10, 2, 10, 2);
			lv.setAdapter(new BaseAdapter() {
				
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					LinearLayout ll = new LinearLayout(getActivity());
					ll.setOrientation(LinearLayout.HORIZONTAL);
					ll.setBackgroundResource(R.drawable.place_bg);
					ll.setGravity(Gravity.CENTER_VERTICAL);
					
					TextView yearView = new TextView(getActivity());
					yearView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
					yearView.setTextSize(40);
					yearView.setText(String.valueOf(years[position]));
					
					TextView titleView = new TextView(getActivity());
					titleView.setTextSize(15);
					titleView.setText(titles[position]);
					
					ll.addView(yearView);
					ll.addView(titleView);
					
					return ll;
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
					return years.length;
				}
			});
			return lv;
		}
	}
	private class GalleryFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.gallery_screen_layout, null);
			Gallery gallery = (Gallery) v.findViewById(R.id.m_gallery);
			gallery.setAdapter(new BaseAdapter() {
				
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					if(convertView == null){
						convertView = LayoutInflater.from(getActivity()).inflate(R.layout.gallery_item_layout, null);
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
			});
			
			return v;
		}
	}
	private class InfoFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			LinearLayout ll = new LinearLayout(getActivity());
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.setBackgroundResource(R.drawable.place_bg);
			
			TextView title = new TextView(getActivity());
			title.setTextSize(18);
			title.setText("Березинский заповедник");
			
			TextView info = new TextView(getActivity());
			info.setText("В целях охраны и размножения ценных диких животных, в особенности речных бобров  и пернатой дичи 30 янвааря 1925 года был основан Березинский заповедникю Основой его появления послужили материалы экспедиции под руководством зоолога А.В. Федюшина\n" +
					"Благодаря уникальным природным особенностям территории и высокой степени охраны природных комплексов заповедника, в 1979 году в числе первых прподоохранных учреждений Советского Союза он получил статус биосферного.");
			
			
			ll.addView(title);
			ll.addView(info);
			return ll;
		}
	}
	public void addButtonProcess(View v){
		Toast.makeText(this, "Not suported yet...", Toast.LENGTH_SHORT).show();
	}
	public void backButtonProcess(View v){
		this.onBackPressed();
	}
}