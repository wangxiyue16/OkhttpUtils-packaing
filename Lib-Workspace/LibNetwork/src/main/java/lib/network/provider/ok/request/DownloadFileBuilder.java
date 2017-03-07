package lib.network.provider.ok.request;

import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkRequest.TNetworkMethod;

/**
 * @author WDSG
 */
public class DownloadFileBuilder extends GetBuilder {

    public DownloadFileBuilder(NetworkRequest request, Object tag, int id, NetworkListener listener) {
        super(request, tag, id, listener);
    }

    @Override
    public TNetworkMethod method() {
        return TNetworkMethod.download_file;
    }
}
