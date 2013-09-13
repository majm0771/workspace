package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

/*
 *      all
 *      0 0 0 0  
 *      0 1 1 0  
 *      0 1 1 0  
 *      0 0 0 0  
 */
public class Matrix_O extends BlockMatrix
{
    
    public Matrix_O()
    {
        EraseMatrix();
        for (int i = 1; i < 3; i++)
        {
            for (int j = 1; j < 3; j++)
            {
                mMatrix[i][j] = SquareType.SQUARE_O;
            }
        }

       nWidth = 2;
       nHeight = 2;
        
       nMinX = nMinY = 1;
       nMaxX = nMaxY = 2;
       Print();
    }
    
    @Override
    public void SetMatrix(int direction)
    {
        TraceLog.Print_D("MakeMatrix: " + direction);
        nDirection = direction;
    }
}
