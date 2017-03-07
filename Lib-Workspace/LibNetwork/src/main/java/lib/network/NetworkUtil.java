package lib.network;

import android.content.Context;

import java.util.List;

import lib.network.param.NameValuePair;

/**
 * http里使用的一些小工具
 *
 * @author WDSG
 */
public class NetworkUtil {

    public static final String KTextEmpty = "";

    private static final char KSymbolQuestion = '?';
    private static final char KSymbolAnd = '&';
    private static final char KSymbolEqual = '=';


    /**
     * @param url
     * @param params
     * @return
     */
    public static String generateGetUrl(String url, List<NameValuePair> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(url);
        sb.append(KSymbolQuestion);

        sb.append(generateGetParams(params));

        return sb.toString();
    }

    /**
     * 按照格式生成get参数
     *
     * @param params
     * @return
     */
    public static String generateGetParams(List<NameValuePair> params) {
        if (params == null || params.isEmpty()) {
            return KTextEmpty;
        }

        StringBuilder sb = new StringBuilder();
        NameValuePair pair = null;
        for (int i = 0; i < params.size(); i++) {
            pair = params.get(i);
            if (i != 0) {
                sb.append(KSymbolAnd);
            }
            sb.append(pair.getName());
            sb.append(KSymbolEqual);
            sb.append(pair.getValue());
        }

        return sb.toString();
    }

    public static void init(Context context) {
    }
}
