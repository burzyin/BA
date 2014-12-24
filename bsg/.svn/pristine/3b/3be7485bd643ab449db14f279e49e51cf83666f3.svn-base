package com.exadel.bsgdemo.place;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exadel.bsgdemo.R;

public class PlaceInfoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundResource(R.drawable.place_bg);

        TextView title = new TextView(getActivity());
        title.setTextSize(18);
        title.setText("Березинский заповедник");

        TextView info = new TextView(getActivity());
        info.setText("В целях охраны и размножения ценных диких животных, в особенности речных бобров  и пернатой дичи 30 янвааря 1925 года был основан Березинский заповедникю Основой его появления послужили материалы экспедиции под руководством зоолога А.В. Федюшина\n" +
                "Благодаря уникальным природным особенностям территории и высокой степени охраны природных комплексов заповедника, в 1979 году в числе первых прподоохранных учреждений Советского Союза он получил статус биосферного.");

        linearLayout.addView(title);
        linearLayout.addView(info);
        return linearLayout;
    }
}