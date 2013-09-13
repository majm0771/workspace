package com.maze.tetris.screens;

import android.os.Handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private int           nWinWidth         = 0;
    private int           nWinHeight        = 0;

    private int           nViewMarginLeft   = 0;
    private int           nViewMarginBottom    = 0;

    private int           nBlockLen         = 24;

    // 水平12块方格
    private int           nGameWCount       = 12;
    private int           nGameWidth        = nBlockLen * nGameWCount;
    // 垂直20块方格
    private int           nGameHCount       = 20;
    private int           nGameHeight       = nBlockLen * 20;

    private boolean       bIsLanding        = false;
    
    // 下落中的方块阵列
    private int[][]       mLandingMatrix    = new int[4][4];
    // 已经固定的方块阵列
    private int[][]       mGameMatrix       = new int[20][12];
    
    // 下落中的方块处在游戏阵列的行偏移量
    private int           nOffsetX          = 0;
    // 下落中的方块处在游戏阵列的列偏移量
    private int           nOffsetY          = 0;

    private Square        mCurrentSquare    = null;

    private SpriteBatch   mBatch            = null;
    private Texture       mBGTexture        = null;
    private Sprite        mBGSprite         = null;

    private Image         mButtonCenter     = null;
    private ImageButton   mButtonUp         = null;
    private ImageButton   mButtonLeft       = null;
    private ImageButton   mButtonDown       = null;
    private ImageButton   mButtonRight      = null;

    private Stage         mBTNStage         = null;
    
    private TextureRegion[] mBlockImages = new TextureRegion[7];
    
    int temp = 1;

    private InputListener mBTNClickListener = new InputListener()
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
        {
            TraceLog.Print_D("view onClick");
            if (event.getListenerActor() == mButtonUp)
            {
                TraceLog.Print_D("up onClick");
                ActionUP();
            }
            else if (event.getListenerActor() == mButtonLeft)
            {
                TraceLog.Print_D("left onClick");
                ActionLeft();
            }
            else if (event.getListenerActor() == mButtonDown)
            {
                TraceLog.Print_D("down onClick");
                ActionDown();
            }
            else if (event.getListenerActor() == mButtonRight)
            {
                TraceLog.Print_D("right onClick");
                ActionRight();
            }
            else
            {
                TraceLog.Print_E("unkown view was clicked");
            }
            return true;
        }
    };

    public MainScreen(Handler h)
    {
        super();

        setHandler(h);

        nWinWidth = Gdx.graphics.getWidth();
        nWinHeight = Gdx.graphics.getHeight();

        if (mBatch == null)
        {
            mBatch = new SpriteBatch();
        }

        Init();
    }

    @Override
    protected void InitControl()
    {
        // up button
        Texture tUpNormal = new Texture(Gdx.files.internal("ui/up_normal.png"));
        Texture tUpPress = new Texture(Gdx.files.internal("ui/up_press.png"));

        TextureRegion trUpNormal = new TextureRegion(tUpNormal);
        TextureRegion trUpPress = new TextureRegion(tUpPress);

        TextureRegionDrawable trdUpNormal = new TextureRegionDrawable(
                trUpNormal);
        TextureRegionDrawable trdUpPress = new TextureRegionDrawable(trUpPress);
        mButtonUp = new ImageButton(trdUpNormal, trdUpPress);

        // left button
        Texture tLeftNormal = new Texture(
                Gdx.files.internal("ui/left_normal.png"));
        Texture tLeftPress = new Texture(
                Gdx.files.internal("ui/left_press.png"));

        TextureRegion trLeftNormal = new TextureRegion(tLeftNormal);
        TextureRegion trLeftPress = new TextureRegion(tLeftPress);

        TextureRegionDrawable trdLeftNormal = new TextureRegionDrawable(
                trLeftNormal);
        TextureRegionDrawable trdLeftPress = new TextureRegionDrawable(
                trLeftPress);
        mButtonLeft = new ImageButton(trdLeftNormal, trdLeftPress);

        // down button
        Texture tDownNormal = new Texture(
                Gdx.files.internal("ui/down_normal.png"));
        Texture tDownPress = new Texture(
                Gdx.files.internal("ui/down_press.png"));

        TextureRegion trDownNormal = new TextureRegion(tDownNormal);
        TextureRegion trDownPress = new TextureRegion(tDownPress);

        TextureRegionDrawable trdDownNormal = new TextureRegionDrawable(
                trDownNormal);
        TextureRegionDrawable trdDownPress = new TextureRegionDrawable(
                trDownPress);
        mButtonDown = new ImageButton(trdDownNormal, trdDownPress);

        // right button
        Texture tRightNormal = new Texture(
                Gdx.files.internal("ui/right_normal.png"));
        Texture tRightPress = new Texture(
                Gdx.files.internal("ui/right_press.png"));

        TextureRegion trRightNormal = new TextureRegion(tRightNormal);
        TextureRegion trRightPress = new TextureRegion(tRightPress);

        TextureRegionDrawable trdRightNormal = new TextureRegionDrawable(
                trRightNormal);
        TextureRegionDrawable trdRightPress = new TextureRegionDrawable(
                trRightPress);
        mButtonRight = new ImageButton(trdRightNormal, trdRightPress);

        // set button listener
        mButtonUp.addListener(mBTNClickListener);
        mButtonLeft.addListener(mBTNClickListener);
        mButtonDown.addListener(mBTNClickListener);
        mButtonRight.addListener(mBTNClickListener);

        // button center
        Texture tCenter = new Texture(Gdx.files.internal("ui/arrow_center.png"));
        TextureRegion trCenterNormal = new TextureRegion(tCenter);
        TextureRegionDrawable trdCenterNormal = new TextureRegionDrawable(
                trCenterNormal);
        mButtonCenter = new Image(trdCenterNormal);

        // init block's image
        for (int i = 1; i <= 7; i++)
        {
            Texture texture = new Texture(Gdx.files.internal("blocks/" + i + ".png"));
            mBlockImages[i - 1] = new TextureRegion(texture);
        }
    }

    @Override
    protected void SetView()
    {
        if (mBTNStage == null)
        {
            mBTNStage = new Stage(nWinWidth, nWinHeight, true);
        }

        if (mBGTexture == null)
        {
            mBGTexture = new Texture(Gdx.files.internal("ui/game_bg.png"));
        }

        nViewMarginLeft = (nWinWidth - nGameWidth) / 2;
        nViewMarginBottom = (nWinHeight - 10 - nGameHeight);

        if (mBGSprite == null)
        {
            mBGSprite = new Sprite(mBGTexture);
            mBGSprite.setSize(nGameWidth, nGameHeight);
            mBGSprite
                    .setPosition(nViewMarginLeft, nViewMarginBottom);
            // mBGSprite.setPosition((mWinWidth - mGameWidth) / 2, (mWinHeight -
            // mGameHeight) * 2 / 3);
        }

        // set button position
        float btnPositionX = (nWinWidth - mButtonCenter.getWidth() * 3) / 2;
        mButtonDown.setPosition(btnPositionX + mButtonCenter.getWidth(), 10);
        mButtonLeft.setPosition(btnPositionX, 10 + mButtonCenter.getHeight());
        mButtonUp.setPosition(btnPositionX + mButtonCenter.getWidth(),
                10 + mButtonCenter.getHeight() * 2);
        mButtonRight.setPosition(btnPositionX + mButtonCenter.getWidth() * 2,
                10 + mButtonCenter.getHeight());
        mButtonCenter.setPosition(btnPositionX + mButtonCenter.getWidth(),
                10 + mButtonCenter.getHeight());

        // add button to stage
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
    { // 白色清屏
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        mBatch.begin();
        DrawBackground(mBatch);
        DrawGameView(mBatch);
        DrawLandingView(mBatch);
        // RefreshGameView();
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

        if (!bIsLanding)
        {
            CreateNewSquare();
            CopyMatrix(mLandingMatrix, mCurrentSquare.GetMatrix(), 0, 0);
        }
        
        Gdx.input.setInputProcessor(mBTNStage);
    }

    private void DrawBackground(SpriteBatch batch)
    {
        mBGSprite.draw(mBatch);
    }

    private void DrawLandingView(SpriteBatch batch)
    {
        if (mCurrentSquare == null)
        {
            return;
        }
        
        // 设置下落方块的最小和最大边长
        int min_x = mCurrentSquare.GetMinX();
        int min_y = mCurrentSquare.GetMinY();
        int max_x = mCurrentSquare.GetMaxX();
        int max_y = mCurrentSquare.GetMaxY();

        int draw_offset_x = nGameHCount - nOffsetX;
        int draw_offset_y = nOffsetY;
        // 绘制下落中的方块阵列
        for (int i = min_x; i <= max_x; i++)
        {
            for (int j = min_y; j <= max_y; j++)
            {
                if (mLandingMatrix[i][j] != 0)
                {
                    int index = mLandingMatrix[i][j] - 1;
                    int m = nViewMarginLeft + nBlockLen * draw_offset_y;
                    int n = nViewMarginBottom + nBlockLen * draw_offset_x;
//                    TraceLog.Print_D("" + nOffsetX + ":" + nOffsetY + "|" + i + ":" + j + "|" + m + ":" + n);
                    batch.draw(mBlockImages[index], 
                            m,    //x position
                            n,     //y position 
                            nBlockLen,
                            nBlockLen);
                }
                draw_offset_y++;
            }
            //由于draw函数是由图片左下角坐标开始向上绘制，所以这里x偏移量--
            draw_offset_x--;
            //恢复y偏移量
            draw_offset_y = nOffsetY;
        }
    }
    
    private void DrawGameView(SpriteBatch batch)
    {
        // 绘制已经固定的方块阵列
        for (int i = 0; i < nGameHCount; i++)
        {
            for (int j = 0; j < nGameWCount; j++)
            {
                if (mGameMatrix[i][j] != 0)
                {
                    // TraceLog.Print_D("load file: " + mGameMatrix[i][j] +
                    // ".png");
                    int index = mGameMatrix[i][j] - 1;
                    
                    int m = nViewMarginLeft + nBlockLen * nOffsetY;
                    int n = nViewMarginBottom + nBlockLen * (nGameHCount - nOffsetX);
                    
                    batch.draw(mBlockImages[index], 
                            m,  //x position
                            n,   //y position 
                            nBlockLen,
                            nBlockLen);
                }
            }
        }
    }

    private void CreateNewSquare()
    {
        int type = (int) (Math.random() * 7);
        if (type == 0)
        {
            type++;
        }

        TraceLog.Print_D("Create new square: " + type);
        mCurrentSquare = new Square(type);
        mCurrentSquare.UpdateMatrix(SquareDirection.SD_DOWN);

        //由于draw函数是由图片左下角坐标开始向上绘制，所以这里x偏移量第1行默认为1
        nOffsetX = 1;
        nOffsetY = (nGameWCount - (mCurrentSquare.GetMaxY() - mCurrentSquare.GetMinY())) / 2;
        TraceLog.Print_D("default offset: x=" + nOffsetX + " | y=" + nOffsetY);

        bIsLanding = true;
    }

    private void ActionUP()
    {
        TraceLog.Print_D("ActionUP");
        if (!CheckMatrixConflict(nOffsetX, nOffsetY, mLandingMatrix,
                mGameMatrix))
        {
            // mCurrentSquare.GetMatrix(true);
        }
    }

    private void ActionLeft()
    {
        TraceLog.Print_D("ActionLeft");
        
        if (bIsLanding && !CheckMatrixConflict(nOffsetX, nOffsetY - 1, mLandingMatrix,
                mGameMatrix))
        {
            nOffsetY--;
        }
    }

    private void ActionRight()
    {
        TraceLog.Print_D("ActionRight");
        if (bIsLanding && !CheckMatrixConflict(nOffsetX, nOffsetY + 1, mLandingMatrix,
                mGameMatrix))
        {
            nOffsetY++;
        }
    }

    private void ActionDown()
    {
        TraceLog.Print_D("ActionDown: " + bIsLanding);

        if (!bIsLanding)
        {
            CreateNewSquare();
            CopyMatrix(mLandingMatrix, mCurrentSquare.GetMatrix(), 0, 0);
        }
        else if (!CheckMatrixConflict(nOffsetX + 1, nOffsetY, mLandingMatrix, mGameMatrix))
        {
            nOffsetX++;
            TraceLog.Print_D("Move Down");
        }
        else
        {
            bIsLanding = false;
            TraceLog.Print_D("copy to game matrix");
            //矩阵拷贝由起始偏移量开始计算，这里nOffsetX要减1
            CopyMatrix(mGameMatrix, mLandingMatrix, nOffsetX - 1, nOffsetY);
            PrintGameMatrix();
            EreaseMatrix(mLandingMatrix);
        }
    }

    /*
     * 下落中的方块碰撞检测函数
     * param:
     *  x - 相对于固定矩阵的行偏移量
     *  y - 相对于固定矩阵的列偏移量
     *  src - 下落矩阵
     *  dst - 固定矩阵
     */
    private boolean CheckMatrixConflict(final int x, final int y, int[][] src, int[][] dst)
    {
        TraceLog.Print_D("check conflict: " + x + " | " + y);
        
        //由于是从下向上显示，这里加入行偏移量来计算,由于初始偏移量是1，这里减去1
        int temp_x = x - 1;
        int temp_y = y;
        
        // 获取方块的高度
        int height = mCurrentSquare.GetHeight();
        TraceLog.Print_D("height: " + height);
        
        // 设置下落方块的最小和最大边长
        int min_x = mCurrentSquare.GetMinX();
        int min_y = mCurrentSquare.GetMinY();
        int max_x = mCurrentSquare.GetMaxX();
        int max_y = mCurrentSquare.GetMaxY();

        boolean ret = false;

        if (temp_x +  height > nGameHCount || temp_y + min_y < 0 || temp_y + max_y > nGameWCount)
        {
            TraceLog.Print_D("block conflict.");
            ret = true;
        }
        else
        {
            
            for (int i = min_x; i <= max_x; i++)
            {
                for (int j = min_y; j <= max_y; j++)
                {
                    try
                    {
                        if (src[i][j] != 0 && dst[temp_x][temp_y] != 0)
                        {
                            ret = true;
                            TraceLog.Print_D("block conflict.");
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        // TraceLog.Print_E(e.getCause());
                        TraceLog.Print_E(e.toString());
                        ret = true;
                    }
                    temp_y++;
                }
                temp_y = y;
                temp_x++;
            }
        }
        return ret;
    }
    
    /*
     * 方块拷贝函数
     * param:
     *  dst - 目标矩阵
     *  src - 原始矩阵
     *  x - 拷贝起始x索引（从0开始计数）
     *  y - 拷贝起始y索引（从0开始计数）
     */
    private void CopyMatrix(int[][] dstMatrix, int[][] srcMatrix, int x, int y)
    {
        TraceLog.Print_D("CopyMatrix");
        
        // 设置下落方块的最小和最大边长
        int min_x = mCurrentSquare.GetMinX();
        int min_y = mCurrentSquare.GetMinY();
        int max_x = mCurrentSquare.GetMaxX();
        int max_y = mCurrentSquare.GetMaxY();
        
        int index_x = 0;
        int index_y = 0;

        for (int i = min_x; i <= max_x; i++)
        {
            for (int j = min_y; j <= max_y; j++)
            {
                try
                {
                    dstMatrix[x + index_x][y + index_y] = srcMatrix[i][j];
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    TraceLog.Print_E(e.toString());
                }
                index_y++;
            }
            index_y = 0;
            index_x++;
        }
    }
    
    private void EreaseMatrix(int[][] matrix)
    {
        TraceLog.Print_D("EreaseMatrix");
        for (int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                matrix[i][j] = 0;
            }
        }
    }

    private void PrintGameMatrix()
    {
        for (int i = 0; i < 20; i++)
        {
            TraceLog.Print_D(mGameMatrix[i][0] + " " + mGameMatrix[i][1] + " "
                    + mGameMatrix[i][2] + " " + mGameMatrix[i][3] + " "
                    + mGameMatrix[i][4] + " " + mGameMatrix[i][5] + " "
                    + mGameMatrix[i][6] + " " + mGameMatrix[i][7] + " "
                    + mGameMatrix[i][8] + " " + mGameMatrix[i][9] + " "
                    + mGameMatrix[i][10] + " " + mGameMatrix[i][11]);
        }
    }
}
