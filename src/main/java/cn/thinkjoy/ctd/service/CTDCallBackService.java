package cn.thinkjoy.ctd.service;

/**
 * Created by wpliu on 16/3/13.
 */
public interface CTDCallBackService {


    void callBack(String sessionid, String callerId, String calledId, long duration, String date,String state);
}
