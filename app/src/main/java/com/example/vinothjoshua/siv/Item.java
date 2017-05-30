package com.example.vinothjoshua.siv;

import android.graphics.drawable.Drawable;

/**
 * Created by Vinoth Joshua on 03-May-17.
 */

public class Item {
    String title;
    String FragmentName;
    int drawableId;

    // Empty Constructor
    public Item()
    {

    }

    // Constructor
    public Item(String title, int drawableId, String FragmentName)
    {
        super();
        this.title = title;
        this.drawableId = drawableId;
        this.FragmentName = FragmentName;
    }

    // Getter and Setter Method
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getImage()
    {
        return drawableId;
    }

    public void setImage(int drawableId)
    {
        this.drawableId = drawableId;
    }

    public String getFragmentName()
    {
        return FragmentName;
    }

    public void setFragmentName(String FragmentName)
    {
        this.FragmentName = FragmentName;
    }
}
