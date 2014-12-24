package com.exadel.bsgdemo.place;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import com.exadel.bsgdemo.ImageAdapter;
import com.exadel.bsgdemo.PagerContainer;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.data.Place;

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
            Cursor c = getContentResolver().query(uri, null, null, null, null);
            if (c.moveToFirst())
                mCurrentPlace = new Place(c);
            c.close();

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
            Toast.makeText(getApplicationContext(), "Can't find place in database", Toast.LENGTH_SHORT).show();
        }
    }

    private void switchFragment(PlaceFragments which) {
        Fragment fr;
        switch (which) {
            case list:
                fr = new OptionsListFragment();
                break;
            case gallery:
                fr = new GalleryFragment();
                break;
            case history:
                fr = new HistoryFragment();
                break;
            case info:
                fr = new InfoFragment();
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

    private class HistoryFragment extends Fragment {

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
                    yearView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
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

    private class GalleryFragment extends Fragment {
        private PagerContainer mPagerContainer;

        private final Integer[] images = {R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal};

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.gallery_screen_layout, null);

            mPagerContainer = (PagerContainer) v.findViewById(R.id.screen_pager_container);

            ViewPager viewPager = mPagerContainer.getViewPager();
            ImageAdapter imageAdapter = new ImageAdapter(getActivity(), R.layout.gallery_item_layout, images);
            viewPager.setAdapter(imageAdapter);
            //Necessary or the pager will only have one extra page to show
            // make this at least however many pages you can see
            viewPager.setOffscreenPageLimit(imageAdapter.getCount());
            //A little space between pages
            viewPager.setPageMargin(1);

            //If hardware acceleration is enabled, you should also remove
            // clipping on the pager for its children.
            viewPager.setClipChildren(false);
            return v;
        }
    }

    private class InfoFragment extends Fragment {
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

    public void addButtonProcess(View view) {
        Toast.makeText(this, "Not supported yet...", Toast.LENGTH_SHORT).show();
    }

    public void backButtonProcess(View view) {
        this.onBackPressed();
    }
}