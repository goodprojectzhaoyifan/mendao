package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;

public class VoiceStopRequest
{
    private String sessionId;
	private String developerCDR;
	public VoiceStopRequest(String sessionId,String developerCDR) {
		this.sessionId = sessionId;
		this.developerCDR=developerCDR;
	}
    
    public JSONObject initParams()
    {
    	JSONObject param = new JSONObject();
    	param.put("sessionid", sessionId);
		param.put("developerCDR",developerCDR);
    	return param;
    }
    
}
