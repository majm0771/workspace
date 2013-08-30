package com.maze.igame.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class Utils
{
    public static File GetSDCardRoot(Context ctx)
    {
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在 
        
        if (sdCardExist) 
        { 
            return Environment.getExternalStorageDirectory();//获取跟目录 
        } 
        return null;
    }
}
