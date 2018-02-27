package com.gbcorp.sivbeta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Seetha on 30-May-17.
 */

public class ParentFeedBackFragment extends Fragment {
    EditText schoolfeedbackText, appfeedbackText;
    RatingBar rateSchoolBar, rateAppBar;
    Utils utils = new Utils();
    String registernumber = utils.getUserId();
    String apiUrl = utils.getApiHost();
    int instituteid = utils.getInstituteId();
    Button rateSubmitBtn;
    String schoolFeedback, appFeedback;
    int schoolRating, appRating;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.parentfeedback, container, false);
        schoolfeedbackText = (EditText) view.findViewById(R.id.schoolfeedback);
        appfeedbackText = (EditText) view.findViewById(R.id.appfeedback);
        rateSchoolBar = (RatingBar) view.findViewById(R.id.RateSchool);
        rateAppBar = (RatingBar) view.findViewById(R.id.RateMobileApp);
        rateSubmitBtn = (Button) view.findViewById(R.id.feedbackButton);

        rateSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                schoolFeedback = schoolfeedbackText.getText().toString();
                appFeedback = appfeedbackText.getText().toString();
                schoolRating = (int)Math.round(rateSchoolBar.getRating());
                appRating = (int)Math.round(rateAppBar.getRating());

                SubmitFeedbackTask submitFeedbackTask = new SubmitFeedbackTask();
                submitFeedbackTask.execute();
            }
        });


        return view;
    }






    //API starts copy from here
    private class SubmitFeedbackTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://" + apiUrl + "/api/v1/submitfeedback");



                JSONObject postDataParams = new JSONObject();
                postDataParams.put("schoolFeedback", schoolFeedback);
                postDataParams.put("appFeedback", appFeedback);
                postDataParams.put("schoolRating", schoolRating);
                postDataParams.put("appRating", appRating);
                postDataParams.put("registernumber", registernumber);
                postDataParams.put("instituteid", instituteid);
                postDataParams.put("appRating", appRating);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000 /* milliseconds */);
//                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                //&username=+username+
                writer.write(utils.getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (IOException e) {
                // writing exception to log
                e.printStackTrace();

            } catch (Exception e) {

            }
            //JSONObject response = new JSONObject(responseStringBuffer.toString());

            return res;
        }//close doInBackground

        protected void onProgressUpdate(Integer... progress) {
//            setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
            if(result.equals("success")){
                Toast.makeText(getActivity().getApplicationContext(), "Feedback Sent Successfully!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Oops! Something went wrong", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
//            if(result.equals("PASSWORD CHANGED SUCCESSFULLY!")){
//                getActivity().finish();
//            }
        }
    }
}

