package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      up&down     left&right       
 *      0 0 1 0  |  0 0 0 0
 *      0 0 1 0  |  1 1 1 1
 *      0 0 1 0  |  0 0 0 0
 *      0 0 1 0  |  0 0 0 0
 */
public class Matrix_I extends BlockMatrix
{
    @Override
    public void SetMatrix(int direction)
    {
        TraceLog.Print_D("MakeMatrix: " + direction);

        EraseMatrix();

        nDirection = direction;
        int n = nDirection % 4;

        if (n == SquareDirection.SD_UP || n == SquareDirection.SD_DOWN)
        {
            for (int i = 0; i < 4; i++)
            {
                mMatrix[i][2] = SquareType.SQUARE_I;
            }
            
            nWidth = 1;
            nHeight = 4;
            
            nMinX = 0;
            nMaxX = 3;
            
            nMinY = 2;
            nMaxY = 2;
        }
        else if (n == SquareDirection.SD_LEFT || n == SquareDirection.SD_RIGHT)
        {
            for (int i = 0; i < 4; i++)
            {
                mMatrix[1][i] = SquareType.SQUARE_I;
            }

            nWidth = 4;
            nHeight = 1;
            
            nMinX = 1;
            nMaxX = 1;
            
            nMinY = 0;
            nMaxY = 3;
        }
        else
        {
            TraceLog.Print_E("Matrix_I: unkown direction");
        }
        Print();
    }
}
