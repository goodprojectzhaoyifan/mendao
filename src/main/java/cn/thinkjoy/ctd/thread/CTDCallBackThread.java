package cn.thinkjoy.ctd.thread;

import cn.thinkjoy.ctd.bean.CtdCallInfoBean;
import cn.thinkjoy.ctd.service.CTDCallBackService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wpliu on 16/3/14.
 */
@Component("ctdCallBackThread")
public class CTDCallBackThread   extends Observable implements  Runnable{

    private static final Logger logger = LoggerFactory.getLogger(CTDCallBackThread.class);

    public static  BlockingQueue<JSONObject> queue=new LinkedBlockingQueue<JSONObject>();

        public  static Map<String,CtdCallInfoBean> map=new HashMap<String,CtdCallInfoBean>();

    @Autowired
    private CTDCallBackService ctdCallBackService;

    public void run(){
            while (true) {
                try{
                JSONObject jsonObject = queue.poll();
                if (jsonObject != null) {
                    String sessionid = jsonObject.get("sessionId").toString();
                    String reason = jsonObject.get("reason").toString();
                    String state = jsonObject.get("state").toString();
                    String date = jsonObject.get("date").toString();
                    String callerId = jsonObject.get("callerId").toString();
                    String calledId = jsonObject.get("calledId").toString();
                    callerId=callerId.substring(2);
                    calledId=calledId.substring(2);
                    //判断是否第一次调用

                    if(map.get(sessionid)==null){
                        CtdCallInfoBean ctdCallInfoBean=new CtdCallInfoBean();
                        ctdCallInfoBean.setSessionId(sessionid);
                        ctdCallInfoBean.setState(state);
                        ctdCallInfoBean.setDate(date);
                        ctdCallInfoBean.setReason(reason);
                        ctdCallInfoBean.setCalledId(calledId);
                        ctdCallInfoBean.setCallerId(callerId);
                        ctdCallInfoBean.setStep(1);
                        map.put(sessionid,ctdCallInfoBean);
                        continue;

                    }else{
                        CtdCallInfoBean ctdCallInfoBean= map.get(sessionid);
                        int step=ctdCallInfoBean.getStep();
                         if(step==0){
                             logger.info("sessionid:"+ctdCallInfoBean.getSessionId()+",step:"+step);
                             continue;
                         }
                       else if(step==1){
                             logger.info("sessionid:"+ctdCallInfoBean.getSessionId()+",step:"+step);
                            //主叫失败
                            if("DISCONNECTED".equals(state)||"FAILED".equals(state)){
                                ctdCallBackService.callBack(sessionid, callerId, calledId, 0, date, 0 + "");
                                ctdCallInfoBean.setStep(0);
                            }else if("ANSWERED".equals(state)){
                                ctdCallInfoBean.setDate(date);
                                ctdCallInfoBean.setCaller(calledId);
                                ctdCallInfoBean.setStep(2);
                            }

                        }else if(step==2){
                             logger.info("sessionid:"+ctdCallInfoBean.getSessionId()+",step:"+step);
                            //被叫失败
                            if("DISCONNECTED".equals(state)||"FAILED".equals(state)){
                                ctdCallBackService.callBack(sessionid,callerId,calledId,0,date,0+"");
                                ctdCallInfoBean.setStep(0);
                            }else if("ANSWERED".equals(state)){
                                if(!calledId.equals(ctdCallInfoBean.getCaller())){
                                    ctdCallInfoBean.setDate(date);
                                    ctdCallInfoBean.setCalled(calledId);
                                    ctdCallInfoBean.setStep(3);
                                }
                            }
                        }else if(step==3){
                             logger.info("sessionid:"+ctdCallInfoBean.getSessionId()+",step:"+step);
                            //主叫或被叫挂断 ，一段通话结束
                            if("DISCONNECTED".equals(state)||"FAILED".equals(state)){
                                SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS");
                                long duration= 0;
                                duration = (format.parse(date).getTime()-format.parse(ctdCallInfoBean.getDate()).getTime())/1000;
                                ctdCallBackService.callBack(sessionid,ctdCallInfoBean.getCaller(),calledId,duration,date,0+"");
                                ctdCallInfoBean.setStep(0);
                            }
                        }
                    }

                } else {
                    try {
                        Thread.sleep(1000l);
                    }catch (Exception e){
                        e.printStackTrace();
                        logger.error("回调线程出错",e);
                    }
                    continue;
                }

                }catch (Exception e){
                    e.printStackTrace();
                    logger.error("回调线程操作程序出错:"+e);
                }
            }

    }


}
