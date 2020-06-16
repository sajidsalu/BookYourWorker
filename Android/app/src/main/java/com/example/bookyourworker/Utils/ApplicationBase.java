package com.example.bookyourworker.Utils;


import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;


import java.sql.Date;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class ApplicationBase extends MultiDexApplication {
    private static Context context;
    /**
     * to get number of process currently running
     */
    public static int ASYNTASK_COUNTER = 0,SYNCTASK_COUNTER=0,AUTOSYNC_COUNTER=0;

    //private RefWatcher refWatcher;
    /**
     * Last click time
     */
    public static long lastOnlinePostTime,lastOnlineSyncTime, lastOfflineMethodTime;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        context = this;
        ApplicationBase.context = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler(context));
    }

    public static Context getAppBaseContext() {
        return context;
    }

    public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {
        Context myContext;

        public DefaultExceptionHandler(Context context)

        {
            this.myContext=context;
        }

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            String stackTrace = Log.getStackTraceString(ex);
            String time = new Date(System.currentTimeMillis()).toString();
            String message = ex.getMessage();
//            Intent intent = new Intent(myContext, LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            myContext.startActivity(intent);
            Process.killProcess(Process.myPid());
            System.exit(0);

        }

    }

    public static Context getAppContext() {
        return ApplicationBase.context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}