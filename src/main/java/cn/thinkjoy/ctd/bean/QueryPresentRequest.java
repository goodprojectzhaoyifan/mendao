package cn.thinkjoy.ctd.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhangxilin on 2016/12/13.
 */
public class QueryPresentRequest {

    private String optCode;

    private String tradeSerialNo;

    private String bizCode;

    private String billId;

    private String orderOrgTradeNo;

    private String clientIp;

    private String macAddress;

    public QueryPresentRequest(String optCode, String tradeSerialNo, String bizCode,
               String billId, String orderOrgTradeNo, String clientIp, String macAddress) {
        this.optCode = optCode;
        this.tradeSerialNo = tradeSerialNo;
        this.bizCode = bizCode;
        this.billId = billId;
        this.orderOrgTradeNo = orderOrgTradeNo;
        this.clientIp = clientIp;
        this.macAddress = macAddress;
    }

    public JSONObject initParams()
    {
        JSONObject param = new JSONObject();
        param.put("optCode", optCode);
        param.put("tradeSerialNo", tradeSerialNo);
        param.put("bizCode", bizCode);
        param.put("billId", billId);
        param.put("orderOrgTradeNo", orderOrgTradeNo);
//        param.put("clientIp",clientIp);
//        param.put("macAddress",macAddress);

        return param;
    }

}
