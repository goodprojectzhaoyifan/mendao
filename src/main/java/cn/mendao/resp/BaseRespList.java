package cn.mendao.resp;

import java.util.List;

/**
 * Created by warden on 17/11/1.
 */
public class BaseRespList {

    private long code;
    private String msg;
    private List<?> list;

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

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }


}
