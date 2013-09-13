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

    public int[][] GetMatrix()
    {
        return mBlocks.GetMatrix();
    }
    
    public void UpdateMatrix(int direction)
    {
        int n = direction % 4;
        if (n != mBlocks.GetDirection())
        {
            mBlocks.SetMatrix(direction);
        }
        else
        {
            TraceLog.Print_D("same direction, need not update");
        }
    }    
    
    public int GetWidth()
    {
        return mBlocks.nWidth;
    }
    
    public int GetHeight()
    {
        return mBlocks.nHeight;
    }

    public int GetMinX()
    {
        return mBlocks.nMinX;
    }    
    public int GetMinY()
    {
        return mBlocks.nMinY;
    }    
    
    public int GetMaxX()
    {
        return mBlocks.nMaxX;
    }   
    
    public int GetMaxY()
    {
        return mBlocks.nMaxY;
    }
    
    public void PrintLog()
    {
        mBlocks.Print();
    }
}
