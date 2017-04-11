package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.reginalddc.bahalanapp.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView back_textView;
    private String receivedName;
    private double receivedLat;
    private double receivedLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        back_textView = (TextView) findViewById(R.id.back_textView);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");

        back_textView.setTypeface(typeface);

        back_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, SpecificRestaurantActivity.class);
                startActivity(intent);
            }
        });

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

        Intent receivedIntent = getIntent();
        if(receivedIntent != null) {
            receivedName = receivedIntent.getStringExtra("NameOfResto");
            receivedLat = Double.parseDouble(receivedIntent.getStringExtra("LatOfResto"));
            receivedLong = Double.parseDouble(receivedIntent.getStringExtra("LongOfResto"));

            if((receivedName != null)) {
                if(!receivedName.equals("")) {
                    // Add a marker in IICS and move the camera
                    LatLng resto = new LatLng(receivedLat, receivedLong);
                    mMap.addMarker(new MarkerOptions().position(resto).title(receivedName));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(resto, 18));
                }
            }
        }

    }
}
