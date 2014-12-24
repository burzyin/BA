package com.exadel.bsgdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

public class RectImageView extends ImageView{
	private int displayWidth;
	public RectImageView(Context context) {
		super(context);
	}
	public RectImageView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    displayWidth = display.getWidth();
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
		int width = Math.min(getWidth(), displayWidth);
		setMeasuredDimension(width/2, getHeight()/2);
	}
}
