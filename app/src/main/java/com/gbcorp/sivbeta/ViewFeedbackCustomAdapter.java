package com.gbcorp.sivbeta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Seetha on 06-Feb-18.
 */

public class ViewFeedbackCustomAdapter extends BaseAdapter {
    JSONArray feedbackJsonArray;
    Context context;
    private static LayoutInflater inflater=null;
    public ViewFeedbackCustomAdapter(Context viewfeedbackFragment, JSONArray jsonArr) {
        // TODO Auto-generated constructor stub
        feedbackJsonArray=jsonArr;
        context=viewfeedbackFragment;
//        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(viewfeedbackFragment);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return feedbackJsonArray.length();
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
        TextView appFeedback;
        RatingBar appRating;
        TextView schoolFeedback;
        RatingBar schoolRating;
        TextView feedbackDate;
        TextView registernumber;
        TextView name;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewFeedbackCustomAdapter.Holder holder=new ViewFeedbackCustomAdapter.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.viewfeedbackrow, null);
        holder.name=(TextView) rowView.findViewById(R.id.name);
        holder.feedbackDate=(TextView) rowView.findViewById(R.id.feedbackDate);
        holder.schoolFeedback=(TextView) rowView.findViewById(R.id.schoolFeedback);
        holder.schoolRating=(RatingBar) rowView.findViewById(R.id.schoolRating);
        holder.appFeedback=(TextView) rowView.findViewById(R.id.appFeedback);
        holder.appRating=(RatingBar) rowView.findViewById(R.id.appRating);
        try {
            JSONObject feedbackObj=feedbackJsonArray.getJSONObject(position);
            holder.name.setText(feedbackObj.getString("candidatename") +", "+ feedbackObj.getString("registernumber"));
            Utils utils = new Utils();
            holder.feedbackDate.setText(utils.convertToDateFormat(feedbackObj.getString("entrydate")));
            holder.schoolFeedback.setText(feedbackObj.getString("schoolfeedback"));
            holder.schoolRating.setRating(Float.parseFloat(feedbackObj.getString("schoolrating")));
            holder.appFeedback.setText(feedbackObj.getString("appfeedback"));
            holder.appRating.setRating(Float.parseFloat(feedbackObj.getString("apprating")));
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
