package lib.network.param;

public class NameByteValuePair {

    private String mName = "";
    private byte[] mValue;

    public NameByteValuePair() {
    }

    public NameByteValuePair(String name, byte[] value) {
        setName(name);
        setValue(value);
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

    public byte[] getValue() {
        return mValue;
    }

    public void setValue(byte[] data) {
        mValue = data;
    }
}
