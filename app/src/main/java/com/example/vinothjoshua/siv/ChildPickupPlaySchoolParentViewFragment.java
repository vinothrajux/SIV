package com.example.vinothjoshua.siv;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
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
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Seetha on 13-Jan-18.
 */

public class ChildPickupPlaySchoolParentViewFragment extends Fragment {

    String registerNumber;
    EditText regNumberText;
    Date hwdate;
    TextView regnoText,pickupDateText,pickupPersonNameText,relationshipText,pickupPersonContactNoText,pickUpTimeText,remarksText;

    SearchView searchView;
    String SearchNo;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.childpickupplayschoolparentview, container, false);

        //searchView = (SearchView) view.findViewById(R.id.registerno);
        //registerNumber = LoginActivity.uid;

        //searchView.setSubmitButtonEnabled(true);

        pickupDateText=(TextView)view.findViewById(R.id.pickupDate);
        pickupPersonNameText =(TextView) view.findViewById(R.id.pickuppersonname);
        relationshipText =(TextView) view.findViewById(R.id.Relationship);
        pickupPersonContactNoText=(TextView)view.findViewById(R.id.pickuppersoncontactno);
        pickUpTimeText=(TextView) view.findViewById(R.id.pickupTime);
        remarksText=(TextView) view.findViewById(R.id.Remarks);
        ChildPickupPlaySchoolParentViewFragment.GetPickupDetailTask getStudentPickupDetail = new ChildPickupPlaySchoolParentViewFragment.GetPickupDetailTask();
        getStudentPickupDetail.execute();
        return view;
    }
    //API starts copy from here
    private class GetPickupDetailTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;



            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/childspickupplayschool/getStudentPickupPlaySchool");

                String registernumber = utils.getUserId();
                JSONObject postDataParams = new JSONObject();

                postDataParams.put("registernumber", registernumber);
                postDataParams.put("pickupddate", hwdate);


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

            //Toast.makeText(getActivity().getApplicationContext(), "From Server: " + result, Toast.LENGTH_SHORT).show();
            Log.e("infobase:",result);
            try {
                JSONObject jsonObj = new JSONObject(result);
                String PickupDate,PickupPersonName, PickupPersonRelationship, PickupPersonContactNo,Pickuptime,Remarks;
                PickupDate = jsonObj.getString("pickupddate");
                PickupPersonName = jsonObj.getString("pickuppersonname");
                PickupPersonRelationship = jsonObj.getString("pickuppersonrelation");
                PickupPersonContactNo = jsonObj.getString("pickuppersonmobileno");
                Pickuptime = jsonObj.getString("pickuptime");
                Remarks = jsonObj.getString("remarks");

                pickupDateText.setText(utils.convertToDateFormat(PickupDate));
                pickupPersonNameText.setText(PickupPersonName);
                relationshipText.setText(PickupPersonRelationship);
                pickupPersonContactNoText.setText(PickupPersonContactNo);
                pickUpTimeText.setText(Pickuptime);
                remarksText.setText(Remarks);

            }
            catch (Exception e){

            }
        }





        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }


}
