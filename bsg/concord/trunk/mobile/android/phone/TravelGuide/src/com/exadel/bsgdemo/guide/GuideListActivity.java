package com.exadel.bsgdemo.guide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.exadel.bsgdemo.FragmentListActivity;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Guide;
import com.exadel.bsgdemo.generators.GuideGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:41 PM, 10/17/13
 */

public class GuideListActivity extends FragmentListActivity {
    private GuideGenerator mGuideGenerator;
    private ProgressDialog mProgressDialog = null;
    private ArrayList<Guide> mGuides = null;
    private GuideAdapter mGuideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGuideGenerator = new GuideGenerator();
        mGuides = new ArrayList<Guide>();
        this.mGuideAdapter = new GuideAdapter(this, R.layout.guide_list_item, mGuides);
        setListAdapter(this.mGuideAdapter);
        mProgressDialog = ProgressDialog.show(GuideListActivity.this, "Please wait...", "Retrieving data ...", true);

        Runnable viewGuides = new Runnable() {
            @Override
            public void run() {
                getGuides();
            }
        };
        Thread thread = new Thread(null, viewGuides, "MagentoBackground");
        thread.start();
    }

    private Runnable initializeAdapter = new Runnable() {

        @Override
        public void run() {
            if (mGuides != null && mGuides.size() > 0) {
                mGuideAdapter.notifyDataSetChanged();
                for (Guide guide : mGuides) {
                    mGuideAdapter.add(guide);
                }
            }
            mProgressDialog.dismiss();
            mGuideAdapter.notifyDataSetChanged();
        }
    };

    private void getGuides() {
        try {
            List<Guide> guides = mGuideGenerator.getGuides();
            for (Guide guide : guides) {
                this.mGuides.add(guide);
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        runOnUiThread(initializeAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent target = new Intent(GuideListActivity.this, GuideActivity.class);
        target.putExtra("id", id);
        startActivity(target);
    }

    private class GuideAdapter extends ArrayAdapter<Guide> {
        private ArrayList<Guide> guides;

        private GuideAdapter(Context context, int textViewResourceId, ArrayList<Guide> guides) {
            super(context, textViewResourceId);
            this.guides = guides;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View inflatedView = convertView;
            if (inflatedView == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflatedView = layoutInflater.inflate(R.layout.guide_list_item, null);
            }
            Guide guide = guides.get(position);
//                TODO: implement image setting
//                ImageView imageView = (ImageView) inflatedView.findViewById(R.id.icon);
//                imageView.setImageResource(mGuides.get(position).getIconPath());
            if (guide != null) {
                assert inflatedView != null;
                TextView title = (TextView) inflatedView.findViewById(R.id.guide_item_title);
                title.setText(guide.getName());

                TextView place = (TextView) inflatedView.findViewById(R.id.guide_item_position);
                place.setText(guide.getPlace());

                TextView cost = (TextView) inflatedView.findViewById(R.id.guide_item_cost);
                cost.setText(guide.getCost());

                ImageView button = (ImageView) inflatedView.findViewById(R.id.action_button);
                button.setImageResource(guide.getOption());
            }

            return inflatedView;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.guide_list_layout;

    }

    @SuppressWarnings("UnusedParameters")
    public void actionButtonProcess(View view) {
        Context applicationContext = getApplicationContext();
        assert applicationContext != null;
        Toast.makeText(applicationContext, "Not supported yet", Toast.LENGTH_SHORT).show();
    }
}
