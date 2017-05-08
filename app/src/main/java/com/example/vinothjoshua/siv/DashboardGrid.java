package com.example.vinothjoshua.siv;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Vinoth Joshua on 18-Apr-17.
 */

public class DashboardGrid extends ArrayAdapter<Item> {
    Context mContext;
    //private final String[] dashboardOpt;
    String[] dashboardColors;
    ArrayList<Item> data = new ArrayList<Item>();
    int layoutResourceId;
    //private final int[] Imageid;

    public DashboardGrid(Context c, int layoutResourceId, ArrayList<Item> data, String[] dashboardColors) {
        super(c,layoutResourceId,data);
        this.mContext = c;
//        this.Imageid = Imageid;
//        this.dashboardOpt = dashboardOpt;
        this.data =data;
        this.dashboardColors = dashboardColors;
    }

//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return dashboardColors.length;
//    }

//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return null;
//    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int recursivePosition=0;
        View itemView = convertView;
        ViewHolder holder = null;

        if (itemView == null)
        {
            final LayoutInflater layoutInflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(R.layout.gridview_dashboardtool_single, parent, false);

            holder = new ViewHolder();
            holder.imgItem = (ImageView) itemView.findViewById(R.id.grid_image);
            holder.txtItem = (TextView) itemView.findViewById(R.id.grid_text);
            holder.dashSingleLinear = (RelativeLayout) itemView.findViewById(R.id.dash_grid_single);
            itemView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) itemView.getTag();
        }

        Item item = getItem(position);
        holder.imgItem.setImageResource(item.getImage());
        holder.txtItem.setText(item.getTitle());
        recursivePosition = position%6;
        holder.dashSingleLinear.setBackgroundColor(Color.parseColor(dashboardColors[recursivePosition]));
        return itemView;
    }

    static class ViewHolder
    {
        ImageView imgItem;
        TextView txtItem;
        RelativeLayout dashSingleLinear;
    }
//        // TODO Auto-generated method stub
//        int recursivePosition=0;
////        View grid;
//        View itemView = convertView;
//        LayoutInflater inflater = (LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//
//        /// test
//
//           // Item item = new Item();
//
//
//        ////
//        if (convertView == null) {
//
////            grid = new View(mContext);
//
//            itemView = inflater.inflate(R.layout.gridview_dashboardtool_single, null);
//            //data.
//            RelativeLayout dashSingleLinear = (RelativeLayout) itemView.findViewById(R.id.dash_grid_single);
//            //ImageView dashInnerLinear = (ImageView) grid.findViewById(R.id.dash_grid_inner);
//            TextView textView = (TextView) itemView.findViewById(R.id.grid_text);
//            ImageView imageView = (ImageView)itemView.findViewById(R.id.grid_image);
//
//            /// test
//
//            Item item = getItem(position);
//
//            //Item item = new Item();
//            imageView.setImageResource(item.getImage());
//            textView.setText(item.getTitle());
//            //imageView.setImageDrawable(mContext.getResources().getDrawable(item.getImage(), null));
//
//            ////
//
//
//            //textView.setText(dashboardOpt[position]);
//            //imageView.setImageResource(Imageid[position]);
////            if(position%2==0){
////                dashInnerLinear.setImageResource(R.drawable.starsleft);
////            }else{
////                dashInnerLinear.setImageResource(R.drawable.starsright);
////            }
//            recursivePosition = position%6;
//            Log.i(TAG,"test:"+recursivePosition);
//            dashSingleLinear.setBackgroundColor(Color.parseColor(dashboardColors[recursivePosition]));
//            //dashSingleLinear.setBackgroundColor(Color.parseColor("#2DA3AD"));
//        } else {
//            itemView = (View) convertView;
//        }
//
//        return itemView;
//    }
}

