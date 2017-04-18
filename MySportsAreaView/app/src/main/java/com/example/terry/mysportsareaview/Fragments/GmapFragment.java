package com.example.terry.mysportsareaview.Fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.terry.mysportsareaview.InfoWindowRefresher;
import com.example.terry.mysportsareaview.Lieux;
import com.example.terry.mysportsareaview.MainActivity;
import com.example.terry.mysportsareaview.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GmapFragment extends Fragment implements OnMapReadyCallback {

    private String sport;
    private GoogleMap map;

    private ArrayList<LatLng> latLngs;
    private JSONArray lieuxData;
    private boolean canMove;
    private boolean not_first_time_showing_info_window;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gmaps, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.idMap);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        // Check permission
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        }

        // Set a listener for marker click.
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                map.getUiSettings().setMapToolbarEnabled(true);
                return true;
            }

        });

        // Set Custom InfoWindow
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker marker) {
                // Getting view from the layout file info_window_layout
                View v = getActivity().getLayoutInflater().inflate(R.layout.windowlayout, null);

                // Getting the position from the marker
                LatLng latLng = marker.getPosition();

                // Getting reference to the Image
                ImageView img = (ImageView) v.findViewById(R.id.tv_img);
                img.setImageBitmap(resizeMapIcons("football", 50, 50));

                // set image view like this:
//                if (not_first_time_showing_info_window) {
//                    Picasso.with(getActivity())
//                            .load(R.drawable.ic_tv_light)
//                            .placeholder(R.drawable.ic_tv_light)
//                            .into(tvImg);
//                    System.out.println("1");
//                } else { // if it's the first time, load the image with the callback set
//                    not_first_time_showing_info_window=true;
//                    System.out.println("2");
//                    Picasso.with(getActivity())
//                            .load(R.drawable.ic_tv_light)
//                            .placeholder(R.drawable.ic_tv_light)
//                            .into(tvImg, new InfoWindowRefresher(arg0));
//                }


                // Getting reference to the TextView to set latitude
                TextView title = (TextView) v.findViewById(R.id.tv_lat);
                title.setTypeface(null, Typeface.BOLD);


                // Getting reference to the TextView to set longitude
                TextView description = (TextView) v.findViewById(R.id.tv_lng);

                // Setting the latitude
                title.setText(marker.getTitle());

                // Setting the longitude
                description.setText(marker.getSnippet());

                // Returning the view containing InfoWindow contents
                return v;
            }


            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                return null;

            }
        });

        // ClickListener on InfoWindowListener
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getActivity(), Lieux.class);
                intent.putExtra("title", marker.getTitle());
                intent.putExtra("description", marker.getSnippet());
                startActivity(intent);
            } });

        canMove = false;

    }

    // Gestion des Permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "Vous devez activer la localisation pour accéder à certaines fonctionnalités", Toast.LENGTH_LONG).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void setDataLieux(JSONArray lieux){
        map.clear();
        lieuxData = lieux;
        latLngs = new ArrayList<>();

        try {
            for (int i = 0; i < lieuxData.length(); i++) {

                JSONObject json = lieuxData.getJSONObject(i);

                String nomLieux = json.getString("nomLieux");
                String nomSport = json.getString("idSport");
                String description = json.getString("description");
                String status = json.getString("status");
                double latitude = json.getDouble("latitude");
                double longitude = json.getDouble("longitude");

                latLngs.add(new LatLng(latitude, longitude));
                //Add marker to the map
                if(status.equalsIgnoreCase("public")) {
                    map.addMarker(new MarkerOptions()
                            .title(nomLieux)
                            .position(latLngs.get(i))
                            .snippet(description)
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker_public", 75, 100))));
                }else {
                    map.addMarker(new MarkerOptions()
                            .title(nomLieux)
                            .position(latLngs.get(i))
                            .snippet(description)
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("marker_private", 75, 100))));
                }
                canMove = true;

            }

        } catch (JSONException e) {
                e.printStackTrace();
        }

        //Move Camera to first Marker
        if(canMove) {
            map.moveCamera(CameraUpdateFactory.newLatLng(latLngs.get(0)));
            canMove = false;
        }



    }


    //Fonction permettant de changer la taille des icônes des marqueurs Google Maps
    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
}

