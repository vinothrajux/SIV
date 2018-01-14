package com.gbcorp.sivbeta;

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
 * Created by Seetha on 09-Dec-17.
 */

public class ApplicationSaleViewPlaySchoolFragment extends Fragment {
    String applicationNumber;
    EditText appNoText;
    TextView AppSaleDateText,CategoryText,AppForText,ApplicationNoText,CandidateFirstNameText,CandidateMiddleNameText,CandidateLastNameText,DateofBirthText,AgeText,GenderText,FatherNameText;
    TextView MotherNameText,PresentAddress1Text,PresentAddress2Text,PresentAreaText;
    TextView PresentPinCodeText,PresentStateText,FathersMobileNoText,FathersAltMobileNoText;
    TextView MothersMobileNoText,MothersAltMobileNoText,FathersEmailText,MothersEmailText;
    TextView ReferenceText,WillingtojoinText,FollowupDateText;
    TextView ApplicationPriceText,ApplicationPaidModeText,RemarksText,AcademicYearText;
    //Button searchBtn;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.applicationsaleviewplayschool, container, false);

        searchView = (SearchView) view.findViewById(R.id.applicationno);

        searchView.setSubmitButtonEnabled(true);
        AppSaleDateText=(TextView) view.findViewById(R.id.SaleDate);
        CategoryText=(TextView) view.findViewById(R.id.Category);
        ApplicationNoText =(TextView) view.findViewById(R.id.EnquiryNo);
        AppForText = (TextView) view.findViewById(R.id.AppFor);
        CandidateFirstNameText = (TextView) view.findViewById(R.id.FirstName);
        CandidateMiddleNameText = (TextView) view.findViewById(R.id.MiddleName);
        CandidateLastNameText = (TextView) view.findViewById(R.id.LastName);
        DateofBirthText = (TextView) view.findViewById(R.id.DateofBirth);
        AgeText = (TextView) view.findViewById(R.id.Age);
        GenderText =(TextView) view.findViewById(R.id.Gender);
        FatherNameText=(TextView) view.findViewById(R.id.FatherName);
        MotherNameText=(TextView) view.findViewById(R.id.MotherName);

        PresentAddress1Text=(TextView) view.findViewById(R.id.PresentAddress1);
        PresentAddress2Text=(TextView) view.findViewById(R.id.PresentAddress2);
        PresentAreaText=(TextView) view.findViewById(R.id.PresentArea);
        PresentPinCodeText=(TextView) view.findViewById(R.id.PresentPin);
        PresentStateText=(TextView) view.findViewById(R.id.PresentState);
        FathersMobileNoText=(TextView) view.findViewById(R.id.FatherMobNo);
        FathersAltMobileNoText=(TextView) view.findViewById(R.id.FatherAltMobNo);
        MothersMobileNoText=(TextView) view.findViewById(R.id.MotherMobNo);
        MothersAltMobileNoText=(TextView) view.findViewById(R.id.MotherAltMobNo);

        FathersEmailText=(TextView) view.findViewById(R.id.FathersEmailId);
        MothersEmailText=(TextView) view.findViewById(R.id.MothersEmailId);
        ReferenceText=(TextView) view.findViewById(R.id.Reference);
        WillingtojoinText=(TextView) view.findViewById(R.id.WillingtoJoin);
        FollowupDateText=(TextView) view.findViewById(R.id.Followupdate);
        ApplicationPriceText=(TextView) view.findViewById(R.id.ApplicationPrice);
        ApplicationPaidModeText=(TextView) view.findViewById(R.id.ApplicationPaidMode);
        RemarksText=(TextView) view.findViewById(R.id.Remarks);

        AcademicYearText=(TextView) view.findViewById(R.id.AcademicYear);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                applicationNumber=query;
                ApplicationSaleViewPlaySchoolFragment.GetApplicationDetailTask getPlaySchoolApplcationDetail = new ApplicationSaleViewPlaySchoolFragment.GetApplicationDetailTask();
                getPlaySchoolApplcationDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/playschoolapplicationsale/getPlaySchoolApplcationDetail");


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

            //Toast.makeText(getActivity().getApplicationContext(), "From Server: " + result, Toast.LENGTH_SHORT).show();
            try {
                //JSONArray jsonArr = new JSONArray(result);

                JSONObject jsonObj = new JSONObject(result);
                String AppSaleDate,Category,AppFor,ApplicationNo, CandidateFirstName, CandidateMiddleName, CandidateLastName,DateofBirth,Age,Gender, CandidateFatherName, CandidateMotherName, CandidateName;
                String AddressL1,AddressL2,PreA1,PreA2,PreArea,PrePin,PreState,FathersMobileNo,FathersAltMobNo,MothersMobNo,MothersAltMobNo,FathersEmail,MothersEmail,Reference;
                String Willingtojoin;
                String FollowupDate,ApplicationPrice,ApplicationPaidMode,Remarks,AcademicYear;


                AppSaleDate=jsonObj.getString("saledate");
                Category=jsonObj.getString("category");
                AppFor = jsonObj.getString("appfor");
                ApplicationNo = jsonObj.getString("applno");
                Log.d("appfor:",AppFor);

                CandidateFirstName = jsonObj.getString("candfirstname");
                CandidateMiddleName = jsonObj.getString("candmiddlename");
                CandidateLastName = jsonObj.getString("candlastname");
                DateofBirth = jsonObj.getString("dateofbirth");
                Age = jsonObj.getString("age");
                Gender = jsonObj.getString("gender");
                CandidateFatherName = jsonObj.getString("candfathername");
                CandidateMotherName = jsonObj.getString("candmothername");

                PreA1=jsonObj.getString("presentaddress1");
                PreA2=jsonObj.getString("presentaddress2");
                PreArea=jsonObj.getString("presentarea");
                PrePin=jsonObj.getString("presentpincode");
                PreState=jsonObj.getString("presentstate");
                FathersMobileNo=jsonObj.getString("fathersmobileno");
                FathersAltMobNo=jsonObj.getString("fathersaltmobno");
                MothersMobNo=jsonObj.getString("mothersmobileno");
                MothersAltMobNo=jsonObj.getString("mothersaltmobno");

                FathersEmail=jsonObj.getString("fathersemail");
                MothersEmail=jsonObj.getString("mothersemail");
                Reference=jsonObj.getString("reference");
                Willingtojoin=jsonObj.getString("willingtojoin");
                FollowupDate=jsonObj.getString("followupdate");
                ApplicationPrice=jsonObj.getString("applicationprice");
                ApplicationPaidMode=jsonObj.getString("applicationpaidmode");
                Remarks=jsonObj.getString("remarks");
                AcademicYear=jsonObj.getString("academicyear");


                CandidateName = CandidateFirstName + ' ' + CandidateMiddleName + ' ' + CandidateLastName;
                AddressL1= PreA1 + ',' + PreA2 + ',' + PreArea + '-' + PrePin + '.'+ PreState;
                AddressL2 = "Mob:" + FathersMobileNo + ' ' + MothersMobNo;

                Log.e("applicationsale:",CandidateFirstName);
                //AppForText.setText(AppFor);
                AppSaleDateText.setText(utils.convertToDateFormat(AppSaleDate));
                CategoryText.setText(Category);
                AppForText.setText(AppFor);
                ApplicationNoText.setText(ApplicationNo);

                CandidateFirstNameText.setText(CandidateFirstName);
//                if(CandidateMiddleName == '0'){
//                    CandidateMiddleNameText.setText(" ");
//                }else if(CandidateMiddleName == '1'){
//                    CandidateMiddleNameText.setText(" ");
//                }else{
//
//                }
                CandidateMiddleNameText.setText(CandidateMiddleName);
                CandidateLastNameText.setText(CandidateLastName);

                DateofBirthText.setText(utils.convertToDateFormat(DateofBirth));
                AgeText.setText(Age);
                GenderText.setText(Gender);
                FatherNameText.setText(CandidateFatherName);
                MotherNameText.setText(CandidateMotherName);

                PresentAddress1Text.setText(PreA1);
                PresentAddress2Text.setText(PreA2);
                PresentAreaText.setText(PreArea);
                PresentPinCodeText.setText(PrePin);
                PresentStateText.setText(PreState);
                FathersMobileNoText.setText(FathersMobileNo);
                FathersAltMobileNoText.setText(FathersAltMobNo);
                MothersMobileNoText.setText(MothersMobNo);
                MothersAltMobileNoText.setText(MothersAltMobNo);
                FathersEmailText.setText(FathersEmail);
                MothersEmailText.setText(MothersEmail);
                ReferenceText.setText(Reference);
                WillingtojoinText.setText(Willingtojoin);
                FollowupDateText.setText(utils.convertToDateFormat(FollowupDate));
                ApplicationPriceText.setText(ApplicationPrice);
                ApplicationPaidModeText.setText(ApplicationPaidMode);
                RemarksText.setText(Remarks);
                AcademicYearText.setText(AcademicYear);





            }
            catch (Exception e){

            }
        }

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.applicationsale, container, false);
    }


}
