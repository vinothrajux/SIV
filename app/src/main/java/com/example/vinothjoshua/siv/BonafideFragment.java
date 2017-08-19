package com.example.vinothjoshua.siv;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static com.example.vinothjoshua.siv.R.id.Gender;

/**
 * Created by Seetha on 29-May-17.
 */

public class BonafideFragment extends Fragment {
    String bonaId;
    TextView BonafideIdText,RegnoText,AdmissionnoText,nameText,semesterText,branchNameText,branchCodeText,academicYearText;
    TextView fatherNameText,issuedDateText,certificateForText,noOfYearsText;

    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.bonafide, container, false);
    searchView = (SearchView) view.findViewById(R.id.bonafideIdSearch );

    searchView.setSubmitButtonEnabled(true);

    BonafideIdText = (TextView) view.findViewById(R.id.BonafideId);
    RegnoText = (TextView) view.findViewById(R.id.Regno);
    AdmissionnoText =(TextView) view.findViewById(R.id.AdmissionNo);
    nameText=(TextView) view.findViewById(R.id.Name);
    semesterText=(TextView) view.findViewById(R.id.Semester);
    branchNameText=(TextView) view.findViewById(R.id.branch);
    branchCodeText=(TextView) view.findViewById(R.id.BranchCode);
    academicYearText=(TextView) view.findViewById(R.id.AcademicYear);
    fatherNameText=(TextView) view.findViewById(R.id.FatherName);
    issuedDateText=(TextView) view.findViewById(R.id.Date);
    certificateForText=(TextView) view.findViewById(R.id.CertficateFor);
    noOfYearsText=(TextView) view.findViewById(R.id.NoOfYears);
   // @Override
   // public View onCreateView(LayoutInflater inflater, ViewGroup container,
     //                        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.bonafide, container, false);
   // }

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            //callSearch(query);
            bonaId=query;
            BonafideFragment.GetBonafideDetailTask getBonafideDetail = new BonafideFragment.GetBonafideDetailTask();
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
//        getActivity().getActionBar().setTitle("Yest");
//        getActivity().getActionBar().show();

        return view;
        //usernameEditText = (EditText) findViewById(R.id.username);
        }

//API starts copy from here
private class GetBonafideDetailTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        String res = null;


        try {
            URL url = new URL("http://"+apiUrl+"//api/v1/bonafide/getBonafideDetail");


            JSONObject postDataParams = new JSONObject();
            postDataParams.put("bonafideid", bonaId);
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
            String BonafideId, Regno, AdmissionNo, Name, Semester, BranchName, BranchCode,AcademicYear;
            String FatherName,IssuedDate,CertificateFor,NoOfYears;
            BonafideId = jsonObj.getString("bonafideid");
//            Log.d("appfor:",AppFor);
            Regno = jsonObj.getString("regno");
            AdmissionNo = jsonObj.getString("admissionno");
            Name = jsonObj.getString("name");
            Semester = jsonObj.getString("semester");
            BranchName = jsonObj.getString("branchname");
            BranchCode=jsonObj.getString("branchcode");
            AcademicYear=jsonObj.getString("academicyear");
            FatherName=jsonObj.getString("fathername");
            IssuedDate=jsonObj.getString("issueddate");
            CertificateFor=jsonObj.getString("certificatefor");
            NoOfYears=jsonObj.getString("noofyears");

            BonafideIdText.setText(BonafideId);
            RegnoText.setText(Regno);
            AdmissionnoText.setText(AdmissionNo);
//            NameText.setText(Name);
//            SemesterText.setText(Semester);
//            BranchNameText.setText(BranchName);
//            BranchCodeText.setText(BranchCode);
//            AcademicYearText.setText(AcademicYear);
//            FatherNameText.setText(FatherName);
//            IssuedDateText.setText(IssuedDate);
//            CertificateForText.setText(CertificateFor);
//            NoOfYearText.setText(NoOfYears);



        }
        catch (Exception e){

        }
    }
}

//API starts copy from here

}

