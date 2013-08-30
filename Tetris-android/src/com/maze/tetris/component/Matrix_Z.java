package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      0 0 0 0  |  0 0 0 0
 *      0 1 0 0  |  0 1 1 0
 *      0 1 1 0  |  1 1 0 0
 *      0 0 1 0  |  0 0 0 0
 */
public class Matrix_Z extends BlockMatrix
{
    @Override
    public int[][] MakeMatrix(int nDirection)
    {
        TraceLog.Print_D("Matrix_Z: MakeMatrix");
        EraseMatrix();

        mMatrix[1][1] = 1;
        mMatrix[2][1] = 1;
        int n = nDirection % 4;
        if (n == SquareDirection.SD_UP || n == SquareDirection.SD_DOWN)
        {
            mMatrix[2][2] = 1;
            mMatrix[3][2] = 1;
        }
        else if (n == SquareDirection.SD_LEFT || n == SquareDirection.SD_RIGHT)
        {
            mMatrix[1][2] = 1;
            mMatrix[2][0] = 1;
        }
        else
        {
            TraceLog.Print_E("Matrix_Z: unkown direction");            
        }
        return super.MakeMatrix(nDirection);
    }

}