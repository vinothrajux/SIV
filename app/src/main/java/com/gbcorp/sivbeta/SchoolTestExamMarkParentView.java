package com.gbcorp.sivbeta;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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
 * Created by Seetha on 02-Jun-18.
 */

public class SchoolTestExamMarkParentView extends Fragment{
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();
    ListView studentmarkdetaillist;
    Context context;
    ImageView testimage;
    String registerNumber;
    int instituteId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schooltestexammarkparentview, container, false);

        SchoolTestExamMarkParentView.GetMarksDetailTask getMarksDetailTask = new SchoolTestExamMarkParentView.GetMarksDetailTask();
        getMarksDetailTask.execute();

        return view;
    }

    //API starts copy from here
    private class GetMarksDetailTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/schooltestexammarkentry/getStudentMarks");


                JSONObject postDataParams = new JSONObject();
                registerNumber = utils.getUserId();
                instituteId = utils.getInstituteId();
                postDataParams.put("registernumber", registerNumber);
                postDataParams.put("instituteid", instituteId);
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
                JSONArray jsonArr = new JSONArray(result);


                studentmarkdetaillist=(ListView) getActivity().findViewById(R.id.studentMarksDetailList);
                studentmarkdetaillist.setAdapter(new StudentMarkDetailCustomAdaptor(getActivity(),jsonArr));

//                for(int i=0;i<jsonArr.length();i++){
//                    JSONObject obj = jsonArr.getJSONObject(i);
//                    String testtype = obj.getString("testtype");
////                    obj.getString("maxmarks");
////                    obj.getString("minmarks");
////                    obj.getString("studentmarks");
//                }
//                JSONObject jsonObj = new JSONObject(result);
//                String EntryDate,EntryDay, Program, Section,AcademicYear,SubjectCategory,HomeWorkContent;
//
//                EntryDate=jsonObj.getString("entrydate");
//                EntryDay=jsonObj.getString("entryday");
//                Program = jsonObj.getString("program");
//                Section = jsonObj.getString("section");
//                AcademicYear=jsonObj.getString("academicyear");
//                SubjectCategory = jsonObj.getString("subjectcategory");
//                HomeWorkContent = jsonObj.getString("homeworkcontent");
//                //AppForText.setText(AppFor);
//                EntryDateText.setText(utils.convertToDateFormat(EntryDate));
//                EntryDayText.setText(EntryDay);
//                ProgramText.setText(Program);
//                SectionText.setText(Section);
//                AcademicYearText.setText(AcademicYear);
//                SubjectCategoryText.setText(SubjectCategory);
//                HomeWorkContentText.setText(HomeWorkContent);

            }
            catch (Exception e){

            }
        }

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.applicationsale, container, false);
    }
}
