package com.gbcorp.sivbeta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Seetha on 17-Oct-18.
 */

public class PhotoGalleryStudentListAdaptor extends BaseAdapter {
    JSONArray studentlistArray = new JSONArray();
    Context context;
    Utils utils = new Utils();
    private static LayoutInflater inflater=null;
    public PhotoGalleryStudentListAdaptor(Context photoGalleryUploadFragment, JSONArray jsonArr) {
        // TODO Auto-generated constructor stub
        studentlistArray=jsonArr;
        context=photoGalleryUploadFragment;
//        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(photoGalleryUploadFragment);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return studentlistArray.length();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView studentName;
        TextView registerNumber;
        CheckBox studentPermission;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        PhotoGalleryStudentListAdaptor.Holder holder=new PhotoGalleryStudentListAdaptor.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.photo_gallery_student_list_row, null);
        holder.studentName=(TextView) rowView.findViewById(R.id.name);
        holder.registerNumber=(TextView) rowView.findViewById(R.id.registerNumber);
        holder.studentPermission=(CheckBox) rowView.findViewById(R.id.studentPermission);
        try {
            JSONObject studentListObj=studentlistArray.getJSONObject(position);
            holder.studentName.setText(studentListObj.getString("candidatename"));
            holder.registerNumber.setText(studentListObj.getString("registernumber"));
            holder.studentPermission.setChecked(true);
            holder.studentPermission.setTag(position);
            holder.studentPermission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = Integer.parseInt(buttonView.getTag().toString());
                    if(isChecked){
                        utils.updatePhotoAccessForStudent(position, 1);
                        Log.e("check status", "=====================================");
                        Log.e("check status", "true"+position);
                        Log.e("check status", "=====================================");
                    }else {
                        utils.updatePhotoAccessForStudent(position, 0);
                        Log.e("check status", "=====================================");
                        Log.e("check status", "false"+position);
                        Log.e("check status", "=====================================");
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                try {
//                    studentlistArray.getJSONObject(position).put("studentphotoaccess")
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                //Toast.makeText(context, "You Clicked "+position, Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
