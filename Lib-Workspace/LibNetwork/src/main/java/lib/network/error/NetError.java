package lib.network.error;

/**
 * @author WDSG
 */
public class NetError {
    private String mMessage = "";
    private int mCode;

    public NetError() {
        this(0, "");
    }

    public NetError(String msg) {
        this(0, msg);
    }

    public NetError(int code) {
        this(code, "");
    }

    public NetError(int code, String msg) {
        mCode = code;
        mMessage = msg;
    }

    public int code() {
        return mCode;
    }

    public String message() {
        return mMessage;
    }

    @Override
    public String toString() {
        return mMessage;
    }
}
