package lib.network.provider.ok.request;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.util.List;

import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkRequest.TNetworkMethod;
import lib.network.param.NameValuePair;

/**
 * @author WDSG
 */
public class PostBuilder extends BaseBuilder {

    public PostBuilder(NetworkRequest request, Object tag, int id, NetworkListener listener) {
        super(request, tag, id, listener);
    }

    @Override
    protected OkHttpRequestBuilder initBuilder() {
        String url = request().url();
        List<NameValuePair> pairs = request().params();

        PostFormBuilder builder = OkHttpUtils.post().url(url);

        if (pairs != null) {
            for (NameValuePair pair : pairs) {
                builder.addParams(pair.getName(), pair.getValue());
            }
        }

        return builder;
    }

    @Override
    public TNetworkMethod method() {
        return TNetworkMethod.post;
    }
}
