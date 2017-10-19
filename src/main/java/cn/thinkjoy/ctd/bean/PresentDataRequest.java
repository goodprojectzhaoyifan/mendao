package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhangxilin on 2016/12/13.
 */
public class PresentDataRequest {

    private String optCode;

    private String tradeSerialNo;

    private String tradeDate;

    private String bizCode;

    private String billId;

    private String accountDate;

    private String offerId;

    private String clientIp;

    private String macAddress;

    public PresentDataRequest(String optCode, String tradeSerialNo, String tradeDate,
                  String bizCode, String billId, String accountDate,
                  String offerId, String clientIp, String macAddress) {
        this.optCode = optCode;
        this.tradeSerialNo = tradeSerialNo;
        this.tradeDate = tradeDate;
        this.bizCode = bizCode;
        this.billId = billId;
        this.accountDate = accountDate;
        this.offerId = offerId;
        this.clientIp = clientIp;
        this.macAddress = macAddress;
    }

    public JSONObject initParams()
    {
        JSONObject param = new JSONObject();
        param.put("optCode", optCode);
        param.put("tradeSerialNo", tradeSerialNo);
        param.put("tradeDate", tradeDate);
        param.put("bizCode", bizCode);
        param.put("billId", billId);
        param.put("accountDate", accountDate);
        param.put("offerId",offerId);
//        param.put("clientIp",clientIp);
//        param.put("macAddress",macAddress);

        return param;
    }

}
