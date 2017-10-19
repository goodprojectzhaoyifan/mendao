package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

public class VoiceStopResponse extends HttpResponse
{
    
    private boolean needReceipt = false;
    
    private Result result;
    
    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
    public VoiceStopResponse parse()
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
                if (!json.containsKey("status"))
                {
                    return this;
                }
                
                this.needReceipt = true;
                this.status = json.getString("status");
            }
        }
        return this;
    }
}
