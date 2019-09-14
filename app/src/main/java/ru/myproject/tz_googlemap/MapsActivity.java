package ru.myproject.tz_googlemap;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        new JsonParser().execute();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }





//        JSONArray jsonArray = null;
//        try {
//            jsonArray = new JSONArray("https://github.com/Ivan1998popov/my/blob/master/my.json");
//
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
//                String value1 = (String) jsonArray1.get(0);
//                String value2 = (String) jsonArray1.get(1);
//                String value3 = (String) jsonArray1.get(2);
//                System.out.println("/////////////////////////  " + value1 + "  // " + value2 + " //// " + value3);
//            }
//
//
//        } catch (
//                JSONException e) {
//            e.printStackTrace();
//        }
//    }
}
