package com.exadel.bsgdemo.near;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.sourceforge.zbar.android.CameraTest.CameraTestActivity;
import net.sourceforge.zbar.android.CameraTest.QRDialogFragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.exadel.bsgdemo.MediaContentProvider;
import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.data.Place;
import com.exadel.bsgdemo.newroute.NewRouteActivity;

public class NearListActivity extends FragmentActivity {
	private enum NearFragment {/* grid, */
		list, map, none
	};

	private LinearLayout fragmentContainer;
	private NearFragment currentFragment = NearFragment.none;

	private ImageButton gridButton;
	private ImageButton listButton;
	private ImageButton mapButton;

	private final NearGridFragment gridFragment = new NearGridFragment();
	private final NearListFragment listFragment = new NearListFragment();

	// private final OpenStreetMapFragment mapFragment = new
	// OpenStreetMapFragment();

	private void copyToSD(String fileName) {
		FileOutputStream f = null;
		try {
			AssetManager am = getAssets();
			File destDir = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/BSGDemo");
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			File destinationFile = new File(destDir, fileName);
			if (!destinationFile.exists()) {
				destinationFile.createNewFile();
				InputStream in = am.open(fileName);
				f = new FileOutputStream(destinationFile);
				byte[] buffer = new byte[1024];
				int len1 = 0;
				while ((len1 = in.read(buffer)) > 0) {
					f.write(buffer, 0, len1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
		}
	}
	
	private void resizeAndSaveImage(String fileName){
		int height = getWindowManager().getDefaultDisplay().getHeight();
		
		FileOutputStream f = null;
		try {
			Bitmap b = getBitmapFromAsset(getApplicationContext(), fileName);
			int targetHeight = (int) (height * 0.4f);
			float coef = targetHeight/(float)b.getHeight();
			b = Bitmap.createScaledBitmap(b, (int) (b.getWidth() * coef), targetHeight, false);
			
			File destDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BSGDemo");
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			File destinationFile = new File(destDir, fileName);
			if (!destinationFile.exists()) {
				f = new FileOutputStream(destinationFile);
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				b.compress(Bitmap.CompressFormat.PNG, 100, bytes);
				b.recycle();
				b = null;
				f.write(bytes.toByteArray());
				bytes.close();
				f.close();
			}
		}catch (Exception e) {
			e.getMessage();
		}
	}
	public static Bitmap getBitmapFromAsset(Context context, String strName) {
	    AssetManager assetManager = context.getAssets();

	    InputStream istr;
	    Bitmap bitmap = null;
	    try {
	        istr = assetManager.open(strName);
	        bitmap = BitmapFactory.decodeStream(istr);
	    } catch (IOException e) {
	        return null;
	    }

	    return bitmap;
	}

	private void copyAtlas() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// String fileName = "minsk2.zip";
				// FileOutputStream f = null;
				// try {
				// AssetManager am = getAssets();
				// File destDir = new File(Environment
				// .getExternalStorageDirectory().getAbsolutePath()
				// + "/osmdroid");
				// if (!destDir.exists()) {
				// destDir.mkdirs();
				// }
				// File destinationFile = new File(destDir, fileName);
				// if (!destinationFile.exists()) {
				// destinationFile.createNewFile();
				// InputStream in = am.open(fileName);
				// ZipInputStream zin = new ZipInputStream(in);
				// f = new FileOutputStream(destinationFile);
				// byte[] buffer = new byte[1024];
				// int len1 = 0;
				// while ((len1 = zin.read(buffer)) > 0) {
				// f.write(buffer, 0, len1);
				// }
				// }
				// } catch (IOException e) {
				// e.printStackTrace();
				// } finally {
				// if (f != null)
				// try {
				// f.close();
				// } catch (IOException e2) {
				// e2.printStackTrace();
				// }
				// }
				unzip();
			}
		}).start();
	}

	String unzipLocation = Environment.getExternalStorageDirectory()
			+ "/osmdroid/tiles/";

	public void unzip() {
		File cacheFolder = new File(unzipLocation);
		if (!cacheFolder.exists())
			cacheFolder.mkdirs();
		try {
			ZipInputStream zin = new ZipInputStream(getAssets().open(
					"minsk2.zip"));
			ZipEntry ze = null;
			while ((ze = zin.getNextEntry()) != null) {
				Log.v("Decompress", "Unzipping " + ze.getName());

				if(!ze.isDirectory())
					_dirChecker(ze.getName());
				//else{
					FileOutputStream fout = new FileOutputStream(unzipLocation
							+ ze.getName());
					for (int c = zin.read(); c != -1; c = zin.read()) {
						fout.write(c);
					}
	
					zin.closeEntry();
					fout.close();
				//}
			}
			zin.close();
		} catch (Exception e) {
			Log.e("Decompress", "unzip", e);
		}
	}

	private void _dirChecker(String fileName) {
		String folder = unzipLocation + fileName.substring(0, fileName.lastIndexOf("/") + 1) ;
		File f = new File(folder);

		//if (f.isDirectory()) {
			boolean b = f.mkdirs();
			//}
	}

	private void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.near_list_layout);

		try {
			InputStream place = getAssets().open("demo_gallery.txt");
			List<Place> places = Place.serializeList(place);
			for (Iterator iterator = places.iterator(); iterator.hasNext();) {
				Place curplace = (Place) iterator.next();
				//copyToSD(curplace.getIconPath());
				resizeAndSaveImage(curplace.getIconPath());
				curplace.moveToDatabese(this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//copyAtlas();

		fragmentContainer = (LinearLayout) findViewById(R.id.fragment_container);
		gridButton = (ImageButton) findViewById(R.id.top_swither_1);
		listButton = (ImageButton) findViewById(R.id.top_swither_2);
		mapButton = (ImageButton) findViewById(R.id.top_swither_3);
		switchDynamicFragment(NearFragment.list);
	}

	private void switchDynamicFragment(NearFragment which) {
		if (!which.equals(NearFragment.map)) {
			int height = findViewById(R.id.top_switcher).getLayoutParams().height;
			findViewById(R.id.fragment_container).setPadding(5, height + 10, 5,
					0);
		} else
			findViewById(R.id.fragment_container).setPadding(0, 0, 0, 0);

		switch (currentFragment) {
		/*
		 * case grid:
		 * gridButton.setImageResource(R.drawable.top_swithcer_near_selector);
		 * break;
		 */
		case list:
			listButton
					.setImageResource(R.drawable.top_switcher_near_list_selector);
			break;
		case map:
			mapButton
					.setImageResource(R.drawable.top_switcher_near_map_selector);
			break;
		default:
			break;
		}
		android.support.v4.app.Fragment targetFragment = null;
		switch (which) {
		/*
		 * case grid: targetFragment = gridFragment; currentFragment =
		 * NearFragment.grid;
		 * gridButton.setImageResource(R.drawable.top_swither_near_places_focused
		 * ); break;
		 */
		case list:
			targetFragment = listFragment;
			currentFragment = NearFragment.list;
			listButton
					.setImageResource(R.drawable.top_swither_near_places_list_focused);
			break;
		case map:
			Cursor c = getContentResolver().query(
					MediaContentProvider.PLACES_URI, null, null, null, null);
			List<Place> places = new ArrayList<Place>();
			while (c.moveToNext()) {
				places.add(new Place(c));
			}
			c.close();
			targetFragment = new OpenStreetMapFragment(places);
			currentFragment = NearFragment.map;
			mapButton
					.setImageResource(R.drawable.top_swither_near_places_map_focused);
			break;
		default:
			break;
		}

		if (currentFragment.equals(NearFragment.none)) {
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

	public void processTopSwitherClick(View v) {
		switch (v.getId()) {
		/*
		 * case R.id.top_swither_1: switchDynamicFragment(NearFragment.grid);
		 * break;
		 */
		case R.id.top_swither_2:
			switchDynamicFragment(NearFragment.list);
			break;
		case R.id.top_swither_3:
			switchDynamicFragment(NearFragment.map);
			break;
		default:
			break;
		}
	}

	public void qrButtonProcess(View v) {
		QRDialogFragment qrdf = new QRDialogFragment();
		qrdf.show(getSupportFragmentManager(), "qr");
		//startActivity(new Intent(this, CameraTestActivity.class));
	}

	public void backButtonProcess(View v) {
		Toast.makeText(this, "Not suported yet...", Toast.LENGTH_SHORT).show();
	}

	public void newRouteClick(View v) {
		this.startActivity(new Intent(this, NewRouteActivity.class));
	}
}
