package lib.network;

import android.util.Log;

public final class LogNetwork {

    private static final String TAG = LogNetwork.class.getSimpleName();

    private static final String KSeparate = "=========";
    private static final boolean mIsDebug = true;

    private LogNetwork() {
    }

    public static boolean isDebug() {
        return mIsDebug;
    }

    public static int v(String tag, String msg) {
        if (mIsDebug) {
            return Log.v(tag, msg);
        } else {
            return 0;
        }
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (mIsDebug) {
            return Log.v(tag, msg, tr);
        } else {
            return 0;
        }
    }

    public static int d(String tag, String msg) {
        if (mIsDebug) {
            return Log.d(tag, msg);
        } else {
            return 0;
        }
    }

    public static int d(String msg) {
        if (mIsDebug) {
            return Log.d(TAG, msg);
        } else {
            return 0;
        }
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (mIsDebug) {
            return Log.d(tag, msg, tr);
        } else {
            return 0;
        }
    }

    public static int i(String tag, String msg) {
        if (mIsDebug) {
            return Log.i(tag, msg);
        } else {
            return 0;
        }
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (mIsDebug) {
            return Log.i(tag, msg, tr);
        } else {
            return 0;
        }
    }

    public static void e(String msg, Throwable tr) {
        if (mIsDebug) {
            Log.e(TAG, msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (mIsDebug) {
            Log.e(tag, msg, tr);
        }
    }

    public static void e(Throwable e) {
        if (mIsDebug) {
            Log.e(TAG, KSeparate + e.getClass().getSimpleName() + KSeparate, e);
        }
    }

    public static void e(String tag, String log) {
        if (mIsDebug) {
            Log.e(tag, log);
        }
    }

    public static void e(String log) {
        if (mIsDebug) {
            Log.e(TAG, log);
        }
    }

    public static void w(String tag, String log) {
        if (mIsDebug) {
            Log.w(tag, log);
        }
    }
}
