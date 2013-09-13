package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      up          left        down        right
 *      0 0 0 0  |  0 0 0 0  |  0 0 1 0  |  0 0 0 0
 *      0 1 1 0  |  0 1 0 0  |  0 0 1 0  |  1 1 1 0
 *      0 1 0 0  |  0 1 1 1  |  0 1 1 0  |  0 0 1 0
 *      0 1 0 0  |  0 0 0 0  |  0 0 0 0  |  0 0 0 0
 */
public class Matrix_L extends BlockMatrix
{
    @Override
    public void SetMatrix(int direction)
    {
        TraceLog.Print_D("MakeMatrix: " + direction);

        EraseMatrix();
        
        nDirection = direction;
        int n = nDirection % 4;        
        
        for (int i = 1; i < 4; i++)
        {
            if (n == SquareDirection.SD_UP)
            {
                mMatrix[1][2] = SquareType.SQUARE_L;
                mMatrix[i][1] = SquareType.SQUARE_L;

                nWidth = 2;
                nHeight = 3;
                
                nMinX = 1;
                nMaxX = 3;
                nMinY = 1;
                nMaxY = 2;
            }
            else if (n == SquareDirection.SD_LEFT)
            {
                mMatrix[1][1] = SquareType.SQUARE_L;
                mMatrix[2][i] = SquareType.SQUARE_L;

                nWidth = 3;
                nHeight = 2;
                
                nMinX = 1;
                nMaxX = 2;
                nMinY = 1;
                nMaxY = 3;
            }
            else if (n == SquareDirection.SD_DOWN)
            {
                mMatrix[2][1] = SquareType.SQUARE_L;
                mMatrix[i - 1][2] = SquareType.SQUARE_L;

                nWidth = 2;
                nHeight = 3;
                
                nMinX = 0;
                nMaxX = 2;
                nMinY = 1;
                nMaxY = 2;
            }
            else if (n == SquareDirection.SD_RIGHT)
            {
                mMatrix[2][2] = SquareType.SQUARE_L;
                mMatrix[1][i - 1] = SquareType.SQUARE_L;

                nWidth = 3;
                nHeight = 2;
                
                nMinX = 1;
                nMaxX = 2;
                nMinY = 0;
                nMaxY = 2;
            }
            else
            {
                TraceLog.Print_E("Matrix_L: unkown direction");
            }
        }

        Print();
    }
}
