package com.example.mapp;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Balise {

    MapsActivity context;
    LatLng coordonnees;
    String titre;
    boolean valide;
    Marker marqueur;
    String labelQRCode;

    public Balise(MapsActivity context, LatLng coordonnees, String titre, String labelQRCode) {
        this.context = context;
        this.coordonnees = coordonnees;
        this.titre = titre;
        this.labelQRCode = labelQRCode;
        this.valide = false;
        this.marqueur = null;
    }

    public void creerMarqueur(GoogleMap pMap) {
        marqueur = pMap.addMarker(new MarkerOptions()
                .position(coordonnees)
                .title(titre)
                .snippet("Scanner QRCODE"));
    }

    public void validerBalise() {
        valide = true;
        marqueur.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
    }
}
