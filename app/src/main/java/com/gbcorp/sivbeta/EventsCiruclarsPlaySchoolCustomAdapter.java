package com.gbcorp.sivbeta;

import android.content.Context;
import android.util.Log;
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
 * Created by Seetha on 06-Jan-18.
 */

public class EventsCiruclarsPlaySchoolCustomAdapter extends BaseAdapter {

    JSONArray homeworkJsonArray;
    Context context;
    private static LayoutInflater inflater=null;
    public EventsCiruclarsPlaySchoolCustomAdapter(Context homeworkFragment, JSONArray jsonArr) {
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
        TextView eventtitle;
        TextView eventdateday;
        TextView eventmessage;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        EventsCiruclarsPlaySchoolCustomAdapter.Holder holder=new EventsCiruclarsPlaySchoolCustomAdapter.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.eventscircularsplayschool_list, null);
        holder.eventtitle=(TextView) rowView.findViewById(R.id.eventtitle);
        holder.eventdateday=(TextView) rowView.findViewById(R.id.eventDateDay);
        holder.eventmessage=(TextView) rowView.findViewById(R.id.eventmessage);
        try {
            JSONObject homeworkObj=homeworkJsonArray.getJSONObject(position);
            holder.eventtitle.setText(homeworkObj.getString("eventtitle"));
            Utils utils = new Utils();
            holder.eventdateday.setText(utils.convertToDateFormat(homeworkObj.getString("eventdate"))+", "+utils.getDayOfDate(homeworkObj.getString("eventdate")));
            holder.eventmessage.setText(homeworkObj.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(context, "You Clicked "+position, Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}
