package com.example.terry.mysportsareaview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class ServletAccess extends AppCompatActivity {

    TextView textMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servlet_access);
        textMsg = (TextView) findViewById(R.id.textMsg);



    }

    public void conClick(View v){
        new ServletConnexion().execute();
    }

    private class ServletConnexion extends AsyncTask<Void, Void, Void> {

        String textResult;

        @Override
        protected Void doInBackground(Void... params) {
            URL url;

            try {
                url = new URL("http://10.0.2.2:8080/MySportsAreaInterface/Servlet");
                HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
                urlCon.setRequestMethod("GET");
                urlCon.setRequestProperty("Accept-Charset", "UTF-8");
                urlCon.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

                BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
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
            System.out.println();
        }
    }

}
