package cn.thinkjoy.ctd.util;

import cn.thinkjoy.ctd.bean.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * The Class AbstractSdkSender.
 */
public class AbstractSdkSender
{
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AbstractSdkSender.class);
    
    /** The wsse header. */
    private static final String WSSE_HEADER = "X-WSSE";
    
    /** The content-type header. */
    private static final String CONTENT_TYPE = "Content-Type";
    
    /** The content-type content. */
    private static final String CONTENT_CONTENT = "application/json;charset=UTF-8";
    
    /** The host header. */
    private static final String HOST = "Host";
    
    /** The host content. */
    private static final String HOST_CONTENT = "aep.sdp.com";
    //private static final String HOST_CONTENT = "aep.test.sdp.com";

    /** The authorization header. */
    private static final String AUTHORIZATION_HEADER = "Authorization";
    
    /** The authorization content. */
    private static final String AUTHORIZATION_CONTENT =
        "WSSE realm=\"SDP\", profile=\"UsernameToken\", type=\"Appkey\"";

    /** The accept-encoding header. */
    private static final String ACCEPT_ENCODING = "Accept-Encoding";

    /** The accept-encoding content. */
    private static final String ACCEPT_ENCODING_CONTENT = "deflate";
    
    /** The client for send http message. */
    private CloseableHttpClient client;
    
    public AbstractSdkSender(String webCertFile){
    	try {
    		SSLSocketFactoryLoad.loadSSLSocketFactory(webCertFile);
    		SSLConnectionSocketFactory socketFactory = SSLSocketFactoryLoad.getSocketFactory();
            client = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    /**
     * Send message.
     * 
     * @param httpMethod the method : POST,GET,PUT...
     * @param response the http response when client receviced the messsage
     * @throws org.apache.http.HttpException the http exception
     * @see org.apache.http.client.methods.HttpUriRequest
     * @see VoiceResponse
     */
    public void sendMessage(HttpUriRequest httpMethod, VoiceResponse response){

        // send message
        try
        {

            CloseableHttpResponse httpResponse = client.execute(httpMethod);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (null != statusLine)
            {
                response.setStatusCode(statusLine.getStatusCode());
                response.setReasonPhrase(statusLine.getReasonPhrase());
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null)
            {
                String content = EntityUtils.toString(httpEntity, "UTF-8");
                logger.debug("The httpResponse is :\r\n\r\n" + httpResponse.getStatusLine() + "\n" + content
                        + "\r\n\r\n");
                response.setRspContent(content);
            }
            response.parse();
            EntityUtils.consume(httpEntity);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }

    /**
     * Send message.
     *
     * @param httpMethod the method : POST,GET,PUT...
     * @param response the http response when client receviced the messsage
     * @throws org.apache.http.HttpException the http exception
     * @see org.apache.http.client.methods.HttpUriRequest
     * @see VoiceResponse
     */
    public void sendStopMessage(HttpUriRequest httpMethod, VoiceStopResponse response){
        logger.debug("Send message to the url : " + httpMethod.getURI() + ".");
        logger.debug("The httpMethod is : " + httpMethod.getMethod() + ".");

        // send message
        try
        {

            CloseableHttpResponse httpResponse = client.execute(httpMethod);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (null != statusLine)
            {
                response.setStatusCode(statusLine.getStatusCode());
                response.setReasonPhrase(statusLine.getReasonPhrase());
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null)
            {
                String content = EntityUtils.toString(httpEntity, "UTF-8");
                logger.debug("The httpResponse is :\r\n\r\n" + httpResponse.getStatusLine() + "\n" + content
                    + "\r\n\r\n");
                System.out.println("The httpResponse is :\r\n\r\n" + httpResponse.getStatusLine() + "\n" + content
                        + "\r\n\r\n");
                response.setRspContent(content);
            }
            response.parse();
            EntityUtils.consume(httpEntity);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
    }

    /**
     * Send PresentData message.
     *
     * @param httpMethod the method : POST,GET,PUT...
     * @param response the http response when client receviced the messsage
     * @throws org.apache.http.HttpException the http exception
     * @see org.apache.http.client.methods.HttpUriRequest
     * @see VoiceResponse
     */
    public void sendPresentDataMessage(HttpUriRequest httpMethod, PresentDataResponse response){
        logger.debug("Send message to the url : " + httpMethod.getURI() + ".");
        logger.debug("The httpMethod is : " + httpMethod.getMethod() + ".");

        // send message
        try
        {
            httpMethod.setHeader(ACCEPT_ENCODING,ACCEPT_ENCODING_CONTENT);
            CloseableHttpResponse httpResponse = client.execute(httpMethod);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (null != statusLine)
            {
                response.setStatusCode(statusLine.getStatusCode());
                response.setReasonPhrase(statusLine.getReasonPhrase());
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null)
            {
                String content = EntityUtils.toString(httpEntity, "UTF-8");
                logger.debug("The httpResponse is :\r\n\r\n" + httpResponse.getStatusLine() + "\n" + content
                        + "\r\n\r\n");
                System.out.println("The httpResponse is :\r\n\r\n" + httpResponse.getStatusLine() + "\n" + content
                        + "\r\n\r\n");
                response.setRspContent(content);
            }
            response.parse();
            EntityUtils.consume(httpEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Send QueryPresent message.
     *
     * @param httpMethod the method : POST,GET,PUT...
     * @param response the http response when client receviced the messsage
     * @throws org.apache.http.HttpException the http exception
     * @see org.apache.http.client.methods.HttpUriRequest
     * @see VoiceResponse
     */
    public void sendQueryPresentMessage(HttpUriRequest httpMethod, QueryPresentResponse response){
        logger.debug("Send message to the url : " + httpMethod.getURI() + ".");
        logger.debug("The httpMethod is : " + httpMethod.getMethod() + ".");

        // send message
        try
        {
            httpMethod.setHeader(ACCEPT_ENCODING,ACCEPT_ENCODING_CONTENT);
            CloseableHttpResponse httpResponse = client.execute(httpMethod);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (null != statusLine)
            {
                response.setStatusCode(statusLine.getStatusCode());
                response.setReasonPhrase(statusLine.getReasonPhrase());
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null)
            {
                String content = EntityUtils.toString(httpEntity, "UTF-8");
                logger.debug("The httpResponse is :\r\n\r\n" + httpResponse.getStatusLine() + "\n" + content
                        + "\r\n\r\n");
                System.out.println("The httpResponse is :\r\n\r\n" + httpResponse.getStatusLine() + "\n" + content
                        + "\r\n\r\n");
                response.setRspContent(content);
            }
            response.parse();
            EntityUtils.consume(httpEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Adds the http headers.
     *
     * @param httpMethod the method : POST,GET,PUT...
     * @param auth the authorization header for appKey authorization or other authorization
     * @see org.apache.http.client.methods.HttpUriRequest
     * @see Auth
     * @see org.apache.http.Header
     */
    public void addHttpHeaders(HttpUriRequest httpMethod, Auth auth)
    {
        String wsseHeader = bulidWsseHeader(auth);
        httpMethod.setHeader(new BasicHeader(WSSE_HEADER, wsseHeader));
        logger.debug("The WSSE_HEADER is :\r\n\r\n" + WSSE_HEADER + ": " + wsseHeader + "\r\n\r\n");
        httpMethod.setHeader(new BasicHeader(AUTHORIZATION_HEADER, AUTHORIZATION_CONTENT));
        logger.debug("The AUTHORIZATION_HEADER is :\r\n\r\n" + AUTHORIZATION_HEADER + ": " + AUTHORIZATION_CONTENT + "\r\n\r\n");
        httpMethod.setHeader(new BasicHeader(CONTENT_TYPE, CONTENT_CONTENT));
        logger.debug("The CONTENT_TYPE is :\r\n\r\n" + CONTENT_TYPE + ": " + CONTENT_CONTENT + "\r\n\r\n");
        httpMethod.setHeader(new BasicHeader(HOST, HOST_CONTENT));
        logger.debug("The HOST is :\r\n\r\n" + HOST + ": " + HOST_CONTENT + "\r\n\r\n");

    }

    /**
     * Inits the send voice entity.
     *
     * @return the http entity
     * @throws java.io.UnsupportedEncodingException
     * @see org.apache.http.NameValuePair
     */
    public StringEntity initSendSmsEntity(JSONObject smsRequestJson) throws UnsupportedEncodingException
    {
    	StringEntity s = new StringEntity(smsRequestJson.toString(), "UTF-8");
        logger.debug("Request body : " + smsRequestJson.toString() + "\r\n\r\n");
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
    	return s;
    }
    
    /**
     * Bulid wsse header.
     * 
     * @param auth the auth
     * @return the string
     */
    private String bulidWsseHeader(Auth auth)
    {
        String appKey = auth.getAppKey();
        String nonce = RandomNonceUtil.genRandomNonce();
        String created = TimestampUtil.getLocalUtcTimestamp();
        String source =
            (new StringBuilder(String.valueOf(nonce))).append(created).append(auth.getAppSecret()).toString();
        String pwdDigest = null;
        if (EncryptUtil.ENCRYPT_SHA1_TYPE.equals(auth.getEncryptType()))
        {
            pwdDigest = EncryptUtil.encryptSha1Base64(source);
        }
        else
        {
            pwdDigest = EncryptUtil.encryptSha256Base64(source);
        }
        
        String header =
            "UsernameToken Username=" + "\"" + appKey + "\"" + ", PasswordDigest=" + "\"" + pwdDigest + "\""
                + ", Nonce=" + "\"" + nonce + "\"" + ",Created=" + "\"" + created + "\"" + "";
        return header;
    }
}
