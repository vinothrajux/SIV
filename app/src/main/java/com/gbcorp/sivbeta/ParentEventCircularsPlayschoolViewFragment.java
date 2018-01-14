package com.gbcorp.sivbeta;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Seetha on 06-Jan-18.
 */

public class ParentEventCircularsPlayschoolViewFragment extends Fragment {

    Utils utils = new Utils();
    String apiUrl = utils.getApiHost();
    ListView hwlist;
    Context context;
    SearchView hwDateText;
    Date hwdate;
    Boolean datesearchStatus = true;
    //    ParentHomeWorkFragment.GetHomeworkTask getHomeWorkDetail = new ParentHomeWorkFragment.GetHomeworkTask();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.parenteventcircularsplayschoolview, container, false);
       // hwDateText = (SearchView) view.findViewById(R.id.homeworkdate);
       // hwDateText.setSubmitButtonEnabled(true);
        ParentEventCircularsPlayschoolViewFragment.GetEventCircularTask getEventCircularDetailInitial = new ParentEventCircularsPlayschoolViewFragment.GetEventCircularTask();
        getEventCircularDetailInitial.execute();


//        hwDateText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //callSearch(query);
//                hwdate=query;
//                datesearchStatus = false;
//                ParentHomeWorkFragment.GetHomeworkTask getHomeWorkDetail = new ParentHomeWorkFragment.GetHomeworkTask();
//                getHomeWorkDetail.execute();
//                return true;
//
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
//                callSearch(newText);
//                //              }
//                return true;
//            }
//            public void callSearch(String query) {
//                //Do searching
//            }
//
//        });
        return view;
    }


    //API starts copy from here
    private class GetEventCircularTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/eventscircularsplayschool/getStudentEventCircularListPlaySchool");

                String registernumber = utils.getUserId();

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("registernumber", registernumber);
                postDataParams.put("eventdate", hwdate);
//                postDataParams.put("currentdatestatus", datesearchStatus);
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

            //Toast.makeText(getActivity().getApplicationContext(), "From Server: " + result, Toast.LENGTH_SHORT).show();
            try {
                JSONArray jsonArr = new JSONArray(result);
                JSONObject firsthomeworkObj=jsonArr.getJSONObject(0);
 //               String homeworkdate = firsthomeworkObj.getString("eventdate");
//                hwDateText.setText(utils.convertToDateFormat(homeworkdate));
//                hwDateText.setQuery(utils.convertToDateFormat(homeworkdate),false);
//                JSONObject jsonObj = new JSONObject(result);
//                String EntryDate,EntryDay,EntryTime,RegisterNumber,Name,Program, Section,AcademicYear,Pickuppersonname,Pickuppersonrelation,Pickuppersonmobileno,remarks;
//
//                EntryDate=jsonObj.getString("pickupddate");
//                RemarksText.setText(remarks);


                hwlist=(ListView) getActivity().findViewById(R.id.eventcircularListView);
                hwlist.setAdapter(new EventsCiruclarsPlaySchoolCustomAdapter(getActivity(),jsonArr));
            }
            catch (Exception e){

            }
        }

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.applicationsale, container, false);
    }

}
