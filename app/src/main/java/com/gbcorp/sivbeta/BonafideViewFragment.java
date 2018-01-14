package com.gbcorp.sivbeta;

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
 * Created by Seetha on 03-Sep-17.
 */

public class BonafideViewFragment extends Fragment {


    String registerNumber;
    EditText regNumberText;
    TextView regnoText,admissionnoText,nameText,academicyearText,branchnameText,branchcodeText,fathernameText;
    TextView issueddateText,certificateforText,noofyearsText,bonafideidText,semesterText;

    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.studentpersonalinformation, container, false);

        searchView = (SearchView) view.findViewById(R.id.registerno);

        searchView.setSubmitButtonEnabled(true);

        regnoText=(TextView)view.findViewById(R.id.RegisterNo);
        admissionnoText=(TextView)view.findViewById(R.id.AdmissionNo);
        nameText =(TextView) view.findViewById(R.id.Name);
        academicyearText=(TextView)view.findViewById(R.id.AcademicYear);
        branchnameText=(TextView) view.findViewById(R.id.branch);
        branchcodeText=(TextView) view.findViewById(R.id.BranchCode);
        fathernameText=(TextView) view.findViewById(R.id.FatherName);
        issueddateText=(TextView) view.findViewById(R.id.MotherName);
        certificateforText=(TextView) view.findViewById(R.id.CertficateFor);
        noofyearsText=(TextView) view.findViewById(R.id.NoOfYears);
        bonafideidText=(TextView) view.findViewById(R.id.BonafideId);
        semesterText=(TextView) view.findViewById(R.id.Semester);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                registerNumber=query;
                BonafideViewFragment.GetApplicationDetailTask getBonafideDetail = new BonafideViewFragment.GetApplicationDetailTask();
                getBonafideDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/bonafide/getBonafideDetail");


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
                String Regno,Admissionno,name,AcademicYear,Semester, Branchname,Branchcode;
                String Fathername,Issueddate,Certificatefor,Noofyears,Bonafideid;
                Regno=jsonObj.getString("regno");
                Admissionno=jsonObj.getString("admissionno");
                name = jsonObj.getString("name");
                //Log.d("appfor:",AppFor);
                AcademicYear=jsonObj.getString("academicyear");
                Semester = jsonObj.getString("semester");
                Branchname = jsonObj.getString("branchname");
                Branchcode = jsonObj.getString("branchcode");
                Fathername = jsonObj.getString("fathername");

                Issueddate=jsonObj.getString("issueddate");
                Certificatefor=jsonObj.getString("certificatefor");
                Noofyears=jsonObj.getString("noofyears");
                Bonafideid=jsonObj.getString("bonafideid");

                regnoText.setText(Regno);
                admissionnoText.setText(Admissionno);
                nameText.setText(name);
                academicyearText.setText(AcademicYear);
                semesterText.setText(Semester);
                branchnameText.setText(Branchname);
                branchcodeText.setText(Branchcode);
                issueddateText.setText(Issueddate);

                certificateforText.setText(Certificatefor);
                noofyearsText.setText(Noofyears);
                bonafideidText.setText(Bonafideid);


            }
            catch (Exception e){

            }
        }





        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }

}


