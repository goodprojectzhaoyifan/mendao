package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;

public class VoiceRequest
{
    private String displayNbr;
    
    private String calleeNbr;
    
    private String callerNbr;
    
    private Integer languageType;
    
    private String developerCDR;
    
    private String statusURL;

	private boolean callRecord;

	private String accessToken;
    
	public VoiceRequest(String displayNbr, String calleeNbr,
			String callerNbr, Integer languageType, String developerCDR,
			String statusURL,boolean callRecord,String accessToken) {
		this.displayNbr = displayNbr;
		this.calleeNbr = calleeNbr;
		this.callerNbr = callerNbr;
		this.languageType = languageType;
		this.developerCDR = developerCDR;
		this.statusURL = statusURL;
		this.callRecord=callRecord;
		this.accessToken=accessToken;
	}
    
    public JSONObject initParams()
    {
    	JSONObject param = new JSONObject();
    	param.put("displayNbr", displayNbr);
    	param.put("calleeNbr", calleeNbr);
    	param.put("callerNbr", callerNbr);
    	param.put("languageType", languageType);
    	param.put("developerCDR", developerCDR);
    	param.put("statusURL", statusURL);
		param.put("callRecord",callRecord);
		param.put("accessToken",accessToken);
    	return param;
    }
    
}
