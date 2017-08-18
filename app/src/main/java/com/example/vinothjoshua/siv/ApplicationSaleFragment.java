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
import android.widget.Spinner;
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
 * Created by Seetha on 27-May-17.
 */

public class ApplicationSaleFragment extends Fragment {

    String applicationNumber;
    EditText appNoText;
    TextView AppForText, CandidateFirstNameText,CandidateMiddleNameText,CandidateLastNameText,GenderText,FatherNameText;
    TextView MotherNameText,PresentAddress1Text,PresentAddress2Text,PresentAreaText;
    TextView PresentPinCodeText,PresentMobileNoText,PresentAltMobileNoText;
    TextView PresentEmailText,PresentAltEmailText,PermanentAddress1Text,PermanentAddress2Text;
    TextView PermanentAreaText,PermanentPinCodeText,PermanentMobileNoText;
    TextView PermanentAltMobNoText,PermanentEmailText,PermanentAltEmailText,QualifiedText;
    TextView ReferenceText;
    TextView FollowupDateText,ApplicationPriceText,RemarksText,PresentAddLine1,PresentAddLine2;
    Spinner PresentStateText,PermanentStateText,PrefferedCour1Text,PrefferedCour2Text,PrefferedCour3Text,ApplicationPaidModeText,WillingtojoinText;
    //Button searchBtn;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.applicationsale, container, false);

        searchView = (SearchView) view.findViewById(R.id.applicationno);

        searchView.setSubmitButtonEnabled(true);
        AppForText = (TextView) view.findViewById(R.id.AppFor);
        CandidateFirstNameText = (TextView) view.findViewById(R.id.FirstName);
        CandidateMiddleNameText = (TextView) view.findViewById(R.id.MiddleName);
        CandidateLastNameText = (TextView) view.findViewById(R.id.LastName);
        GenderText =(TextView) view.findViewById(R.id.Gender);
        FatherNameText=(TextView) view.findViewById(R.id.FatherName);
        MotherNameText=(TextView) view.findViewById(R.id.MotherName);
        PresentAddress1Text=(TextView) view.findViewById(R.id.PresentAddress1);
        PresentAddress2Text=(TextView) view.findViewById(R.id.PresentAddress2);
        PresentAreaText=(TextView) view.findViewById(R.id.PresentArea);
        PresentPinCodeText=(TextView) view.findViewById(R.id.PresentPin);
        PresentStateText=(Spinner) view.findViewById(R.id.PresentState);
        PresentMobileNoText=(TextView) view.findViewById(R.id.PresentMobNo);
        PresentAltMobileNoText=(TextView) view.findViewById(R.id.PresentAltMobNo);
        PresentEmailText=(TextView) view.findViewById(R.id.PresentEmailId);
        PresentAltEmailText=(TextView) view.findViewById(R.id.PresentAltEmailId);

        PermanentAddress1Text=(TextView) view.findViewById(R.id.PermanentAddress1);
        PermanentAddress2Text=(TextView) view.findViewById(R.id.PermanentAddress2);
        PermanentAreaText=(TextView) view.findViewById(R.id.PermanentArea);
        PermanentPinCodeText=(TextView) view.findViewById(R.id.PermanentPin);
        PermanentStateText=(Spinner) view.findViewById(R.id.PermanentState);
        PermanentMobileNoText=(TextView) view.findViewById(R.id.PermanentMobNo);
        PermanentAltMobNoText=(TextView) view.findViewById(R.id.PermanentAltMobNo);
        PermanentEmailText=(TextView) view.findViewById(R.id.PermanentEmailId);
        PermanentAltEmailText=(TextView) view.findViewById(R.id.PermanentAltEmailId);

        QualifiedText=(TextView) view.findViewById(R.id.Qualified);
        PrefferedCour1Text=(Spinner) view.findViewById(R.id.PrefferedCourse1);
        PrefferedCour2Text=(Spinner) view.findViewById(R.id.PrefferedCourse2);
        PrefferedCour3Text=(Spinner) view.findViewById(R.id.PrefferedCourse3);
        ReferenceText=(TextView) view.findViewById(R.id.Reference);
        WillingtojoinText=(Spinner) view.findViewById(R.id.Willing);
        FollowupDateText=(TextView) view.findViewById(R.id.Followupdate);
        ApplicationPriceText=(TextView) view.findViewById(R.id.ApplicationPrice);
        ApplicationPaidModeText=(Spinner) view.findViewById(R.id.ApplicationPaidMode);
        RemarksText=(TextView) view.findViewById(R.id.Remarks);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                applicationNumber=query;
                ApplicationSaleFragment.GetApplicationDetailTask getApplicationDetail = new ApplicationSaleFragment.GetApplicationDetailTask();
                getApplicationDetail.execute();
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
                String AddressL1,AddressL2,PreA1,PreA2,PreArea,PrePin,PreMob,PreAltMob,PreState,PreEmail,PreAltEmail,Qualified,PrefferedCourse1,PrefferedCourse2,PrefferedCourse3,Reference;
                String PerA1,PerA2,PerArea,PerPin,PerMob,PerAltMob,PerState,PerEmail,PerAltEmail;
                String Willingtojoin;
                String FollowupDate,ApplicationPaidMode,Remarks;
                String ApplicationPrice;

                AppFor = jsonObj.getString("appfor");
                Log.d("appfor:",AppFor);
                CandidateFirstName = jsonObj.getString("candfirstname");
                CandidateMiddleName = jsonObj.getString("candmiddlename");
                CandidateLastName = jsonObj.getString("candlastname");
                CandidateFatherName = jsonObj.getString("candfathername");
                CandidateMotherName = jsonObj.getString("candmothername");
                Gender = jsonObj.getString("gender");
                PreA1=jsonObj.getString("presentaddress1");
                PreA2=jsonObj.getString("presentaddress2");
                PreArea=jsonObj.getString("presentarea");
                PrePin=jsonObj.getString("presentpincode");
                PreState=jsonObj.getString("presentstate");
                PreMob=jsonObj.getString("presentmobileno");
                PreAltMob=jsonObj.getString("presentaltmobno");
                PreEmail=jsonObj.getString("presentemail");
                PreAltEmail=jsonObj.getString("presentaltemail");

                PerA1=jsonObj.getString("permanentaddress1");
                PerA2=jsonObj.getString("permanentaddress2");
                PerArea=jsonObj.getString("permanentarea");
                PerPin=jsonObj.getString("permanentpincode");
                PerState=jsonObj.getString("permanentstate");
                PerMob=jsonObj.getString("permanentmobileno");
                PerAltMob=jsonObj.getString("permanentaltmobno");
                PerEmail=jsonObj.getString("permanentemail");
                PerAltEmail=jsonObj.getString("permanentaltemail");

                Qualified=jsonObj.getString("qualified");
                PrefferedCourse1=jsonObj.getString("prefferedcour1");
                PrefferedCourse2=jsonObj.getString("prefferedcour2");
                PrefferedCourse3=jsonObj.getString("prefferedcour3");
                Reference=jsonObj.getString("reference");
                Willingtojoin=jsonObj.getString("willingtojoin");
                FollowupDate=jsonObj.getString("followupdate");
                ApplicationPrice=jsonObj.getString("applicationprice");
                ApplicationPaidMode=jsonObj.getString("applicationpaidmode");
                Remarks=jsonObj.getString("remarks");


                CandidateName = CandidateFirstName + ' ' + CandidateMiddleName + ' ' + CandidateLastName;
                AddressL1= PreA1 + ',' + PreA2 + ',' + PreArea + '-' + PrePin + '.'+ PreState;
                AddressL2 = "Mob:" + PreMob + ' ' + PreEmail;


                AppForText.setText(AppFor);
                CandidateFirstNameText.setText(CandidateFirstName);
                CandidateMiddleNameText.setText(CandidateMiddleName);
                CandidateLastNameText.setText(CandidateLastName);
                FatherNameText.setText(CandidateFatherName);
                MotherNameText.setText(CandidateMotherName);
                GenderText.setText(Gender);
                PresentAddress1Text.setText(PreA1);
                PresentAddress2Text.setText(PreA2);
                PresentAreaText.setText(PreArea);
                PresentPinCodeText.setText(PrePin);
                //PresentStateText.setText(PreState);
                PresentMobileNoText.setText(PreMob);
                PresentAltMobileNoText.setText(PreAltMob);
                PresentEmailText.setText(PreEmail);
                PresentAltEmailText.setText(PreAltEmail);
                PermanentAddress1Text.setText(PerA1);
                PermanentAddress2Text.setText(PerA2);
                PermanentAreaText.setText(PerArea);
                PermanentPinCodeText.setText(PerPin);
                //PermanentStateText.setText(PerState);
                PermanentMobileNoText.setText(PerMob);
                PermanentAltMobNoText.setText(PerAltMob);
                PermanentEmailText.setText(PerEmail);
                PermanentAltEmailText.setText(PerAltEmail);
                QualifiedText.setText(Qualified);
              //  PrefferedCour1Text(PrefferedCourse1);
              //  PrefferedCour2Text.setText(PrefferedCourse2);
              //  PrefferedCour3Text.setText(PrefferedCourse3);
                ReferenceText.setText(Reference);
                //WillingtojoinText.setText(Willingtojoin);
                FollowupDateText.setText(FollowupDate);
                ApplicationPriceText.setText(ApplicationPrice);
                //ApplicationPaidModeText.setText(ApplicationPaidMode);
                RemarksText.setText(Remarks);





            }
            catch (Exception e){

            }
        }

    // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.applicationsale, container, false);
    }

}

