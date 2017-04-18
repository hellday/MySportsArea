package com.example.terry.mysportsareaview;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ServletAccess extends AppCompatActivity {

    TextView textMsg;
    EditText loginField, passwordField;
    String login, mdp;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servlet_access);
        textMsg = (TextView) findViewById(R.id.textMsg);
        loginField = (EditText) findViewById(R.id.loginField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        pb = (ProgressBar) findViewById(R.id.progressBar3);
        pb.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        pb.getIndeterminateDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        pb.setVisibility(ProgressBar.INVISIBLE);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    public void conClick(View v){
        login = loginField.getText().toString();
        mdp = passwordField.getText().toString();

        if(!login.equals("") || !mdp.equals("")){
            new ServletConnexionPost().execute();
        }

    }

    private class ServletConnexionGet extends AsyncTask<Void, Void, Void> {

        String textResult;

        @Override
        protected Void doInBackground(Void... params) {
            URL url;
            String urlContent = "http://10.0.2.2:8080/MySportsAreaController/Serv?action=connexion&login=" + login + "&password=" + mdp;

            try {
                url = new URL(urlContent);
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("GET");
                urlCon.setRequestProperty("Accept-Charset", "UTF-8");
                urlCon.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

                BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    textResult = inputLine;
                }
                in.close();

            } catch(IOException e) {
                e.printStackTrace();
                textResult = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            textMsg.setText(textResult);
            super.onPostExecute(result);
        }
    }

    private class ServletConnexionPost extends AsyncTask<Void, Void, Void> {

        String textResult;

        @Override
        protected Void doInBackground(Void... params) {
            //URL url;
            String urlContent = "http://10.0.2.2:8080/MySportsAreaController/Serv?action=log";

            try {


                //Create HashMap
                HashMap<String , String> parameters = new HashMap<String, String>();

                //Add Key/Value pairs
                parameters.put("login", login);
                parameters.put("password", mdp);

                //Call
                textResult = performPostCall(urlContent, parameters);




            } catch(Exception e) {
                e.printStackTrace();
                textResult = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            textMsg.setText(textResult);
            if(textMsg.getText().toString().equals("Connexion...")){
                pb.setVisibility(ProgressBar.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }, 3000);
            }
            super.onPostExecute(result);
        }
    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);



            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;

                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while ((line=br.readLine()) != null) {
                    response+=line;
                    System.out.println("Réponse : " + response);
                }
            }
            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Réponse : " + response);
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }


        System.out.println("Résultat : " + result.toString());
        return result.toString();
    }

}
