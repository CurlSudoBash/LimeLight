package com.microsoft.cfd.limelight;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static ClusterManager<MyItem> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        setLocations();

/*
            // Set some lat/lng coordinates to start with.
            double lat = 51.5145160;
            double lng = -0.1270060;

            // Add ten cluster items in close proximity, for purposes of this example.
            for (int i = 0; i < 10; i++) {
                double offset = i / 60d;
                lat = lat + offset;
                lng = lng + offset;
                MyItem offsetItem = new MyItem(lat, lng);
                mClusterManager.addItem(offsetItem);
            }


            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
                }
            });*/
        mMap.setOnMarkerClickListener(this);

        /*

        LatLng sydney = new LatLng(-34, 151);
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(sydney)
                .radius(10000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle circle) {
                Toast toast = Toast.makeText(MapsActivity.this, "Clicked circle", Toast.LENGTH_LONG);
                // Intent intent = new Intent(this, AssignActivity.class);
                //startActivity(intent);
                toast.show();
            }
        });
        */
        double lat = Double.parseDouble(Utils.location.split("_")[0]);
        double lon = Double.parseDouble(Utils.location.split("_")[1]);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon),12.0f));
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng present = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(present).title("You are here"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast toast = Toast.makeText(this, "Clicked marker", Toast.LENGTH_LONG);
        if(Utils.role.equals("V")) return false;
        LatLng position = marker.getPosition();
        Intent intent = new Intent(this, AssignActivity.class);
        intent.putExtra("Location", position.latitude+"_"+position.longitude);
        startActivity(intent);
        toast.show();
        return false;
    }

    public static void setLocations() {
        if(mClusterManager==null) return;
        for (Map.Entry<String, String> entry : Utils.locationMap.entrySet()) {
            String locationString = entry.getValue();
            String[] loc = locationString.split("_");
            Double latx = Double.parseDouble(loc[0]);
            Double longy = Double.parseDouble(loc[1]);
            String role = loc[2];
            int v = 0, r = 0;
            String title = "";
            if (role.equals("V")) {
                title = "Victim";
            } else if (role.equals("S")) {
                title = "Scout";
            } else if (role.equals("M")) {
                title = "Medic";
            } else if (role.equals("L")) {
                title = "Lifter";
            }
            MyItem offsetItem = new MyItem(latx, longy, title, "");
            mClusterManager.addItem(offsetItem);
        }
    }
}
