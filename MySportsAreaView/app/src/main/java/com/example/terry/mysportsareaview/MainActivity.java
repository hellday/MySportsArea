package com.example.terry.mysportsareaview;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.terry.mysportsareaview.Fragments.GmapFragment;
import com.example.terry.mysportsareaview.Fragments.ImportFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<String> sportList, statutList, typeList;
    private String sport, status, type;
    private Bundle bundle;
    private GmapFragment gmapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new GmapFragment()).commit();

        //Variables
        sport = "Tous";
        status = "Tous";
        type = "Tous";

        //Spinner
        sportList = new ArrayList<String>();
        sportList.add("Tous");
        //GetSports
        getAllSports();

        statutList = new ArrayList<String>();
        statutList.add("Tous");
        statutList.add("Public");
        statutList.add("Privé");

        typeList = new ArrayList<String>();
        typeList.add("Tous");
        typeList.add("Club");
        typeList.add("Espace Santé");
        typeList.add("Gymnase");
        typeList.add("Gymnase Associatif");
        typeList.add("Piscine");
        typeList.add("Salle de Sport");
        typeList.add("Skatepark");
        typeList.add("Stade");
        typeList.add("Terrain de Tennis");


        Spinner sp = (Spinner) navigationView.getMenu().findItem(R.id. nav_lang).getActionView();
        sp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sportList));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,sportList.get(position),Toast.LENGTH_SHORT).show();
                sport = sportList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner sp2 = (Spinner) navigationView.getMenu().findItem(R.id. status).getActionView();
        sp2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, statutList));
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,statutList.get(position),Toast.LENGTH_SHORT).show();
                status = statutList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner sp3 = (Spinner) navigationView.getMenu().findItem(R.id. type).getActionView();
        sp3.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typeList));
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this,statutList.get(position),Toast.LENGTH_SHORT).show();
                type = typeList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                Fragment f = getFragmentManager().findFragmentById(R.id.content_frame);

                if (f instanceof GmapFragment) {
                    Toast.makeText(MainActivity.this, "Carte mise à jour", Toast.LENGTH_SHORT).show();

                    //GetLieux
                    new ServletGetLieux().execute();
                }

            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();

        int id = item.getItemId();

        if (id == R.id.partenaires) {
            fm.beginTransaction().replace(R.id.content_frame, new ImportFragment()).commit();
        } else if (id == R.id.myMap) {
            fm.beginTransaction().replace(R.id.content_frame, new GmapFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getSport() {
        return sport;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void getAllSports() {
        new ServletGetSports().execute();
    }

    private class ServletGetLieux extends AsyncTask<Void, Void, Void> {

        String textResult = "";
        List<String> list;
        JSONArray jsonArray;

        @Override
        protected Void doInBackground(Void... params) {
            URL url;

            // Replace Space to match GET Request
            type = type.replaceAll(" ", "+");
            String urlContent = "http://10.0.2.2:8080/MySportsAreaController/Serv?action=lieux&sport=" + sport + "&status=" + status + "&type=" + type;

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
            //Send to Gmap
            gmapFragment = (GmapFragment) getFragmentManager().findFragmentById(R.id.content_frame);
            gmapFragment.setDataLieux(jsonArray);

            super.onPostExecute(result);
        }
    }


    private class ServletGetSports extends AsyncTask<Void, Void, Void> {

        String textResult = "";
        List<String> list;
        JSONArray jsonArray;

        @Override
        protected Void doInBackground(Void... params) {
            URL url;
            String urlContent = "http://10.0.2.2:8080/MySportsAreaController/Serv?action=sports";

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

            try {
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject json = jsonArray.getJSONObject(i);
                    String nomSport = json.getString("nomSport");
                    sportList.add(nomSport);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(result);
        }
    }

}
