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

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by GBCorp on 29/09/2017.
 */

public class ParentProfileViewFragment extends Fragment {
    String registerNumber;
    EditText regNumberText;
    TextView regnoText,admissionnoText,nameText,academicyearText,dateofbirthText,genderText,fathernameText,mothernameText;
    TextView PresentAddress1Text,PresentAddress2Text,PresentAreaText;
    TextView PresentPinCodeText,PresentStateText,PresentMobileNoText,PresentAltMobileNoText;
    TextView PresentEmailText,PresentAltEmailText,PermanentAddress1Text,PermanentAddress2Text;
    TextView PermanentAreaText,PermanentPinCodeText,PermanentStateText,PermanentMobileNoText;
    TextView PermanentAltMobNoText,PermanentEmailText,PermanentAltEmailText;
    TextView ReferenceText;

    SearchView searchView;
    String SearchNo;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parentprofileview, container, false);

        //searchView = (SearchView) view.findViewById(R.id.registerno);
        //registerNumber = LoginActivity.uid;

        //searchView.setSubmitButtonEnabled(true);

        regnoText=(TextView)view.findViewById(R.id.RegNo);
       // admissionnoText=(TextView)view.findViewById(R.id.admissionNumber);
        nameText =(TextView) view.findViewById(R.id.Name);
        academicyearText=(TextView)view.findViewById(R.id.AcademicYear);
        dateofbirthText=(TextView) view.findViewById(R.id.DOB);
        //genderText=(TextView) view.findViewById(R.id.Gender);
        fathernameText=(TextView) view.findViewById(R.id.Fathername);
        mothernameText=(TextView) view.findViewById(R.id.Mothername);
        PresentAddress1Text=(TextView) view.findViewById(R.id.Presentaddress1);
        PresentAddress2Text=(TextView) view.findViewById(R.id.Presentaddress2);
        PresentAreaText=(TextView) view.findViewById(R.id.PresentArea);
        PresentPinCodeText=(TextView) view.findViewById(R.id.Presentpin);
        PresentStateText=(TextView) view.findViewById(R.id.Presentstate);
        PresentMobileNoText=(TextView) view.findViewById(R.id.Presentmobileno);
        PresentAltMobileNoText=(TextView) view.findViewById(R.id.Presentaltmobileno);
        PresentEmailText=(TextView) view.findViewById(R.id.PresentEmailId);
        PresentAltEmailText=(TextView) view.findViewById(R.id.PresentaltEmailId);

       // PermanentAddress1Text=(TextView) view.findViewById(R.id.PermanentAddress1);
       // PermanentAddress2Text=(TextView) view.findViewById(R.id.PermanentAddress2);
       // PermanentAreaText=(TextView) view.findViewById(R.id.PermanentArea);
       // PermanentPinCodeText=(TextView) view.findViewById(R.id.PermanentPin);
        // PermanentStateText=(TextView) view.findViewById(R.id.PermanentState);
       // PermanentMobileNoText=(TextView) view.findViewById(R.id.PermanentMobNo);
       // PermanentAltMobNoText=(TextView) view.findViewById(R.id.PermanentAltMobNo);
      //  PermanentEmailText=(TextView) view.findViewById(R.id.PermanentEmailId);
      //  PermanentAltEmailText=(TextView) view.findViewById(R.id.PermanentAltEmailId);
      //  ReferenceText=(TextView) view.findViewById(R.id.Reference);
        ParentProfileViewFragment.GetApplicationDetailTask getStudentPersonalInformationDetail = new ParentProfileViewFragment.GetApplicationDetailTask();
        getStudentPersonalInformationDetail.execute();
//       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //callSearch(query);
//                registerNumber=query;
//                ParentProfileViewFragment.GetApplicationDetailTask getStudentPersonalInformationDetail = new ParentProfileViewFragment.GetApplicationDetailTask();
//                getStudentPersonalInformationDetail.execute();
//                return true;
//
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
//                callSearch(newText);
//                //              }
//                return true;
//            }
//            public void callSearch(String query) {
//                //Do searching
//            }
//
//        });
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
                URL url = new URL("http://"+apiUrl+"/api/v1/studentpersonalinformation/getStudentProfileInformationDetail");


                JSONObject postDataParams = new JSONObject();
                registerNumber = utils.getUserId();
                postDataParams.put("regno", registerNumber);

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
            Log.e("infobase:",result);
            try {
                //JSONArray jsonArr = new JSONArray(result);

                JSONObject jsonObj = new JSONObject(result);
                String Regno,Admissionno,name,AcademicYear, DateOfBirth, GenderStr,CandidateFatherName, CandidateMotherName;
                String PreA1,PreA2,PreArea,PrePin,PreMob,PreAltMob,PreState,PreEmail,PreAltEmail,Reference;
                String PerA1,PerA2,PerArea,PerPin,PerMob,PerAltMob,PerState,PerEmail,PerAltEmail;

                JSONObject StudentPersonaljsonObj = new JSONObject(jsonObj.getString("studentPersonalInfo"));
                JSONObject StudentBasejsonObj = new JSONObject(jsonObj.getString("studentBaseInfo"));

                Regno=StudentPersonaljsonObj.getString("regno");
               // Admissionno=jsonObj.getString("admissionno");
                name = StudentPersonaljsonObj.getString("name");
                //Log.d("appfor:",AppFor);
                AcademicYear=StudentPersonaljsonObj.getString("academicyear");
                DateOfBirth = StudentPersonaljsonObj.getString("dateofbirth");
               // GenderStr = jsonObj.getString("gender");
                CandidateFatherName = StudentPersonaljsonObj.getString("fathername");
                CandidateMotherName = StudentPersonaljsonObj.getString("mothername");

                PreA1=jsonObj.getString("presentaddress1");
                PreA2=jsonObj.getString("presentaddress2");
                PreArea=jsonObj.getString("presentarea");
                PrePin=jsonObj.getString("presentpincode");
                PreState=jsonObj.getString("presentstate");
                PreMob=jsonObj.getString("presentmobileno");
                PreAltMob=jsonObj.getString("presentaltmobileno");
                PreEmail=jsonObj.getString("presentemail");
                PreAltEmail=jsonObj.getString("presentaltemail");

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

                regnoText.setText(Regno);
                //admissionnoText.setText(Admissionno);
                nameText.setText(name);
                academicyearText.setText(AcademicYear);
                dateofbirthText.setText(DateOfBirth);
                //genderText.setText(GenderStr);
                fathernameText.setText(CandidateFatherName);
                mothernameText.setText(CandidateMotherName);

                PresentAddress1Text.setText(PreA1);
                PresentAddress2Text.setText(PreA2);
                PresentAreaText.setText(PreArea);
                PresentPinCodeText.setText(PrePin);
                PresentStateText.setText(PreState);
                PresentMobileNoText.setText(PreMob);
                PresentAltMobileNoText.setText(PreAltMob);
                PresentEmailText.setText(PreEmail);
                PresentAltEmailText.setText(PreAltEmail);
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
