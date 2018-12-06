package com.example.tamdang.restaurantguide;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnDirections;
    // Define DB
    private SQLiteDatabase db;
    private RestaurantDBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Defining Button
        btnDirections = findViewById(R.id.btnDirections);
        // set even for btnEdit
        btnDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lat, Lng, and Address should be set to the database variables for them.

                Double Lat = 43.6453;
                Double Lng = -79.3806;

                String sLat = "40";
                String sLng = "-80";

                String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + sLat + sLng + "&destination=" + Lat.toString() + Lng.toString() + "&key=AIzaSyBYGKaq0TLEAN-TA3Q7d3GnrqvLcCBcsQ8";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Lat, Lng, and Address should be set to the database variables for them.

        Double Lat = 43.6453;
        Double Lng = -79.3806;

        LatLng destination = new LatLng(Lat, Lng);

//         DB instantiation
        myDB = new RestaurantDBHelper(this);
        db = myDB.getWritableDatabase();
        // Get restaurantID from intent
        Intent i = getIntent();
        final long restaurantID = i.getLongExtra("restaurantID", -1);
        Restaurant r = myDB.getRestaurant(db, restaurantID);
        String Address = r.getName();

        mMap.addMarker(new MarkerOptions().position(destination).title(Address));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
    }
}
