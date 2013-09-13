package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      up          left        down        right
 *      0 0 0 0  |  0 0 0 0  |  0 0 0 0  |  0 0 0 0
 *      0 1 0 0  |  0 1 0 0  |  0 1 0 0  |  0 0 0 0
 *      0 1 1 0  |  1 1 1 0  |  1 1 0 0  |  1 1 1 0
 *      0 1 0 0  |  0 0 0 0  |  0 1 0 0  |  0 1 0 0
 */
public class Matrix_T extends BlockMatrix
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
                mMatrix[2][2] = SquareType.SQUARE_T;
                mMatrix[i][1] = SquareType.SQUARE_T;

                nWidth = 2;
                nHeight = 3;
                
                nMinX = 1;
                nMaxX = 3;
                nMinY = 1;
                nMaxY = 2;
            }
            else if (n == SquareDirection.SD_LEFT)
            {
                mMatrix[1][1] = SquareType.SQUARE_T;
                mMatrix[2][i - 1] = SquareType.SQUARE_T;

                nWidth = 3;
                nHeight = 2;
                
                nMinX = 1;
                nMaxX = 2;
                nMinY = 0;
                nMaxY = 2;
            }
            else if (n == SquareDirection.SD_DOWN)
            {
                mMatrix[2][0] = SquareType.SQUARE_T;
                mMatrix[i][1] = SquareType.SQUARE_T;

                nWidth = 2;
                nHeight = 3;
                
                nMinX = 1;
                nMaxX = 3;
                nMinY = 0;
                nMaxY = 1;
            }
            else if (n == SquareDirection.SD_RIGHT)
            {
                mMatrix[3][1] = SquareType.SQUARE_T;
                mMatrix[2][i - 1] = SquareType.SQUARE_T;

                nWidth = 3;
                nHeight = 2;
                
                nMinX = 2;
                nMaxX = 3;
                nMinY = 0;
                nMaxY = 2;
            }
            else
            {
                TraceLog.Print_E("Matrix_T: unkown direction");
            }
        }
        Print();
    }
}
