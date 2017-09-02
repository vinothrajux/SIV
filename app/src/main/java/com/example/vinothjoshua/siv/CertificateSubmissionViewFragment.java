package com.example.vinothjoshua.siv;

import android.support.v4.app.Fragment;
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
 * Created by GBCorp on 02/09/2017.
 */

public class CertificateSubmissionViewFragment extends Fragment {
    String registerNumber;
    EditText regNumberText;
    TextView regNoText,admissionNoText,studentNameText,semesterText,branchText,branchCodeText,batchText,academicYearText,studentTypeText;
    TextView foloiNoText,marksheetsubmittedText,marksheetSlNoText,transfercertificatesubmittedText;
    TextView transferCertificateSlNoText,communitycertificatesubmittedText,communityCertificateSlNoText;
    TextView xeroxmarksheetsubmittedText,xeroxMarksheetCopiesText,xeroxTransfercertificatesubmittedText;
    TextView xeroxTransferCertificateCopiesText,xeroxCommunitycertificatesubmittedText,xeroxCommunityCertificateCopiesText;
    TextView migrationcertificatesubmittedText,migrationCertificateSlNoText,conductcertificatesubmittedText;
    TextView conductCertificateSlNoText,stampSizePhotosubmittedText,stampSizePhotoCopiesText,passPortPhotosubmittedText;
    TextView passportSizePhotoCopiesText;
    SearchView searchView;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.certificatesubmissionview, container, false);

        searchView = (SearchView) view.findViewById(R.id.registerno);

        searchView.setSubmitButtonEnabled(true);

        regNoText=(TextView)view.findViewById(R.id.registerNumber);
        admissionNoText=(TextView)view.findViewById(R.id.admissionNumber);
        studentNameText =(TextView) view.findViewById(R.id.name);
        semesterText =(TextView) view.findViewById(R.id.semester);
        branchText=(TextView) view.findViewById(R.id.branch);
        branchCodeText=(TextView) view.findViewById(R.id.branchCode);
        //batchText=(TextView) view.findViewById(R.id.batch);

        academicYearText=(TextView)view.findViewById(R.id.academicYear);
        studentTypeText=(TextView) view.findViewById(R.id.studentType);
        foloiNoText=(TextView) view.findViewById(R.id.folioNo);
        marksheetsubmittedText=(TextView) view.findViewById(R.id.markSheetSubmitted);
        marksheetSlNoText=(TextView) view.findViewById(R.id.markSheetSlNo);
        transfercertificatesubmittedText=(TextView) view.findViewById(R.id.transferCertificateSubmitted);
        transferCertificateSlNoText=(TextView) view.findViewById(R.id.tcSerialNo);
        communitycertificatesubmittedText=(TextView) view.findViewById(R.id.communityCertificateSubmitted);
        communityCertificateSlNoText=(TextView) view.findViewById(R.id.communityCertificateSlNo);
        xeroxmarksheetsubmittedText=(TextView) view.findViewById(R.id.xeroxMarkSheetSubmitted);
        xeroxMarksheetCopiesText=(TextView) view.findViewById(R.id.xeroxMarkSheetCopies);
        xeroxTransfercertificatesubmittedText=(TextView) view.findViewById(R.id.xeroxTCSubmitted);
        xeroxTransferCertificateCopiesText=(TextView) view.findViewById(R.id.xeroxTCCopies);
        xeroxCommunitycertificatesubmittedText=(TextView) view.findViewById(R.id.xeroxCommunityCertificateSubmitted);
        xeroxCommunityCertificateCopiesText=(TextView) view.findViewById(R.id.xeroxCommunityCertificateCopies);
        migrationcertificatesubmittedText=(TextView) view.findViewById(R.id.migrationCertificateSubmitted);
        migrationCertificateSlNoText=(TextView) view.findViewById(R.id.migrationCertificateSlNo);
        conductcertificatesubmittedText=(TextView) view.findViewById(R.id.conductCertificateSubmitted);
        conductCertificateSlNoText=(TextView) view.findViewById(R.id.conductCertificateSlNo);
        stampSizePhotosubmittedText=(TextView) view.findViewById(R.id.stampSizePhotoSubmitted);
        stampSizePhotoCopiesText=(TextView) view.findViewById(R.id.stampSizePhotoCopies);
        passPortPhotosubmittedText=(TextView) view.findViewById(R.id.passPortPotoSubmitted);
        passportSizePhotoCopiesText=(TextView) view.findViewById(R.id.passPortPotoCopies);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                registerNumber=query;
                CertificateSubmissionViewFragment.GetCertificateSubmissionDetailTask getCertificateSubmissionDetail = new CertificateSubmissionViewFragment.GetCertificateSubmissionDetailTask();
                getCertificateSubmissionDetail.execute();
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
    private class GetCertificateSubmissionDetailTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/certificatesubmission/getStudentCertificateSubmissionDetail");


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
                String Regno,Admissionno,name,Semester,Branch,BranchCode,Batch,AcademicYear,StudentType;
                String FoloiNo,Marksheetsubmitted,MarksheetSlNo,Transfercertificatesubmitted,TransferCertificateSlNo;
                String Communitycertificatesubmitted,CommunityCertificateSlNo,Xeroxmarksheetsubmitted,XeroxMarksheetCopies;
                String XeroxTransfercertificatesubmitted,XeroxTransferCertificateCopies,XeroxCommunitycertificatesubmitted;
                String XeroxCommunityCertificateCopies,Migrationcertificatesubmitted,MigrationCertificateSlNo,Conductcertificatesubmitted;
                String ConductCertificateSlNo,StampSizePhotosubmitted,StampSizePhotoCopies,PassPortPhotosubmitted;
                String PassportSizePhotoCopies;

                Regno=jsonObj.getString("regno");
                Admissionno=jsonObj.getString("admissionno");
                name = jsonObj.getString("studentname");
                //Log.d("appfor:",AppFor);
                Semester = jsonObj.getString("semester");
                Branch = jsonObj.getString("branch");
                BranchCode = jsonObj.getString("branchcode");
               // Batch = jsonObj.getString("batch");

                AcademicYear=jsonObj.getString("academicyear");
                StudentType = jsonObj.getString("studenttype");
                FoloiNo = jsonObj.getString("foloiNo");
                Marksheetsubmitted = jsonObj.getString("marksheetsubmitted");
                MarksheetSlNo = jsonObj.getString("marksheetSlNo");
                Transfercertificatesubmitted = jsonObj.getString("transfercertificatesubmitted");
                TransferCertificateSlNo = jsonObj.getString("transferCertificateSlNo");
                Communitycertificatesubmitted = jsonObj.getString("communitycertificatesubmitted");
                CommunityCertificateSlNo = jsonObj.getString("communityCertificateSlNo");
                Xeroxmarksheetsubmitted = jsonObj.getString("xeroxmarksheetsubmitted");
                XeroxMarksheetCopies = jsonObj.getString("xeroxMarksheetCopies");
                XeroxTransfercertificatesubmitted = jsonObj.getString("xeroxTransfercertificatesubmitted");
                XeroxTransferCertificateCopies = jsonObj.getString("xeroxTransferCertificateCopies");
                XeroxCommunitycertificatesubmitted = jsonObj.getString("xeroxCommunitycertificatesubmitted");
                XeroxCommunityCertificateCopies = jsonObj.getString("xeroxCommunityCertificateCopies");
                Migrationcertificatesubmitted = jsonObj.getString("migrationcertificatesubmitted");
                MigrationCertificateSlNo = jsonObj.getString("migrationCertificateSlNo");
                Conductcertificatesubmitted = jsonObj.getString("conductcertificatesubmitted");
                ConductCertificateSlNo = jsonObj.getString("conductCertificateSlNo");
                StampSizePhotosubmitted = jsonObj.getString("stampSizePhotosubmitted");
                StampSizePhotoCopies = jsonObj.getString("stampSizePhotoCopies");
                PassPortPhotosubmitted = jsonObj.getString("passPortPhotosubmitted");
                PassportSizePhotoCopies = jsonObj.getString("passportSizePhotoCopies");


                regNoText.setText(Regno);
                admissionNoText.setText(Admissionno);
                studentNameText.setText(name);
                semesterText.setText(Semester);
                branchText.setText(Branch);
                branchCodeText.setText(BranchCode);
//                batchText.setText(Batch);

                academicYearText.setText(AcademicYear);
                studentTypeText.setText(StudentType);
                foloiNoText.setText(FoloiNo);
                marksheetsubmittedText.setText(Marksheetsubmitted);
                marksheetSlNoText.setText(MarksheetSlNo);
                transfercertificatesubmittedText.setText(Transfercertificatesubmitted);
                transferCertificateSlNoText.setText(TransferCertificateSlNo);
                communitycertificatesubmittedText.setText(Communitycertificatesubmitted);
                communityCertificateSlNoText.setText(CommunityCertificateSlNo);
                xeroxmarksheetsubmittedText.setText(Xeroxmarksheetsubmitted);
                xeroxMarksheetCopiesText.setText(XeroxMarksheetCopies);
                xeroxTransfercertificatesubmittedText.setText(XeroxTransfercertificatesubmitted);
                xeroxTransferCertificateCopiesText.setText(XeroxTransferCertificateCopies);
                xeroxCommunitycertificatesubmittedText.setText(XeroxCommunityCertificateCopies);
                xeroxCommunityCertificateCopiesText.setText(XeroxCommunityCertificateCopies);
                migrationcertificatesubmittedText.setText(Migrationcertificatesubmitted);
                migrationCertificateSlNoText.setText(MigrationCertificateSlNo);
                conductcertificatesubmittedText.setText(Conductcertificatesubmitted);
                conductCertificateSlNoText.setText(ConductCertificateSlNo);
                stampSizePhotosubmittedText.setText(StampSizePhotosubmitted);
                stampSizePhotoCopiesText.setText(StampSizePhotoCopies);
                passPortPhotosubmittedText.setText(PassPortPhotosubmitted);
                passportSizePhotoCopiesText.setText(PassportSizePhotoCopies);


            }
            catch (Exception e){

            }
        }







        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.teachstaffstudattend, container, false);
    }

}
