package com.example.cloudbank;

import androidx.fragment.app.FragmentActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cloudbank.databinding.ActivityMapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    FloatingActionButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, Menu.class);
                startActivity(intent);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double[][] choords = {
            {33.993683933181345, -6.847645175363522},
            {33.99766902687812, -6.85026283482123},
            {33.99827381580889, -6.847773630831469},
            {33.998949829093405, -6.847387330233625},
            {33.99991058539232, -6.849747744090523},
            {34.00136926184636, -6.846314189102174},
            {34.001903040329466, -6.847816309293754},
            {34.00293498286977, -6.853867876209431},
            {34.00521202031692, -6.84657137703123}
        };

        // Add a marker in Sydney and move the camera
        for (double[] array: choords) {
            LatLng bank = new LatLng(array[0], array[1]);
            mMap.addMarker(new MarkerOptions().position(bank).title("CloudBank Rabat Agdal Agency"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bank, 15));
        }

    }
}