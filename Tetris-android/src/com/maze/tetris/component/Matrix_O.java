package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      0 0 0 0  
 *      0 1 1 0  
 *      0 1 1 0  
 *      0 0 0 0  
 */
public class Matrix_O extends BlockMatrix
{
    @Override
    public int[][] MakeMatrix(int nDirection)
    {
        TraceLog.Print_D("Matrix_O: MakeMatrix");
        EraseMatrix();
        
        for (int i = 1; i < 3; i++)
        {
            for (int j = 1; j < 3; j++)
            {
                mMatrix[i][j] = 1;
            }
        }
        return super.MakeMatrix(nDirection);
    }
}
