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
 * Created by Seetha on 10-Dec-17.
 */

public class IndividualStudentProgressPlaySchoolViewFragment extends Fragment {
    String remarkId;
    EditText remarkIdText;
    TextView EntryDateText,EntryDayText,MonthText,RegisterNumberText,NameText,ProgramText,SectionText,AcademicYearText;
    TextView Category1RatingText,Category2RatingText,Category3RatingText,Category4RatingText,Category5RatingText,Category6RatingText,Category7RatingText,Category8RatingText,Category9RatingText,Category10RatingText;
    //Button searchBtn;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.individualstudentprogressplayschoolview, container, false);

        searchView = (SearchView) view.findViewById(R.id.RemarkId);

        searchView.setSubmitButtonEnabled(true);
        EntryDateText=(TextView) view.findViewById(R.id.Date);
        EntryDayText = (TextView) view.findViewById(R.id.Day);
        MonthText = (TextView) view.findViewById(R.id.FromMonth);
        RegisterNumberText = (TextView) view.findViewById(R.id.RegNo);
        NameText=(TextView) view.findViewById(R.id.Name);
        ProgramText = (TextView) view.findViewById(R.id.Program);
        SectionText = (TextView) view.findViewById(R.id.Section);
        AcademicYearText = (TextView) view.findViewById(R.id.AcademicYear);
        Category1RatingText = (TextView) view.findViewById(R.id.Category1);
        Category2RatingText=(TextView) view.findViewById(R.id.Category2);
        Category3RatingText = (TextView) view.findViewById(R.id.Category3);
        Category4RatingText = (TextView) view.findViewById(R.id.Category4);
        Category5RatingText = (TextView) view.findViewById(R.id.Category5);
        Category6RatingText = (TextView) view.findViewById(R.id.Category6);
        Category7RatingText = (TextView) view.findViewById(R.id.Category7);
        Category8RatingText = (TextView) view.findViewById(R.id.Category8);
        Category9RatingText = (TextView) view.findViewById(R.id.Category9);
        Category10RatingText = (TextView) view.findViewById(R.id.Category10);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                remarkId=query;
                IndividualStudentProgressPlaySchoolViewFragment.GetApplicationDetailTask getStudentMonthlyProgressPlaySchoolDetail = new IndividualStudentProgressPlaySchoolViewFragment.GetApplicationDetailTask();
                getStudentMonthlyProgressPlaySchoolDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/studentsmonthlyprogressplayschool/getStudentMonthlyProgressPlaySchoolDetail");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("remarkid", remarkId);
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
                String EntryDate,EntryDay,Month,RegisterNumber,Name,Program, Section,AcademicYear;
                String Category1Rating,Category2Rating,Category3Rating,Category4Rating,Category5Rating,Category6Rating, Category7Rating,Category8Rating,Category9Rating,Category10Rating;

                EntryDate=jsonObj.getString("entrydate");
                EntryDay=jsonObj.getString("entryday");
                Month=jsonObj.getString("month");
                RegisterNumber=jsonObj.getString("registernumber");
                Name=jsonObj.getString("name");
                Program = jsonObj.getString("program");
                Section = jsonObj.getString("section");
                AcademicYear=jsonObj.getString("academicyear");
                Category1Rating = jsonObj.getString("category1rating");
                Category2Rating = jsonObj.getString("category2rating");
                Category3Rating = jsonObj.getString("category3rating");
                Category4Rating = jsonObj.getString("category4rating");
                Category5Rating = jsonObj.getString("category5rating");
                Category6Rating = jsonObj.getString("category6rating");
                Category7Rating = jsonObj.getString("category7rating");
                Category8Rating = jsonObj.getString("category8rating");
                Category9Rating = jsonObj.getString("category9rating");
                Category10Rating = jsonObj.getString("category10rating");
                //AppForText.setText(AppFor);
                EntryDateText.setText(utils.convertToDateFormat(EntryDate));
                EntryDayText.setText(EntryDay);
                MonthText.setText(Month);
                RegisterNumberText.setText(RegisterNumber);
                NameText.setText(Name);
                ProgramText.setText(Program);
                SectionText.setText(Section);
                AcademicYearText.setText(AcademicYear);
                Category1RatingText.setText(Category1Rating);
                Category2RatingText.setText(Category2Rating);
                Category3RatingText.setText(Category3Rating);
                Category4RatingText.setText(Category4Rating);
                Category5RatingText.setText(Category5Rating);
                Category6RatingText.setText(Category6Rating);
                Category7RatingText.setText(Category7Rating);
                Category8RatingText.setText(Category8Rating);
                Category9RatingText.setText(Category9Rating);
                Category10RatingText.setText(Category10Rating);

            }
            catch (Exception e){

            }
        }

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.applicationsale, container, false);
    }

}
