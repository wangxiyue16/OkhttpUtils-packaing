package lib.network.bean;

import java.io.InputStream;

import lib.network.NetworkUtil;


/**
 * 保存http返回的数据
 *
 * @author WDSG
 */
public class NetworkResponse {
    private String mText = NetworkUtil.KTextEmpty;
    private InputStream mInputStream;

    public void setText(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    public void setInputStream(InputStream is) {
        mInputStream = is;
    }

    public InputStream getInputStream() {
        return mInputStream;
    }
}
