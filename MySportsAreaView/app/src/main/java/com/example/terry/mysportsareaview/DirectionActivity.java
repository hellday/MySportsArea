package com.example.terry.mysportsareaview;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.example.terry.mysportsareaview.AsyncTask.GetDirectionsAsyncTask;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectionActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private double currentLongitude, currentLatitude;
    private LatLng toPosition;
    private String title, description, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Get Extras
        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        description = intent.getExtras().getString("description");
        double latitude = intent.getExtras().getDouble("lat");
        double longitude = intent.getExtras().getDouble("lng");
        toPosition = new LatLng(latitude, longitude);
        status = intent.getExtras().getString("status");
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
        map = googleMap;

        // Get location manager instance
        LocationManager locationManager = (LocationManager) DirectionActivity.this.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(DirectionActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }


        // Get last known location to start with
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        currentLatitude = myLocation.getLatitude();
        currentLongitude = myLocation.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        // Find Directions
        findDirections(currentLatitude, currentLongitude,
                toPosition.latitude, toPosition.longitude,
                GMapV2Direction.MODE_DRIVING );

        // Add marker to End Location
        if(status.equalsIgnoreCase("public")) {
            map.addMarker(new MarkerOptions()
                    .title(title)
                    .position(toPosition)
                    .snippet(description)
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker_public", 100, 150))));
        }else {
            map.addMarker(new MarkerOptions()
                    .title(title)
                    .position(toPosition)
                    .snippet(description)
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker_private", 100, 150))));
        }


    }

    //Fonction permettant de changer la taille des icônes des marqueurs Google Maps
    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getApplicationContext().getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints)
    {
        Polyline newPolyline;
        PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.BLUE);

        for(int i = 0 ; i < directionPoints.size() ; i++)
        {
            rectLine.add(directionPoints.get(i));
        }
        newPolyline = map.addPolyline(rectLine);
    }


    public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);

        GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(this);
        asyncTask.execute(map);
    }
}
