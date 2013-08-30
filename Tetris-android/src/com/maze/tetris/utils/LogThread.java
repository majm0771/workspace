package com.maze.tetris.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;

public class LogThread extends Thread
{
    private Context mContext = null;
    private ArrayList<String> mList = new ArrayList<String>(0);
    private boolean bRunning = true;

    private static String  mLogFile        = "iGame_%s.log";
    private static String  mLogPath        = null;
    
    public LogThread(Context ctx)
    {
        mContext = ctx;
        File root = Utils.GetSDCardRoot(mContext);
        
        if (root != null)
        {
            mLogPath = new File(root.getAbsolutePath(), "iGame").getAbsolutePath();
        }
    }
    
    public class LogType
    {
        public static final int Type_Unkown  = 0x0000;
        public static final int Type_Info    = Type_Unkown + 1;
        public static final int Type_Debug   = Type_Info + 1;
        public static final int Type_Warning = Type_Debug + 1;
        public static final int Type_Error   = Type_Warning + 1;
    }
    
    public void Close()
    {
        bRunning = false;
    }

    public synchronized void AddTraceLog(int type, String msg)
    {
        if (mList != null)
        {
            mList.add(type + msg);
        }
    }

    @Override
    public void run()
    {
        while (bRunning)
        {
            try
            {
                if (mList.isEmpty())
                {
                    sleep(50);
                    continue;
                }
                
                for (int i = 0; i < mList.size(); i++)
                {
                    String item = mList.remove(0);
                    int type = Integer.valueOf(item.substring(0, 1));
                    String msg = item.substring(1);
                    
                    Print(type, msg);
                    Log2File(msg);
                }
                sleep(50);
            }
            catch (Exception e)
            {}            
        }
    }

    private void Print(int type, String msg)
    {
        switch (type)
        {
        case LogType.Type_Info:
            Log.i(CommonDef.TAG, msg);
            break;
        case LogType.Type_Debug:
            Log.d(CommonDef.TAG, msg);
            break;
        case LogType.Type_Warning:
            Log.w(CommonDef.TAG, msg);
            break;
        case LogType.Type_Error:
            Log.e(CommonDef.TAG, msg);
            break;
        default:
            Log.d(CommonDef.TAG, msg);
            break;
        }
    }

    private void Log2File(String msg)
    {
        if (!CommonDef.EnableLog2File)
        {
            return;
        }

        if (!MakeDir())
        {
            return;
        }

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat formater = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.S");
        String date = formater.format(new Date());

        sb.append(date);
        sb.append(":(");
        sb.append(android.os.Process.myPid());
        sb.append("/");
        sb.append(Thread.currentThread().getId());
        sb.append(") \"");
        sb.append(CommonDef.TAG);
        sb.append("\" -> ");
        sb.append(msg);

        try
        {
            FileOutputStream fw = new FileOutputStream(new File(mLogPath,
                    mLogFile), true);
            PrintStream ps = new PrintStream(fw);
            ps.println(sb.toString());
            ps.close();
            fw.close();
        }
        catch (Exception e)
        {}
    }
    
    private boolean MakeDir()
    {
        if (mLogPath == null)
        {
            return false;
        }
        
        File dir = new File(mLogPath);
        if (!dir.exists())
        {
            return dir.mkdir();
        }
        else
        {
            return true;
        }
    }
}
