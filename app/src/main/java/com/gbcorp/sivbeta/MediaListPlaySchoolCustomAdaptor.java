package com.gbcorp.sivbeta;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Seetha on 22-Feb-18.
 */

public class MediaListPlaySchoolCustomAdaptor extends BaseAdapter {
    JSONArray mediaJsonArray;
    Context context;
    private static LayoutInflater inflater=null;
    public MediaListPlaySchoolCustomAdaptor(Context mediaFragment, JSONArray jsonArr) {
        // TODO Auto-generated constructor stub
        mediaJsonArray=jsonArr;
        context=mediaFragment;
//        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(mediaFragment);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mediaJsonArray.length();
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
        TextView mediatitle;
        TextView uploaddate;
        ImageView galleryimage;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        MediaListPlaySchoolCustomAdaptor.Holder holder=new MediaListPlaySchoolCustomAdaptor.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.mediaplayschool_list, null);
        holder.mediatitle=(TextView) rowView.findViewById(R.id.mediatitle);
        holder.uploaddate=(TextView) rowView.findViewById(R.id.uploaddate);
        holder.galleryimage=(ImageView) rowView.findViewById(R.id.galleryimage);
        try {
            JSONObject mediaObj=mediaJsonArray.getJSONObject(position);
            holder.mediatitle.setText(mediaObj.getString("imagetitle"));
            Utils utils = new Utils();
            holder.uploaddate.setText(utils.convertToDateFormat(mediaObj.getString("uploaddate")));
            URL url = null;
            try {
                url = new URL(mediaObj.getString("imagepath"));
                Glide.with(context).load(url).into(holder.galleryimage);
//                Bitmap bmp = null;
//                try {
//                    Log.e("ur:",url.toString());
//                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
//            Log.e("imageurl",mediaObj.getString("imagepath"));
//            Log.e("imageurl",url.toString());
//            holder.galleryimage.setImageBitmap(bmp);
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
