package com.gbcorp.sivbeta;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by GBCorp on 19/08/2017.
 */

public class FirstAidEntryAdaptor extends ArrayAdapter<FirstAidDetailModel>{
    Context context;
    int layoutResourceId;
    ArrayList<FirstAidDetailModel> firstAidModel;
    FirstAidDetailModel data[] = null;

    public FirstAidEntryAdaptor(Context context, int layoutResourceId, ArrayList<FirstAidDetailModel> firstAidModel) {
        super(context, layoutResourceId, firstAidModel);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.firstAidModel = firstAidModel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FirstAidHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new FirstAidHolder();
            holder.currentdateTxt = (TextView)row.findViewById(R.id.firstaiddate);
            holder.reasonforfirstaidTxt = (TextView)row.findViewById(R.id.reasonforfirstaid);
            holder.firstaiddetailsTxt = (TextView)row.findViewById(R.id.firstaiddetails);
            holder.hospitalnameTxt = (TextView)row.findViewById(R.id.hospitalname);
            holder.hospitalfeesTxt = (TextView)row.findViewById(R.id.hospitalfees);
            holder.remarksTxt = (TextView)row.findViewById(R.id.remarks);

            row.setTag(holder);
        }
        else
        {
            holder = (FirstAidHolder)row.getTag();
        }

        FirstAidDetailModel firstaiddetail = firstAidModel.get(position);
        holder.currentdateTxt.setText(firstaiddetail.getCurrentDate());
        holder.reasonforfirstaidTxt.setText(firstaiddetail.getFirstAidReason());
        holder.firstaiddetailsTxt.setText(firstaiddetail.getFirstAidDetail());
        holder.hospitalnameTxt.setText(firstaiddetail.getHospitalName());
        holder.hospitalfeesTxt.setText(firstaiddetail.getHospitalFees());
        holder.remarksTxt.setText(firstaiddetail.getRemarks());

        return row;
    }

    static class FirstAidHolder
    {
        TextView currentdateTxt, reasonforfirstaidTxt, firstaiddetailsTxt, hospitalnameTxt, hospitalfeesTxt, remarksTxt;
    }
}
