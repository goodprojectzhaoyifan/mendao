package cn.mendao.resp;

/**
 * Created by warden on 17/11/1.
 */
public class BaseResp {

    private long code;
    private String msg;


    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
