package lib.network.provider.ok.request;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;

import lib.network.NetworkUtil;
import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkRequest.TNetworkMethod;

/**
 * @author WDSG
 */
public class GetBuilder extends BaseBuilder {

    public GetBuilder(NetworkRequest request, Object tag, int id, NetworkListener listener) {
        super(request, tag, id, listener);
    }

    @Override
    protected OkHttpRequestBuilder initBuilder() {
        String url = NetworkUtil.generateGetUrl(request().url(), request().params());
        return OkHttpUtils.get().url(url);
    }

    @Override
    public TNetworkMethod method() {
        return TNetworkMethod.get;
    }
}
