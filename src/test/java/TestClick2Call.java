import cn.thinkjoy.ctd.bean.Auth;
import cn.thinkjoy.ctd.bean.VoiceResponse;
import cn.thinkjoy.ctd.util.AbstractSdkSender;
import org.apache.http.HttpException;
import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;

public class TestClick2Call

{
	//接入号，终端上显示的主叫号码
	private static String displayNbr = "+8657156056200";
	//双向通话的号码1
    private static String callerNbr = "+8615191469259";
    //双向通话的号码2
    private static String calleeNbr = "+8618292482654";
    //点击拨号接口的请求URI
    private static String url="https://211.138.118.15:1101/tropo/click2CallEx/v1";
//    private static String url = "https://aep.zmccopen.com/tropo/click2CallEx/v1";
    //状态报告回调地址
    private static String statusURL = "http://xxxxx/xx";
    //App标识
    private static String appKey = "fe6ecd699b7949d7b03b8f66d5e4d49a";
    //App密钥
    private static String appSecret = "be20238a3b476070";
    //开发者自定义CDR字段，该字段会被记录在话单文件中，协助开发者区分用户话单。
    private static String developerCDR = "xxxxxxxx";
    //语音类型
    private static int languageType = 1;
    //证书文件
//    private static String webCertFile = "webCert_translate.cer";
    private static String webCertFile = "iSimularClient2.cer";

    
    public static void main(String[] args) throws HttpException, UnsupportedEncodingException
    {
        //封装消息头
    	HttpPost postMethod = new HttpPost(url);
        AbstractSdkSender abstractSdkSender = new AbstractSdkSender(webCertFile);
        Auth auth = new Auth(appKey, appSecret);
        abstractSdkSender.addHttpHeaders(postMethod, auth);
        
        //封装消息体
//        VoiceRequest voiceRequest = new VoiceRequest(displayNbr, calleeNbr, callerNbr, languageType, developerCDR, statusURL);
//        JSONObject voiceRequestJson = voiceRequest.initParams();
//        postMethod.setEntity(abstractSdkSender.initSendSmsEntity(voiceRequestJson));
        
        //封装响应消息
        VoiceResponse voiceResponse = new VoiceResponse();
        
        //发送HTTP请求
        abstractSdkSender.sendMessage(postMethod, voiceResponse);
    }
}