package lib.network.param;

/**
 * @author WDSG
 */
public class NameValuePair {
    private String mName = "";
    private String mValue = "";

    public NameValuePair() {
    }

    public NameValuePair(String name, String value) {
        setName(name);
        setValue(value);
    }

    public void setName(String name) {
        if (name == null) {
            return;
        }
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setValue(String value) {
        if (value == null) {
            return;
        }
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }
}
