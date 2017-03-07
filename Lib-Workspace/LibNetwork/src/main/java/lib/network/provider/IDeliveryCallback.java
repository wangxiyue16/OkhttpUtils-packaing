package lib.network.provider;

import lib.network.error.NetError;

public interface IDeliveryCallback {
    void deliverProgress(IRequestBuilder builder, float progress, long totalSize);

    void deliverError(IRequestBuilder builder, NetError error);

    void deliverSuccess(IRequestBuilder builder, Object obj);
}