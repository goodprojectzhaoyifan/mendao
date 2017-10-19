package cn.thinkjoy.ctd.controller;

/**
 * Created by zhangxilin on 16/3/2.
 */

import cn.thinkjoy.ctd.bean.*;
import cn.thinkjoy.ctd.service.DataTrafficService;
import cn.thinkjoy.ctd.util.AbstractSdkSender;
import cn.thinkjoy.ctd.util.ExportExcelUtil;
import cn.thinkjoy.ctd.util.ImportExcelUtil;
import cn.thinkjoy.ctd.util.PresentDataUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.org.mozilla.javascript.internal.ast.TryStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 流量包赠送控制类
 */
@Scope("prototype")
@Controller("dataTrafficController")
@RequestMapping(value = "/dataTraffic")
public class DataTrafficController {

    private static final Logger logger = LoggerFactory.getLogger(DataTrafficController.class);

    @Autowired
    private PropertyBean propertyBean;

    @Autowired
    private DataTrafficService dataTrafficService;

    @RequestMapping(value = "/presentPage", method = RequestMethod.GET)
    public ModelAndView presentPage() {
        return new ModelAndView("present");
    }

    @RequestMapping(value = "/batchQueryPage", method = RequestMethod.GET)
    public ModelAndView batchQueryPage() {
        return new ModelAndView("batchQuery");
    }

    @RequestMapping(value = "/dataImportPage", method = RequestMethod.GET)
    public ModelAndView dataImportPage() {
        return new ModelAndView("dataImport");
    }

    /**
     * 流量包赠送
     */
    @RequestMapping(value = "/present", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> presentData(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        PresentDataResponse presentDataResponse = new PresentDataResponse();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String cerFile = request.getServletContext().getRealPath("/") + propertyBean.getWebCertFile();
            HttpPost postMethod = new HttpPost(propertyBean.getPresentDataUrl());
            AbstractSdkSender abstractSdkSender = new AbstractSdkSender(cerFile);
            Auth auth = new Auth(propertyBean.getAppKey(), propertyBean.getAppSecret());
            abstractSdkSender.addHttpHeaders(postMethod, auth);

            String optCode = PresentDataUtil.PRESENT_OPT_CODE;
            String tradeDate = PresentDataUtil.getTradeDate();
            String bizCode = PresentDataUtil.BIZ_CODE;
            String tradeSerialNo = optCode + tradeDate + bizCode + PresentDataUtil.nextSerialNo();
            String accountDate = PresentDataUtil.getAccountDate();
            String billId = jsonObject.getString("billId");
            String offerId = jsonObject.getString("offerId");
            String clientIp = jsonObject.getString("clientIp");
            String macAddress = jsonObject.getString("macAddress");

            PresentDataRequest presentDataRequest = new PresentDataRequest(optCode, tradeSerialNo, tradeDate, bizCode, billId, accountDate, offerId, clientIp, macAddress);
            JSONObject presentDataRequestJson = presentDataRequest.initParams();
            postMethod.setEntity(abstractSdkSender.initSendSmsEntity(presentDataRequestJson));
            abstractSdkSender.sendPresentDataMessage(postMethod, presentDataResponse);
            if (!StringUtils.isEmpty(presentDataResponse.getRspContent())) {
                resultMap.put("code", presentDataResponse.getCode());
                resultMap.put("description", presentDataResponse.getDescription());
                if(presentDataResponse.getResult() != null){
                    resultMap.put("result", presentDataResponse.getResult());
                }
            } else {
                resultMap.put("code", "0000");
                resultMap.put("description", "服务器内部错误！");
            }

            return resultMap;
        } catch (Exception e) {
            resultMap.put("code", "0000");
            resultMap.put("description", "服务器内部错误！");

            logger.error("流量包赠送失败:" + e);
            return resultMap;
        }
    }

    /**
     * 流量包赠送结果查询
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryPresentPResult(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        QueryPresentResponse queryPresentResponse = new QueryPresentResponse();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String cerFile = request.getServletContext().getRealPath("/") + propertyBean.getWebCertFile();
            HttpPost postMethod = new HttpPost(propertyBean.getQueryPresentUrl());
            AbstractSdkSender abstractSdkSender = new AbstractSdkSender(cerFile);
            Auth auth = new Auth(propertyBean.getAppKey(), propertyBean.getAppSecret());
            abstractSdkSender.addHttpHeaders(postMethod, auth);

            String optCode = PresentDataUtil.QUERY_OPT_CODE;
            String bizCode = PresentDataUtil.BIZ_CODE;
            String tradeSerialNo = optCode + PresentDataUtil.getTradeDate() + bizCode + PresentDataUtil.nextSerialNo();
            String billId = jsonObject.getString("billId");
            String orderOrgTradeNo = jsonObject.getString("orderOrgTradeNo");
            String clientIp = jsonObject.getString("clientIp");
            String macAddress = jsonObject.getString("macAddress");

            QueryPresentRequest queryPresentRequest = new QueryPresentRequest(optCode, tradeSerialNo, bizCode, billId, orderOrgTradeNo, clientIp, macAddress);
            JSONObject queryPresentRequestJson = queryPresentRequest.initParams();
            postMethod.setEntity(abstractSdkSender.initSendSmsEntity(queryPresentRequestJson));
            abstractSdkSender.sendQueryPresentMessage(postMethod, queryPresentResponse);
            if (!StringUtils.isEmpty(queryPresentResponse.getRspContent())) {
                resultMap.put("code", queryPresentResponse.getCode());
                resultMap.put("description", queryPresentResponse.getDescription());
                if(queryPresentResponse.getResult() != null){
                    resultMap.put("result", queryPresentResponse.getResult());
                }
            } else {
                resultMap.put("code", "0000");
                resultMap.put("description", "服务器内部错误！");
            }

            return resultMap;
        } catch (Exception e) {
            resultMap.put("code", "0000");
            resultMap.put("description", "服务器内部错误！");

            logger.error("查询流量包赠送结果失败:" + e);
            return resultMap;
        }
    }

    /**
     * 流量包批量赠送
     */
    @RequestMapping(value = "/presentBatch", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> presentDataBatch(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String cerFile = request.getServletContext().getRealPath("/") + propertyBean.getWebCertFile();
            HttpPost postMethod = new HttpPost(propertyBean.getPresentDataUrl());
            AbstractSdkSender abstractSdkSender = new AbstractSdkSender(cerFile);
            Auth auth = new Auth(propertyBean.getAppKey(), propertyBean.getAppSecret());
            abstractSdkSender.addHttpHeaders(postMethod, auth);

            int total = dataTrafficService.findPresentDataTotal();
            int successCount = 0;
            if (total == 0) {
                resultMap.put("code", "0000");
                resultMap.put("description", "没有需要赠送的记录！");
                logger.info("没有需要赠送的记录");
            } else {
                List<PresentDataInfo> presentList = new ArrayList<PresentDataInfo>();
                int totalPage = PresentDataUtil.totalPage(total);
                for (int i = 0; i < totalPage; i++) {
                    int start = PresentDataUtil.DEFAULT_ROWS * i;
                    int end = start + PresentDataUtil.DEFAULT_ROWS;
                    List<PresentDataInfo> list = dataTrafficService.findPresentData(start, end);
                    if (list != null && list.size() > 0) {
                        for (PresentDataInfo presentDataInfo : list) {
                            String optCode = PresentDataUtil.PRESENT_OPT_CODE;
                            String tradeDate = PresentDataUtil.getTradeDate();
                            String bizCode = PresentDataUtil.BIZ_CODE;
                            String tradeSerialNo = optCode + tradeDate + bizCode + PresentDataUtil.nextSerialNo();
                            String accountDate = PresentDataUtil.getAccountDate();
                            String billId = presentDataInfo.getBillId();
                            String offerId = "";
                            if(PresentDataUtil.getOfferIDMap().containsKey(presentDataInfo.getOfferName())){
                                offerId = PresentDataUtil.getOfferIDMap().get(presentDataInfo.getOfferName());
                            }
                            String clientIp = presentDataInfo.getClientIp();
                            String macAddress = presentDataInfo.getMacAddress();

                            PresentDataRequest presentDataRequest = new PresentDataRequest(optCode, tradeSerialNo, tradeDate, bizCode, billId, accountDate, offerId, clientIp, macAddress);
                            JSONObject presentDataRequestJson = presentDataRequest.initParams();
                            postMethod.setEntity(abstractSdkSender.initSendSmsEntity(presentDataRequestJson));
                            PresentDataResponse presentDataResponse = new PresentDataResponse();
                            abstractSdkSender.sendPresentDataMessage(postMethod, presentDataResponse);

                            if (!StringUtils.isEmpty(presentDataResponse.getRspContent())) {
                                if(!StringUtils.isEmpty(presentDataResponse.getCode()) && presentDataResponse.getCode().equals(PresentDataUtil.SUCCESS_CODE)){
                                    presentDataInfo.setPresentStatus(0);
                                    presentDataInfo.setBusiLogId(presentDataResponse.getResult().getBusiLogId());
                                    presentDataInfo.setOsbSerialNo(presentDataResponse.getResult().getOsbSerialNo());
                                    successCount++;
                                }else{
                                    presentDataInfo.setPresentStatus(1);
                                }
                                presentDataInfo.setTradeDate(tradeDate);
                                presentDataInfo.setAccountDate(accountDate);
                                presentDataInfo.setTradeSerialNo(tradeSerialNo);
                                presentDataInfo.setRetCode(presentDataResponse.getCode());
                                presentDataInfo.setRetMsg(presentDataResponse.getDescription());
                                presentList.add(presentDataInfo);
                            }
                        }
                    }
                }
                if (presentList.size() > 0) {
                    dataTrafficService.updatePresentData(presentList);
                }

                resultMap.put("code", "0000000");
                resultMap.put("description", "批量赠送执行完毕，共执行" + total + "条记录，其中" + successCount + "条赠送成功");
                logger.info("批量赠送执行完毕，共执行" + total + "条记录，其中" + successCount + "条赠送成功");
            }

            return resultMap;

        } catch (Exception e) {
            resultMap.put("code", "0000");
            resultMap.put("description", "服务器内部错误！");

            logger.error("流量包赠送失败:" + e);
            return resultMap;
        }
    }

    /**
     * 流量包批量赠送结果查询
     */
    @RequestMapping(value = "/batchQuery", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> batchPresentQuery(String startTime, String endTime, String billId, String presentStatus, Integer rows, Integer page){
        Map<String, Object> map = new HashMap<String, Object>();
        List<PresentDataInfo> list = new ArrayList<PresentDataInfo>();
        int total = 0;
        map.put("rows", list);
        map.put("total", total);

        Integer startRow = (page - 1) * rows + 1;
        Integer endRow = page * rows;

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        if(!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)){
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = sdf.parse(startTime);
                endDate = sdf.parse(endTime);
            } catch (ParseException e) {
                map.put("code", "00000");
                map.put("description", "日期参数格式错误");
                return map;
            }

            if(startDate != null && endDate != null && startDate.after(endDate)){
                map.put("code", "00000");
                map.put("description", "开始日期不能大于结束日期");
                return map;
            }
        }

        try {
            total = dataTrafficService.findBatchPresentTotal(startTime, endTime, billId, presentStatus);
            list = dataTrafficService.findBatchPresent(startTime, endTime, billId, presentStatus, startRow, endRow);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "00000");
            map.put("description", "查询数据库时出错");
            return map;
        }

        if (list != null && list.size() > 0) {
            map.put("code", "0000000");
            map.put("description", "查询成功");
        }else{
            map.put("code", "00000");
            map.put("description", "查询结果为空");
        }

        map.put("total", total);
        map.put("rows", list);

        return map;
    }

    /**
     * excel数据导入
     * @param file
     * @param request
     */
    @RequestMapping(value = "/excelUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> excelUploadAndImport(@RequestParam("uploadExcel") MultipartFile file, HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();

        InputStream in = null;
        String fileName = file.getOriginalFilename();
        List<PresentDataInfo> list = null;

        // 获取前台exce的输入流
        try {
            in = file.getInputStream();
            list = ImportExcelUtil.getListByExcel(in, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
            map.put("msg", "导入失败，读取Excel文件时出错");
            return map;
        }

        List<Map<String,String>> wrongList = ImportExcelUtil.wrongList;
        String fileDir = request.getSession().getServletContext().getRealPath(PresentDataUtil.FILE_SEPARATOR) + "excel";

        try {
            if (wrongList != null && wrongList.size() > 0){
                ExportExcelUtil.exportWrongDataToExcel(wrongList, fileDir);
            }else{
                File excelFile = new File(fileDir);
                if (excelFile.exists()) {
                    excelFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "1");
            map.put("msg", "导入失败，保存导入失败数据到Excel文件时出错");
            return map;
        }

        if (list != null && list.size() > 0){
            try {
                dataTrafficService.insertPresentData(list);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", "1");
                map.put("msg", "导入失败，插入数据到数据库时出错");
                return map;
            }
        }

        map.put("code", "0");
        map.put("msg", "导入执行完毕, 导入成功"+list.size()+"条记录，导入失败"+wrongList.size()+"条记录");
        map.put("successSize", list.size());
        map.put("wrongSize", wrongList.size());

        return map;
    }

    /**
     * 下载导入失败数据信息的Excel
     * @param response
     */
    @RequestMapping(value = "/excelDownload", method = RequestMethod.GET)
    @ResponseBody
    public void wrongDataExcelDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fileDir = request.getSession().getServletContext().getRealPath(PresentDataUtil.FILE_SEPARATOR) + "excel" + PresentDataUtil.FILE_SEPARATOR + "Excel导入失败数据信息.xls";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        try {
            File excelFile = new File(fileDir);
            if (excelFile.exists()) {
                ExportExcelUtil.downloadWrongDataExcel(response, excelFile);
            }else{
                response.getWriter().println("<script language='javascript'>alert('没有导入失败信息！');</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<script language='javascript'>alert('下载失败！');</script>");
        }
    }
}