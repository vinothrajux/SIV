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
 * Created by Seetha on 09-Dec-17.
 */

public class StaffBioDataViewPlaySchoolFragment extends Fragment {
    String staffId;
    EditText staffIdText;
    TextView DateofJoinText,StaffNameText,DesignationText,DateofBirthText,AgeText,BloodGroupText,GenderText,MaritalStatusText;
    TextView FathersNameText,AadharNoText,PANNoText,MothersNameText,SpouseNameText,AddressLine1Text,AddressLine2Text,AreaText,PinText;
    TextView MobileNumberText,AlternativeMobileNoText,EmailIdText,QualificationText,CourseText,UniversityText,PercentageText;
    TextView MonthYearofPassText,ExperienceText,NoofYearsText;

    //Button searchBtn;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.staffbiodataviewplayschool, container, false);

        searchView = (SearchView) view.findViewById(R.id.Staffid);

        searchView.setSubmitButtonEnabled(true);
        DateofJoinText=(TextView) view.findViewById(R.id.DateofJoin);
        StaffNameText=(TextView) view.findViewById(R.id.Staffname);
        DesignationText = (TextView) view.findViewById(R.id.Designation);
        DateofBirthText = (TextView) view.findViewById(R.id.DOB);
        AgeText = (TextView) view.findViewById(R.id.Age);
        BloodGroupText=(TextView) view.findViewById(R.id.BloodGroup);
        GenderText = (TextView) view.findViewById(R.id.Gender);
        MaritalStatusText=(TextView) view.findViewById(R.id.MaritalStatus);
        AadharNoText=(TextView) view.findViewById(R.id.AadharNumber);
        PANNoText = (TextView) view.findViewById(R.id.PanNo);
        FathersNameText = (TextView) view.findViewById(R.id.FathersName);
        MothersNameText = (TextView) view.findViewById(R.id.MothersName);
        SpouseNameText=(TextView) view.findViewById(R.id.Spousename);
        AddressLine1Text = (TextView) view.findViewById(R.id.Address1);

        AddressLine2Text=(TextView) view.findViewById(R.id.Address2);
        AreaText=(TextView) view.findViewById(R.id.Area);
        PinText = (TextView) view.findViewById(R.id.Pincode);
        MobileNumberText = (TextView) view.findViewById(R.id.PresentMobileNo);
        AlternativeMobileNoText = (TextView) view.findViewById(R.id.PresentAltMobileNo);
        EmailIdText=(TextView) view.findViewById(R.id.PresentEmailId);
        QualificationText = (TextView) view.findViewById(R.id.Qualification);
        CourseText=(TextView) view.findViewById(R.id.Coursecompleted);
        UniversityText=(TextView) view.findViewById(R.id.UniversityBoard);
        PercentageText = (TextView) view.findViewById(R.id.Percentage);
        MonthYearofPassText = (TextView) view.findViewById(R.id.MonthYearOfPassing);
        ExperienceText = (TextView) view.findViewById(R.id.Experience);
        NoofYearsText=(TextView) view.findViewById(R.id.NoOfYears);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                staffId=query;
                StaffBioDataViewPlaySchoolFragment.GetApplicationDetailTask getStaffPersonalInformationPlaySchoolDetail = new StaffBioDataViewPlaySchoolFragment.GetApplicationDetailTask();
                getStaffPersonalInformationPlaySchoolDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/staffpersonalinformationplayschool/getStaffPersonalInformationPlaySchoolDetail");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("staffid", staffId);
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
                String DateofJoin,StaffName, Designation, DateofBirth, Age,Gender,BloodGroup,MaritalStatus,Aadharnumber,Panno;
                String Fathersname,MothersName, SpouseName, AddressLine1, AddressLine2,Area,Pin,MobileNumber,AlternativeMobileNo,EmailId;
                String Qualification,Course, University, Percentage, MonthYearOfPass,Experience,NoofYears;


                DateofJoin=jsonObj.getString("dateofjoin");
                StaffName=jsonObj.getString("staffname");
                Designation = jsonObj.getString("designation");
                DateofBirth = jsonObj.getString("dateofbirth");
                Age = jsonObj.getString("age");
                Gender=jsonObj.getString("gender");
                BloodGroup = jsonObj.getString("bloodgroup");

                MaritalStatus=jsonObj.getString("maritalstatus");
                Aadharnumber=jsonObj.getString("aadharnumber");
                Panno = jsonObj.getString("panno");
                Fathersname = jsonObj.getString("fathersname");
                MothersName = jsonObj.getString("mothersname");
                SpouseName=jsonObj.getString("spousename");
                AddressLine1 = jsonObj.getString("addressline1");

                AddressLine2=jsonObj.getString("addressline2");
                Area=jsonObj.getString("area");
                Pin = jsonObj.getString("pin");
                MobileNumber = jsonObj.getString("mobilenumber");
                AlternativeMobileNo = jsonObj.getString("alternativemobilenumber");
                EmailId=jsonObj.getString("emailid");
                Qualification = jsonObj.getString("qualification");

                Course=jsonObj.getString("course");
                University=jsonObj.getString("university");
                Percentage = jsonObj.getString("percentage");
                MonthYearOfPass = jsonObj.getString("monthyearofpass");
                Experience = jsonObj.getString("experience");
                NoofYears=jsonObj.getString("noofyears");

                //AppForText.setText(AppFor);
                DateofJoinText.setText(utils.convertToDateFormat(DateofJoin));
                StaffNameText.setText(StaffName);
                DesignationText.setText(Designation);
                DateofBirthText.setText(utils.convertToDateFormat(DateofBirth));
                AgeText.setText(Age);
                BloodGroupText.setText(BloodGroup);
                GenderText.setText(Gender);

                MaritalStatusText.setText(MaritalStatus);
                AadharNoText.setText(Aadharnumber);
                PANNoText.setText(Panno);
                FathersNameText.setText(Fathersname);
                MothersNameText.setText(MothersName);
                SpouseNameText.setText(SpouseName);
                AddressLine1Text.setText(AddressLine1);

                AddressLine2Text.setText(AddressLine2);
                AreaText.setText(Area);
                PinText.setText(Pin);
                MobileNumberText.setText(MobileNumber);
                AlternativeMobileNoText.setText(AlternativeMobileNo);
                EmailIdText.setText(EmailId);
                QualificationText.setText(Qualification);

                CourseText.setText(Course);
                UniversityText.setText(University);
                PercentageText.setText(Percentage);
                MonthYearofPassText.setText(MonthYearOfPass);
                ExperienceText.setText(Experience);
                NoofYearsText.setText(NoofYears);


            }
            catch (Exception e){

            }
        }

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.applicationsale, container, false);
    }


}
