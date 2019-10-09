package com.example.mapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        // Add a marker in Sydney and move the camera
        LatLng IUT = new LatLng(48.08584806532316,-0.7575219207350936);
        mMap.addMarker(new MarkerOptions().position(IUT).title("IUT de Laval"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IUT, 17));

        LatLng info = new LatLng(48.08606128426794,-0.7595080965106216);
        LatLng mmi = new LatLng(48.086304961979664,-0.7588871651236739);
        LatLng tc = new LatLng(48.086143704799646,-0.7581616275851455);
        LatLng gb = new LatLng(48.08559184306898,-0.7577660017554488);
        mMap.addMarker(new MarkerOptions().position(info).title("bat info").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions().position(mmi).title("bat mmi"));
        mMap.addMarker(new MarkerOptions().position(tc).title("bat tc"));
        mMap.addMarker(new MarkerOptions().position(gb).title("bat gb"));
    }
}
