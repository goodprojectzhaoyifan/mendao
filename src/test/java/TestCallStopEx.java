import cn.thinkjoy.ctd.bean.Auth;
import cn.thinkjoy.ctd.bean.VoiceStopRequest;
import cn.thinkjoy.ctd.bean.VoiceStopResponse;
import cn.thinkjoy.ctd.util.AbstractSdkSender;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpException;
import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;

public class TestCallStopEx

{
    //点击拨号终止接口的请求URI
    private static String url = "https://211.138.118.15:1101/tropo/callStopEx/v1";
    //App标识
    private static String appKey = "fe6ecd699b7949d7b03b8f66d5e4d49a";
    //App密钥
    private static String appSecret = "be20238a3b476070";
    //“点击拨号接口”返回的sessionid
    private static String sessionId = "6d26ee7a787cd5901fe71a43174f6f6d";
    //证书文件
    private static String webCertFile = "iSimularClient2.cer";
    //开发者自定义CDR字段，该字段会被记录在话单文件中，协助开发者区分用户话单。
    private static String developerCDR = "xxxxxxxx";
    
    public static void main(String[] args) throws HttpException, UnsupportedEncodingException
    {
        //封装消息头
    	HttpPost postMethod = new HttpPost(url);
        AbstractSdkSender abstractSdkSender = new AbstractSdkSender(webCertFile);
        Auth auth = new Auth(appKey, appSecret);
        abstractSdkSender.addHttpHeaders(postMethod, auth);
        
        //封装消息体
        VoiceStopRequest voiceStopRequest = new VoiceStopRequest(sessionId,developerCDR);
        JSONObject voiceStopRequestJson = voiceStopRequest.initParams();
        System.out.println(voiceStopRequestJson.toString());
        postMethod.setEntity(abstractSdkSender.initSendSmsEntity(voiceStopRequestJson));
        
        //封装响应消息
        VoiceStopResponse voiceStopResponse = new VoiceStopResponse();
        
        //发送HTTP请求
        abstractSdkSender.sendStopMessage(postMethod, voiceStopResponse);
    }
}