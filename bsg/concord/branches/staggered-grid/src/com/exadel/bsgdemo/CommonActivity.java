package com.exadel.bsgdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

/**
 * Developer: Paulau Aliaksandr
 * Created: 10:15 AM, 12/9/13
 */
public abstract class CommonActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
    }

    protected abstract int getLayout();

    public void backButtonProcess(View v) {
        this.onBackPressed();
    }
}
