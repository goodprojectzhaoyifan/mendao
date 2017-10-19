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
    //���������ֹ�ӿڵ�����URI
    private static String url = "https://211.138.118.15:1101/tropo/callStopEx/v1";
    //App��ʶ
    private static String appKey = "fe6ecd699b7949d7b03b8f66d5e4d49a";
    //App��Կ
    private static String appSecret = "be20238a3b476070";
    //��������Žӿڡ����ص�sessionid
    private static String sessionId = "6d26ee7a787cd5901fe71a43174f6f6d";
    //֤���ļ�
    private static String webCertFile = "iSimularClient2.cer";
    //�������Զ���CDR�ֶΣ����ֶλᱻ��¼�ڻ����ļ��У�Э�������������û�������
    private static String developerCDR = "xxxxxxxx";
    
    public static void main(String[] args) throws HttpException, UnsupportedEncodingException
    {
        //��װ��Ϣͷ
    	HttpPost postMethod = new HttpPost(url);
        AbstractSdkSender abstractSdkSender = new AbstractSdkSender(webCertFile);
        Auth auth = new Auth(appKey, appSecret);
        abstractSdkSender.addHttpHeaders(postMethod, auth);
        
        //��װ��Ϣ��
        VoiceStopRequest voiceStopRequest = new VoiceStopRequest(sessionId,developerCDR);
        JSONObject voiceStopRequestJson = voiceStopRequest.initParams();
        System.out.println(voiceStopRequestJson.toString());
        postMethod.setEntity(abstractSdkSender.initSendSmsEntity(voiceStopRequestJson));
        
        //��װ��Ӧ��Ϣ
        VoiceStopResponse voiceStopResponse = new VoiceStopResponse();
        
        //����HTTP����
        abstractSdkSender.sendStopMessage(postMethod, voiceStopResponse);
    }
}