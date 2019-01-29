package com.microsoft.cfd.limelight;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.util.Map;

public class GoogleMapActivity extends Fragment implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener{
    private GoogleMap mMap;
    private static ClusterManager<MyItem> mClusterManager;
    private FragmentActivity myContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mClusterManager = new ClusterManager<MyItem>(getContext(), mMap);

        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
            @Override
            public boolean onClusterClick(Cluster<MyItem> cluster) {
                if(Utils.role.equals("V")) return false;
                LatLng position = cluster.getPosition();
                Log.d("Cluster Marker",position.latitude+"_"+position.longitude);
                Intent intent = new Intent(getActivity(), AssignActivity.class);
                intent.putExtra("Location", position.latitude+"_"+position.longitude);
                startActivity(intent);
                return false;
            }
        });

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItem>() {
            @Override
            public boolean onClusterItemClick(MyItem myItem) {
                if(Utils.role.equals("V")) return false;
                Toast toast = Toast.makeText(getActivity(), "Zoom out and click on cluster", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        setLocations();
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
        if(Utils.role.equals("V")) return false;
        Toast toast = Toast.makeText(getContext(), "Zoom out and click on cluster", Toast.LENGTH_SHORT);
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
            } else {
                title = "Unknown";
            }
            MyItem offsetItem = new MyItem(latx, longy, title, "");
            mClusterManager.addItem(offsetItem);
        }
    }
}
