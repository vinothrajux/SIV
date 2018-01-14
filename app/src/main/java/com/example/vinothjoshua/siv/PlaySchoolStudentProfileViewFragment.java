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
 * Created by GBCorp on 27/11/2017.
 */

public class PlaySchoolStudentProfileViewFragment extends Fragment {
    String registerNumber;
    EditText regNumberText;
    TextView regnoText,nameText,academicyearText,programText,sectionText;
    TextView dateofbirthText,ageText,genderText,candfathernameText,candmothernameText;
    TextView presentaddress1Text,presentaddress2Text,presentareaText,presentpincodeText,presentstateText,fathersmobilenoText,fathersaltmobnoText,mothersmobilenoText,mothersaltmobnoText,fathersemailText,mothersemailText,referenceText;
    TextView fathersoccupationText,fathersofficenameText,fathersofficeaddress1Text,fathersofficeaddress2Text,fathersofficeareaText,fathersofficepincodeText,fathersofficestateText,fathersofficephonenoText,fathersofficealtphonenoText,fathersofficeextensionnoText;
    TextView mothersoccupationText,mothersofficenameText,mothersofficeaddress1Text,mothersofficeaddress2Text,mothersofficeareaText,mothersofficepincodeText,mothersofficestateText,mothersofficephonenoText,mothersofficealtphonenoText,mothersofficeextensionnoText;
    TextView fathersdobText,mothersdobText,parentsweddingdateText,religionText,pickuppersonnameText,pickuppersoncontactnoText,pickuppersonaltcontactnoText,pickuppersonrelationshipText;
    TextView transportText, transportstageText;

    SearchView searchView;
    String SearchNo;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.studentprofileviewplayschool, container, false);

        //searchView = (SearchView) view.findViewById(R.id.registerno);
        //registerNumber = LoginActivity.uid;

        //searchView.setSubmitButtonEnabled(true);

        regnoText=(TextView)view.findViewById(R.id.RegNo);
        // admissionnoText=(TextView)view.findViewById(R.id.admissionNumber);
        nameText =(TextView) view.findViewById(R.id.Name);


        programText=(TextView)view.findViewById(R.id.Program);
        sectionText=(TextView)view.findViewById(R.id.Section);
        dateofbirthText=(TextView)view.findViewById(R.id.dateofbirth);
        ageText=(TextView)view.findViewById(R.id.age);
        genderText=(TextView)view.findViewById(R.id.Gender);
        candfathernameText=(TextView)view.findViewById(R.id.Fathername);
        candmothernameText=(TextView)view.findViewById(R.id.Mothername);
        presentaddress1Text=(TextView)view.findViewById(R.id.Presentaddress1);
        presentaddress2Text=(TextView)view.findViewById(R.id.Presentaddress2);
        presentareaText=(TextView)view.findViewById(R.id.PresentArea);
        presentpincodeText=(TextView)view.findViewById(R.id.Presentpin);
        presentstateText=(TextView)view.findViewById(R.id.Presentstate);
        fathersmobilenoText=(TextView)view.findViewById(R.id.FathersMobileNo);
        fathersaltmobnoText=(TextView)view.findViewById(R.id.Fathersaltmobileno);
        mothersmobilenoText=(TextView)view.findViewById(R.id.Mothersmobileno);
        mothersaltmobnoText=(TextView)view.findViewById(R.id.Mothersaltmobileno);
        fathersemailText=(TextView)view.findViewById(R.id.FathersEmailId);
        mothersemailText=(TextView)view.findViewById(R.id.MothersEmailId);
        referenceText=(TextView)view.findViewById(R.id.Reference);
        fathersoccupationText=(TextView)view.findViewById(R.id.Fathersoccupation);
        fathersofficenameText=(TextView)view.findViewById(R.id.Fathersofficename);
        fathersofficeaddress1Text=(TextView)view.findViewById(R.id.Fathersofficeaddress1);
        fathersofficeaddress2Text=(TextView)view.findViewById(R.id.Fathersofficeaddress2);
        fathersofficeareaText=(TextView)view.findViewById(R.id.FathersOfficeArea);
        fathersofficepincodeText=(TextView)view.findViewById(R.id.Fathersofficepincode);
        fathersofficestateText=(TextView)view.findViewById(R.id.Fathersofficestate);
        fathersofficephonenoText=(TextView)view.findViewById(R.id.FathersOfficePhoneNo);
        fathersofficealtphonenoText=(TextView)view.findViewById(R.id.Fathersofficealtphoneno);
        fathersofficeextensionnoText=(TextView)view.findViewById(R.id.Fathersofficeextnno);
        mothersoccupationText=(TextView)view.findViewById(R.id.Mothersoccupation);
        mothersofficenameText=(TextView)view.findViewById(R.id.Mothersofficename);
        mothersofficeaddress1Text=(TextView)view.findViewById(R.id.Mothersofficeaddress1);
        mothersofficeaddress2Text=(TextView)view.findViewById(R.id.Mothersofficeaddress2);
        mothersofficeareaText=(TextView)view.findViewById(R.id.MothersOfficeArea);
        mothersofficepincodeText=(TextView)view.findViewById(R.id.Mothersofficepincode);
        mothersofficestateText=(TextView)view.findViewById(R.id.Mothersofficestate);
        mothersofficephonenoText=(TextView)view.findViewById(R.id.MothersOfficePhoneNo);
        mothersofficealtphonenoText=(TextView)view.findViewById(R.id.Mothersofficealtphoneno);
        mothersofficeextensionnoText=(TextView)view.findViewById(R.id.Mothersofficeextnno);
        fathersdobText=(TextView)view.findViewById(R.id.fathersDateofBirth);
        mothersdobText=(TextView)view.findViewById(R.id.MothersDateofBirth);
        parentsweddingdateText=(TextView)view.findViewById(R.id.ParentsWeddingDate);
        religionText=(TextView)view.findViewById(R.id.Religion);
        pickuppersonnameText=(TextView)view.findViewById(R.id.Pickuppersonname);
        pickuppersoncontactnoText=(TextView)view.findViewById(R.id.Pickuppersoncontactno);
        pickuppersonaltcontactnoText=(TextView)view.findViewById(R.id.Pickuppersonaltcontactno);
        pickuppersonrelationshipText=(TextView)view.findViewById(R.id.Pickuppersonrelationship);
        transportText=(TextView)view.findViewById(R.id.Transport);
        transportstageText=(TextView)view.findViewById(R.id.TransportStage);
        academicyearText=(TextView)view.findViewById(R.id.AcademicYear);


        PlaySchoolStudentProfileViewFragment.GetApplicationDetailTask getPlaySchoolStudentProfileInformationDetail = new PlaySchoolStudentProfileViewFragment.GetApplicationDetailTask();
        getPlaySchoolStudentProfileInformationDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/playschoolstudentpersonalinformation/getPlaySchoolStudentProfileInformationDetail");


                JSONObject postDataParams = new JSONObject();
                registerNumber = utils.getUserId();
                postDataParams.put("registernumber", registerNumber);

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
                JSONArray jsonArr = new JSONArray(result);

                JSONObject tableoneObj = jsonArr.getJSONObject(0);
                JSONObject tabletwoObj = jsonArr.getJSONObject(1);
                String Registernumber,name,AcademicYear, Program, Section,DateofBirth, Age, Gender,CandFatherName,CandMotherName;
                String PresentAddress1,PresentAddress2,PresentArea,PresentPinCode,PresentState,FathersMobileNo,FathersAltMobileNo,MothersMobileNo,MothersAltMobileNo,FathersEmail,MothersEmail,Reference;
                String FathersOccupation,FathersOfficeName,FathersOfficeAddress1,FathersOfficeAddress2,FathersOfficeArea,FathersOfficePinCode,FathersOfficeState,FathersOfficePhoneNo,FathersOfficeAltPhoneNo,FathersOfficeExtensionNo;
                String MothersOccupation,MothersOfficeName,MothersOfficeAddress1,MothersOfficeAddress2,MothersOfficeArea,MothersOfficePinCode,MothersOfficeState,MothersOfficePhoneNo,MothersOfficeAltPhoneNo,MothersOfficeExtensionNo;
                String FathersDOB,MothersDOB,ParentsWeddingDate,Religion,PickupPersonName,PickupPersonContactNo,PickupPersonAltContactNo,PickupPersonRelationShip,Transport,TransportStage;
                name = tableoneObj.getString("candidatename");
                Registernumber=tableoneObj.getString("registernumber");

                Program=tableoneObj.getString("standardstudying");
                Section=tableoneObj.getString("section");
                DateofBirth = tabletwoObj.getString("dateofbirth");
                Age = tabletwoObj.getString("age");
                Gender = tabletwoObj.getString("gender");
                CandFatherName = tabletwoObj.getString("candfathername");
                CandMotherName = tabletwoObj.getString("candmothername");
                PresentAddress1 = tabletwoObj.getString("presentaddress1");
                PresentAddress2 = tabletwoObj.getString("presentaddress2");
                PresentArea = tabletwoObj.getString("presentarea");
                PresentPinCode = tabletwoObj.getString("presentpincode");
                PresentState = tabletwoObj.getString("presentstate");
                FathersMobileNo = tabletwoObj.getString("fathersmobileno");
                FathersAltMobileNo = tabletwoObj.getString("fathersaltmobno");
                MothersMobileNo = tabletwoObj.getString("mothersmobileno");
                MothersAltMobileNo = tabletwoObj.getString("mothersaltmobno");
                FathersEmail = tabletwoObj.getString("fathersemail");
                MothersEmail = tabletwoObj.getString("mothersemail");
                Reference = tabletwoObj.getString("reference");
                FathersOccupation = tabletwoObj.getString("fathersoccupation");
                FathersOfficeName = tabletwoObj.getString("fathersofficename");
                FathersOfficeAddress1 = tabletwoObj.getString("fathersofficeaddress1");
                FathersOfficeAddress2 = tabletwoObj.getString("fathersofficeaddress2");
                FathersOfficeArea = tabletwoObj.getString("fathersofficearea");
                FathersOfficePinCode = tabletwoObj.getString("fathersofficepincode");
                FathersOfficeState = tabletwoObj.getString("fathersofficestate");
                FathersOfficePhoneNo = tabletwoObj.getString("fathersofficephoneno");
                FathersOfficeAltPhoneNo = tabletwoObj.getString("fathersofficealtphoneno");
                FathersOfficeExtensionNo = tabletwoObj.getString("fathersofficeextensionno");
                MothersOccupation = tabletwoObj.getString("mothersoccupation");
                MothersOfficeName = tabletwoObj.getString("mothersofficename");
                MothersOfficeAddress1 = tabletwoObj.getString("mothersofficeaddress1");
                MothersOfficeAddress2 = tabletwoObj.getString("mothersofficeaddress2");
                MothersOfficeArea = tabletwoObj.getString("mothersofficearea");
                MothersOfficePinCode = tabletwoObj.getString("mothersofficepincode");
                MothersOfficeState = tabletwoObj.getString("mothersofficestate");
                MothersOfficePhoneNo = tabletwoObj.getString("mothersofficephoneno");
                MothersOfficeAltPhoneNo = tabletwoObj.getString("mothersofficealtphoneno");
                MothersOfficeExtensionNo = tabletwoObj.getString("mothersofficeextensionno");
                FathersDOB = tabletwoObj.getString("fathersdob");
                MothersDOB = tabletwoObj.getString("mothersdob");
                ParentsWeddingDate = tabletwoObj.getString("parentsweddingdate");
                Religion = tabletwoObj.getString("religion");
                PickupPersonName = tabletwoObj.getString("pickuppersonname");
                PickupPersonContactNo = tabletwoObj.getString("pickuppersoncontactno");
                PickupPersonAltContactNo = tabletwoObj.getString("pickuppersonaltcontactno");
                PickupPersonRelationShip = tabletwoObj.getString("pickuppersonrelationship");
                Transport = tabletwoObj.getString("transport");
                TransportStage = tabletwoObj.getString("transportstage");
                AcademicYear=tableoneObj.getString("academicyear");

                nameText.setText(name);
                regnoText.setText(Registernumber);

                programText.setText(Program);
                sectionText.setText(Section);
                dateofbirthText.setText(utils.convertToDateFormat(DateofBirth));
                ageText.setText(Age);
                genderText.setText(Gender);
                candfathernameText.setText(CandFatherName);
                candmothernameText.setText(CandMotherName);
                presentaddress1Text.setText(PresentAddress1);
                presentaddress2Text.setText(PresentAddress2);
                presentareaText.setText(PresentArea);
                presentpincodeText.setText(PresentPinCode);
                presentstateText.setText(PresentState);
                fathersmobilenoText.setText(FathersMobileNo);
                fathersaltmobnoText.setText(FathersAltMobileNo);
                mothersmobilenoText.setText(MothersMobileNo);
                mothersaltmobnoText.setText(MothersAltMobileNo);
                fathersemailText.setText(FathersEmail);
                mothersemailText.setText(MothersEmail);
                referenceText.setText(Reference);
                fathersoccupationText.setText(FathersOccupation);
                fathersofficenameText.setText(FathersOfficeName);
                fathersofficeaddress1Text.setText(FathersOfficeAddress1);
                fathersofficeaddress2Text.setText(FathersOfficeAddress2);
                fathersofficeareaText.setText(FathersOfficeArea);
                fathersofficepincodeText.setText(FathersOfficePinCode);
                fathersofficestateText.setText(FathersOfficeState);
                fathersofficephonenoText.setText(FathersOfficePhoneNo);
                fathersofficealtphonenoText.setText(FathersOfficeAltPhoneNo);
                fathersofficeextensionnoText.setText(FathersOfficeExtensionNo);
                mothersoccupationText.setText(MothersOccupation);
                mothersofficenameText.setText(MothersOfficeName);
                mothersofficeaddress1Text.setText(MothersOfficeAddress1);
                mothersofficeaddress2Text.setText(MothersOfficeAddress2);
                mothersofficeareaText.setText(MothersOfficeArea);
                mothersofficepincodeText.setText(MothersOfficePinCode);
                mothersofficestateText.setText(MothersOfficeState);
                mothersofficephonenoText.setText(MothersOfficePhoneNo);
                mothersofficealtphonenoText.setText(MothersOfficeAltPhoneNo);
                mothersofficeextensionnoText.setText(MothersOfficeExtensionNo);
                fathersdobText.setText(utils.convertToDateFormat(FathersDOB));
                mothersdobText.setText(utils.convertToDateFormat(MothersDOB));
                parentsweddingdateText.setText(utils.convertToDateFormat(ParentsWeddingDate));
                religionText.setText(Religion);
                pickuppersonnameText.setText(PickupPersonName);
                pickuppersoncontactnoText.setText(PickupPersonContactNo);
                pickuppersonaltcontactnoText.setText(PickupPersonAltContactNo);
                pickuppersonrelationshipText.setText(PickupPersonRelationShip);
                transportText.setText(Transport);
                transportstageText.setText(TransportStage);
                academicyearText.setText(AcademicYear);


            }
            catch (Exception e){

            }
        }





        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }

}
