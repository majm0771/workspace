package com.maze.igame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AppListener implements ApplicationListener
{
    // ªÊÕº”√
    private SpriteBatch               mBatch   = null;
    private BitmapFont                mFont    = null;
    private ParticleEffect            mPE      = null;
    private ParticleEffectPool        mPool    = null;
    private ArrayList<ParticleEffect> mList    = null;

    @Override
    public void create()
    {
        if (mBatch == null)
        {
            mBatch = new SpriteBatch();
        }

        if (mFont == null)
        {
            mFont = new BitmapFont();
        }
        
        mPE = new ParticleEffect();
        mPE.load(Gdx.files.internal("test.p"), Gdx.files.internal(""));
        
        mPool = new ParticleEffectPool(mPE, 5, 10);
        mList = new ArrayList<ParticleEffect>();
    }

    @Override
    public void dispose()
    {
        mFont.dispose();
        mBatch.dispose();
        
        mPE.dispose();
        mPool.clear();
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void render()
    {
        // clear screen
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);

        mBatch.begin();
        mFont.draw(mBatch, "Hello game", Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);
        
        ParticleEffect mPE_Temp = null;
        if (Gdx.input.isTouched())
        {
            mPE_Temp = mPool.obtain();
            mPE_Temp.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            mList.add(mPE_Temp);
        }
        
        for (int i = 0; i < mList.size(); i++)
        {
            mList.get(i).draw(mBatch, Gdx.graphics.getDeltaTime());
        }
        
        mBatch.end();
        
        for (int i = 0; i < mList.size(); i++)
        {
            if (mList.get(i).isComplete())
            {
                mList.remove(i);
            }
        }
    }

    @Override
    public void resize(int arg0, int arg1)
    {

    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub

    }

}
