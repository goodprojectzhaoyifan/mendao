package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

// TODO: Auto-generated Javadoc

/**
 * HttpResponse
 * <p>the common model for storage the aep http response message<br>
 */
public abstract class HttpResponse
{
    //httpRspMessage
    /** The status code. */
    private int statusCode;
    
    /** The reason phrase. */
    private String reasonPhrase;
    
    /** The response content. */
    private String rspContent;
    
    /**
     * Gets the response content.
     * 
     * @return the response content
     */
    public String getRspContent()
    {
        return rspContent;
    }
    
    /**
     * Sets the response content.
     * 
     * @param rspContent the new response content
     * 
     */
    
    public void setRspContent(String rspContent)
    {
        this.rspContent = rspContent;
    }
    
    /**
     * Gets the status code.
     * 
     * @return the status code
     */
    public int getStatusCode()
    {
        return statusCode;
    }
    
    /**
     * Sets the status code.
     * 
     * @param statusCode the new status code
     */
    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }
    
    /**
     * Gets the reason phrase.
     * 
     * @return the reason phrase
     */
    public String getReasonPhrase()
    {
        return reasonPhrase;
    }
    
    /**
     * Sets the reason phrase.
     * 
     * @param reasonPhrase the new reason phrase
     */
    public void setReasonPhrase(String reasonPhrase)
    {
        this.reasonPhrase = reasonPhrase;
    }
    
    /**
     * Gets the json model from byte.
     * 
     * @param data the data
     * @return the json model from byte
     */
    protected JSONObject getJsonModelFromByte(byte[] data)
    {
        JSONObject jsonSrcObj = new JSONObject();
        if (null == data)
        {
            return jsonSrcObj;
        }
        
        try
        {
            Feature[] fArray = new Feature[] {};
            jsonSrcObj = (JSONObject)JSON.parse(data, fArray);
        }
        catch (Exception e)
        {
            return jsonSrcObj;
        }
        
        return jsonSrcObj;
    }
    
    /**
     * Parses the response.
     * 
     * @return the http response
     */
    public abstract HttpResponse parse();
}
