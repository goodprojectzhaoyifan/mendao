package cn.thinkjoy.ctd.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.ctd.bean.*;
import cn.thinkjoy.ctd.service.CTDCallBackService;
import cn.thinkjoy.ctd.thread.CTDCallBackThread;
import cn.thinkjoy.ctd.util.AbstractSdkSender;
import cn.thinkjoy.ctd.util.Base64;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 点击拨号控制类
 */
@Scope("prototype")
@Controller("clickToDailController")
@RequestMapping(value = "/click")
public class ClickToDailController {

    private static final Logger logger = LoggerFactory.getLogger(ClickToDailController.class);


    @Autowired
    private PropertyBean propertyBean;

    @Autowired
    private CTDCallBackService ctdCallBackService;



    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView clickView(HttpServletRequest request){

        return new ModelAndView("index");
    }

    /**
     * 点击拨号
     */
    @RequestMapping(value = "/click2Call", method = RequestMethod.POST)
    @ResponseBody
     public Map<String,Object> click2Call (@RequestBody JSONObject jsonObject,HttpServletRequest request,HttpServletResponse response){
         VoiceResponse voiceResponse = new VoiceResponse();
         Map<String,Object> resultMap=new HashMap<String, Object>();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
         try {
             String cerFile=  request.getServletContext().getRealPath("/") +propertyBean.getWebCertFile();
             HttpPost postMethod = new HttpPost(propertyBean.getClick2callUrl());
             AbstractSdkSender abstractSdkSender = new AbstractSdkSender(cerFile);
             Auth auth = new Auth(propertyBean.getAppKey(), propertyBean.getAppSecret());
             abstractSdkSender.addHttpHeaders(postMethod, auth);

             String calleeNbr=jsonObject.getString("calleeNbr");
             String callerNbr=jsonObject.getString("callerNbr");
             String statusUrl=propertyBean.getStatusUrl();
             String accesstoken="143090935490304696053727091608412356509368489:1101@uportal.ims.zj.chinamobile.com";
//             String accesstoken="";
             VoiceRequest voiceRequest = new VoiceRequest(callerNbr, calleeNbr, callerNbr, propertyBean.getLanguageType(), propertyBean.getDeveloperCDR(),  Base64.getBase64(statusUrl),false,accesstoken);
             JSONObject voiceRequestJson = voiceRequest.initParams();
             postMethod.setEntity(abstractSdkSender.initSendSmsEntity(voiceRequestJson));
             abstractSdkSender.sendMessage(postMethod, voiceResponse);
             String responseContent=voiceResponse.getRspContent();
             JSONObject resjsonObj=new JSONObject();
             resjsonObj=JSONObject.parseObject(responseContent);
             resultMap.put("sessionid",resjsonObj.get("sessionid"));
             resultMap.put("code",200);
             resultMap.put("description",resjsonObj.get("description"));
             ctdCallBackService.callBack(resjsonObj.get("sessionid").toString(), "", "", 0,dateFormat.format(new Date()) , 0 + "");
             return resultMap;

         }catch (Exception e){
             Result result=new Result();
             result.setResultCode("0000");
             result.setResultMessage("点击拨号系统内部错误");
             voiceResponse.setResult(result);
             voiceResponse.setStatusCode(500);
             logger.error("点击拨号失败:" + e);
             return  resultMap;
         }


     }


    /**
     * 停止拨号
     * @param request
     * @param response
     */
    @RequestMapping(value = "/click2Stop", method = RequestMethod.POST)
    @ResponseBody
     public VoiceStopResponse click2Stop(@RequestBody JSONObject jsonObject,HttpServletRequest request,HttpServletResponse response){
         VoiceStopResponse voiceStopResponse = new VoiceStopResponse();
         try {
             String cerFile=  request.getServletContext().getRealPath("/") +propertyBean.getWebCertFile();
             String sessionId=jsonObject.getString("sessionid");
             HttpPost postMethod = new HttpPost(propertyBean.getClick2stopUrl());
             AbstractSdkSender abstractSdkSender = new AbstractSdkSender(cerFile);
             Auth auth = new Auth(propertyBean.getAppKey(), propertyBean.getAppSecret());
             abstractSdkSender.addHttpHeaders(postMethod, auth);
             VoiceStopRequest voiceStopRequest = new VoiceStopRequest(sessionId, propertyBean.getDeveloperCDR());
             JSONObject voiceStopRequestJson = voiceStopRequest.initParams();
             postMethod.setEntity(abstractSdkSender.initSendSmsEntity(voiceStopRequestJson));
             abstractSdkSender.sendStopMessage(postMethod, voiceStopResponse);
             return voiceStopResponse;
         }catch (Exception e){
             Result result=new Result();
             result.setResultCode("0000");
             result.setResultMessage("点击拨号停止系统内部错误");
             voiceStopResponse.setResult(result);
             voiceStopResponse.setStatusCode(500);
             logger.error("点击拨号停止失败:" + e);
             return voiceStopResponse;
         }
     }


    /**
     * 停止拨号
     * @param request
     * @param response
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public void callback(@RequestBody JSONObject jsonObject,HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            logger.info("回调参数:"+jsonObject.toJSONString());
            CTDCallBackThread.queue.put(jsonObject);
            PrintWriter pw=response.getWriter();
            map.put("resultCode",0);
            map.put("resultMsg","成功");
            map.put("sessionId",jsonObject.get("sessionId").toString());
            pw.write(JSONObject.toJSONString(map));
        }catch (Exception e){
            Result result=new Result();
            result.setResultCode("0000");
            result.setResultMessage("点击拨号停止系统内部错误");
            logger.error("点击拨号停止失败:" + e);
        }
    }




}
