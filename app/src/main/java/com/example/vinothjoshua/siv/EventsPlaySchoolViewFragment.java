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

public class EventsPlaySchoolViewFragment extends Fragment {
    String eventId;
    EditText eventIdText;
    TextView EventIdText,EntryDateText,EventDateText,EventDayText,ProgramText,SectionText,AcademicYearText,MessageText;
    //Button searchBtn;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.eventsplayschoolview, container, false);

        searchView = (SearchView) view.findViewById(R.id.EventId);

        searchView.setSubmitButtonEnabled(true);
        EntryDateText=(TextView) view.findViewById(R.id.Date);
        EventDateText=(TextView) view.findViewById(R.id.EventDate);
        EventDayText = (TextView) view.findViewById(R.id.EventDay);
        ProgramText = (TextView) view.findViewById(R.id.Program);
        SectionText = (TextView) view.findViewById(R.id.Section);
        AcademicYearText=(TextView) view.findViewById(R.id.AcademicYear);
        MessageText = (TextView) view.findViewById(R.id.Message);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                eventId=query;
                EventsPlaySchoolViewFragment.GetApplicationDetailTask getPlaySchoolEventsCircularsDetail = new EventsPlaySchoolViewFragment.GetApplicationDetailTask();
                getPlaySchoolEventsCircularsDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/eventscircularsplayschool/getEventsCircularsPlaySchoolDetail");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("eventid", eventId);
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
                String EntryDate,EventDate, EventDay, Program, Section,AcademicYear,Message;


                EntryDate=jsonObj.getString("entrydate");
                EventDate=jsonObj.getString("eventdate");
                EventDay = jsonObj.getString("eventday");
                Program = jsonObj.getString("program");
                Section = jsonObj.getString("section");
                AcademicYear=jsonObj.getString("academicyear");
                Message = jsonObj.getString("message");
                //AppForText.setText(AppFor);
                EntryDateText.setText(EntryDate);
                EventDateText.setText(EventDate);
                EventDayText.setText(EventDay);
                ProgramText.setText(Program);
                SectionText.setText(Section);
                AcademicYearText.setText(AcademicYear);
                MessageText.setText(Message);

            }
            catch (Exception e){

            }
        }

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.applicationsale, container, false);
    }


}
