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

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Seetha on 09-Dec-17.
 */

public class TransportChangePlaySchoolViewFragment extends Fragment {
    String transportChangeId;
    EditText transportChangeIdText;
    TextView TransportChangeIdText,EntryDateText,RegisterNumberText,NameText,ProgramText,SectionText,TransportinitfeesText,TransportTerm1FeesText,TransportTerm2FeesText;
    Spinner TransportRequiredSpinner, StageSpinner;
    TextView TransportTotalFeesText,AcademicYearText,RemarksText;
    //Button searchBtn;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transportchangeplayschoolview, container, false);

        searchView = (SearchView) view.findViewById(R.id.TransportChangeId);

        searchView.setSubmitButtonEnabled(true);
        TransportChangeIdText=(TextView) view.findViewById(R.id.TransportId);
        EntryDateText=(TextView) view.findViewById(R.id.Date);
        RegisterNumberText = (TextView) view.findViewById(R.id.RegNo);
        NameText = (TextView) view.findViewById(R.id.Name);
        ProgramText = (TextView) view.findViewById(R.id.Program);
        SectionText = (TextView) view.findViewById(R.id.Section);
        //TransportRequiredText = (TextView) view.findViewById(R.id.Transport);
        TransportRequiredSpinner = (Spinner) view.findViewById(R.id.Transport);
        //StageText = (TextView) view.findViewById(R.id.Stage);
        StageSpinner = (Spinner) view.findViewById(R.id.Stage);
        TransportinitfeesText =(TextView) view.findViewById(R.id.TransportInitFees);
        TransportTerm1FeesText=(TextView) view.findViewById(R.id.TranspoortTermIFees);
        TransportTerm2FeesText=(TextView) view.findViewById(R.id.TransportTermIIFees);

        TransportTotalFeesText=(TextView) view.findViewById(R.id.TranspoortTotalFees);
        AcademicYearText=(TextView) view.findViewById(R.id.AcademicYear);
        RemarksText=(TextView) view.findViewById(R.id.Remarks);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                transportChangeId=query;
                TransportChangePlaySchoolViewFragment.GetApplicationDetailTask getPlaySchoolTransportChangeDetail = new TransportChangePlaySchoolViewFragment.GetApplicationDetailTask();
                getPlaySchoolTransportChangeDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/transportchangeplayschool/getPlaySchoolTransportChangeDetail");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("transportchangeid", transportChangeId);
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
                String TransportChangeId,EntryDate,RegisterNumber, Name, Program, Section,TransportRequired,Stage,TransportInitFees, TransportTerm1Fees, TransportTerm2Fees, TransportTotalFees;
                String Remarks,AcademicYear;


                TransportChangeId=jsonObj.getString("transportchangeid");
                EntryDate=jsonObj.getString("entrydate");
                RegisterNumber = jsonObj.getString("registernumber");

                Name = jsonObj.getString("name");
                Program = jsonObj.getString("program");
                Section = jsonObj.getString("section");
                TransportRequired = jsonObj.getString("transportrequired");
                Stage = jsonObj.getString("stage");
                TransportInitFees = jsonObj.getString("transportinitfees");
                TransportTerm1Fees = jsonObj.getString("transportterm1fees");
                TransportTerm2Fees = jsonObj.getString("transportterm2fees");

                TransportTotalFees=jsonObj.getString("transporttotalfees");
                AcademicYear=jsonObj.getString("academicyear");
                Remarks=jsonObj.getString("remarks");
                //AppForText.setText(AppFor);
                TransportChangeIdText.setText(TransportChangeId);
                EntryDateText.setText(utils.convertToDateFormat(EntryDate));
                RegisterNumberText.setText(RegisterNumber);

                NameText.setText(Name);
//                if(CandidateMiddleName == '0'){
//                    CandidateMiddleNameText.setText(" ");
//                }else if(CandidateMiddleName == '1'){
//                    CandidateMiddleNameText.setText(" ");
//                }else{
//
//                }
                ProgramText.setText(Program);
                SectionText.setText(Section);

//                TransportRequiredText.setText(TransportRequired);
//                StageText.setText(Stage);
                TransportinitfeesText.setText(TransportInitFees);
                TransportTerm1FeesText.setText(TransportTerm1Fees);
                TransportTerm2FeesText.setText(TransportTerm2Fees);

                TransportTotalFeesText.setText(TransportTotalFees);
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
