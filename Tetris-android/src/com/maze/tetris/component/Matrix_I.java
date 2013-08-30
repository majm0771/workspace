package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      0 0 1 0  |  0 0 0 0
 *      0 0 1 0  |  1 1 1 1
 *      0 0 1 0  |  0 0 0 0
 *      0 0 1 0  |  0 0 0 0
 */
public class Matrix_I extends BlockMatrix
{
    @Override
    public int[][] MakeMatrix(int nDirection)
    {
        TraceLog.Print_D("Matrix_I: MakeMatrix");
        EraseMatrix();
        
        int n = nDirection % 4;
        
        if (n == SquareDirection.SD_UP || n == SquareDirection.SD_DOWN)
        {
            for (int i = 0; i < 3; i++)
            {
                mMatrix[i][2] = 1;
            }
        }
        else if (n == SquareDirection.SD_LEFT || n == SquareDirection.SD_RIGHT)
        {
            for (int i = 0; i < 3; i++)
            {
                mMatrix[1][i] = 1;
            }
        }
        else
        {
            TraceLog.Print_E("Matrix_I: unkown direction");
        }
        return super.MakeMatrix(nDirection);
    }
}
