package hnpbc.bean;

public class FeedBack {
    public final static String TYPE_SUCC = "0";
    public final static String TYPE_FAIL = "1";
    public final static String TYPE_NO_LOGIN = "2";

    private String type;
    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
