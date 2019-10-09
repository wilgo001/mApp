package com.example.mapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Balise[] balises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LatLng info = new LatLng(48.08606128426794,-0.7595080965106216);
        LatLng mmi = new LatLng(48.086304961979664,-0.7588871651236739);
        LatLng tc = new LatLng(48.086143704799646,-0.7581616275851455);
        LatLng gb = new LatLng(48.08559184306898,-0.7577660017554488);
        Balise baliseInfo = new Balise(this, info, "bat info", "info");
        Balise baliseMMI = new Balise(this, mmi, "bat MMI", "mmi");
        Balise baliseTC = new Balise(this, tc, "bat TC", "tc");
        Balise baliseGB = new Balise(this, gb, "bat GB", "gb");
        balises = new Balise[]{baliseInfo, baliseMMI, baliseTC, baliseGB};
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(IUT, 17));

        mMap.setOnInfoWindowClickListener(this);

        for (Balise balise : balises) {
            balise.creerMarqueur(mMap);
        }

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.e("click sur le marqueur", marker.getTitle());
        scannerQRCode();
    }

    public void scannerQRCode() {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");

            startActivityForResult(intent, 0);

        } catch (Exception e) {
            Uri markeUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, markeUri);

        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                String infoDansQRCode = data.getStringExtra("SCAN_RESULT");
                Log.e("scanner", infoDansQRCode);

                for (Balise balise : balises) {
                    if (infoDansQRCode == balise.labelQRCode) {
                        balise.validerBalise();
                    }
                }

            }

            if (resultCode == RESULT_CANCELED) {
                onBackPressed();
            }
        }
    }
}
