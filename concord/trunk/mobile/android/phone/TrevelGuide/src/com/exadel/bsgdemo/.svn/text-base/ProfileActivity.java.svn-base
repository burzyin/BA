package com.exadel.bsgdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Gallery;

public class ProfileActivity extends Activity{
	private Gallery mGallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.profile_layout);
		mGallery = (Gallery) findViewById(R.id.m_gallery);
		mGallery.setAdapter(new BaseAdapter() {
			private int count = 10;
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				if(arg1 == null){
					arg1 = new RectImageView(ProfileActivity.this);
					((RectImageView)arg1).setImageResource(R.drawable.profile_gallery);
				}
				return arg1;
			}
			
			@Override
			public long getItemId(int arg0) {
				return arg0;
			}
			
			@Override
			public Object getItem(int arg0) {
				return arg0;
			}
			
			@Override
			public int getCount() {
				return count;
			}
		});
	}
}
