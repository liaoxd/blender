package com.halfcigarette.dietitian.utils;

import android.app.Activity;
import android.util.Log;

import com.halfcigarette.dietitian.app.AppManager;


/**
 * Author：WeihangDong
 * Create time：2015/10/18 9:03
 * Email：WeihangDong@foxmail.com
 * Description：日志管理器
 */

public class Logger {

    /**
     * 是否为开发者模式(开发模式打印LOG,非开发模式不打印LOG)
     */
    private static boolean mDebug = true;

    private Logger() {
    }

    /**
     * 打印verbose级别的log
     *
     * @param msg
     */
    public static void v(String msg) {
        if (mDebug) {
            Activity activity = AppManager.getAppManager().currentActivity();
            Log.v("   ----   " + activity.getClass().getSimpleName() + "   ----   ", msg);
        }
    }

    /**
     * 打印debug级别的log
     *
     * @param msg
     */
    public static void d(String msg) {
        if (mDebug) {
            Activity activity = AppManager.getAppManager().currentActivity();
            Log.d("   ---!   " + activity.getClass().getSimpleName() + "   !---   ", msg);
        }
    }

    /**
     * 打印info级别的log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (mDebug) {
            Activity activity = AppManager.getAppManager().currentActivity();
            Log.i("   --!!   " + activity.getClass().getSimpleName() + "   !!--   ", msg);
        }
    }

    /**
     * 打印warm级别的log
     *
     * @param msg
     */
    public static void w(String msg) {
        if (mDebug) {
            Activity activity = AppManager.getAppManager().currentActivity();
            Log.w("   -!!!   " + activity.getClass().getSimpleName() + "   !!!-   ", msg);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (mDebug) {
            Activity activity = AppManager.getAppManager().currentActivity();
            Log.e("   !!!!   " + activity.getClass().getSimpleName() + "   !!!!   ", msg);
        }
    }
}
