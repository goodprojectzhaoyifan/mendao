package cn.thinkjoy.ctd.util;

import cn.thinkjoy.ctd.bean.PresentDataInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxilin on 2017/1/10.
 */
public class ImportExcelUtil {
    private final static String EXCEL_XLS =".xls";
    private final static String EXCEL_XLSX =".xlsx";

    private final static String TEACHER_TYPE ="1";
    private final static String PARENT_TYPE ="3";

    public static List<Map<String,String>> wrongList;

    /**
     * 描述：获取IO流中的数据，组装成List<PresentDataInfo>对象
     * @param in
     * @param fileName
     * @throws Exception
     */
    public static List<PresentDataInfo> getListByExcel(InputStream in,String fileName) throws Exception {
        List<PresentDataInfo> insertList = new ArrayList<PresentDataInfo>();
        wrongList = new ArrayList<Map<String,String>>();

        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if(null == work){
            throw new Exception("Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        //遍历Excel中所有的sheet
        for (int i = 0; i < 2; i++) {
            sheet = work.getSheetAt(i);
            if (sheet != null) {
                //遍历当前sheet中的所有行
                for (int j = 2; j <= sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row != null) {
                        PresentDataInfo presentDataInfo = new PresentDataInfo();
                        Map<String,String> wrongData = new HashMap<String,String>();
                        wrongData.put("sheetName", sheet.getSheetName());
                        wrongData.put("rowNum", String.valueOf(j + 1));

                        String city = "";
                        String district = "";
                        String schoolId = "";
                        String presentUser = "";
                        String userType = "";
                        String objectId = "";
                        String billId = "";
                        String offerTempName = "";
                        String offerName = "";
                        String remark = "";
                        String wrongMessage = "";

                        if (row.getCell(5) != null) {
                            billId = getCellValue(row.getCell(5));
                            if (StringUtils.isEmpty(billId)) {
                                wrongMessage = "手机号码不能为空";
                                addWrongData(wrongData, wrongMessage, row);
                                wrongList.add(wrongData);
                                continue;
                            }
                        } else {
                            wrongMessage = "手机号码不能为空";
                            addWrongData(wrongData, wrongMessage, row);
                            wrongList.add(wrongData);
                            continue;
                        }
                        if (row.getCell(6) != null) {
                            offerTempName = getCellValue(row.getCell(6));
                            if (StringUtils.isEmpty(offerTempName)) {
                                wrongMessage = "所获奖励不能为空";
                                addWrongData(wrongData, wrongMessage, row);
                                wrongList.add(wrongData);
                                continue;
                            }else{
                                if(PresentDataUtil.getOfferNameMap().containsKey(offerTempName)){
                                    offerName = PresentDataUtil.getOfferNameMap().get(offerTempName);
                                }else{
                                    wrongMessage = "移动流量套餐不包含所获奖励的流量值";
                                    addWrongData(wrongData, wrongMessage, row);
                                    wrongList.add(wrongData);
                                    continue;
                                }
                            }
                        } else {
                            wrongMessage = "所获奖励不能为空";
                            addWrongData(wrongData, wrongMessage, row);
                            wrongList.add(wrongData);
                            continue;
                        }
                        if (row.getCell(7) != null) {
                            remark = getCellValue(row.getCell(7));
                            if (StringUtils.isEmpty(remark)) {
                                wrongMessage = "备注不能为空";
                                addWrongData(wrongData, wrongMessage, row);
                                wrongList.add(wrongData);
                                continue;
                            }
                        } else {
                            wrongMessage = "备注不能为空";
                            addWrongData(wrongData, wrongMessage, row);
                            wrongList.add(wrongData);
                            continue;
                        }
                        if (row.getCell(0) != null) {
                            city = getCellValue(row.getCell(0));
                        }
                        if (row.getCell(1) != null) {
                            district = getCellValue(row.getCell(1));
                        }
                        if (row.getCell(2) != null) {
                            schoolId = getCellValue(row.getCell(2));
                        }
                        if (row.getCell(3) != null) {
                            presentUser = getCellValue(row.getCell(3));
                        }
                        if (row.getCell(4) != null) {
                            objectId = getCellValue(row.getCell(4));
                            if(StringUtils.isNotEmpty(objectId)){
                                if(i == 0){
                                    userType = PARENT_TYPE;
                                } else if (i == 1) {
                                    userType = TEACHER_TYPE;
                                }
                            }
                        }

                        presentDataInfo.setCity(city);
                        presentDataInfo.setDistrict(district);
                        presentDataInfo.setSchoolId(schoolId);
                        presentDataInfo.setPresentUser(presentUser);
                        presentDataInfo.setType(userType);
                        presentDataInfo.setObjectId(objectId);
                        presentDataInfo.setBillId(billId);
                        presentDataInfo.setOfferName(offerName);
                        presentDataInfo.setRemark(remark);

                        insertList.add(presentDataInfo);
                    }
                }
            }
        }
        return insertList;
    }

    /**
     * 描述：根据文件后缀，建立相应的Workbook
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    private static Workbook getWorkbook(InputStream in,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(EXCEL_XLS.equals(fileType)){
            wb = new HSSFWorkbook(in);
        }else if(EXCEL_XLSX.equals(fileType)){
            wb = new XSSFWorkbook(in);
        }else{
            throw new Exception("导入的文件格式错误！");
        }
        return wb;
    }

    /**
     * 描述：获取单元格的值
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell){
        String value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 将错误信息加到Map中
     * @param wrongData 错误数据
     * @param wrongMessage 错误信息
     * @param row 行
     */
    private static void addWrongData(Map<String,String> wrongData, String wrongMessage, Row row) {

        //获取错误行各个单元格的值
        String wCity = "";
        String wDistrict = "";
        String wSchoolId = "";
        String wPresentUser = "";
        String wObjectId = "";
        String wBillId = "";
        String wOfferTempName = "";
        String wRemark = "";

        if (row.getCell(0) != null) {
            wCity = getCellValue(row.getCell(0));
        }
        if (row.getCell(1) != null) {
            wDistrict = getCellValue(row.getCell(1));
        }
        if (row.getCell(2) != null) {
            wSchoolId = getCellValue(row.getCell(2));
        }
        if (row.getCell(3) != null) {
            wPresentUser = getCellValue(row.getCell(3));
        }
        if (row.getCell(4) != null) {
            wObjectId = getCellValue(row.getCell(4));
        }
        if (row.getCell(5) != null) {
            wBillId = getCellValue(row.getCell(5));
        }
        if (row.getCell(6) != null) {
            wOfferTempName = getCellValue(row.getCell(6));
        }
        if (row.getCell(7) != null) {
            wRemark = getCellValue(row.getCell(7));
        }
        // 将错误行数据加到Map中
        wrongData.put("wrongMessage", wrongMessage);
        wrongData.put("wCity", wCity);
        wrongData.put("wDistrict", wDistrict);
        wrongData.put("wSchoolId", wSchoolId);
        wrongData.put("wPresentUser", wPresentUser);
        wrongData.put("wObjectId", wObjectId);
        wrongData.put("wBillId", wBillId);
        wrongData.put("wOfferTempName", wOfferTempName);
        wrongData.put("wRemark", wRemark);
    }
}