package com.maze.tetris;

import com.maze.tetris.utils.TraceLog;

import android.app.Application;

public class GameApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        TraceLog.Init(getApplicationContext());
        TraceLog.Print_D("GameApplication onCreate");
    }
}
