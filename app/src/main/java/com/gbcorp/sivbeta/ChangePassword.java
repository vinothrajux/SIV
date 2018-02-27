package com.gbcorp.sivbeta;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by Seetha on 05-Jan-18.
 */

public class ChangePassword extends Fragment {
    EditText currentPasswordText, newPasswordText, confirmPasswordText;
    Button changePwdBtn;
    Utils utils = new Utils();
    String apiUrl = utils.getApiHost();
    String currentPwd, newPwd, confirmPwd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.changepassword, container, false);
        currentPasswordText = (EditText) view.findViewById(R.id.CurrentPassword);
        newPasswordText = (EditText) view.findViewById(R.id.NewPassword);
        confirmPasswordText = (EditText) view.findViewById(R.id.ConfirmPassword);
        changePwdBtn = (Button) view.findViewById(R.id.changePasswordButton);

        changePwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentPwd = currentPasswordText.getText().toString();
                newPwd = newPasswordText.getText().toString();
                confirmPwd = confirmPasswordText.getText().toString();
                if(newPwd.equals(null)||newPwd.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "New Password must not be Empty!", Toast.LENGTH_SHORT).show();
                }else
                if(newPwd.equals(confirmPwd)){
                    ChangePasswordTask changePasswordTask = new ChangePasswordTask();
                    changePasswordTask.execute();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Confirm Password Doesn't Match with New Password!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }
    //API starts copy from here
    private class ChangePasswordTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://" + apiUrl + "/api/v1/changepassword");

                String registerNumber = utils.getUserId();
                int instituteid = utils.getInstituteId();

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("regno", registerNumber);
                postDataParams.put("instituteid", instituteid);
                postDataParams.put("currentpassword", currentPwd);
                postDataParams.put("newpassword", newPwd);
                postDataParams.put("confirmpassword", confirmPwd);

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

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (IOException e) {
                // writing exception to log
                e.printStackTrace();

            } catch (Exception e) {

            }
            //JSONObject response = new JSONObject(responseStringBuffer.toString());

            return res;
        }//close doInBackground

        protected void onProgressUpdate(Integer... progress) {
//            setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {

            Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            if(result.equals("PASSWORD CHANGED SUCCESSFULLY!")){
                getActivity().finish();
            }
        }
    }
}
