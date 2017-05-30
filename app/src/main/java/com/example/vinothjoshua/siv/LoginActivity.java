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


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    String username;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hide Title Bar
        getSupportActionBar().hide();

        Button LoginBtn = (Button) findViewById(R.id.loginButton);
        usernameEditText = (EditText) findViewById(R.id.username);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LoginAccessTask runLoginAccessTask = new LoginAccessTask();
//                runLoginAccessTask.execute();
                username=usernameEditText.getText().toString();
                switch(username){
                    case "kamal":
                        role = "Office Admin";
                        break;

                    case "seetha":
                        role = "Principal";
                        break;

                    default:
                        role = "Invalid";
                        break;
                }

                if(role == "Invalid"){
                    Toast.makeText(getApplicationContext(), "Invalid User" + username, Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                    intent.putExtra("userRole", role);
                    startActivity(intent);
                }

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
//            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
//            postParameters.add(new BasicNameValuePair("username", params[0] ));
//            postParameters.add(new BasicNameValuePair("password", params[1] ));
            Log.i("Testing","Testing1");
            String res = null;


            try {
                URL url = new URL("http://192.168.43.177:8080/test");
                Log.i("Testing","Testing2");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.i("Testing","Testing3");
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                byte[] postParamsByte = "postdata".getBytes("UTF-8");
                Log.i("Testing","Testing5");
                conn.setRequestProperty("Content-Length", String.valueOf(postParamsByte.length));
                conn.setDoOutput(true);
                Log.i("Testing","Testing6");
                conn.getOutputStream().write(postParamsByte);
                Log.i("Testing","Testing7");
                InputStream responseInputStream = conn.getInputStream();

                StringBuffer responseStringBuffer = new StringBuffer();
                byte[] byteContainer = new byte[1024];
                for (int i; (i = responseInputStream.read(byteContainer)) != -1; ) {
                    responseStringBuffer.append(new String(byteContainer, 0, i));
                }
                Log.i("Testing","Testing4");
                res = responseStringBuffer.toString();
            }
            catch (IOException e) {
                Log.i("Testing","Testing8");
                // writing exception to log
                e.printStackTrace();

            }
            //JSONObject response = new JSONObject(responseStringBuffer.toString());









//            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost("http://192.168.43.177:8080/test");
//            // Building post parameters, key and value pair
//            Log.i("Testing","Testing2");
//            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
//            nameValuePair.add(new BasicNameValuePair("email", "user@gmail.com"));
//            nameValuePair.add(new BasicNameValuePair("password", "encrypted_password"));
//            // Url Encoding the POST parameters
//            Log.i("Testing","Testing3");
//            try {
//                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
//                Log.i("Testing","Testing4");
//            }
//            catch (UnsupportedEncodingException e) {
//                // writing error to Log
//                e.printStackTrace();
//                Log.i("Testing","Testing5");
//            }
//
//
//            // Making HTTP Request
//            try {
//                Log.i("Testing","Testing6");
//                HttpResponse response = httpClient.execute(httpPost);
//
//                // writing response to log
//                Log.i("Testing","Testing7");
//                Log.d("Http Response:", response.toString());
//
//                Log.i("Testing",response.toString());
//                res =response.toString();
//            } catch (ClientProtocolException e) {
//                // writing exception to log
//                e.printStackTrace();
//            } catch (IOException e) {
//                // writing exception to log
//                e.printStackTrace();
//
//            }
            return res;
        }//close doInBackground

        protected void onProgressUpdate(Integer... progress) {
//            setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), "From Server: " + result, Toast.LENGTH_SHORT).show();
        }
    }

}
