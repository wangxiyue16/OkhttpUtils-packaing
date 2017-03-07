package lib.network.bean;


import lib.network.error.CallbackEmptyError;
import lib.network.error.CancelError;
import lib.network.error.ConnectionNetError;
import lib.network.error.NetError;
import lib.network.error.ParseNetError;

/**
 * 网络任务监听
 *
 * @author WDSG
 */
public interface NetworkListener {
    /**
     * 任务数据回调
     *
     * @param id 编号
     * @param nr 返回的数据
     * @return
     */
    Object onNetworkResponse(int id, NetworkResponse nr);

    /**
     * 任务成功
     *
     * @param id     编号
     * @param result 解析后的数据
     */
    void onNetworkSuccess(int id, Object result);

    /**
     * 任务错误, 类型如下:
     * <pre>
     * {@link ConnectionNetError} 网络错误
     * {@link ParseNetError} 网络错误
     * {@link CancelError} 取消任务
     * {@link CallbackEmptyError} 没有设置回调
     * <pre/>
     * @param id  编号
     * @param error 错误
     */
    void onNetworkError(int id, NetError error);

    /**
     * 任务下载或上传进度
     *
     * @param id        编号
     * @param progress  进度
     * @param totalSize 总大小
     */
    void onNetworkProgress(int id, float progress, long totalSize);
}
