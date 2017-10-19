import cn.thinkjoy.ctd.bean.Auth;
import cn.thinkjoy.ctd.bean.VoiceResponse;
import cn.thinkjoy.ctd.util.AbstractSdkSender;
import org.apache.http.HttpException;
import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;

public class TestClick2Call

{
	//����ţ��ն�����ʾ�����к���
	private static String displayNbr = "+8657156056200";
	//˫��ͨ���ĺ���1
    private static String callerNbr = "+8615191469259";
    //˫��ͨ���ĺ���2
    private static String calleeNbr = "+8618292482654";
    //������Žӿڵ�����URI
    private static String url="https://211.138.118.15:1101/tropo/click2CallEx/v1";
//    private static String url = "https://aep.zmccopen.com/tropo/click2CallEx/v1";
    //״̬����ص���ַ
    private static String statusURL = "http://xxxxx/xx";
    //App��ʶ
    private static String appKey = "fe6ecd699b7949d7b03b8f66d5e4d49a";
    //App��Կ
    private static String appSecret = "be20238a3b476070";
    //�������Զ���CDR�ֶΣ����ֶλᱻ��¼�ڻ����ļ��У�Э�������������û�������
    private static String developerCDR = "xxxxxxxx";
    //��������
    private static int languageType = 1;
    //֤���ļ�
//    private static String webCertFile = "webCert_translate.cer";
    private static String webCertFile = "iSimularClient2.cer";

    
    public static void main(String[] args) throws HttpException, UnsupportedEncodingException
    {
        //��װ��Ϣͷ
    	HttpPost postMethod = new HttpPost(url);
        AbstractSdkSender abstractSdkSender = new AbstractSdkSender(webCertFile);
        Auth auth = new Auth(appKey, appSecret);
        abstractSdkSender.addHttpHeaders(postMethod, auth);
        
        //��װ��Ϣ��
//        VoiceRequest voiceRequest = new VoiceRequest(displayNbr, calleeNbr, callerNbr, languageType, developerCDR, statusURL);
//        JSONObject voiceRequestJson = voiceRequest.initParams();
//        postMethod.setEntity(abstractSdkSender.initSendSmsEntity(voiceRequestJson));
        
        //��װ��Ӧ��Ϣ
        VoiceResponse voiceResponse = new VoiceResponse();
        
        //����HTTP����
        abstractSdkSender.sendMessage(postMethod, voiceResponse);
    }
}