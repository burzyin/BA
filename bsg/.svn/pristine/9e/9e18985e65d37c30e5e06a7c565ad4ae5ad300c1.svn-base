package com.exadel.bsgdemo.near;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.exadel.bsgdemo.BaseActivity;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.near.grid.NearGridFragment;

import net.sourceforge.zbar.android.CameraTest.CameraTestActivity;
import net.sourceforge.zbar.android.CameraTest.QRDialogFragment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

public class NearPlaceActivity extends BaseActivity {
    private enum NearPlaceFragment {
        grid, list, map, none
    }

    private LinearLayout fragmentContainer;
    private NearPlaceFragment currentFragment = NearPlaceFragment.none;

    private ImageButton gridButton;
    private ImageButton listButton;
    private ImageButton mapButton;
    private View switchGridButton;

    private final NearGridFragment gridFragment = new NearGridFragment();
    private final NearListFragment listFragment = new NearListFragment();

    private String tiles = "minsk.zip";
//    private String unzipLocation = Environment.getExternalStorageDirectory() + "/osmdroid/tiles/";

//    private final OpenStreetMapFragment mapFragment = new OpenStreetMapFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActiveButton(R.id.near_places_button, R.drawable.tab2_a);

        try {
            InputStream place = getAssets().open("demo_gallery.txt");
            List<Place> places = Place.serializeList(place);
            for (Place currentPlace : places) {
                if (!currentPlace.checkInDatabase(this)) {
                    copyToSD(currentPlace.getIconPath());
                    resizeAndSaveImage(currentPlace.getIconPath());
                    currentPlace.moveToDatabase(this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        copyAtlas();

        fragmentContainer = (LinearLayout) findViewById(R.id.fragment_container);
        gridButton = (ImageButton) findViewById(R.id.top_switcher_grid);
        listButton = (ImageButton) findViewById(R.id.top_switcher_list);
        mapButton = (ImageButton) findViewById(R.id.top_switcher_map);

        switchGridButton = findViewById((R.id.switch_grid));
        switchGridButton.setVisibility(1);

        switchDynamicFragment(NearPlaceFragment.list);
    }

    private void switchDynamicFragment(NearPlaceFragment fragment) {
        if (!fragment.equals(NearPlaceFragment.map)) {
            View topSwitcher = findViewById(R.id.top_switcher);
            ViewGroup.LayoutParams layoutParams = topSwitcher.getLayoutParams();
            assert layoutParams != null;
            int height = layoutParams.height;
            fragmentContainer.setPadding(5, height + 10, 5, 0);
        } else {
            fragmentContainer.setPadding(0, 0, 0, 0);
        }

        switch (currentFragment) {
            case grid:
                gridButton.setImageResource(R.drawable.top_switcher_near_grid_selector);
                break;
            case list:
                listButton.setImageResource(R.drawable.top_switcher_near_list_selector);
                break;
            case map:
                mapButton.setImageResource(R.drawable.top_switcher_near_map_selector);
                break;
            default:
                break;
        }
        Fragment targetFragment = null;
        switch (fragment) {
            case grid:
                targetFragment = gridFragment;
                currentFragment = NearPlaceFragment.grid;
                gridButton.setImageResource(R.drawable.top_switcher_near_places_focused);
                switchGridButton.setVisibility(View.VISIBLE);
                break;

            case list:
                targetFragment = listFragment;
                currentFragment = NearPlaceFragment.list;
                listButton.setImageResource(R.drawable.top_switcher_near_places_list_focused);
                switchGridButton.setVisibility(View.GONE);
                break;

            case map:
                List<Place> places = getPlaces();
                targetFragment = new OpenStreetMapFragment(places);
                currentFragment = NearPlaceFragment.map;
                mapButton.setImageResource(R.drawable.top_switcher_near_places_map_focused);
                switchGridButton.setVisibility(View.GONE);
                break;

            default:
                break;
        }

        if (currentFragment.equals(NearPlaceFragment.none)) {
            if (targetFragment != null)
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, targetFragment,
                                "mFragment").commit();
        } else {
            if (targetFragment != null)
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, targetFragment,
                                "mFragment").commit();
        }
    }

    private List<Place> getPlaces() {
        Cursor cursor = getContentResolver().query(PlaceContentProvider.PLACES_URI, null, null, null, null);
        List<Place> places = new ArrayList<Place>();
        assert cursor != null;
        while (cursor.moveToNext()) {
            places.add(new Place(cursor));
        }
        cursor.close();
        return places;
    }

    private void copyToSD(String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            AssetManager assets = getAssets();
            File destinationDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BSGDemo");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            File destinationFile = new File(destinationDir, fileName);
            if (!destinationFile.exists()) {
                destinationFile.createNewFile();
                InputStream in = assets.open(fileName);
                fileOutputStream = new FileOutputStream(destinationFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, length);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private void resizeAndSaveImage(String fileName) {
        int height = getWindowManager().getDefaultDisplay().getHeight();

        FileOutputStream fileOutputStream;
        try {
            Bitmap bitmap = getBitmapFromAsset(getApplicationContext(), fileName);
            int targetHeight = (int) (height * 0.4f);
            float coefficient = targetHeight / (float) bitmap.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * coefficient), targetHeight, false);

            File destinationDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BSGDemo");
            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }
            File destinationFile = new File(destinationDir, fileName);
            if (!destinationFile.exists()) {
                fileOutputStream = new FileOutputStream(destinationFile);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                bitmap.recycle();
                fileOutputStream.write(bytes.toByteArray());
                bytes.close();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static Bitmap getBitmapFromAsset(Context context, String name) {
        AssetManager assets = context.getAssets();

        Bitmap bitmap;
        try {
            InputStream inputStream = assets.open(name);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            return null;
        }

        return bitmap;
    }

    private void copyAtlas() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                FileOutputStream fileOutputStream = null;
                try {
                    AssetManager assetManager = getAssets();
                    File destinationDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/osmdroid");
                    destinationDir.exists();
                    if (!destinationDir.exists()) {
                        destinationDir.mkdirs();
                    }
                    File destinationFile = new File(destinationDir, tiles);
                    destinationFile.exists();
                    if (!destinationFile.exists()) {
                        destinationFile.createNewFile();
                        InputStream in = assetManager.open(tiles);
                        ZipInputStream zipInputStream = new ZipInputStream(in);
                        fileOutputStream = new FileOutputStream(destinationFile);
                        copyFile(in, fileOutputStream);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null)
                        try {
                            fileOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                }
//                unzip();
            }
        }).start();
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

//    public void unzip() {
//        File cacheFolder = new File(unzipLocation);
//        if (!cacheFolder.exists())
//            cacheFolder.mkdirs();
//        try {
//
//            ZipInputStream zipInputStream = new ZipInputStream(getAssets().open(tiles));
//            ZipEntry zipEntry;
//            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
//                Log.v("Decompress", "Unzipping " + zipEntry.getName());
//
//                if (!zipEntry.isDirectory()) {
//                    createDirectory(zipEntry.getName());
//                }
//
//                FileOutputStream fileOutputStream = new FileOutputStream(unzipLocation + zipEntry.getName());
//                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
//                    fileOutputStream.write(c);
//                }
//
//                zipInputStream.closeEntry();
//                fileOutputStream.close();
//            }
//            zipInputStream.close();
//        } catch (Exception e) {
//            Log.e("Decompress", "unzip", e);
//        }
//    }
//
//    private boolean createDirectory(String name) {
//        String folder = unzipLocation + name.substring(0, name.lastIndexOf("/") + 1);
//        File file = new File(folder);
//        return file.mkdirs();
//    }

    public void processTopSwitcherClick(View v) {
        switch (v.getId()) {
            case R.id.top_switcher_grid:
                switchDynamicFragment(NearPlaceFragment.grid);
                break;
            case R.id.top_switcher_list:
                switchDynamicFragment(NearPlaceFragment.list);
                break;
            case R.id.top_switcher_map:
                switchDynamicFragment(NearPlaceFragment.map);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("UnusedParameters")
    public void qrButtonProcess(View view) {
        QRDialogFragment qrDialogFragment = new QRDialogFragment();
        qrDialogFragment.show(getSupportFragmentManager(), "qr");
        startActivity(new Intent(this, CameraTestActivity.class));
    }

    public void switchGrid(View view) {
        gridFragment.switchGrid();
    }

    @Override
    protected int getLayout() {
        return R.layout.near_places_layout;
    }
}