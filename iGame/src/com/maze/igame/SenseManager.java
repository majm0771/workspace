package com.maze.igame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class SenseManager extends Game
{
    private Screen mScreen = null;
    
    public SenseManager(Screen sc)
    {
        mScreen = sc;
    }

    @Override
    public void create()
    {
        setScreen(mScreen);
    }

}
