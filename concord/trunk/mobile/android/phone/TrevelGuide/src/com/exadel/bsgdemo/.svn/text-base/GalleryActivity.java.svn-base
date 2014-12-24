package com.exadel.bsgdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Toast;

public class GalleryActivity extends Activity{
	private Gallery gallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gallery_screen_layout);
		gallery = (Gallery) findViewById(R.id.m_gallery);
		gallery.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if(convertView == null){
					convertView = LayoutInflater.from(GalleryActivity.this).inflate(R.layout.gallery_item_layout, null);
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
	}
	public void addButtonProcess(View v){
		Toast.makeText(this, "Not suported yet...", Toast.LENGTH_SHORT).show();
	}
	public void backButtonProcess(View v){
		Toast.makeText(this, "Not suported yet...", Toast.LENGTH_SHORT).show();
	}
}