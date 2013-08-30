package com.maze.igame;

import com.maze.igame.util.TraceLog;

import android.app.Application;

public class GameApplication extends Application
{    
    @Override
    public void onCreate()
    {
        super.onCreate();

        TraceLog.Init(getApplicationContext());
    }
}
