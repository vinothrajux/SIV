package com.gbcorp.sivbeta;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity{
    String response = null;
    EditText usernameEditText;
    EditText passwordEditTExt;
    String username;
    String password;
    String role;


    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hide Title Bar
        getSupportActionBar().hide();

        Button LoginBtn = (Button) findViewById(R.id.loginButton);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditTExt = (EditText) findViewById(R.id.password);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginAccessTask runLoginAccessTask = new LoginAccessTask();
                runLoginAccessTask.execute();
                username=usernameEditText.getText().toString();
                password=passwordEditTExt.getText().toString();


            }
        });

    }

//    public void dashboard(View view){
//        Intent intent = new Intent(LoginActivity.this,DashboardDemoActivity.class);
//        startActivity(intent);
//    }


    private class LoginAccessTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/user");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", username);
                postDataParams.put("password", password);
                postDataParams.put("sourceDevice", "app");
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
//            Toast.makeText(getApplicationContext(), "From Server: " + result, Toast.LENGTH_SHORT).show();
//            Log.e("loginoutput",result);
            try {
                //JSONArray jsonArr = new JSONArray(result);
                JSONObject jsonObj = new JSONObject(result);
                utils.setUserData(jsonObj);
                role = jsonObj.getString("userRole");

                utils.setUserId(jsonObj.getString("username"));
                utils.setUserRole(role);

                int instId = Integer.parseInt(jsonObj.getString("instituteid"));
                utils.setInstituteId(instId);
//                for (int i = 0; i < jsonArr.length(); i++) {
//                    JSONObject jsonObj = jsonArr.getJSONObject(i);
//                    role = jsonObj.getString("userRole");
//                    System.out.println(jsonObj);
//                }
                Intent intent = new Intent(LoginActivity.this, InstituteSplashScreenActivity.class);
//                intent.putExtra("userRole", role);
                startActivity(intent);
            }
            catch (Exception e){

            }
        }
    }

}
