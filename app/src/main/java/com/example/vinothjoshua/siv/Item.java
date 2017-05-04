package com.example.vinothjoshua.siv;

import android.graphics.drawable.Drawable;

/**
 * Created by Vinoth Joshua on 03-May-17.
 */

public class Item {
    String title;
    String nextActivityClassName;
    int drawableId;

    // Empty Constructor
    public Item()
    {

    }

    // Constructor
    public Item(String title, int drawableId, String nextActivityClassName)
    {
        super();
        this.title = title;
        this.drawableId = drawableId;
        this.nextActivityClassName = nextActivityClassName;
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

    public String getNextActivityClassName()
    {
        return nextActivityClassName;
    }

    public void setNextActivityClassName(String nextActivityClassName)
    {
        this.nextActivityClassName = nextActivityClassName;
    }
}
