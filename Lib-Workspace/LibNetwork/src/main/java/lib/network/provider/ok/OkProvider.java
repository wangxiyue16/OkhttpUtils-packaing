package lib.network.provider.ok;

import android.util.SparseArray;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkRequest.TNetworkMethod;
import lib.network.provider.BaseProvider;
import lib.network.provider.ok.callback.DownloadFileCallback;
import lib.network.provider.ok.callback.ObjectCallback;
import lib.network.provider.ok.request.BaseBuilder;
import lib.network.provider.ok.request.DownloadFileBuilder;
import lib.network.provider.ok.request.GetBuilder;
import lib.network.provider.ok.request.PostBuilder;
import lib.network.provider.ok.request.UploadBuilder;

/**
 * @author WDSG
 */
public class OkProvider extends BaseProvider {

    private SparseArray<RequestCall> mCallMap;

    public OkProvider(Object tag) {
        super(tag);

        mCallMap = new SparseArray<>();
    }

    @Override
    public void load(NetworkRequest networkRequest, final int id, final NetworkListener lsn) {
        // FIXME: id的检测应该是在网络callback的时候进行, 暂时先放到这里, 如果出问题的话再更改
        if (mCallMap.get(id) != null) {
            if (mCallMap.get(id).getCall().isExecuted()) {
                mCallMap.remove(id);
            } else {
                return;
            }
        }

        BaseBuilder builder = null;
        switch (networkRequest.method()) {
            case get: {
                builder = new GetBuilder(networkRequest, tag(), id, lsn);
            }
            break;
            case post: {
                builder = new PostBuilder(networkRequest, tag(), id, lsn);
            }
            break;
            case download_file: {
                builder = new DownloadFileBuilder(networkRequest, tag(), id, lsn);
            }
            break;
            case upload: {
                builder = new UploadBuilder(networkRequest, tag(), id, lsn);
            }
            break;
        }

        RequestCall call = builder.build();
        mCallMap.put(id, call);

        if (networkRequest.method() == TNetworkMethod.download_file) {
            call.execute(new DownloadFileCallback(builder, getDelivery()));
        } else {
            call.execute(new ObjectCallback(builder, getDelivery()));
        }
    }

    @Override
    public void cancel(int id) {
        if (mCallMap.get(id) != null) {
            mCallMap.get(id).cancel();
            mCallMap.remove(id);
        }
    }

    @Override
    public void cancelAll() {
        OkHttpUtils.getInstance().cancelTag(tag());
    }
}
