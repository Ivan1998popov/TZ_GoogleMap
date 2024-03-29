package ru.myproject.tz_googlemap;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.algo.GridBasedAlgorithm;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.clustering.algo.PreCachingAlgorithmDecorator;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JsonParser extends AsyncTask<Void, Void, List<Coordinates>> {

    private ClusterManager<MyItem> mClusterManager;
    private List<Coordinates> mCoordinates = new ArrayList<>();
    private  MyItem offsetItem;

    @SuppressLint("StaticFieldLeak")
    private MapsActivity mapsActivity;


    JsonParser(MapsActivity mapsActivity) {
        this.mapsActivity = mapsActivity;
    }


    @Override
    protected List<Coordinates> doInBackground(Void... voids) {
        HTTP_Handler http_handler = new HTTP_Handler();
        String url =mapsActivity.getResources().getString(R.string.json);

        try {
            JSONArray jsonArray = new JSONArray(http_handler.makeServiceCall(url));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray innerArray = jsonArray.getJSONArray(i);
                mCoordinates.add(new Coordinates(innerArray.getString(0),
                        innerArray.getString(1), innerArray.getString(2)));
            }
            System.out.println("/////// " + mCoordinates.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mCoordinates;
    }


    @Override
    protected void onPostExecute(List<Coordinates> coordinates) {

        setUpClusterer();
        super.onPostExecute(coordinates);
    }


    private void setUpClusterer() {

        mClusterManager = new ClusterManager<>(mapsActivity, mapsActivity.mMap);
        mapsActivity.mMap.setOnCameraIdleListener(mClusterManager);
        mapsActivity.mMap.setOnMarkerClickListener(mClusterManager);
        mClusterManager.setAlgorithm(new GridBasedAlgorithm<>());
        addItems();

    }

    private void addItems() {
        for (int i = 0; i <mCoordinates.size() ; i++) {
           offsetItem = new MyItem(Double.parseDouble(mCoordinates.get(i).getLongitude()),
                    Double.parseDouble(mCoordinates.get(i).getLatitude()));


            mClusterManager.addItem(offsetItem);
        }
    }
}

