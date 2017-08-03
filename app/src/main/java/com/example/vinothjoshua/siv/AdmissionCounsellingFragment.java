package com.example.vinothjoshua.siv;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Seetha on 27-May-17.
 */

public class AdmissionCounsellingFragment extends Fragment {
    String applicationNumber;
    EditText appNoText;
    TextView AppForText, CandidateNameText,GenderText,FatherNameText;
    Button searchBtn;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admissioncounselling, container, false);
        searchBtn = (Button) view.findViewById(R.id.searchApplicationBtn);
        appNoText = (EditText) view.findViewById(R.id.applicationno);

        AppForText = (TextView) view.findViewById(R.id.AppFor);
        CandidateNameText = (TextView) view.findViewById(R.id.CandidateName);
        GenderText =(TextView) view.findViewById(R.id.Gender);
        FatherNameText=(TextView) view.findViewById(R.id.FatherName);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applicationNumber=appNoText.getText().toString();
                GetApplicationDetailTask getApplicationDetail = new GetApplicationDetailTask();
                getApplicationDetail.execute();


            }
        });
//        getActivity().getActionBar().setTitle("Yest");
//        getActivity().getActionBar().show();

        return view;
        //usernameEditText = (EditText) findViewById(R.id.username);
    }
    //API starts copy from here
    private class GetApplicationDetailTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/admissioncounselling/getApplcationDetail");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("applno", applicationNumber);
                Log.e("params",postDataParams.toString());

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

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch (IOException e) {
                // writing exception to log
                e.printStackTrace();

            }
            catch (Exception e){

            }
            //JSONObject response = new JSONObject(responseStringBuffer.toString());

            return res;
        }//close doInBackground

        protected void onProgressUpdate(Integer... progress) {
//            setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {

            Toast.makeText(getActivity().getApplicationContext(), "From Server: " + result, Toast.LENGTH_SHORT).show();
            try {
                //JSONArray jsonArr = new JSONArray(result);

                JSONObject jsonObj = new JSONObject(result);
                String AppFor, CandidateFirstName, CandidateMiddleName, CandidateLastName, CandidateFatherName, CandidateMotherName, CandidateName,Gender;
                AppFor = jsonObj.getString("appfor");
                Log.d("appfor:",AppFor);
                CandidateFirstName = jsonObj.getString("candfirstname");
                CandidateMiddleName = jsonObj.getString("candmiddlename");
                CandidateLastName = jsonObj.getString("candlastname");
                CandidateFatherName = jsonObj.getString("candfathername");
                CandidateMotherName = jsonObj.getString("candmothername");
                Gender = jsonObj.getString("gender");

                CandidateName = CandidateFirstName + ' ' + CandidateMiddleName + ' ' + CandidateLastName;

                AppForText.setText(AppFor);
                CandidateNameText.setText(CandidateName);
                GenderText.setText(Gender);
            }
            catch (Exception e){

            }
        }
    }

    //API starts copy from here
}
