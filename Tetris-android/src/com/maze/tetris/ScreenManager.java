package com.maze.tetris;

import android.os.Handler;
import android.os.Message;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.maze.tetris.screens.MainScreen;
import com.maze.tetris.screens.SettingScreen;
import com.maze.tetris.utils.TraceLog;
import com.maze.tetris.utils.CommonDef.MessageType;

public class ScreenManager extends Game
{
    private SettingScreen mSettingScreen = null;
    private MainScreen mMainScreen = null;
    
    private Screen mCurrentScreen = null;
    private UIHandler mHandler = new UIHandler();
    

    public ScreenManager ()
    {  
    }
    
    public void ChangeView(Screen sc)
    {
        TraceLog.Print_D("Change screen: " + sc.toString());
        mCurrentScreen = sc;
        setScreen(mCurrentScreen);
    }
    
    @Override
    public void create()
    {
        TraceLog.Print_D("ScreenManager create");
        mSettingScreen = new SettingScreen(mHandler);
        mMainScreen = new MainScreen(mHandler);      
        
        ChangeView(mSettingScreen);
    }    
    
    private class UIHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            int type = msg.what;
            TraceLog.Print_D("MSG: " + type);
            
            switch (type)
            {
            case MessageType.MSG_START_GAME:
                TraceLog.Print_D("Get message: start game");
                ChangeView(mMainScreen);
                break;
            default:
                TraceLog.Print_D("Get message: unkown");
                break;
            }
        }
    }
}
