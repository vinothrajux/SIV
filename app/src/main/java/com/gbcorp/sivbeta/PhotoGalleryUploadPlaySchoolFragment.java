package com.gbcorp.sivbeta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.mime.HttpMultipartMode;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Seetha on 09-Dec-17.
 */

public class PhotoGalleryUploadPlaySchoolFragment extends Fragment{
    private Button uploadBn, ChooseBn, getStudentBn;
    File myFile;
    private ImageView chosenImg;
    private final int IMG_REQUEST = 1;
    private static final int SELECT_VIDEO = 3;
    private String selectedPath;
    String selectedProgram, selectedSection, selectedAcademicyear;
    private Bitmap bitmap;
    Utils utils = new Utils();
    String apiUrl = utils.getApiHost();
    private String UploadUrl = "http://"+apiUrl+"/api/v1/playschoolphotogallery";
    ListView PhotoGalleryStudentList;
    EditText imageTitleEdit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.photogalleryplayschool, container, false);
        uploadBn = (Button) view.findViewById(R.id.uploadImageBtn);
        ChooseBn = (Button) view.findViewById(R.id.chooseImageBtn);
        chosenImg = (ImageView) view.findViewById(R.id.chosenImage);
        getStudentBn = (Button) view.findViewById(R.id.getstudentlist);
        imageTitleEdit = (EditText) view.findViewById(R.id.imageTitle);
        ChooseBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVideo();
            }
        });

        uploadBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                uploadVideo();
                PhotoGalleryUploadPlaySchoolFragment.GetApplicationDetailTask getApplicationDetailTask = new PhotoGalleryUploadPlaySchoolFragment.GetApplicationDetailTask();
                getApplicationDetailTask.execute();
            }
        });

        getStudentBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                uploadVideo();
                PhotoGalleryUploadPlaySchoolFragment.GetStudentListTask getStudentListTask = new PhotoGalleryUploadPlaySchoolFragment.GetStudentListTask();
                getStudentListTask.execute();
            }
        });

        Spinner spinProgram;
        spinProgram= (Spinner) view.findViewById(R.id.programspinner);
        ArrayAdapter<String> programAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.program_array));
        programAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinProgram.setAdapter(programAdapter);
//if you want to set any action you can do in this listener
        spinProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                selectedProgram = arg0.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Spinner spinSection;
        spinSection= (Spinner) view.findViewById(R.id.sectionspinner);
        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.section_array));
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSection.setAdapter(sectionAdapter);
//if you want to set any action you can do in this listener
        spinSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                selectedSection = arg0.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        Spinner spinAcademicYear;
        spinAcademicYear= (Spinner) view.findViewById(R.id.academicyearspinner);
        ArrayAdapter<String> academicYearAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.academicyear_array));
        academicYearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAcademicYear.setAdapter(academicYearAdapter);
//if you want to set any action you can do in this listener
        spinAcademicYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                selectedAcademicyear = arg0.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        return view;

    }
    private void selectVideo(){
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select a Video "), SELECT_VIDEO);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_VIDEO) {
                System.out.println("SELECT_VIDEO");
                Uri selectedVideoUri = data.getData();
                selectedPath = getPath(selectedVideoUri);
                System.out.println("SELECT_VIDEO Path : " + selectedPath);

//                UploadVideo(selectedPath);
            }
        }
    }

    private String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }
    //API starts copy from here
    private class GetStudentListTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;



            try {
                URL url = new URL("http://"+apiUrl+"/api/v1/playschoolphotogallery/getStudentList");


                JSONObject postDataParams = new JSONObject();
                String registerNumber = utils.getUserId();
                int instituteId = utils.getInstituteId();
                postDataParams.put("registernumber", registerNumber);
                postDataParams.put("instituteid", instituteId);
                postDataParams.put("program", selectedProgram);
                postDataParams.put("section", selectedSection);
                postDataParams.put("academicyear", selectedAcademicyear);

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
            Log.e("infobase:",result);
            try {
                JSONArray jsonArr = new JSONArray(result);
                for (int i=0; i<jsonArr.length(); i++){
                    jsonArr.getJSONObject(i).put("studentphotoaccess", 1);
                }
                utils.setPhotoGalleryStudentList(jsonArr);
                Log.e("infobase:",jsonArr.toString());
                PhotoGalleryStudentList = (ListView) getActivity().findViewById(R.id.photoGalleryStudentListView);
                PhotoGalleryStudentList.setAdapter(new PhotoGalleryStudentListAdaptor(getActivity(),jsonArr));
                setListViewHeightBasedOnChildren(PhotoGalleryStudentList);
//
//                JSONObject tableoneObj = jsonArr.getJSONObject(0);
//                JSONObject tabletwoObj = jsonArr.getJSONObject(1);
//                String Registernumber,name,AcademicYear, Program, Section,DateofBirth, Age, Gender,CandFatherName,CandMotherName;
//                String PresentAddress1,PresentAddress2,PresentArea,PresentPinCode,PresentState,FathersMobileNo,FathersAltMobileNo,MothersMobileNo,MothersAltMobileNo,FathersEmail,MothersEmail,Reference;
//                String FathersOccupation,FathersOfficeName,FathersOfficeAddress1,FathersOfficeAddress2,FathersOfficeArea,FathersOfficePinCode,FathersOfficeState,FathersOfficePhoneNo,FathersOfficeAltPhoneNo,FathersOfficeExtensionNo;
//                String MothersOccupation,MothersOfficeName,MothersOfficeAddress1,MothersOfficeAddress2,MothersOfficeArea,MothersOfficePinCode,MothersOfficeState,MothersOfficePhoneNo,MothersOfficeAltPhoneNo,MothersOfficeExtensionNo;
//                String FathersDOB,MothersDOB,ParentsWeddingDate,Religion,PickupPersonName,PickupPersonContactNo,PickupPersonAltContactNo,PickupPersonRelationShip,Transport,TransportStage;
//                name = tableoneObj.getString("candidatename");
//                Registernumber=tableoneObj.getString("registernumber");
//
//                Program=tableoneObj.getString("standardstudying");
//                Section=tableoneObj.getString("section");
//                DateofBirth = tabletwoObj.getString("dateofbirth");
//                Age = tabletwoObj.getString("age");
//                Gender = tabletwoObj.getString("gender");
//                CandFatherName = tabletwoObj.getString("candfathername");
//                CandMotherName = tabletwoObj.getString("candmothername");
//                PresentAddress1 = tabletwoObj.getString("presentaddress1");
//                PresentAddress2 = tabletwoObj.getString("presentaddress2");
//                PresentArea = tabletwoObj.getString("presentarea");
//                PresentPinCode = tabletwoObj.getString("presentpincode");
//                PresentState = tabletwoObj.getString("presentstate");
//                FathersMobileNo = tabletwoObj.getString("fathersmobileno");
//                FathersAltMobileNo = tabletwoObj.getString("fathersaltmobno");
//                MothersMobileNo = tabletwoObj.getString("mothersmobileno");
//                MothersAltMobileNo = tabletwoObj.getString("mothersaltmobno");
//                FathersEmail = tabletwoObj.getString("fathersemail");
//                MothersEmail = tabletwoObj.getString("mothersemail");
//                Reference = tabletwoObj.getString("reference");
//                FathersOccupation = tabletwoObj.getString("fathersoccupation");
//                FathersOfficeName = tabletwoObj.getString("fathersofficename");
//                FathersOfficeAddress1 = tabletwoObj.getString("fathersofficeaddress1");
//                FathersOfficeAddress2 = tabletwoObj.getString("fathersofficeaddress2");
//                FathersOfficeArea = tabletwoObj.getString("fathersofficearea");
//                FathersOfficePinCode = tabletwoObj.getString("fathersofficepincode");
//                FathersOfficeState = tabletwoObj.getString("fathersofficestate");
//                FathersOfficePhoneNo = tabletwoObj.getString("fathersofficephoneno");
//                FathersOfficeAltPhoneNo = tabletwoObj.getString("fathersofficealtphoneno");
//                FathersOfficeExtensionNo = tabletwoObj.getString("fathersofficeextensionno");
//                MothersOccupation = tabletwoObj.getString("mothersoccupation");
//                MothersOfficeName = tabletwoObj.getString("mothersofficename");
//                MothersOfficeAddress1 = tabletwoObj.getString("mothersofficeaddress1");
//                MothersOfficeAddress2 = tabletwoObj.getString("mothersofficeaddress2");
//                MothersOfficeArea = tabletwoObj.getString("mothersofficearea");
//                MothersOfficePinCode = tabletwoObj.getString("mothersofficepincode");
//                MothersOfficeState = tabletwoObj.getString("mothersofficestate");
//                MothersOfficePhoneNo = tabletwoObj.getString("mothersofficephoneno");
//                MothersOfficeAltPhoneNo = tabletwoObj.getString("mothersofficealtphoneno");
//                MothersOfficeExtensionNo = tabletwoObj.getString("mothersofficeextensionno");
//                FathersDOB = tabletwoObj.getString("fathersdob");
//                MothersDOB = tabletwoObj.getString("mothersdob");
//                ParentsWeddingDate = tabletwoObj.getString("parentsweddingdate");
//                Religion = tabletwoObj.getString("religion");
//                PickupPersonName = tabletwoObj.getString("pickuppersonname");
//                PickupPersonContactNo = tabletwoObj.getString("pickuppersoncontactno");
//                PickupPersonAltContactNo = tabletwoObj.getString("pickuppersonaltcontactno");
//                PickupPersonRelationShip = tabletwoObj.getString("pickuppersonrelationship");
//                Transport = tabletwoObj.getString("transport");
//                TransportStage = tabletwoObj.getString("transportstage");
//                AcademicYear=tableoneObj.getString("academicyear");
//
//                nameText.setText(name);
//                regnoText.setText(Registernumber);
//
//                programText.setText(Program);
//                sectionText.setText(Section);
//                dateofbirthText.setText(utils.convertToDateFormat(DateofBirth));
//                ageText.setText(Age);
//                genderText.setText(Gender);
//                candfathernameText.setText(CandFatherName);
//                candmothernameText.setText(CandMotherName);
//                presentaddress1Text.setText(PresentAddress1);
//                presentaddress2Text.setText(PresentAddress2);
//                presentareaText.setText(PresentArea);
//                presentpincodeText.setText(PresentPinCode);
//                presentstateText.setText(PresentState);
//                fathersmobilenoText.setText(FathersMobileNo);
//                fathersaltmobnoText.setText(FathersAltMobileNo);
//                mothersmobilenoText.setText(MothersMobileNo);
//                mothersaltmobnoText.setText(MothersAltMobileNo);
//                fathersemailText.setText(FathersEmail);
//                mothersemailText.setText(MothersEmail);
//                referenceText.setText(Reference);
//                fathersoccupationText.setText(FathersOccupation);
//                fathersofficenameText.setText(FathersOfficeName);
//                fathersofficeaddress1Text.setText(FathersOfficeAddress1);
//                fathersofficeaddress2Text.setText(FathersOfficeAddress2);
//                fathersofficeareaText.setText(FathersOfficeArea);
//                fathersofficepincodeText.setText(FathersOfficePinCode);
//                fathersofficestateText.setText(FathersOfficeState);
//                fathersofficephonenoText.setText(FathersOfficePhoneNo);
//                fathersofficealtphonenoText.setText(FathersOfficeAltPhoneNo);
//                fathersofficeextensionnoText.setText(FathersOfficeExtensionNo);
//                mothersoccupationText.setText(MothersOccupation);
//                mothersofficenameText.setText(MothersOfficeName);
//                mothersofficeaddress1Text.setText(MothersOfficeAddress1);
//                mothersofficeaddress2Text.setText(MothersOfficeAddress2);
//                mothersofficeareaText.setText(MothersOfficeArea);
//                mothersofficepincodeText.setText(MothersOfficePinCode);
//                mothersofficestateText.setText(MothersOfficeState);
//                mothersofficephonenoText.setText(MothersOfficePhoneNo);
//                mothersofficealtphonenoText.setText(MothersOfficeAltPhoneNo);
//                mothersofficeextensionnoText.setText(MothersOfficeExtensionNo);
//                fathersdobText.setText(utils.convertToDateFormat(FathersDOB));
//                mothersdobText.setText(utils.convertToDateFormat(MothersDOB));
//                parentsweddingdateText.setText(utils.convertToDateFormat(ParentsWeddingDate));
//                religionText.setText(Religion);
//                pickuppersonnameText.setText(PickupPersonName);
//                pickuppersoncontactnoText.setText(PickupPersonContactNo);
//                pickuppersonaltcontactnoText.setText(PickupPersonAltContactNo);
//                pickuppersonrelationshipText.setText(PickupPersonRelationShip);
//                transportText.setText(Transport);
//                transportstageText.setText(TransportStage);
//                academicyearText.setText(AcademicYear);


            }
            catch (Exception e){

            }
        }





        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }
    private class GetApplicationDetailTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String res = null;


            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://"+utils.getApiHost()+"/api/v1/playschoolphotogallery/uploadPhotos");

                MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
                entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);


                String ImageTitle = imageTitleEdit.getText().toString();

                entityBuilder.addTextBody("program", selectedProgram);
                entityBuilder.addTextBody("section", selectedSection);
                entityBuilder.addTextBody("academicyear", selectedAcademicyear);
                entityBuilder.addTextBody("imagetitle", ImageTitle);
                entityBuilder.addTextBody("instituteid", Integer.toString(utils.getInstituteId()));
                entityBuilder.addTextBody("studentlist", utils.getPhotoGalleryStudentList().toString());
                entityBuilder.addTextBody("uploadid", "0");
                myFile = new File(selectedPath);
                if(myFile != null)
                {
                    entityBuilder.addBinaryBody("files", myFile);
                }

                HttpEntity entity = entityBuilder.build();
                post.setEntity(entity);
                HttpResponse response = client.execute(post);
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity);
                Log.v("result", result);
                URL url = new URL("http://"+utils.getApiHost()+"/api/v1/playschoolphotogallery/uploadPhotos1");


                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("regno", registerNumber);

                myFile = new File(selectedPath);
                postDataParams.put("files", myFile);
                postDataParams.put("program", selectedProgram);
                postDataParams.put(
                        "section", selectedSection);
                postDataParams.put("academicyear", selectedAcademicyear);
                postDataParams.put("imagetitle", ImageTitle);
                postDataParams.put("instituteid", utils.getInstituteId());
                postDataParams.put("studentlist", utils.getPhotoGalleryStudentList().toString());
                postDataParams.put("uploadid", "0");
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
                    Log.e("check string:",in.readLine());
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
        }





        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.studentpersonalinformation, container, false);
    }
    private void uploadVideo() {
        class UploadVideo extends AsyncTask<Void, Void, String> {

            ProgressDialog uploading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                uploading = ProgressDialog.show(getActivity(), "Uploading File", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                uploading.dismiss();
//                textViewResponse.setText(Html.fromHtml("<b>Uploaded at <a href='" + s + "'>" + s + "</a></b>"));
//                textViewResponse.setMovementMethod(LinkMovementMethod.getInstance());
            }

            @Override
            protected String doInBackground(Void... params) {
                Upload u = new Upload();
                String msg = u.upLoad2Server(selectedPath);
                return msg;
            }
        }
        UploadVideo uv = new UploadVideo();
        uv.execute();
    }
//    private void selectImage(){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, IMG_REQUEST);
//    }
//    String imagefilename;
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.e("OnActivityResult()", "1");
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
//            Uri path = data.getData();
//            String uriString = path.toString();
//            Log.e("inside3:",uriString);
//            imagefilename = uriString.substring(uriString.lastIndexOf("/")+1);
//            Log.e("inside2:",imagefilename);
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
//                chosenImg.setImageBitmap(bitmap);
//                chosenImg.setVisibility(View.VISIBLE);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void uploadImage(){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String Response = jsonObject.getString("response");
//                            Toast.makeText(getActivity(), Response, Toast.LENGTH_LONG).show();
//                            chosenImg.setImageResource(0);
//                            chosenImg.setVisibility(View.GONE);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })
//        {
//          @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("encodedimage", imagetoString(bitmap));
//                params.put("uploaddate", "12/12/2017");
//                params.put("academicyear", "2017-2018");
//                params.put("program", "TODDLER PROGRAM");
//                params.put("section", "SECTION-A");
//                params.put("uploadedby", "KAMAL");
//                params.put("studentlist", "all");
//              Log.e("inside1:",imagefilename);
//                params.put("imagepath", imagefilename);
//                params.put("imagetitle", "welcome image");
//                return params;
//            }
//        };
//        MySingleton.getInstance(getActivity()).addToRequestQue(stringRequest);
//    }
//
//    private String imagetoString(Bitmap bitmap){
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        byte[] imgBytes = byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
//    }
    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
