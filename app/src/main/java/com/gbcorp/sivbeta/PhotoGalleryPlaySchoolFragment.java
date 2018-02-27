package com.gbcorp.sivbeta;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Seetha on 16-Dec-17.
 */

public class PhotoGalleryPlaySchoolFragment extends Fragment {
    Utils utils = new Utils();
    String apiUrl= utils.getApiHost();
    ListView medialist;
    Context context;
    ImageView testimage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playschoolphotogalleryview, container, false);
        testimage=  view.findViewById(R.id.testImageView);
//        URL url = null;
//        try {
//            url = new URL("http://siv.gbcorp.in/images/uploaded/1519324032251banner-manikkavasakar.jpg");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Bitmap bmp = null;
//        try {
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        testimage.setImageBitmap(bmp);
        // Picasso.with(getActivity()).load("http://siv.gbcorp.in/images/uploaded/1519324032251banner-manikkavasakar.jpg").into(testimage);
//        Glide.with(this).load("http://siv.gbcorp.in/images/uploaded/1519324032251banner-manikkavasakar.jpg").into(testimage);
        PhotoGalleryPlaySchoolFragment.GetPhotoGalleryTask getPhotoGalleryTask = new PhotoGalleryPlaySchoolFragment.GetPhotoGalleryTask();
        getPhotoGalleryTask.execute();
        // Inflate the layout for this fragment
        return view;
    }


    //API starts copy from here
    private class GetPhotoGalleryTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/playschoolphotogallery/getMediaList");

//                Utils utils = new Utils();
                Integer instituteid = utils.getInstituteId();
                String registernumber= utils.getUserId();

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("registernumber", registernumber);
                postDataParams.put("instituteid", instituteid);
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

//                JSONObject jsonObj = new JSONObject(result);

                JSONArray jsonArr = new JSONArray(result);


                medialist=(ListView) getActivity().findViewById(R.id.mediaListView);
                medialist.setAdapter(new MediaListPlaySchoolCustomAdaptor(getActivity(),jsonArr));

            }
            catch (Exception e){

            }
        }

        // Inflate the layout for this fragment
        //   return inflater.inflate(R.layout.applicationsale, container, false);
    }
}
