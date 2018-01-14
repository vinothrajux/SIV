package com.gbcorp.sivbeta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Seetha on 09-Dec-17.
 */

public class PhotoGalleryUploadPlaySchoolFragment extends Fragment{
    private Button uploadBn, ChooseBn;
    private ImageView chosenImg;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    Utils utils = new Utils();
    String apiUrl = utils.getApiHost();
    private String UploadUrl = "http://"+apiUrl+"/api/v1/playschoolphotogallery";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.photogalleryplayschool, container, false);
        uploadBn = (Button) view.findViewById(R.id.uploadImageBtn);
        ChooseBn = (Button) view.findViewById(R.id.chooseImageBtn);
        chosenImg = (ImageView) view.findViewById(R.id.chosenImage);
        ChooseBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        uploadBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });


        return view;

    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }
    String imagefilename;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("OnActivityResult()", "1");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            String uriString = path.toString();
            Log.e("inside3:",uriString);
            imagefilename = uriString.substring(uriString.lastIndexOf("/")+1);
            Log.e("inside2:",imagefilename);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                chosenImg.setImageBitmap(bitmap);
                chosenImg.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(getActivity(), Response, Toast.LENGTH_LONG).show();
                            chosenImg.setImageResource(0);
                            chosenImg.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
          @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("encodedimage", imagetoString(bitmap));
                params.put("uploaddate", "12/12/2017");
                params.put("academicyear", "2017-2018");
                params.put("program", "TODDLER PROGRAM");
                params.put("section", "SECTION-A");
                params.put("uploadedby", "KAMAL");
                params.put("studentlist", "all");
              Log.e("inside1:",imagefilename);
                params.put("imagepath", imagefilename);
                params.put("imagetitle", "welcome image");
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);
    }

    private String imagetoString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}
