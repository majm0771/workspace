package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *  use 4*4 matrix to represent blocks
 *  
 *      0 0 0 0
 *      0 0 0 0
 *      0 0 0 0
 *      0 0 0 0 
 *  
 */
        
public abstract class BlockMatrix
{
    protected int [][] mMatrix = {{0, 0, 0, 0},
                                  {0, 0, 0, 0},
                                  {0, 0, 0, 0},
                                  {0, 0, 0, 0}};   

    public int nWidth = 0;    //������С����
    public int nHeight = 0;    //������С����
    
    public int nMinX = 0;    //������С����
    public int nMinY = 0;    //������С����
    public int nMaxX = 0;    //�����������
    public int nMaxY = 0;    //�����������
    
    protected int nDirection = SquareDirection.SD_UNKOWN;
    
    public abstract void SetMatrix(int direction);

    public int[][] GetMatrix()
    {
        return mMatrix;
    }
    
    public int GetDirection()
    {
        return nDirection;
    }
    
    protected void EraseMatrix()
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                mMatrix[i][j] = 0;
            }
        }
    }
    
    public void Print()
    {
        for (int i = 0; i < 4; i++)
        {
            TraceLog.Print_D(mMatrix[i][0] + " " + mMatrix[i][1] + " " + mMatrix[i][2] + " " + mMatrix[i][3]);
        }  
    }
}
