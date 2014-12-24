package com.exadel.bsgdemo.guide.audio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.exadel.bsgdemo.R;

import java.util.ArrayList;

/**
 * Developer: Paulau Aliaksandr
 * Created: 12:35 PM, 11/18/13
 */

public class AudioListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> mAudioList;
    private int mCount;

    public AudioListAdapter(Context context, int textViewResourceId, ArrayList<String> mAudioList) {
        super(context, textViewResourceId);
        this.mAudioList = mAudioList;
        this.mCount = mAudioList.size();
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public String getItem(int position) {
        return mAudioList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflatedView = convertView;
        if (inflatedView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            inflatedView = layoutInflater.inflate(R.layout.guide_audio_list_item, null);
        }
        String audio = mAudioList.get(position);
        if (audio != null) {
            TextView title = (TextView) inflatedView.findViewById(R.id.audio_title);
            title.setText(audio);
        }
        return inflatedView;
    }
}
