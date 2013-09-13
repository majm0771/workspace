package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      up&down     left&right
 *      0 0 0 0  |  0 0 0 0
 *      0 1 0 0  |  0 1 1 0
 *      0 1 1 0  |  1 1 0 0
 *      0 0 1 0  |  0 0 0 0
 */
public class Matrix_Z extends BlockMatrix
{
    @Override
    public void SetMatrix(int direction)
    {
        TraceLog.Print_D("MakeMatrix: " + direction);

        EraseMatrix();

        nDirection = direction;
        mMatrix[1][1] = SquareType.SQUARE_Z;
        mMatrix[2][1] = SquareType.SQUARE_Z;
        int n = nDirection % 4;
        
        if (n == SquareDirection.SD_UP || n == SquareDirection.SD_DOWN)
        {
            mMatrix[2][2] = SquareType.SQUARE_Z;
            mMatrix[3][2] = SquareType.SQUARE_Z;

            nWidth = 2;
            nHeight = 3;
            
            nMinX = 1;
            nMaxX = 3;
            nMinY = 1;
            nMaxY = 2;
        }
        else if (n == SquareDirection.SD_LEFT || n == SquareDirection.SD_RIGHT)
        {
            mMatrix[1][2] = SquareType.SQUARE_Z;
            mMatrix[2][0] = SquareType.SQUARE_Z;

            nWidth = 3;
            nHeight = 2;
            
            nMinX = 1;
            nMaxX = 2;
            nMinY = 0;
            nMaxY = 2;
        }
        else
        {
            TraceLog.Print_E("Matrix_Z: unkown direction");
        }
        Print();
    }

}
