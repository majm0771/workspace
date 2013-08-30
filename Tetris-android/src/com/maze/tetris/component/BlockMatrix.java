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
        
public class BlockMatrix
{
    protected int [][] mMatrix = {{0, 0, 0, 0},
                                  {0, 0, 0, 0},
                                  {0, 0, 0, 0},
                                  {0, 0, 0, 0}};
    
    public int[][] MakeMatrix(int nDirection)
    {
//        TraceLog.Print_D("" + mMatrix);
//        Print();
        return mMatrix;
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
