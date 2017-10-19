package cn.thinkjoy.ctd.bean;

/**
 * Created by wpliu on 16/3/2.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 */
@Component
public class PropertyBean {
    //能力开放平台点击拨号URL
    @Value("#{config[click2callUrl]}")
    private String click2callUrl;
    //能力开放平台点击拨号终止Url
    @Value("#{config[click2stopUrl]}")
    private String click2stopUrl;
    //能力开放平台流量包赠送Url
    @Value("#{config[presentDataUrl]}")
    private String presentDataUrl;
    //能力开放平台流量包赠送结果查询Url
    @Value("#{config[queryPresentUrl]}")
    private String queryPresentUrl;
    //接入号，终端上显示的主叫号码
    @Value("#{config[ctdDisplayNum]}")
    private String ctdDisplayNum;
    //APP标识
    @Value("#{config[appKey]}")
    private String appKey;
    //APP密钥
    @Value("#{config[appSecret]}")
    private String appSecret;
    //开发者自定义CDR字段，该字段会被记录在话单文件中，协助开发者区分用户话单
    @Value("#{config[developerCDR]}")
    private String developerCDR;
    //语音类型
    @Value("#{config[languageType]}")
    private int languageType;
    //证书文件
    @Value("#{config[webCertFile]}")
    private String webCertFile;
    //回调URL
    @Value("#{config[statusUrl]}")
    private String statusUrl;

    public String getClick2callUrl() {
        return click2callUrl;
    }

    public void setClick2callUrl(String click2callUrl) {
        this.click2callUrl = click2callUrl;
    }

    public String getClick2stopUrl() {
        return click2stopUrl;
    }

    public void setClick2stopUrl(String click2stopUrl) {
        this.click2stopUrl = click2stopUrl;
    }

    public String getPresentDataUrl() {
        return presentDataUrl;
    }

    public void setPresentDataUrl(String presentDataUrl) {
        this.presentDataUrl = presentDataUrl;
    }

    public String getQueryPresentUrl() {
        return queryPresentUrl;
    }

    public void setQueryPresentUrl(String queryPresentUrl) {
        this.queryPresentUrl = queryPresentUrl;
    }

    public String getCtdDisplayNum() {
        return ctdDisplayNum;
    }

    public void setCtdDisplayNum(String ctdDisplayNum) {
        this.ctdDisplayNum = ctdDisplayNum;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getDeveloperCDR() {
        return developerCDR;
    }

    public void setDeveloperCDR(String developerCDR) {
        this.developerCDR = developerCDR;
    }

    public int getLanguageType() {
        return languageType;
    }

    public void setLanguageType(int languageType) {
        this.languageType = languageType;
    }

    public String getWebCertFile() {
        return webCertFile;
    }

    public void setWebCertFile(String webCertFile) {
        this.webCertFile = webCertFile;
    }

    public String getStatusUrl() {
        return statusUrl;
    }

    public void setStatusUrl(String statusUrl) {
        this.statusUrl = statusUrl;
    }
}
