package com.exadel.bsgdemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.exadel.bsgdemo.near.NearPlaceActivity;
import com.exadel.bsgdemo.newroute.NewRouteActivity;
import com.exadel.bsgdemo.profile.ProfileActivity;

import static android.view.View.OnClickListener;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:00 PM, 9/19/13
 */
public abstract class BaseActivity extends CommonActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.new_route_button).setOnClickListener(this);
        findViewById(R.id.my_profile_button).setOnClickListener(this);
        findViewById(R.id.near_places_button).setOnClickListener(this);
        findViewById(R.id.popular_button).setOnClickListener(this);
        findViewById(R.id.search_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_route_button:
                if (!(this instanceof NewRouteActivity)) {
                    this.startActivity(new Intent(this, NewRouteActivity.class));
                }
                break;
            case R.id.my_profile_button:
                if (!(this instanceof ProfileActivity)) {
                    this.startActivity(new Intent(this, ProfileActivity.class));
                }
                break;
            case R.id.near_places_button:
                if (!(this instanceof NearPlaceActivity)) {
                    this.startActivity(new Intent(this, NearPlaceActivity.class));
                }
                break;
            default:
                Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
        }
    }

    protected void setActiveButton(int buttonId, int drawableId) {
        Button button = (Button) findViewById(buttonId);
        Drawable drawable = getResources().getDrawable(drawableId);

        button.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        button.setTextColor(Color.parseColor("#9EC95E"));
    }
}
