package com.example.vinothjoshua.siv;

import android.app.Fragment;
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
 * Created by GBCorp on 31/08/2017.
 */

public class StudentAcademicInformationFragment extends Fragment {

    String registerNumber;
    EditText regNumberText;
    TextView regNoText,admissionNoText,studentNameText,semesterText,branchText,branchCodeText,batchText,schemeText,academicYearText,studentTypeText,potoText;
    TextView communityText,religionText,casteText,nationalText,bloodGroupText,durationFromText,durationToText,admittedDateText;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.studentacademicinformation, container, false);

        searchView = (SearchView) view.findViewById(R.id.regno);

        searchView.setSubmitButtonEnabled(true);

        regNoText=(TextView)view.findViewById(R.id.registerNumber);
        admissionNoText=(TextView)view.findViewById(R.id.admissionNumber);
        studentNameText =(TextView) view.findViewById(R.id.name);
        semesterText =(TextView) view.findViewById(R.id.semester);
        branchText=(TextView) view.findViewById(R.id.branch);
        branchCodeText=(TextView) view.findViewById(R.id.branchCode);
        batchText=(TextView) view.findViewById(R.id.batch);
        schemeText=(TextView) view.findViewById(R.id.scheme);
        academicYearText=(TextView)view.findViewById(R.id.academicYear);
        studentTypeText=(TextView) view.findViewById(R.id.studentType);
        potoText=(TextView)view.findViewById(R.id.photo);
        communityText=(TextView) view.findViewById(R.id.community);
        casteText=(TextView) view.findViewById(R.id.caste);
        religionText=(TextView) view.findViewById(R.id.religion);
        nationalText=(TextView) view.findViewById(R.id.nationality);
        bloodGroupText=(TextView)view.findViewById(R.id.bloodGroup);
        durationFromText=(TextView) view.findViewById(R.id.durationFrom);
        durationToText=(TextView) view.findViewById(R.id.durationTo);
        admittedDateText=(TextView) view.findViewById(R.id.admittedDate);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                registerNumber=query;
                StudentAcademicInformationFragment.GetApplicationDetailTask getStudentAcademicInformationDetail = new StudentAcademicInformationFragment.GetApplicationDetailTask();
                getStudentAcademicInformationDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/studentacademicinfo/getStudentAcademicInformationDetail");


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
                String Regno,Admissionno,name,Semester,Branch,BranchCode,Batch,Scheme,AcademicYear,StudentType,Photo;
                String Community,Caste,Religion,Nationality,BloodGroup,DurationFrom,DurationTo,AdmittedDate;
                Regno=jsonObj.getString("regno");
                Admissionno=jsonObj.getString("admissionno");
                name = jsonObj.getString("studentname");
                //Log.d("appfor:",AppFor);
                Semester = jsonObj.getString("semester");
                Branch = jsonObj.getString("branch");
                BranchCode = jsonObj.getString("branchcode");
                Batch = jsonObj.getString("batch");
                Scheme = jsonObj.getString("scheme");
                AcademicYear=jsonObj.getString("academicyear");
                StudentType = jsonObj.getString("studenttype");
                Photo = jsonObj.getString("poto");
                Community=jsonObj.getString("community");
                Caste=jsonObj.getString("caste");
                Religion=jsonObj.getString("religion");
                Nationality=jsonObj.getString("national");
                BloodGroup=jsonObj.getString("bloodgroup");
                DurationFrom=jsonObj.getString("durationfrom");
                DurationTo=jsonObj.getString("durationto");
                AdmittedDate=jsonObj.getString("admitteddate");



                regNoText.setText(Regno);
                admissionNoText.setText(Admissionno);
                studentNameText.setText(name);
                semesterText.setText(Semester);
                branchText.setText(Branch);
                branchCodeText.setText(BranchCode);
                batchText.setText(Batch);
                schemeText.setText(Scheme);

                academicYearText.setText(AcademicYear);
                studentTypeText.setText(StudentType);
                potoText.setText(Photo);
                communityText.setText(Community);
                casteText.setText(Caste);
                religionText.setText(Religion);
                nationalText.setText(Nationality);
                bloodGroupText.setText(BloodGroup);
                durationFromText.setText(DurationFrom);
                durationToText.setText(DurationTo);
                admittedDateText.setText(AdmittedDate);

            }
            catch (Exception e){

            }
        }







        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.teachstaffstudattend, container, false);
    }

}
