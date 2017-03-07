package lib.network.param;

public class NameFileValuePair {

    private String mName = "";
    private String mUri = "";

    public NameFileValuePair(String name, String uri) {
        setName(name);
        setValue(uri);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        if (name == null) {
            return;
        }

        mName = name;
    }

    public String getValue() {
        return mUri;
    }

    public void setValue(String uri) {
        if (uri == null) {
            return;
        }

        mUri = uri;
    }
}
