package lib.network.error;

/**
 * @author WDSG
 */
public class ParseNetError extends NetError {

    public ParseNetError() {
        super();
    }

    public ParseNetError(String msg) {
        super(msg);
    }

    public ParseNetError(int code, String msg) {
        super(code, msg);
    }
}
