package com.maze.tetris;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.maze.tetris.utils.TraceLog;

public class iTetris extends AndroidApplication
{
    private ScreenManager mScreenMgr = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TraceLog.Print_D("onCreate");

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        if (mScreenMgr == null)
        {
            mScreenMgr = new ScreenManager();
        }
        
        initialize(mScreenMgr, cfg);
    }
}
