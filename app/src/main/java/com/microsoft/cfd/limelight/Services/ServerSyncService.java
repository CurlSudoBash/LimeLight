package com.microsoft.cfd.limelight.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.microsoft.cfd.limelight.Retrofit.RetrofitModule;

import java.util.Timer;
import java.util.TimerTask;

public class ServerSyncService extends Service {
    // constant
    public static final long NOTIFY_INTERVAL = 60 * 1000; // 1 minute

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // cancel if already existed
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    RetrofitModule.synchronize();
                    RetrofitModule.fetchEvents();
                }

            });
        }

    }
}