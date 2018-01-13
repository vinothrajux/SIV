package com.gbcorp.sivbeta;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * Created by Seetha on 11-Dec-17.
 */

public class DailyAttendanceViewParentPlaySchoolFragment extends Fragment {
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();
    String registerNumber;

    TextView attendancestatusText,attendancedateText;
    ImageView attendancestatusImg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dailyattendanceviewparentplayschool, container, false);
        attendancedateText=(TextView) view.findViewById(R.id.attendancedate);
        attendancestatusText=(TextView) view.findViewById(R.id.attendancestatus);
        attendancestatusImg=(ImageView) view.findViewById(R.id.attendancestatusimg);
        DailyAttendanceViewParentPlaySchoolFragment.GetDailyAttendance getDailyAttendance= new DailyAttendanceViewParentPlaySchoolFragment.GetDailyAttendance();
        getDailyAttendance.execute();
        return view;
    }





    //API starts copy from here
    private class GetDailyAttendance extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;



            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/studentattendancedetailplayschool/getPlaySchoolFetchEnteredAttendanceDetail");


                JSONObject postDataParams = new JSONObject();
                registerNumber = utils.getUserId();

//                postDataParams.put("entryDate", "12/12/2017");
                postDataParams.put("registernumber", registerNumber);

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
                JSONObject jsonObj = new JSONObject(result);
                String status, registernum, entrydate;
                status = jsonObj.getString("status");
                registernum = jsonObj.getString("registernumber");
                entrydate = jsonObj.getString("entrydate");

                attendancedateText.setText(utils.convertToDateFormat(entrydate));
                if(status.equals("1")){
                    attendancestatusText.setText("Present");
                    attendancestatusText.setTextColor(Color.parseColor("#1cbc41"));
                    attendancestatusText.setTypeface(null,Typeface.BOLD);
                    attendancestatusImg.setImageResource(R.drawable.present);
                    attendancestatusImg.setVisibility(View.VISIBLE);
                }else if(status.equals("0")){
                    attendancestatusText.setText("Absent");
                    attendancestatusText.setTextColor(Color.parseColor("#ff0000"));
                    attendancestatusText.setTypeface(null,Typeface.BOLD);
                    attendancestatusImg.setImageResource(R.drawable.absent);
                    attendancestatusImg.setVisibility(View.VISIBLE);
                }





            }
            catch (Exception e){

            }
        }





        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }

}
