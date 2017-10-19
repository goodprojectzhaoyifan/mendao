package cn.thinkjoy.ctd.util;


import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * 证书加载，系统启动时即可加载，不需要每次组装请求的时候去加载证书
 */
public class SSLSocketFactoryLoad
{
    @SuppressWarnings("deprecation")
    private static SSLConnectionSocketFactory socketFactory;

    /**
     * 加载证书到证书库
     */
    public static void loadSSLSocketFactory(String webCertFile)
    {
        try
        {
            SSLContext sslcontext = getSSLContext(webCertFile);
            socketFactory = new SSLConnectionSocketFactory(sslcontext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
        catch (Exception e)
        {
            System.out.println("Load cert failed.");
            e.printStackTrace();
        }
    }

    /**
     * 加载证书
     *
     * @return result
     * @throws java.io.IOException IOException
     * @throws java.security.KeyManagementException KeyManagementException
     * @throws java.security.NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws java.security.KeyStoreException KeyStoreException
     */
    private static SSLContext getSSLContext(String webCertFile)
        throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException
    {
        FileInputStream instream = null;
        KeyStore trustStore = null;
        try
        {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            instream = new FileInputStream(new File(webCertFile));
            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");

            Certificate cer = cerFactory.generateCertificate(instream);
            trustStore.load(null, null);
            trustStore.setCertificateEntry("trust", cer);
        }
        catch (Exception e)
        {
            System.out.println("Load cert failed.");
            e.printStackTrace();
        }
        finally
        {
            instream.close();
        }
        return SSLContexts.custom().loadTrustMaterial(trustStore).build();
    }

    public static SSLConnectionSocketFactory getSocketFactory()
    {
        return socketFactory;
    }

    public static void setSocketFactory(SSLConnectionSocketFactory socketFactory)
    {
        SSLSocketFactoryLoad.socketFactory = socketFactory;
    }
}
