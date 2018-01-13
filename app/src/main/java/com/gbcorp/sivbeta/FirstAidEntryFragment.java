package com.gbcorp.sivbeta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
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
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Seetha on 29-May-17.
 */

public class FirstAidEntryFragment extends Fragment {
    String RegNo;
    SearchView searchView;
    ArrayList<FirstAidDetailModel> firstAidModel;
    ListView firstAidList;
    private static FirstAidDetailModel adapter;


    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.firstaidentry, container, false);

        searchView = (SearchView) view.findViewById(R.id.regno );
        firstAidList = (ListView) view.findViewById(R.id.firstaidlist);

        searchView.setSubmitButtonEnabled(true);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //callSearch(query);
                RegNo=query;
                FirstAidEntryFragment.GetFirstAidEntryDetailTask getFirstAidEntryDetail = new FirstAidEntryFragment.GetFirstAidEntryDetailTask();
                getFirstAidEntryDetail.execute();
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
    }

    //API starts copy from here
    private class GetFirstAidEntryDetailTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://"+apiUrl+"//api/v1/firstaidentry/getFirstAidEntryDetail");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("regno", RegNo);
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

            Toast.makeText(getActivity(), "From Server: " + result, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArr = new JSONArray(result);
                String currentdate, reasonforfirstaid, firstaiddetails, hospitalname, hospitalfees, remarks;
                firstAidModel = new ArrayList<>();
                for(int i=0; i < jsonArr.length(); i++ ){
                    JSONObject jsonObj = jsonArr.getJSONObject(i);
                    currentdate = jsonObj.getString("currentdate");
                    reasonforfirstaid = jsonObj.getString("reasonforfirstaid");
                    firstaiddetails = jsonObj.getString("firstaiddetails");
                    hospitalname = jsonObj.getString("hospitalname");
                    hospitalfees = jsonObj.getString("hospitalfees");
                    remarks = jsonObj.getString("remarks");

                    firstAidModel.add(new FirstAidDetailModel(currentdate,reasonforfirstaid, firstaiddetails, hospitalname, hospitalfees, remarks ));
                }
                FirstAidEntryAdaptor adaptor = new FirstAidEntryAdaptor(getActivity() ,R.layout.firstaidentryrow, firstAidModel);
                firstAidList.setAdapter(adaptor);
            }
            catch (Exception e){

            }
        }
    }

//API starts copy from here
}

