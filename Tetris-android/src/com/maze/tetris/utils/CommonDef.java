package com.maze.tetris.utils;

public class CommonDef
{
    public final static String TAG = "Teris";

    public static boolean EnableLog      = true;
    public static boolean EnableLog2File = false;
    
    public class MessageType
    {
        public final static int MSG_START_GAME = 0x0001;
    }
    
    public class OPResult
    {
        public final static boolean OP_Success = true;
        public final static boolean OP_Fail = true;
    }
}
