package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

public class VoiceResponse extends HttpResponse
{
    
    private boolean needReceipt = false;
    
    private Result result;
    
    private String sessionid;
    
    public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Result getResult()
    {
        return result;
    }
    
    public void setResult(Result result)
    {
        this.result = result;
    }
    
    public void setNeedReceipt(boolean needReceipt)
    {
        this.needReceipt = needReceipt;
    }
    
    public boolean isNeedReceipt()
    {
        return needReceipt;
    }
    
    @Override
    public VoiceResponse parse()
    {
        if (!StringUtils.isEmpty(getRspContent()))
        {
            JSONObject json = getJsonModelFromByte(getRspContent().getBytes());
            this.result = new Result();
            result.setResultCode(json.containsKey("code") ? json.getString("code") : StringUtils.EMPTY);
            result.setResultMessage(json.containsKey("description") ? json.getString("description") : StringUtils.EMPTY);
            int httpCode = getStatusCode();
            if (HttpStatus.SC_OK <= httpCode && HttpStatus.SC_BAD_REQUEST > httpCode)
            {
                if (!json.containsKey("sessionid"))
                {
                    return this;
                }
                
                this.needReceipt = true;
                this.sessionid = json.getString("sessionid");
            }
        }
        return this;
    }
}
