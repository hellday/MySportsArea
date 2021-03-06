package com.example.terry.mysportsareaview.AsyncTask;

import android.app.Dialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.terry.mysportsareaview.DirectionActivity;
import com.example.terry.mysportsareaview.GMapV2Direction;
import com.example.terry.mysportsareaview.R;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Terry on 17/04/2017.
 */

public class GetDirectionsAsyncTask extends AsyncTask<Map<String, String>, Object, ArrayList<LatLng>> {

    public static final String USER_CURRENT_LAT = "user_current_lat";
    public static final String USER_CURRENT_LONG = "user_current_long";
    public static final String DESTINATION_LAT = "destination_lat";
    public static final String DESTINATION_LONG = "destination_long";
    public static final String DIRECTIONS_MODE = "directions_mode";
    private DirectionActivity activity;
    private String url;

    private Exception exception;

    private Dialog progressDialog;

    public GetDirectionsAsyncTask(DirectionActivity activity /*String url*/)
    {
        super();
        this.activity = activity;

        //  this.url = url;
    }

    public void onPreExecute() {
        //progressDialog = DialogUtils.createProgressDialog(activity, activity.getString(R.string.get_data_dialog_message));
        //progressDialog.show();
    }

    @Override
    public void onPostExecute(ArrayList<LatLng> result) {
        //progressDialog.dismiss();

        if (exception == null) {
            activity.handleGetDirectionsResult(result);
        } else {
            processException();
        }
    }

    @Override
    protected ArrayList<LatLng> doInBackground(Map<String, String>... params) {

        Map<String, String> paramMap = params[0];
        try{
            LatLng fromPosition = new LatLng(Double.valueOf(paramMap.get(USER_CURRENT_LAT)) , Double.valueOf(paramMap.get(USER_CURRENT_LONG)));
            LatLng toPosition = new LatLng(Double.valueOf(paramMap.get(DESTINATION_LAT)) , Double.valueOf(paramMap.get(DESTINATION_LONG)));
            GMapV2Direction md = new GMapV2Direction();
            Document doc = md.getDocument(fromPosition, toPosition, paramMap.get(DIRECTIONS_MODE));
            ArrayList<LatLng> directionPoints = md.getDirection(doc);
            return directionPoints;

        }
        catch (Exception e) {
            exception = e;
            return null;
        }
    }

    private void processException() {
        //Toast.makeText(activity, activity.getString(R.string.error_when_retrieving_data), Toast.LENGTH_LONG).show();
    }

}