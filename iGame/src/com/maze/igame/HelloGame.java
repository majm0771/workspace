package com.maze.igame;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class HelloGame extends AndroidApplication
{
    private AppListener mAppListener = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        if (mAppListener == null)
        {
            mAppListener = new AppListener();
        }
        
        initialize(mAppListener, false);
    }
}
