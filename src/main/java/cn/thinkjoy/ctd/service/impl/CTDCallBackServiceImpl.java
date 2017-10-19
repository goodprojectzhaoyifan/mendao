package cn.thinkjoy.ctd.service.impl;

import cn.thinkjoy.ctd.dao.ICTDCallBackDao;
import cn.thinkjoy.ctd.service.CTDCallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wpliu on 16/3/13.
 */
@Service("ctdCallBackService")
public class CTDCallBackServiceImpl implements CTDCallBackService {

    @Autowired
    private ICTDCallBackDao callBackDao;



    @Override
    public void callBack(String sessionid, String callerId, String calledId, long duration, String date,String state) {
        callBackDao.updateCallInfo(sessionid,  duration, date, state);
        callBackDao.updateCallSubRel(sessionid,duration);
    }
}
