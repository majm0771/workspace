package com.maze.tetris.screens;

import android.os.Handler;
import android.os.Message;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.maze.tetris.utils.TraceLog;
import com.maze.tetris.utils.CommonDef.MessageType;

public class SettingScreen extends BaseView
{
    private Stage mStage = null;
    private SpriteBatch mBatch = null;
    private ImageButton mButton = null;
    
    private boolean bStart = false;

    public SettingScreen(Handler h)
    {
        super();
        setHandler(h);

        mBatch = new SpriteBatch();
        
        Texture tNormal = new Texture(Gdx.files.internal("ui/button_start_normal.png"));
        Texture tPress = new Texture(Gdx.files.internal("ui/button_start_press.png"));
        
        TextureRegion trNormal = new TextureRegion(tNormal);
        TextureRegion trPress = new TextureRegion(tPress);
        
        TextureRegionDrawable trdNormal = new TextureRegionDrawable(trNormal);
        TextureRegionDrawable trdPress = new TextureRegionDrawable(trPress);
        
        mButton = new ImageButton(trdNormal, trdPress);
        mButton.setPosition((Gdx.graphics.getWidth() - tNormal.getWidth()) / 2, Gdx.graphics.getHeight() / 2);
        
        mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
    }
    
    @Override
    public void dispose()
    {
        mBatch.dispose();
        mStage.dispose();
    }

    @Override
    public void hide()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float arg0)
    {
//        TraceLog.Print_D("setting screen render");
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        mStage.act();
        mStage.draw();
        
        if (!bStart && mButton.isPressed())
        {
            StartView();
        }
        
        mBatch.begin();
//        mSprite.draw(mBatch);
        mBatch.end();
    }

    @Override
    public void resize(int arg0, int arg1)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void show()
    {
        TraceLog.Print_D("setting screen show");
        
        Gdx.input.setInputProcessor(mStage);
        mStage.addActor(mButton);
//        
//        mTexture = new Texture(Gdx.files.internal("data/libgdx.png"));
//        mTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//        
//        TextureRegion region = new TextureRegion(mTexture, 0, 0, 512, 275);
//        
//        mSprite = new Sprite(region);
//        mSprite.setSize(0.9f, 0.9f * mSprite.getHeight() / mSprite.getWidth());
//        mSprite.setOrigin(mSprite.getWidth()/2, mSprite.getHeight()/2);
//        mSprite.setPosition(-mSprite.getWidth()/2, -mSprite.getHeight()/2);
    }
    
    private void StartView()
    {
        TraceLog.Print_D("setting StartView");
        
        if (mUIHandler != null)
        {
            Message m = mUIHandler.obtainMessage();
            m.what = MessageType.MSG_START_GAME;
            m.sendToTarget();
        }
        
    }

    @Override
    protected void InitControl()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void SetView()
    {
        // TODO Auto-generated method stub
        
    }
}
