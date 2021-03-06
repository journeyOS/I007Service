package com.journeyOS.i007Service.core.detect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

import com.journeyOS.i007Service.I007Manager;
import com.journeyOS.i007Service.base.utils.Singleton;
import com.journeyOS.i007Service.core.I007Core;
import com.journeyOS.i007Service.core.NotifyManager;

public class LCDMonitor extends Monitor {
    private static final Singleton<LCDMonitor> gDefault = new Singleton<LCDMonitor>() {
        @Override
        protected LCDMonitor create() {
            return new LCDMonitor();
        }
    };
    private Context mContext;

    private LCDMonitor() {
        mContext = I007Core.getCore().getContext();
    }

    public static LCDMonitor getDefault() {
        return gDefault.get();
    }

    @Override
    public void onStart() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mContext.registerReceiver(new ScreenBroadcastReceiver(), filter);

    }

    public boolean isScreenOn() {
        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }

    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                NotifyManager.getDefault().onFactorChanged(I007Manager.SCENE_FACTOR_LCD, I007Manager.SCENE_LCD_STATE_ON);
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                NotifyManager.getDefault().onFactorChanged(I007Manager.SCENE_FACTOR_LCD, I007Manager.SCENE_LCD_STATE_OFF);
                I007Manager.keepAlive();
            }
        }
    }

}
