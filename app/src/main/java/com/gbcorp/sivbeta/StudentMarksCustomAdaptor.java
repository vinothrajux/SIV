package com.gbcorp.sivbeta;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Seetha on 09-Jun-18.
 */

public class StudentMarksCustomAdaptor extends BaseAdapter {
    JSONArray studentMarksArray;
    Context context;
    private static LayoutInflater inflater=null;
    int passmark;
    public StudentMarksCustomAdaptor(Context studentMarkFragment, JSONArray jsonArr, int passMark) {
        // TODO Auto-generated constructor stub
        studentMarksArray=jsonArr;
        context=studentMarkFragment;
        passmark= passMark;
//        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return studentMarksArray.length();
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
        TextView subjectname;
        TextView mark;
        TextView subjectresult;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView = convertView;
        Utils utils = new Utils();
        rowView = inflater.inflate(R.layout.student_marks_list, parent, false);
        holder.subjectname=(TextView) rowView.findViewById(R.id.subjectname);
        holder.mark=(TextView) rowView.findViewById(R.id.mark);
        holder.subjectresult= (TextView) rowView.findViewById(R.id.subjectresult);
        String SubjectResult = "";
//        ViewGroup.LayoutParams params = rowView.getLayoutParams();
//        params.height = 300;
        try {
            JSONObject studentMarksObj=studentMarksArray.getJSONObject(position);


            holder.subjectname.setText(studentMarksObj.getString("subjectname"));
            holder.mark.setText(studentMarksObj.getString("mark"));
            try {
                int markint = Integer.parseInt(studentMarksObj.getString("mark"));
                if(markint < passmark){
                    holder.subjectresult.setText("Fail");
                    holder.subjectresult.setTextColor(Color.parseColor("#ff0000"));
                }else {
                    holder.subjectresult.setText("Pass");
                }
            } catch(NumberFormatException nfe) {
                // Handle parse error.
            }

//            holder.minmarks.setText(utils.convertToDateFormat(homeworkObj.getString("entrydate")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+position, Toast.LENGTH_LONG).show();
            }
        });

//        rowView.setLayoutParams(params);
        return rowView;
    }
}
