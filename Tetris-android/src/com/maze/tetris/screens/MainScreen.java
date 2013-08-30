package com.maze.tetris.screens;

import android.os.Handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.maze.tetris.component.Square;
import com.maze.tetris.component.SquareDirection;
import com.maze.tetris.component.SquareType;
import com.maze.tetris.utils.TraceLog;

public class MainScreen extends BaseView
{
    private int mWinWidth = 0;
    private int mWinHeight = 0;
    
    private int nBlockLen = 24;

    //水平12块方格
    private int mGameWidth = nBlockLen * 12;
    //垂直20块方格
    private int mGameHeight = nBlockLen * 20;
    
    private boolean bIsLanding = false;
    private int[][] mGameMatrix = new int[20][12];
    private int mBaseLine = 0;
    
    private int nCurrentX = 0;
    private int nCurrentY = 0;
    private Square mCurrentSquare = null;
    
	private SpriteBatch mBatch = null;
	private Texture mBGTexture = null;
	private Sprite mBGSprite = null;

	private Image mButtonCenter = null;
    private ImageButton mButtonUp = null;
    private ImageButton mButtonLeft = null;
    private ImageButton mButtonDown = null;
    private ImageButton mButtonRight = null;
    
    private Stage mBTNStage = null;
    
    private InputListener mBTNClickListener = new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y,
                int pointer, int button)
        {
            TraceLog.Print_D("view onClick");
            int n = SquareDirection.SD_UNKOWN;
            
            if (event.getListenerActor() == mButtonUp)
            {
                TraceLog.Print_D("up onClick");
                n = SquareDirection.SD_UP;
            }
            else if (event.getListenerActor() == mButtonLeft)
            {
                TraceLog.Print_D("left onClick");
                n = SquareDirection.SD_LEFT;                
            }
            else if (event.getListenerActor() == mButtonDown)
            {
                TraceLog.Print_D("down onClick");
                n = SquareDirection.SD_DOWN;                
            }
            else if (event.getListenerActor() == mButtonRight)
            {
                TraceLog.Print_D("right onClick");
                n = SquareDirection.SD_RIGHT;                
            }
            else
            {
                TraceLog.Print_E("unkown view was clicked");
            }
            
            DoSquareAction(n);
            return true;
        }
    };

	public MainScreen(Handler h)
	{
        super();

        setHandler(h);
        
        mWinWidth = Gdx.graphics.getWidth();
        mWinHeight = Gdx.graphics.getHeight();
        
        if (mBatch == null)
        {
            mBatch = new SpriteBatch();
        }
        
        Init();
	}
	
	@Override
	protected void InitControl()
	{
	    //up button
        Texture tUpNormal = new Texture(Gdx.files.internal("ui/up_normal.png"));
        Texture tUpPress = new Texture(Gdx.files.internal("ui/up_press.png"));
        
        TextureRegion trUpNormal = new TextureRegion(tUpNormal);
        TextureRegion trUpPress = new TextureRegion(tUpPress);
        
        TextureRegionDrawable trdUpNormal = new TextureRegionDrawable(trUpNormal);
        TextureRegionDrawable trdUpPress = new TextureRegionDrawable(trUpPress);
        mButtonUp = new ImageButton(trdUpNormal, trdUpPress);
        
        //left button        
        Texture tLeftNormal = new Texture(Gdx.files.internal("ui/left_normal.png"));
        Texture tLeftPress = new Texture(Gdx.files.internal("ui/left_press.png"));

        TextureRegion trLeftNormal = new TextureRegion(tLeftNormal);
        TextureRegion trLeftPress = new TextureRegion(tLeftPress);

        TextureRegionDrawable trdLeftNormal = new TextureRegionDrawable(trLeftNormal);
        TextureRegionDrawable trdLeftPress = new TextureRegionDrawable(trLeftPress);
        mButtonLeft = new ImageButton(trdLeftNormal, trdLeftPress);
        
        //down button
        Texture tDownNormal = new Texture(Gdx.files.internal("ui/down_normal.png"));
        Texture tDownPress = new Texture(Gdx.files.internal("ui/down_press.png"));

        TextureRegion trDownNormal = new TextureRegion(tDownNormal);
        TextureRegion trDownPress = new TextureRegion(tDownPress);

        TextureRegionDrawable trdDownNormal = new TextureRegionDrawable(trDownNormal);
        TextureRegionDrawable trdDownPress = new TextureRegionDrawable(trDownPress);
        mButtonDown = new ImageButton(trdDownNormal, trdDownPress);
        
        //right button
        Texture tRightNormal = new Texture(Gdx.files.internal("ui/right_normal.png"));
        Texture tRightPress = new Texture(Gdx.files.internal("ui/right_press.png"));

        TextureRegion trRightNormal = new TextureRegion(tRightNormal);
        TextureRegion trRightPress = new TextureRegion(tRightPress);

        TextureRegionDrawable trdRightNormal = new TextureRegionDrawable(trRightNormal);
        TextureRegionDrawable trdRightPress = new TextureRegionDrawable(trRightPress);
        mButtonRight = new ImageButton(trdRightNormal, trdRightPress);
        
        //set button listener
        mButtonUp.addListener(mBTNClickListener);
        mButtonLeft.addListener(mBTNClickListener);
        mButtonDown.addListener(mBTNClickListener);
        mButtonRight.addListener(mBTNClickListener);
        
        //button center
        Texture tCenter = new Texture(Gdx.files.internal("ui/arrow_center.png"));
        TextureRegion trCenterNormal = new TextureRegion(tCenter);
        TextureRegionDrawable trdCenterNormal = new TextureRegionDrawable(trCenterNormal);
        mButtonCenter = new Image(trdCenterNormal);
	}

    @Override
    protected void SetView()
    {
        if (mBTNStage == null)
        {
            mBTNStage = new Stage(mWinWidth, mWinHeight, true);
        }
        if (mBGTexture == null)
        {
            mBGTexture = new Texture(Gdx.files.internal("ui/game_bg.png"));
        }
        
        if (mBGSprite == null)
        {
            mBGSprite = new Sprite(mBGTexture);
            mBGSprite.setSize(mGameWidth, mGameHeight);
            mBGSprite.setPosition((mWinWidth - mGameWidth) / 2, (mWinHeight - mGameHeight - 10));
//            mBGSprite.setPosition((mWinWidth - mGameWidth) / 2, (mWinHeight - mGameHeight) * 2 / 3);
        }
        
        //set button position
        float btnPositionX = (mWinWidth - mButtonCenter.getWidth() * 3) / 2;
        mButtonDown.setPosition(btnPositionX + mButtonCenter.getWidth(), 10);
        mButtonLeft.setPosition(btnPositionX, 10 + mButtonCenter.getHeight());
        mButtonUp.setPosition(btnPositionX + mButtonCenter.getWidth(), 10 + mButtonCenter.getHeight() * 2);
        mButtonRight.setPosition(btnPositionX + mButtonCenter.getWidth() * 2, 10 + mButtonCenter.getHeight());
        mButtonCenter.setPosition(btnPositionX + mButtonCenter.getWidth(), 10 + mButtonCenter.getHeight());
        
        //add button to stage
        mBTNStage.addActor(mButtonUp);
        mBTNStage.addActor(mButtonDown);
        mBTNStage.addActor(mButtonLeft);
        mBTNStage.addActor(mButtonRight);
        mBTNStage.addActor(mButtonCenter);        
    }
	
	@Override
	public void dispose()
	{
	    mBatch.dispose();
		mBGTexture.dispose();
	}

	@Override
	public void render(float arg0)
	{
        //白色清屏
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        mBatch.begin();
		DrawBackground(mBatch);
//		RefreshGameView();
        mBatch.end();
        
        mBTNStage.act();
        mBTNStage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

    @Override
    public void hide()
    {
        
    }
    
    @Override
    public void show()
    {
        TraceLog.Print_D("main screen show");
        Gdx.input.setInputProcessor(mBTNStage);
    }
    
    private void DrawBackground(SpriteBatch batch)
    {
        mBGSprite.draw(mBatch);
    }
    
    private void RefreshGameView()
    {
        if (nCurrentY == 0)
        {
            TraceLog.Print_D("no square display.");
            return;
        }
        else if (nCurrentY < 4)
        {
            //currenty小于4时表示当前显示的方块没有完全显示，显示几行就更新game view中对应的几行
            for (int i = 0; i < nCurrentY; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    mGameMatrix[i][j + nCurrentX] = mCurrentSquare.GetMatrix(SquareDirection.SD_DOWN)[4 - nCurrentY][j];
                }
            }
        }
        else if (nCurrentY == 2)
        {
            
        }
        else if (nCurrentY == 3)
        {
            
        }
        else if (nCurrentY >= 4)
        {
            
        }
        
        PrintGameMatrix();
    }
    
    private void CreateNewSquare()
    {
        int type  = SquareType.SQUARE_UNKOWN;
        do
        {
            type = (int)(Math.random() * 7);
        }while (type == 0);
        
        TraceLog.Print_D("Create new square: " + type);
        mCurrentSquare = new Square(type);
        
        nCurrentX = 4;
        nCurrentY = 0;
        
        bIsLanding = true;
    }
    
    private void DoSquareAction(int nDirection)
    {        
        switch (nDirection)
        {
        case SquareDirection.SD_UP:
            TraceLog.Print_D("DoSquareAction: Up");
            ActionUP();
            break;
        case SquareDirection.SD_DOWN:
            TraceLog.Print_D("DoSquareAction: Down");
            ActionDown();
            break;
        case SquareDirection.SD_LEFT:
            TraceLog.Print_D("DoSquareAction: Left");
            ActionLeft();
            break;
        case SquareDirection.SD_RIGHT:
            TraceLog.Print_D("DoSquareAction: Right");
            ActionRight();
            break;
        default:
            TraceLog.Print_E("Unkown direction: " + nDirection);
            break;
        }
    }
    
    private void ActionUP()
    {
        TraceLog.Print_D("ActionUP");
        
    }
    
    private void ActionLeft()
    {
        TraceLog.Print_D("ActionLeft");
        
    }
    
    private void ActionRight()
    {
        TraceLog.Print_D("ActionRight");
        
    }
    
    private void ActionDown()
    {
        TraceLog.Print_D("ActionDown");
        
        if (!bIsLanding)
        {
            CreateNewSquare();
        }
        
        nCurrentY++;
        RefreshGameView();
    }
    
    private void PrintGameMatrix()
    {
        for (int i = 0; i < 20; i++)
        {
            TraceLog.Print_D(
                    mGameMatrix[i][0] + " " + mGameMatrix[i][1] + " " + mGameMatrix[i][2] + " " + 
                    mGameMatrix[i][3] + " " + mGameMatrix[i][4] + " " + mGameMatrix[i][5] + " " + 
                    mGameMatrix[i][6] + " " + mGameMatrix[i][7] + " " + mGameMatrix[i][8] + " " + 
                    mGameMatrix[i][9] + " " + mGameMatrix[i][10] + " " + mGameMatrix[i][11]
                            );
        }
    }
}
