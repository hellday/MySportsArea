package com.example.terry.mysportsareaview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Lieux extends AppCompatActivity {
    private String title, description;
    private TextView statusText, sportText;
    private ListView mListView;
    private double latitude, longitude;
    private double currentLongitude, currentLatitude;

    private boolean permissionLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lieux);

        // Get Action Bar & Set BackButton
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Fiche");

        // Get Values
        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        description = intent.getExtras().getString("description");

        // Get Infos From Servlet
        new ServletGetInfosLieux().execute();

        // Set Values
        TextView titleText = (TextView) findViewById(R.id.title);
        titleText.setText(title);

        TextView descriptionText = (TextView) findViewById(R.id.description);
        descriptionText.setText(description);

        statusText = (TextView) findViewById(R.id.setStatus);
        sportText = (TextView) findViewById(R.id.setSport);

        mListView = (ListView) findViewById(R.id.listView);
        afficherListeComments();

        // Get Current Position
        LocationManager locationManager = (LocationManager) Lieux.this.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(Lieux.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED & locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))  {
            Location myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            currentLatitude = myLocation.getLatitude();
            currentLongitude = myLocation.getLongitude();
            permissionLocation = true;
        }else {
            permissionLocation = false;
        }


    }

    private List<Comment> genererComments(){
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(new Comment(Color.BLACK, "Florent", "Mon premier commentaire sur MySportsArea !"));
        comments.add(new Comment(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
        comments.add(new Comment(Color.GREEN, "Logan", "Que c'est beau..."));
        comments.add(new Comment(Color.RED, "Mathieu", "Il est quelle heure ??"));
        comments.add(new Comment(Color.GRAY, "Willy", "On y est presque"));
        return comments;
    }

    private void afficherListeComments(){
        List<Comment> comments = genererComments();

        CommentAdapter adapter = new CommentAdapter(Lieux.this, comments);
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                System.out.println("Add");
                addComment();
                return true;
            case R.id.action_like:

                if (item.getTitle().equals("Like_off")) {
                    item.setIcon(R.drawable.hearth_on);
                    item.setTitle("Like_on");
                    Toast.makeText(Lieux.this, "Vous aimez : \n << " + title + " >>", Toast.LENGTH_SHORT).show();
                }else {
                    item.setIcon(R.drawable.hearth_off);
                    item.setTitle("Like_off");
                    Toast.makeText(Lieux.this, "Vous n'aimez plus : \n << " + title + " >>", Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.action_goTo:

//                Intent intent = new Intent(Lieux.this, DirectionActivity.class);
//                intent.putExtra("title", title);
//                intent.putExtra("description", description);
//                intent.putExtra("lat", latitude);
//                intent.putExtra("lng", longitude);
//                intent.putExtra("status", statusText.getText().toString());
//                startActivity(intent);

                if(permissionLocation) {

                    String baseUri = "http://maps.google.com/maps?saddr=%s,%s&daddr=%s,%s";

                    String uri = String.format(baseUri, currentLatitude, currentLongitude, latitude, longitude);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }else Toast.makeText(Lieux.this, "Vous devez d'abord activer la localisation pour pouvoir utiliser cette fonctionnalité", Toast.LENGTH_SHORT).show();

                return true;
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void addComment() {
//        new AlertDialog.Builder(Lieux.this)
//                .setTitle("Delete entry")
//                .setMessage("Are you sure you want to delete this entry?")
//                .setPositiveButton("Envoyer", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                    }
//                })
//                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
//                .setIcon(android.R.drawable.ic_dialog_email)
//                .show();

        AlertDialog.Builder builder = new AlertDialog.Builder(Lieux.this);
        // Get the layout inflater
        LayoutInflater inflater = Lieux.this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_comment, null))
                // Add action buttons
                .setPositiveButton("Envoyer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.show();
    }




    private class ServletGetInfosLieux extends AsyncTask<Void, Void, Void> {

        String textResult = "";
        List<String> list;
        JSONArray jsonArray;

        @Override
        protected Void doInBackground(Void... params) {
            URL url;

            // Replace Space to match GET Request
            title = title.replaceAll(" ", "+");
            String urlContent = "http://10.0.2.2:8080/MySportsAreaController/Serv?action=infosLieux&title=" + title;
            System.out.println(urlContent);

            try {
                url = new URL(urlContent);
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("GET");
                urlCon.setRequestProperty("Accept-Charset", "UTF-8");
                urlCon.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

                BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    textResult += inputLine;
                }
                in.close();

                String jsonString = textResult; // HttpClient?
                jsonArray = new JSONArray(jsonString);
                list = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(jsonArray.getString(i));
                }



            } catch(IOException e) {
                e.printStackTrace();
                textResult = e.toString();
            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            System.out.println(jsonArray);

            try {
                JSONObject json = jsonArray.getJSONObject(0);

                String nomLieux = json.getString("nomLieux");
                String nomSport = json.getString("idSport");
                String description = json.getString("description");
                String status = json.getString("status");
                latitude = json.getDouble("latitude");
                longitude = json.getDouble("longitude");

                statusText.setText(status);
                sportText.setText(nomSport);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(result);
        }
    }
}


