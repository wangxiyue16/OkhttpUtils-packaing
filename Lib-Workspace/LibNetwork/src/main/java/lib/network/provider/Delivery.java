package lib.network.provider;

import android.os.Handler;
import android.os.Looper;

import lib.network.error.NetError;

/**
 * @author WDSG
 */
public class Delivery {

    private Handler mHandler;
    private IDeliveryCallback mCallback;

    public Delivery(IDeliveryCallback callback) {
        mHandler = new Handler(Looper.getMainLooper());
        mCallback = callback;
    }

    public void deliverSuccess(final IRequestBuilder builder, final Object obj) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.deliverSuccess(builder, obj);
                }
            }
        });
    }

    public void deliverProgress(final IRequestBuilder builder, final float progress, final long contentLength) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.deliverProgress(builder, progress, contentLength);
                }
            }
        });
    }

    public void deliverError(final IRequestBuilder builder, final NetError error) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                if (mCallback != null) {
                    mCallback.deliverError(builder, error);
                }
            }
        });
    }
}
