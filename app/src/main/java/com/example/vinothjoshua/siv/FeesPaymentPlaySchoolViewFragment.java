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
 * Created by Seetha on 09-Dec-17.
 */

public class FeesPaymentPlaySchoolViewFragment extends Fragment {
    String billNumber;
    EditText billNumberText;
    TextView DateText,registerNumberText,nameText,programText,sectionText,academicyearText;
    TextView schoolFeesTitleText,schoolFeesText,schoolFeesPaidText,transportFeesTitleText,transportFeesAmountText;
    TextView transportFeesPaidText,totalFeesText,totalFeesPaidText,balanceText,dueDateText,paymentMethodText,chequeNoText,chequeDateText,bankNameText;

    SearchView searchView;
    String SearchNo;
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feespaymentplayschoolview, container, false);

        searchView = (SearchView) view.findViewById(R.id.billno);
        //registerNumber = LoginActivity.uid;

        searchView.setSubmitButtonEnabled(true);

        DateText=(TextView)view.findViewById(R.id.Date);
        registerNumberText = (TextView)view.findViewById(R.id.registernumber);
        // admissionnoText=(TextView)view.findViewById(R.id.admissionNumber);
        nameText =(TextView) view.findViewById(R.id.Name);


        programText=(TextView)view.findViewById(R.id.Program);
        sectionText=(TextView)view.findViewById(R.id.Section);
        academicyearText=(TextView)view.findViewById(R.id.AcademicYear);
        schoolFeesTitleText=(TextView)view.findViewById(R.id.Schoolfeestitle);
        schoolFeesText=(TextView)view.findViewById(R.id.Schoolfees);
        schoolFeesPaidText=(TextView)view.findViewById(R.id.SchoolFeesPaid);
        transportFeesTitleText=(TextView)view.findViewById(R.id.TransportFeesTitle);
        transportFeesAmountText=(TextView)view.findViewById(R.id.Transportfeesamount);
        transportFeesPaidText=(TextView)view.findViewById(R.id.Transportfeespaid);
        totalFeesText=(TextView)view.findViewById(R.id.TotalFees);
        totalFeesPaidText=(TextView)view.findViewById(R.id.TotalFeesPaid);
        balanceText=(TextView)view.findViewById(R.id.Balance);
        dueDateText=(TextView)view.findViewById(R.id.Duedate);
        paymentMethodText=(TextView)view.findViewById(R.id.PaymentMethod);
        chequeNoText=(TextView)view.findViewById(R.id.ChequeNumber);
        chequeDateText=(TextView)view.findViewById(R.id.ChequeDate);
        bankNameText=(TextView)view.findViewById(R.id.BankName);


       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                billNumber=query;
                FeesPaymentPlaySchoolViewFragment.GetApplicationDetailTask getFeesPaymentPlaySchoolInformationDetail = new FeesPaymentPlaySchoolViewFragment.GetApplicationDetailTask();
                getFeesPaymentPlaySchoolInformationDetail.execute();
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
                URL url = new URL("http://"+apiUrl+"/api/v1/feespaymentplayschool/getPlaySchoolFeesPaymentInformationDetail");


                JSONObject postDataParams = new JSONObject();
                //billNumber = utils.getUserId();
                postDataParams.put("billno", billNumber);

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
            Log.e("infobase:",result);
            try {
                JSONArray jsonArr = new JSONArray(result);

                JSONObject tableoneObj = jsonArr.getJSONObject(0);
                JSONObject tabletwoObj = jsonArr.getJSONObject(1);
                String BillDate,Registernumber,name,Program, Section,AcademicYear,SchoolFeesTitle, SchoolFeesAmount, SchoolFeesPaid,TransportFeesTitle,TransportFeesAmount;
                String TransportFeesPaid,TotalFees,TotalFeesPaid,Balance,DueDate,PaymentMethod,Chequeno,ChequeDate,BankName;
                BillDate = tableoneObj.getString("billno");
                Registernumber=tableoneObj.getString("registernumber");
                name = tabletwoObj.getString("candidatename");
                Program=tabletwoObj.getString("standardstudying");
                Section=tabletwoObj.getString("section");

                AcademicYear=tabletwoObj.getString("academicyear");
                SchoolFeesTitle = tabletwoObj.getString("schoolfeestitle");
                SchoolFeesAmount = tabletwoObj.getString("schoolfees");
                SchoolFeesPaid = tabletwoObj.getString("schoolfeespaid");
                TransportFeesTitle = tabletwoObj.getString("transportfeestitle");
                TransportFeesAmount = tabletwoObj.getString("transportfees");
                TransportFeesPaid = tabletwoObj.getString("transportfeespaid");
                TotalFees = tabletwoObj.getString("totalfees");
                TotalFeesPaid = tabletwoObj.getString("totalfeespaid");
                Balance = tabletwoObj.getString("balance");
                DueDate = tabletwoObj.getString("duedate");
                PaymentMethod = tabletwoObj.getString("paymentmethod");
                Chequeno = tabletwoObj.getString("chequeno");
                ChequeDate = tabletwoObj.getString("chequedate");
                BankName = tabletwoObj.getString("bankname");

                DateText.setText(BillDate);
                registerNumberText.setText(Registernumber);
                nameText.setText(name);
                programText.setText(Program);
                sectionText.setText(Section);
                academicyearText.setText(AcademicYear);
                schoolFeesTitleText.setText(SchoolFeesTitle);
                schoolFeesText.setText(SchoolFeesAmount);
                schoolFeesPaidText.setText(SchoolFeesPaid);
                transportFeesTitleText.setText(TransportFeesTitle);
                transportFeesAmountText.setText(TransportFeesAmount);
                transportFeesPaidText.setText(TransportFeesPaid);
                totalFeesText.setText(TotalFees);
                totalFeesPaidText.setText(TotalFeesPaid);
                balanceText.setText(Balance);
                dueDateText.setText(DueDate);
                paymentMethodText.setText(PaymentMethod);
                chequeNoText.setText(Chequeno);
                chequeDateText.setText(ChequeDate);
                bankNameText.setText(BankName);


            }
            catch (Exception e){

            }
        }





        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }

}
