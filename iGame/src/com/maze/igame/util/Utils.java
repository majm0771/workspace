package com.maze.igame.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class Utils
{
    public static File GetSDCardRoot(Context ctx)
    {
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //�ж�sd���Ƿ���� 
        
        if (sdCardExist) 
        { 
            return Environment.getExternalStorageDirectory();//��ȡ��Ŀ¼ 
        } 
        return null;
    }
}
