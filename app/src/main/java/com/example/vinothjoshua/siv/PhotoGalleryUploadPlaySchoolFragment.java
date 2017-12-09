package com.example.vinothjoshua.siv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Seetha on 09-Dec-17.
 */

public class PhotoGalleryUploadPlaySchoolFragment extends Fragment{
    private Button uploadBn, ChooseBn;
    private ImageView chosenImg;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
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


        return view;

    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("OnActivityResult()", "1");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                chosenImg.setImageBitmap(bitmap);
                chosenImg.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
