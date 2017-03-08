package lib.network.bean;


import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lib.network.NetworkUtil;
import lib.network.param.NameByteValuePair;
import lib.network.param.NameFileValuePair;
import lib.network.param.NameValuePair;
import okhttp3.OkHttpClient;

/**
 * Network任务实例
 *
 * @author WDSG
 */
public class NetworkRequest {

    private static final String TAG = NetworkRequest.class.getSimpleName();

    public enum TNetworkMethod {
        unknown,
        get,
        post,
        upload,
        download_file // 文件下载
    }

    private List<NameValuePair> mParams;
    private List<NameByteValuePair> mByteParams;
    private List<NameValuePair> mHeaders;
    private List<NameFileValuePair> mFileParams;

    private TNetworkMethod mMethod = TNetworkMethod.unknown;

    private String mUrl;

    private String mDestDir;
    private String mDestFileName;

    private NetworkRetry mRetry;


    public NetworkRequest(TNetworkMethod method, String url) {
        mMethod = method;
        mUrl = url;

        mParams = new ArrayList<NameValuePair>();
    }

    public void setRetry(NetworkRetry retry) {
        mRetry = retry;
    }

    public NetworkRetry getRetry() {
        return mRetry;
    }

    /**
    * String dir 文件目录(文件所在路径)
    */
    private void setDestDir(String dir) {
        mDestDir = dir;
    }

    public String dir() {
        return mDestDir;
    }

    /**
    * String name 文件名
    */
    public void setFileName(String name) {
        mDestFileName = name;
    }

    public String fileName() {
        return mDestFileName;
    }

    public void addParam(String name, String value) {
        mParams.add(new NameValuePair(name, value));
    }

    public void addParam(String name, int value) {
        mParams.add(new NameValuePair(name, String.valueOf(value)));
    }

    public void addParams(List<NameValuePair> pairs) {
        for (NameValuePair pair : pairs) {
            addParam(pair.getName(), pair.getValue());
        }
    }

    /**
     * 添加二进制param
     *
     * @param name
     * @param value
     */
    public void addByteParam(String name, byte[] value) {
        addByteParamWithExtend(name, value, NetworkUtil.KTextEmpty);
    }

    /**
     * 添加带有拓展名的二进制param
     * 部分接口需要传文件后缀名才能正确解析
     *
     * @param name
     * @param value
     * @param extend 拓展名, 如".jpg", ".png"等
     */
    public void addByteParamWithExtend(String name, byte[] value, String extend) {
        if (mByteParams == null) {
            mByteParams = new ArrayList<NameByteValuePair>();
        }
        mByteParams.add(new NameByteValuePair(name + extend, value));
    }

    public List<NameByteValuePair> getByteParams() {
        return mByteParams;
    }

    /**
     * 添加文件param
     *
     * @param name
     * @param uri
     */
    public void addFileParam(String name, String uri) {
        if (mFileParams == null) {
            mFileParams = new ArrayList<NameFileValuePair>();
        }
        mFileParams.add(new NameFileValuePair(name, uri));
    }

    public List<NameFileValuePair> getFileParams() {
        return mFileParams;
    }

    /**
     * 添加header数据
     *
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        if (mHeaders == null) {
            mHeaders = new ArrayList<NameValuePair>();
        }
        mHeaders.add(new NameValuePair(name, value));
    }

    public void addHeader(String name, int value) {
        addHeader(name, String.valueOf(value));
    }

    public List<NameValuePair> headers() {
        return mHeaders;
    }

    public List<NameValuePair> params() {
        return mParams;
    }

    public TNetworkMethod method() {
        return mMethod;
    }

    public String url() {
        return mUrl;
    }

    public static NetworkRequest newPost(String url) {
        return new NetworkRequest(TNetworkMethod.post, url);
    }

    public static NetworkRequest newGet(String url) {
        return new NetworkRequest(TNetworkMethod.get, url);
    }

    public static NetworkRequest newUpload(String url) {
        return new NetworkRequest(TNetworkMethod.upload, url);
    }

    public static NetworkRequest newFileDownload(String url, String destDir, String destFileName) {
        NetworkRequest task = new NetworkRequest(TNetworkMethod.download_file, url);
        task.setDestDir(destDir);
        task.setFileName(destFileName);
        return task;
    }

    public static void init(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
