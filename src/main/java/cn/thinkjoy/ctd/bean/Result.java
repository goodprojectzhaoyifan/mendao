package cn.thinkjoy.ctd.bean;

// TODO: Auto-generated Javadoc
/**
 * Result
 * <p>the common model for storage the result</p>
 */
public class Result
{
    
    /** The result code. */
    private String resultCode; //optional
    
    /** The result message. */
    private String resultMessage; //optional
    
    /**
     * Gets the result code.
     * 
     * @return the result code
     */
    public String getResultCode()
    {
        return resultCode;
    }
    
    /**
     * Sets the result code.
     * 
     * @param resultCode the new result code
     */
    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }
    
    /**
     * Gets the result message.
     * 
     * @return the result message
     */
    public String getResultMessage()
    {
        return resultMessage;
    }
    
    /**
     * Sets the result message.
     * 
     * @param resultMessage the new result message
     */
    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }
    
}
