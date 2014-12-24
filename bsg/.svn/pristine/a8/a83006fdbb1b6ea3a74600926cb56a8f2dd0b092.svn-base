package com.exadel.bsgdemo.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.exadel.bsgdemo.BaseActivity;
import com.exadel.bsgdemo.ImageAdapter;
import com.exadel.bsgdemo.PagerContainer;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Profile;
import com.exadel.bsgdemo.generators.ProfileGenerator;
import com.exadel.bsgdemo.guide.GuideListActivity;

public class ProfileActivity extends BaseActivity {

    private static final Integer[] IMAGES = { R.drawable.cheliuskintsev_park, R.drawable.cheliuskintsev_park, R.drawable.cheliuskintsev_park, R.drawable.cheliuskintsev_park, R.drawable.cheliuskintsev_park, };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setActiveButton(R.id.my_profile_button, R.drawable.tab1_a);

        ProfileGenerator profileGenerator = new ProfileGenerator();
        Profile profile = profileGenerator.getProfile();
        String visited = String.valueOf(profile.getVisited());
        String liked = String.valueOf(profile.getLiked());
        String recommended = String.valueOf(profile.getRecommended());

        TextView visitedCounter = (TextView) this.findViewById(R.id.visitedCounter);
        TextView likedCounter = (TextView) this.findViewById(R.id.likedCounter);
        TextView wantCounter = (TextView) this.findViewById(R.id.wantCounter);

        visitedCounter.setText(visited);
        likedCounter.setText(liked);
        wantCounter.setText(recommended);

        PagerContainer pagerContainer = (PagerContainer) findViewById(R.id.pager_container);

        ViewPager viewPager = pagerContainer.getViewPager();
        ImageAdapter imageAdapter = new ImageAdapter(this, R.layout.profile_gallery_item_layout, IMAGES);
        viewPager.setAdapter(imageAdapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        viewPager.setOffscreenPageLimit(imageAdapter.getCount());
        //A little space between pages
        viewPager.setPageMargin(1);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        viewPager.setClipChildren(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.profile_layout;
    }

    @SuppressWarnings("UnusedParameters")
    public void settingsButtonProcess(View view) {
        Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("UnusedParameters")
    public void routesButtonProcess(View view) {
        Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("UnusedParameters")
    public void guidesButtonProcess(View view) {
        startActivity(new Intent(this, GuideListActivity.class));
    }

    @SuppressWarnings("UnusedParameters")
    public void viewButtonProcess(View view) {
        Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("UnusedParameters")
    public void likeButtonProcess(View view) {
        Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
    }
}
