package com.maze.igame;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maze.igame.actors.CDog;
import com.maze.igame.actors.CProgressBar;
import com.maze.igame.util.TraceLog;

public class AppListener implements Screen
{
    // ��ͼ��
    private SpriteBatch               mBatch        = null;

    // ��ͼ�ķ�ʽ��ʾ����
    private BitmapFont                mFont         = null;

    // ����Ч������
    private ParticleEffect            mPE           = null;
    private ParticleEffectPool        mPool         = null;
    private ArrayList<ParticleEffect> mList         = null;

    // ����������
    private CProgressBar              mProgress     = null;
    private Stage                     mStage        = null;

    // ��Դ���ض���
    private AssetManager              mAssetManager = null;
    private CDog                      mDog          = null;

    public void create()
    {
    }

    @Override
    public void dispose()
    {
        TraceLog.Print_D("dispose");
        mFont.dispose();
        mBatch.dispose();

        mPE.dispose();
        mPool.clear();
        
        mDog.dispose();
        mAssetManager.clear();
        mAssetManager.dispose();
    }

    @Override
    public void pause()
    {
        TraceLog.Print_D("pause");

    }

    @Override
    public void render(float arg0)
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
            mPE_Temp.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()
                    - Gdx.input.getY());
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

        mStage.act(Gdx.graphics.getDeltaTime());
        mStage.draw();
        
//      if (mProgress.GetProgress() < 100)
//      {
//          mProgress.setProgress(mProgress.GetProgress() + 0.5f);
//      }
//      else
//      {
//          mProgress.setProgress(0f);
//      }

        //��Դ����ʱ���½�����
        if (!mAssetManager.update())
        {
            mProgress.setProgress(mAssetManager.getProgress()*100);
        }
        
      //���������֮ǰû�г�ʼ����AnimalActor�������ִ�����Ļʱ��ʼ��CDog,��������������̨���Ƴ���������CDog����
        if (!mDog.IsInit() && mAssetManager.update() && Gdx.input.isTouched())
        {
            TraceLog.Print_D("CDog prepare to do init");
            mProgress.remove();
            mDog.Init();
            mStage.addActor(mDog);
        }

      //������һ����ǣ�����δ���أ�Queued����ɵ���Դ����������ɵ���Դ��������Loaded��
        if(!mAssetManager.update())
        {
            TraceLog.Print_D("QueuedAssets:" + mAssetManager.getQueuedAssets());
            TraceLog.Print_D("LoadedAssets:" + mAssetManager.getLoadedAssets());
            TraceLog.Print_D("Progress:" + mAssetManager.getProgress());
        }
    }

    @Override
    public void resize(int arg0, int arg1)
    {
        TraceLog.Print_D("resize");

    }

    @Override
    public void resume()
    {
        TraceLog.Print_D("resume");

    }

    @Override
    public void hide()
    {        
    }

    @Override
    public void show()
    {
        TraceLog.Print_D("onCreate");
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

        mProgress = new CProgressBar(0, 0);

        mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                true);
        // mStage = new Stage();
        // mStage.setViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
        // true);
        mStage.addActor(mProgress);
        
        mAssetManager = new AssetManager();
        mDog = new CDog(mAssetManager);
        
        for(int i=1; i < 30; i++)
        {
            mAssetManager.load("animal/"+i+".png", Texture.class);
        }
    }

}
