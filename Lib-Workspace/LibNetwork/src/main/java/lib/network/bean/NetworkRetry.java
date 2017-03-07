package lib.network.bean;

/**
 * 网络重试的参数配置
 *
 * @author WDSG
 */
public class NetworkRetry {
    private int mCountDelay;
    private int mCount;
    private long mDelay;

    public NetworkRetry() {
    }

    public NetworkRetry(int countDelay, int count, long delay) {
        mCountDelay = countDelay;
        mCount = count;
        mDelay = delay;
    }

    public boolean reduceCountDelay() {
        mCountDelay--;
        if (mCountDelay < 0) {
            return false;
        }
        return true;
    }

    public boolean reduceCount() {
        mCount--;
        if (mCount < 0) {
            return false;
        }
        return true;
    }

    public long getDelay() {
        return mDelay;
    }
}
