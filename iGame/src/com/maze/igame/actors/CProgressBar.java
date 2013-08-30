package com.maze.igame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.maze.igame.util.TraceLog;

public class CProgressBar extends Actor implements Disposable
{
    private Texture mPlatForm = null;
    private Texture mBar = null;
    
    private float   fPosition = 0;

    private float   fWeightX  = 0;
    private float   fWeightY  = 0;

    public CProgressBar(int x,int y) 
    {
        super();
        
        TraceLog.Print_D("CProgressBar");
        
        setX(x);
        setY(y);
        
        mPlatForm = new Texture(Gdx.files.internal("black.png"));
        mBar = new Texture(Gdx.files.internal("green.png"));
        
        fWeightX = Gdx.graphics.getWidth() / 800f;
        fWeightY = Gdx.graphics.getHeight() / 400f;

        TraceLog.Print_D("w: " + Gdx.graphics.getWidth());
        TraceLog.Print_D("h: " + Gdx.graphics.getHeight());
        
        TraceLog.Print_D("platform w: " + mPlatForm.getWidth());
        TraceLog.Print_D("platform h: " + mPlatForm.getHeight());
        
        TraceLog.Print_D("bar w: " + mBar.getWidth());
        TraceLog.Print_D("bar h: " + mBar.getHeight());

        TraceLog.Print_D("weight w: " + fWeightX);
        TraceLog.Print_D("weight h: " + fWeightY);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
        batch.draw(mPlatForm, (Gdx.graphics.getWidth() - mBar.getWidth()
                * fWeightX) / 2, 0, mPlatForm.getWidth() * fWeightX,
                mPlatForm.getHeight() * fWeightY);
        batch.draw(mBar,
                (Gdx.graphics.getWidth() - mBar.getWidth() * fWeightX) / 2, 0,
                mBar.getWidth() * fPosition / 100f * fWeightX, mBar.getHeight()
                        * fWeightY);
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable)
    {
        return null;
    }

    public void setProgress(float progress)
    {
        this.fPosition = progress;
    }

    @Override
    public void dispose()
    {
        mPlatForm.dispose();
        mBar.dispose();
    }

    public float GetProgress()
    {
        return fPosition;
    }
}
