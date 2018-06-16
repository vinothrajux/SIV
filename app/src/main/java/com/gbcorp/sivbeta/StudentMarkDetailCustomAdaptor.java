package com.gbcorp.sivbeta;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Seetha on 09-Jun-18.
 */

public class StudentMarkDetailCustomAdaptor extends BaseAdapter {
    JSONArray studentMarksArray;
    Context context;
    private static LayoutInflater inflater=null;
//    ListView studentmarkslistview;
    public StudentMarkDetailCustomAdaptor(Context studentMarkDetailFragment, JSONArray jsonArr) {
        // TODO Auto-generated constructor stub
        studentMarksArray=jsonArr;
        context=studentMarkDetailFragment;
//        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(studentMarkDetailFragment);
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
        TextView testtype;
        TextView maxmarks;
        TextView minmarks;
        TextView percentage;
        TextView totalmark;
        ListView studentmarkslistview;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView = convertView;
//        View marklistView = convertView;
        Utils utils = new Utils();
        rowView = inflater.inflate(R.layout.student_markdetail_list, null);
        holder.testtype=(TextView) rowView.findViewById(R.id.testtype);
        holder.maxmarks=(TextView) rowView.findViewById(R.id.maxmarks);
        holder.minmarks=(TextView) rowView.findViewById(R.id.minmarks);
        holder.percentage=(TextView) rowView.findViewById(R.id.percentage);
        holder.totalmark=(TextView) rowView.findViewById(R.id.totalmark);
        try {
            JSONObject studentMarksObj=studentMarksArray.getJSONObject(position);
            String studentMarkDetailInner = studentMarksObj.getString("studentmarks");
            JSONObject studentMarksInnerObj = new JSONObject(studentMarkDetailInner);
            String studentmarkslist = studentMarksInnerObj.getString("subjectmarks");
            JSONArray studentmarksArr = new JSONArray(studentmarkslist);

            holder.testtype.setText(studentMarksObj.getString("testtype"));
            holder.maxmarks.setText(studentMarksObj.getString("maxmarks"));
            holder.minmarks.setText(studentMarksObj.getString("minmarks"));
            holder.percentage.setText(studentMarksInnerObj.getString("percentage"));
            holder.totalmark.setText(studentMarksInnerObj.getString("totalmark"));
            holder.studentmarkslistview =(ListView) rowView.findViewById(R.id.studentmarkslist);

            StudentMarksCustomAdaptor studentMarksCustomAdaptor = new StudentMarksCustomAdaptor(context, studentmarksArr);
            holder.studentmarkslistview.setAdapter(studentMarksCustomAdaptor);

            utils.setListViewHeightBasedOnChildren(holder.studentmarkslistview);
//            Activity activity = (Activity) context;
//            studentmarkslistview=(ListView) activity.findViewById(R.id.studentmarkslist);
//            studentmarkslistview.setAdapter(new StudentMarksCustomAdaptor(activity,studentmarksArr));
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
        return rowView;
    }
}
