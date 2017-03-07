package lib.network.provider;

import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkRequest.TNetworkMethod;

/**
 * @author WDSG
 */
public interface IRequestBuilder {
    /**
     * 请求的id(what)
     *
     * @return
     */
    int id();

    /**
     * 绑定的tag(class name)
     *
     * @return
     */
    Object tag();

    TNetworkMethod method();

    NetworkRequest request();

    NetworkListener listener();

    <T> T build();
}
