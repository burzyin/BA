package com.exadel.bsgdemo.place;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.exadel.bsgdemo.R;

public class PlaceHistoryFragment extends Fragment {
    private short[] mYears = {1978, 1953, 1925};
    private String[] mTitles = {"Березинскому заповеднику был присвоен статус биосферного", "Популяция бобров достигла 600 особей", "Был организован Березинский заповедник"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ListView listView = new ListView(getActivity());
        listView.setDividerHeight(20);
        listView.setPadding(10, 2, 10, 2);
        listView.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setBackgroundResource(R.drawable.place_bg);
                linearLayout.setGravity(Gravity.CENTER_VERTICAL);

                TextView yearView = new TextView(getActivity());
                yearView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                yearView.setTextSize(40);
                yearView.setText(String.valueOf(mYears[position]));

                TextView titleView = new TextView(getActivity());
                titleView.setTextSize(15);
                titleView.setText(mTitles[position]);

                linearLayout.addView(yearView);
                linearLayout.addView(titleView);

                return linearLayout;
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
                return mYears.length;
            }
        });
        return listView;
    }
}