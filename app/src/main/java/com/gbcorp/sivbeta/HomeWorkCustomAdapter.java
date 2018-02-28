package com.gbcorp.sivbeta;

import android.content.Context;
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
 * Created by Seetha on 02-Jan-18.
 */

public class HomeWorkCustomAdapter extends BaseAdapter {

    JSONArray homeworkJsonArray;
    Context context;
    private static LayoutInflater inflater=null;
    public HomeWorkCustomAdapter(Context homeworkFragment, JSONArray jsonArr) {
        // TODO Auto-generated constructor stub
        homeworkJsonArray=jsonArr;
        context=homeworkFragment;
//        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(homeworkFragment);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return homeworkJsonArray.length();
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
        TextView subject;
        TextView homeworkcontent;
        TextView homeworkdate;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        Utils utils = new Utils();
        rowView = inflater.inflate(R.layout.homework_list, null);
        holder.subject=(TextView) rowView.findViewById(R.id.subjectname);
        holder.homeworkcontent=(TextView) rowView.findViewById(R.id.homeworkcontent);
        holder.homeworkdate=(TextView) rowView.findViewById(R.id.homeworkDateDay);
        try {
            JSONObject homeworkObj=homeworkJsonArray.getJSONObject(position);
            holder.subject.setText(homeworkObj.getString("subjectcategory"));
            holder.homeworkcontent.setText(homeworkObj.getString("homeworkcontent"));
            holder.homeworkdate.setText(utils.convertToDateFormat(homeworkObj.getString("entrydate")));
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
