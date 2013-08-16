package com.maze.igame.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class TraceLog
{
    private static boolean mEnableLog = true;
    private static boolean mEnableLog2File = true;
    private static String mLogFile = "iGame_%s.log";
    
    public static void SetLogEnable (boolean tag)
    {
        mEnableLog = tag;
    }
    
    public static void SetLog2FileEnable (boolean tag)
    {
        mEnableLog2File = tag;
    }
    
    public static void Print_I(String msg)
    {
        if (mEnableLog)
        {
            Log.i(CommonDef.TAG, GetPrefix() + msg);
        }
        
        Log2File(GetPrefix() + msg);
    }
    
    public static void Print_D(String msg)
    {
        if (mEnableLog)
        {
            Log.d(CommonDef.TAG, GetPrefix() + msg);
        }

        Log2File(GetPrefix() + msg);
    }
    
    public static void Print_E(String msg)
    {
        if (mEnableLog)
        {
            Log.e(CommonDef.TAG, GetPrefix() + msg);
        }

        Log2File(GetPrefix() + msg);
    }
    
    public static void Print_P(String msg)
    {
        if (mEnableLog)
        {
            Log.d(CommonDef.TAG, GetPrefix() + msg + " -- " + System.currentTimeMillis());
        }

        Log2File(GetPrefix() + GetPrefix() + msg + " -- " + System.currentTimeMillis());
    }
    
    private static String GetPrefix()
    {

        StringBuffer prefix = new StringBuffer();

        try
        {
            String file_Name = Thread.currentThread().getStackTrace()[3]
                    .getFileName();
            int line_Num = Thread.currentThread().getStackTrace()[3]
                    .getLineNumber();

            if (null != file_Name && file_Name.length() > 0)
            {
                prefix.append("(");
                prefix.append(file_Name.substring(0, file_Name.lastIndexOf(".java")));
                prefix.append(": ");
                prefix.append(line_Num);
                prefix.append(")> ");
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e(CommonDef.TAG, e.toString());
        }
        
        return prefix.toString();
    }
    
    private static void Log2File(String msg)
    {
        if (mEnableLog2File)
        {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            String date = formater.format(new Date());
            
            StringBuilder sb = new StringBuilder();
            sb.append(date);
            sb.append(":(Thread#");
            sb.append(Thread.currentThread().getId());
            sb.append("#) \"");
            sb.append(CommonDef.TAG);
            sb.append("\" -> ");
            sb.append(msg);
        }
    }
}
