package lib.network.provider;

import lib.network.LogNetwork;
import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.error.NetError;

/**
 * @author WDSG
 */
abstract public class BaseProvider implements IDeliveryCallback {

    protected String TAG = getClass().getSimpleName();

    private Delivery mDelivery;
    private Object mTag;

    public BaseProvider(Object tag) {
        mTag = tag;
        mDelivery = new Delivery(this);
    }

    @Override
    public void deliverProgress(IRequestBuilder builder, float progress, long totalSize) {
        if (LogNetwork.isDebug()) {
            LogNetwork.d("progress = " + progress);
            LogNetwork.d("contentLength = " + totalSize);
            LogNetwork.d("=====================");
        }
        if (builder.listener() != null) {
            builder.listener().onNetworkProgress(builder.id(), progress, totalSize);
        }
    }

    @Override
    public void deliverSuccess(IRequestBuilder builder, Object obj) {
        if (builder.listener() != null) {
            builder.listener().onNetworkSuccess(builder.id(), obj);
        }
    }

    @Override
    public void deliverError(IRequestBuilder builder, NetError error) {
        if (builder.listener() != null) {
            builder.listener().onNetworkError(builder.id(), error);
        }
    }


    protected Object tag() {
        return mTag;
    }

    protected Delivery getDelivery() {
        return mDelivery;
    }

    abstract public void load(NetworkRequest networkRequest, int id, NetworkListener lsn);

    abstract public void cancel(int id);

    abstract public void cancelAll();
}
