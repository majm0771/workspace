package com.maze.igame.actors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.maze.igame.util.TraceLog;

public class CDog extends Actor implements Disposable
{
    private AssetManager mAssetManager = null;

    private Animation mAnimation = null;
    private TextureRegion mCurrentFrame = null;
    private float fStateTime;
    
    private boolean bInit = false;
    
    private ArrayList<Texture> mTextureList = new ArrayList<Texture>(0);
//    private ArrayList<TextureRegion> mRegionList = new ArrayList<TextureRegion>(0); 
    private TextureRegion[] mRegions = null;
    
    public CDog(AssetManager mgr)
    {
        TraceLog.Print_D("CDog");
        mAssetManager = mgr;
    }
    
    public boolean IsInit()
    {
        return bInit;
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha)
    {
        fStateTime += Gdx.graphics.getDeltaTime();
        mCurrentFrame = mAnimation.getKeyFrame(fStateTime, true);
        batch.draw(mCurrentFrame, 0, 0, 128, 128);
    }
    
    public void Init()
    {        
        TraceLog.Print_D("Init");
        for (int i = 1; i < 30; i++)
        {
            Texture texture = mAssetManager.get("animal/" + i + ".png", Texture.class);
            mTextureList.add(texture);
            
//            TextureRegion region = new TextureRegion(texture);
//            mRegionList.add(region);
        }
        
        mRegions = new TextureRegion[mTextureList.size()];
        
        for (int i = 0; i < mTextureList.size(); i++)
        {
            mRegions[i] = new TextureRegion(mTextureList.get(i));
        }
        
        mAnimation = new Animation(0.06f, mRegions);
        
        bInit = true;
    }
    
    @Override
    public void dispose()
    {
        TraceLog.Print_D("dispose");
        for (int i = 0; i < mTextureList.size(); i++)
        {
            mTextureList.get(i).dispose();
        }
    }

}
