package com.example.vinothjoshua.siv;

import android.util.Log;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;

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
        return "192.168.43.38:8080";
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
}
