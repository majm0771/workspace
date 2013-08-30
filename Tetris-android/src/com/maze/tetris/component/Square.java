package com.maze.tetris.component;

import com.maze.tetris.utils.TraceLog;

public class Square
{
    private BlockMatrix mBlocks = null;
    private int nType = SquareType.SQUARE_UNKOWN;
    
    public Square(int type)
    {
        nType = type;
        switch (type)
        {
        case SquareType.SQUARE_I:
            mBlocks = new Matrix_I();
            break;
        case SquareType.SQUARE_RL:
            mBlocks = new Matrix_RL();            
            break;
        case SquareType.SQUARE_L:
            mBlocks = new Matrix_L();
            break;
        case SquareType.SQUARE_O:
            mBlocks = new Matrix_O();
            break;
        case SquareType.SQUARE_T:
            mBlocks = new Matrix_T();
            break;
        case SquareType.SQUARE_Z:
            mBlocks = new Matrix_Z();
            break;
        case SquareType.SQUARE_RZ:
            mBlocks = new Matrix_RZ();
            break;
        default:
            TraceLog.Print_E("Square: unkown square type.");
            break;
        }
    }
    
    public int GetType()
    {
        return nType;
    }
    
    public int[][] GetMatrix(int nDirection)
    {
        PrintLog();
        return mBlocks.MakeMatrix(nDirection);
    }
    
    public void PrintLog()
    {
        mBlocks.Print();
    }
}
