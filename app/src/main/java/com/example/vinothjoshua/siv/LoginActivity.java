package com.example.vinothjoshua.siv;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

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
            Toast.makeText(getApplicationContext(), "From Server: " + result, Toast.LENGTH_SHORT).show();
            try {
                //JSONArray jsonArr = new JSONArray(result);
                JSONObject jsonObj = new JSONObject(result);
                role = jsonObj.getString("userRole");
                utils.setUserId(jsonObj.getString("username"));
//                for (int i = 0; i < jsonArr.length(); i++) {
//                    JSONObject jsonObj = jsonArr.getJSONObject(i);
//                    role = jsonObj.getString("userRole");
//                    System.out.println(jsonObj);
//                }
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.putExtra("userRole", role);
                startActivity(intent);
            }
            catch (Exception e){

            }
        }
    }

}
