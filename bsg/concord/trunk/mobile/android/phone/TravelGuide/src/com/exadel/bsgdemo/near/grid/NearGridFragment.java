package com.exadel.bsgdemo.near.grid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.exadel.bsgdemo.R;
import com.exadel.bsgdemo.contentprovider.PlaceContentProvider;
import com.exadel.bsgdemo.database.PlaceTable;
import com.exadel.bsgdemo.near.grid.adapters.SimpleGridAdapter;
import com.exadel.bsgdemo.near.grid.adapters.StaggeredGridAdapter;
import com.exadel.bsgdemo.place.PlaceDescriptionActivity;
import com.origamilabs.library.views.StaggeredGridView;

public class NearGridFragment extends Fragment {
    private static final int LOADER_ID = 0x02;
    //    private GridView mGridView;
    //    private PlacesGridAdapter mAdapter;

    private enum GridType {
        none, Simple, Staggered, Quilt
    }

    private GridType currentGrid = GridType.none;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.near_grid_fragment_layout, null);

        switchGrid(GridType.Simple);

        return view;
    }

    public void switchGrid() {
        GridType targetGrid = currentGrid == GridType.Simple ? GridType.Staggered : GridType.Simple;
        switchGrid(targetGrid);
    }

    private void switchGrid(GridType fragment) {

        Fragment targetGrid = null;
        switch (fragment) {
            case Simple:
                targetGrid = new SimpleFragment();
                currentGrid = GridType.Simple;
                break;
            case Staggered:
                targetGrid = new StaggeredFragment();
                currentGrid = GridType.Staggered;
                break;
            default:
                break;
        }

        if (currentGrid.equals(GridType.none)) {
            if (targetGrid != null)
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.grid_container, targetGrid,
                                "mGrid").commit();
        } else {
            if (targetGrid != null)
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.grid_container, targetGrid,
                                "mGrid").commit();
        }
    }

    private class SimpleFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
        private GridView mGridView;
        private SimpleGridAdapter mAdapter;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.near_grid_fragment_simple, null);

            assert view != null;
            mGridView = (GridView) view.findViewById(R.id.placeGridView);

            getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);

            return view;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            String[] projection = {
                    PlaceTable.COLUMN_ID,
                    PlaceTable.COLUMN_TITLE,
                    PlaceTable.COLUMN_ICON,
                    PlaceTable.COLUMN_LONGITUDE,
                    PlaceTable.COLUMN_LATITUDE
            };
            return new CursorLoader(getActivity(), PlaceContentProvider.PLACES_URI, projection, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
            mAdapter = new SimpleGridAdapter(getActivity(), cursor);

            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                    Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class);
                    target.putExtra("id", id);
                    startActivity(target);
                }
            });
            mAdapter.swapCursor(cursor);
        }


        @Override
        public void onLoaderReset(Loader<Cursor> cursorLoader) {
            mAdapter.swapCursor(null);
        }
    }

    private class StaggeredFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
        private StaggeredGridView mGridView;
        private StaggeredGridAdapter mAdapter;


        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.near_grid_fragment_staggered, null);

            assert view != null;
            mGridView = (StaggeredGridView) view.findViewById(R.id.staggeredGridView);

//        int margin = getResources().getDimensionPixelSize();
            int margin = 5;
            mGridView.setItemMargin(margin); // set the GridView margin

            mGridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well

            getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);

            return view;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            String[] projection = {
                    PlaceTable.COLUMN_ID,
                    PlaceTable.COLUMN_TITLE,
                    PlaceTable.COLUMN_ICON,
                    PlaceTable.COLUMN_LONGITUDE,
                    PlaceTable.COLUMN_LATITUDE
            };
            return new CursorLoader(getActivity(), PlaceContentProvider.PLACES_URI, projection, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
//        mAdapter = new PlacesGridAdapter(getActivity(), cursor);
//        mGridView.setAdapter(mAdapter);
//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
//                Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class);
//                target.putExtra("id", id);
//                startActivity(target);
//            }
//        });
//        mAdapter.swapCursor(cursor);
            mAdapter = new StaggeredGridAdapter(getActivity(), cursor);

            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(new StaggeredItemClickListener());
            mAdapter.notifyDataSetChanged();
            mAdapter.swapCursor(cursor);
        }

        private class StaggeredItemClickListener implements StaggeredGridView.OnItemClickListener {
            public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
                Intent target = new Intent(getActivity(), PlaceDescriptionActivity.class);
                target.putExtra("id", id);
                startActivity(target);
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> cursorLoader) {
            mAdapter.swapCursor(null);
        }
    }
}