package com.example.vinothjoshua.siv;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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
 * Created by GBCorp on 30/08/2017.
 */

public class StudentPersonalInformationFragment extends Fragment {
    String registerNumber;
    EditText regNumberText;
    TextView regnoText,admissionnoText,nameText,academicyearText,dateofbirthText,genderText,fathernameText,mothernameText;
    TextView PresentAddress1Text,PresentAddress2Text,PresentAreaText;
    TextView PresentPinCodeText,PresentStateText,PresentMobileNoText,PresentAltMobileNoText;
    TextView PresentEmailText,PresentAltEmailText,PermanentAddress1Text,PermanentAddress2Text;
    TextView PermanentAreaText,PermanentPinCodeText,PermanentStateText,PermanentMobileNoText;
    TextView PermanentAltMobNoText,PermanentEmailText,PermanentAltEmailText;
    TextView ReferenceText,StudentTypeText;

    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.studentpersonalinformation, container, false);

        searchView = (SearchView) view.findViewById(R.id.registerno);

        searchView.setSubmitButtonEnabled(true);

        regnoText=(TextView)view.findViewById(R.id.registerNumber);
        admissionnoText=(TextView)view.findViewById(R.id.admissionNumber);
        nameText =(TextView) view.findViewById(R.id.name);
        StudentTypeText =(TextView) view.findViewById(R.id.studentType);
        academicyearText=(TextView)view.findViewById(R.id.academicYear);
        dateofbirthText=(TextView) view.findViewById(R.id.dateOfBirth);
        genderText=(TextView) view.findViewById(R.id.Gender);
        fathernameText=(TextView) view.findViewById(R.id.FatherName);
        mothernameText=(TextView) view.findViewById(R.id.MotherName);
        PresentAddress1Text=(TextView) view.findViewById(R.id.PresentAddress1);
        PresentAddress2Text=(TextView) view.findViewById(R.id.PresentAddress2);
        PresentAreaText=(TextView) view.findViewById(R.id.PresentArea);
        PresentPinCodeText=(TextView) view.findViewById(R.id.PresentPin);
        PresentStateText=(TextView) view.findViewById(R.id.PresentState);
        PresentMobileNoText=(TextView) view.findViewById(R.id.PresentMobNo);
        PresentAltMobileNoText=(TextView) view.findViewById(R.id.PresentAltMobNo);
        PresentEmailText=(TextView) view.findViewById(R.id.PresentEmailId);
        PresentAltEmailText=(TextView) view.findViewById(R.id.PresentAltEmailId);

        PermanentAddress1Text=(TextView) view.findViewById(R.id.PermanentAddress1);
        PermanentAddress2Text=(TextView) view.findViewById(R.id.PermanentAddress2);
        PermanentAreaText=(TextView) view.findViewById(R.id.PermanentArea);
        PermanentPinCodeText=(TextView) view.findViewById(R.id.PermanentPin);
        PermanentStateText=(TextView) view.findViewById(R.id.PermanentState);
        PermanentMobileNoText=(TextView) view.findViewById(R.id.PermanentMobNo);
        PermanentAltMobNoText=(TextView) view.findViewById(R.id.PermanentAltMobNo);
        PermanentEmailText=(TextView) view.findViewById(R.id.PermanentEmailId);
        PermanentAltEmailText=(TextView) view.findViewById(R.id.PermanentAltEmailId);
        ReferenceText=(TextView) view.findViewById(R.id.Reference);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                registerNumber=query;
                StudentPersonalInformationFragment.GetApplicationDetailTask getStudentPersonalInformationDetail = new StudentPersonalInformationFragment.GetApplicationDetailTask();
                getStudentPersonalInformationDetail.execute();
                return true;

            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
                callSearch(newText);
                //              }
                return true;
            }
            public void callSearch(String query) {
                //Do searching
            }

        });
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
                URL url = new URL("http://"+apiUrl+"/api/v1/studentpersonalinformation/getStudentPersonalInformationDetail");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("regno", registerNumber);
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
                    Log.e("check string:",in.readLine());
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
                Log.e("array:",result);
                JSONArray jsonArr = new JSONArray(result);
                JSONObject studentPersonalObj=jsonArr.getJSONObject(0);
                JSONObject studentBaseObj=jsonArr.getJSONObject(1);
                //JSONObject jsonObj = new JSONObject(result);
                String Regno,Admissionno,name,AcademicYear, DateOfBirth, GenderStr,CandidateFatherName, CandidateMotherName;
                String PreA1,PreA2,PreArea,PrePin,PreMob,PreAltMob,PreState,PreEmail,PreAltEmail,Reference;
                String PerA1,PerA2,PerArea,PerPin,PerMob,PerAltMob,PerState,PerEmail,PerAltEmail, studentType;

                //Regno=studentPersonalObj.getString("regno");
                name = studentPersonalObj.getString("name");
                studentType=studentBaseObj.getString("studenttype");

                //Log.d("appfor:",AppFor);
//                AcademicYear=jsonObj.getString("academicyear");
//                DateOfBirth = jsonObj.getString("dateofbirth");
//                GenderStr = jsonObj.getString("gender");
//                CandidateFatherName = jsonObj.getString("fathername");
//                CandidateMotherName = jsonObj.getString("mothername");
//
//                PreA1=jsonObj.getString("presentaddress1");
//                PreA2=jsonObj.getString("presentaddress2");
//                PreArea=jsonObj.getString("presentarea");
//                PrePin=jsonObj.getString("presentpincode");
//                PreState=jsonObj.getString("presentstate");
//                PreMob=jsonObj.getString("presentmobileno");
//                PreAltMob=jsonObj.getString("presentaltmobileno");
//                PreEmail=jsonObj.getString("presentemail");
//                PreAltEmail=jsonObj.getString("presentaltemail");
//
//                PerA1=jsonObj.getString("permanentaddress1");
//                PerA2=jsonObj.getString("permanentaddress2");
//                PerArea=jsonObj.getString("permanentarea");
//                PerPin=jsonObj.getString("permanentpincode");
//                PerState=jsonObj.getString("permanentstate");
//                PerMob=jsonObj.getString("permanentmobileno");
//                PerAltMob=jsonObj.getString("permanentaltmobileno");
//                PerEmail=jsonObj.getString("permanentemail");
//                PerAltEmail=jsonObj.getString("permanentaltemail");
//                Reference=jsonObj.getString("reference");

                regnoText.setText(name);
                StudentTypeText.setText(studentType);
//                nameText.setText(name);
//                academicyearText.setText(AcademicYear);
//                dateofbirthText.setText(DateOfBirth);
//                genderText.setText(GenderStr);
//                fathernameText.setText(CandidateFatherName);
//                mothernameText.setText(CandidateMotherName);
//
//                PresentAddress1Text.setText(PreA1);
//                PresentAddress2Text.setText(PreA2);
//                PresentAreaText.setText(PreArea);
//                PresentPinCodeText.setText(PrePin);
//                PresentStateText.setText(PreState);
//                PresentMobileNoText.setText(PreMob);
//                PresentAltMobileNoText.setText(PreAltMob);
//                PresentEmailText.setText(PreEmail);
//                PresentAltEmailText.setText(PreAltEmail);
//                PermanentAddress1Text.setText(PerA1);
//                PermanentAddress2Text.setText(PerA2);
//                PermanentAreaText.setText(PerArea);
//                PermanentPinCodeText.setText(PerPin);
//                PermanentStateText.setText(PerState);
//                PermanentMobileNoText.setText(PerMob);
//                PermanentAltMobNoText.setText(PerAltMob);
//                PermanentEmailText.setText(PerEmail);
//                PermanentAltEmailText.setText(PerAltEmail);
//                ReferenceText.setText(Reference);





            }
            catch (Exception e){

            }
        }





        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }

}
