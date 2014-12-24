package com.exadel.bsgdemo.place;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.*;

import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.place.gallery.PlaceGallery;

public class PlaceDescriptionActivity extends FragmentActivity {
    private static final String IMAGES_PATH = Environment.getExternalStorageDirectory() + "/BSGDemo/";

    private enum PlaceFragments {
        none, list, gallery, history, info, routes, secrets
    }

    private PlaceFragments mCurrentFragment = PlaceFragments.none;
    private Place mCurrentPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = getIntent().getLongExtra("id", -1);
        if (id > 0) {
            Uri uri = ContentUris.withAppendedId(PlaceContentProvider.PLACES_URI, id);
            assert uri != null;
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            assert cursor != null;
            if (cursor.moveToFirst())
                mCurrentPlace = new Place(cursor);
            cursor.close();

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.place_description_list_layout);
            switchFragment(PlaceFragments.list);
            ((TextView) findViewById(R.id.page_title)).setText(mCurrentPlace.getTitle());
            if (getIntent().hasExtra("action")) {
                int action = getIntent().getIntExtra("action", 2);
                switch (action) {
                    case 0:
                        switchFragment(PlaceFragments.info);
                        break;
                    case 1:
                        switchFragment(PlaceFragments.gallery);
                        break;
                    case 2:
                        switchFragment(PlaceFragments.history);
                        break;
                    case 3:
                        switchFragment(PlaceFragments.routes);
                        break;
                    case 4:
                        switchFragment(PlaceFragments.secrets);
                        break;
                    default:
                        break;
                }
            }
        } else {
            Context applicationContext = getApplicationContext();
            assert applicationContext != null;
            Toast.makeText(applicationContext, "Can't find place in database", Toast.LENGTH_SHORT).show();
        }
    }

    private void switchFragment(PlaceFragments which) {
        Fragment fr;
        switch (which) {
            case list:
                fr = new OptionsListFragment();
                break;
            case gallery:
                fr = new PlaceGallery();
                break;
            case history:
                fr = new PlaceHistoryFragment();
                break;
            case info:
                fr = new PlaceInfoFragment();
                break;
            default:
                return;
        }
        if (mCurrentFragment.equals(PlaceFragments.none))
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_wrapper, fr, "mFragment").commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_wrapper, fr, "mFragment").commit();
        mCurrentFragment = which;

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_wrapper, fr).commit();
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment.equals(PlaceFragments.list))
            this.finish();
        else
            switchFragment(PlaceFragments.list);
    }

    public void clickProcess(View v) {
        switch (v.getId()) {
            case R.id.gallery_button:
                PlaceDescriptionActivity.this.switchFragment(PlaceFragments.gallery);
                break;
            case R.id.history_button:
                PlaceDescriptionActivity.this.switchFragment(PlaceFragments.history);
                break;
            case R.id.information_button:
                PlaceDescriptionActivity.this.switchFragment(PlaceFragments.info);
                break;
            case R.id.routes_button:
                PlaceDescriptionActivity.this.switchFragment(PlaceFragments.routes);
                break;
            case R.id.secrets_button:
                PlaceDescriptionActivity.this.switchFragment(PlaceFragments.secrets);
                break;
            default:
                break;
        }
    }

    public class OptionsListFragment extends Fragment {
        private final String[] options = {"Gallery", "History", "Info", "Routes", "Secrets"};

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.place_description_layout, null);
            //view.findViewById(R.id.description_buttons);
            assert view != null;
            ImageView placeImage = (ImageView) view.findViewById(R.id.place_description_image);
            placeImage.setImageBitmap(BitmapFactory.decodeFile(IMAGES_PATH + mCurrentPlace.getIconPath()));

            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            ListView listView = new ListView(getActivity());
            listView.setBackgroundResource(R.drawable.place_bg);
            listView.setAdapter(new BaseAdapter() {

                @Override
                public int getCount() {
                    return options.length;
                }

                @Override
                public Object getItem(int position) {
                    return position;
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView = new TextView(getActivity());
                    textView.setTextSize(25);
                    textView.setText(options[position]);
                    return textView;
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        case 0:
                            PlaceDescriptionActivity.this.switchFragment(PlaceFragments.gallery);
                            break;
                        case 1:
                            PlaceDescriptionActivity.this.switchFragment(PlaceFragments.history);
                            break;
                        case 2:
                            PlaceDescriptionActivity.this.switchFragment(PlaceFragments.info);
                            break;
                        case 3:
                            PlaceDescriptionActivity.this.switchFragment(PlaceFragments.routes);
                            break;
                        case 4:
                            PlaceDescriptionActivity.this.switchFragment(PlaceFragments.secrets);
                            break;
                        default:
                            break;
                    }
                }
            });

            ImageView imageView = new ImageView(getActivity());
            imageView.setImageBitmap(BitmapFactory.decodeFile(IMAGES_PATH + mCurrentPlace.getIconPath()));
            linearLayout.addView(imageView);
            linearLayout.addView(listView);
            return view;
        }
    }

    @SuppressWarnings("UnusedParameters")
    public void addButtonProcess(View view) {
        Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("UnusedParameters")
    public void backButtonProcess(View view) {
        this.onBackPressed();
    }
}