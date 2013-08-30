package com.maze.tetris.screens;

import android.os.Handler;

import com.badlogic.gdx.Screen;

public abstract class BaseView implements Screen
{
    protected Handler mUIHandler = null;
    
    public BaseView()
    {
    }
    
    public void setHandler (Handler h)
    {
        mUIHandler = h;
    }
    
    protected void Init()
    {
        InitControl();
        SetView();
    }

    //init view controls
    protected abstract void InitControl();
    //set view controls
    protected abstract void SetView();
    
    @Override
    public void dispose(){};

    @Override
    public void hide(){};

    @Override
    public void pause(){};

    @Override
    public void render(float arg0){};

    @Override
    public void resize(int arg0, int arg1){};

    @Override
    public void resume(){};

    @Override
    public void show(){};
}
