package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      0 0 0 0  |  0 0 0 0  |  0 0 0 0  |  0 0 0 0
 *      0 1 0 0  |  0 1 0 0  |  0 1 0 0  |  0 0 0 0
 *      0 1 1 0  |  1 1 1 0  |  1 1 0 0  |  1 1 1 0
 *      0 1 0 0  |  0 0 0 0  |  0 1 0 0  |  0 1 0 0
 */
public class Matrix_T extends BlockMatrix
{
    @Override
    public int[][] MakeMatrix(int nDirection)
    {
        TraceLog.Print_D("Matrix_T: MakeMatrix");
        EraseMatrix();

        int n = nDirection % 4;
        for (int i = 1; i < 4; i++)
        {
            if (n == SquareDirection.SD_UP)
            {                
                mMatrix[2][2] = 1;
                mMatrix[i][1] = 1;
            }
            else if (n == SquareDirection.SD_LEFT)
            {
                mMatrix[1][1] = 1;
                mMatrix[2][i-1] = 1;
            }
            else if (n == SquareDirection.SD_DOWN)
            {
                mMatrix[2][1] = 1;
                mMatrix[i][1] = 1;
            }
            else if (n == SquareDirection.SD_RIGHT)
            {
                mMatrix[3][1] = 1;
                mMatrix[2][i-1] = 1;
            }
            else
            {
                TraceLog.Print_E("Matrix_T: unkown direction");
            }
        }
        return super.MakeMatrix(nDirection);
    }
}
