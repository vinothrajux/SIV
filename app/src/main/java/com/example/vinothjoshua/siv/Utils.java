package com.example.vinothjoshua.siv;

import android.text.format.DateFormat;
import android.util.Log;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import static com.example.vinothjoshua.siv.R.id.time;

/**
 * Created by VinothJoshua on 8/3/2017.
 */

public class Utils {
    public static String userId;

    public Utils()
    {
    }

    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userid)
    {
        userId = userid;
    }

    public String getApiHost(){
        return "192.168.43.195:8080";
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    public String convertToDateFormat(String getdate){
        long l = Long.parseLong(getdate);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(l);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }
}
