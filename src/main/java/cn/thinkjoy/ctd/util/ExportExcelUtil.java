package cn.thinkjoy.ctd.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxilin on 2017/1/10.
 */
public class ExportExcelUtil {

    /**
     * 导出错误数据到Excel
     *
     * @param list
     * @param fileDir
     * @throws Exception
     */
    public static void exportWrongDataToExcel(List<Map<String, String>> list, String fileDir) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("导入失败数据列表");
        HSSFCellStyle headerStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());//设置背景色
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        // 设置单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中

        String[] excelHeader = {"所属工作表", "所在行号", "错误信息", "地市", "县区", "学校ID", "代理商", "家长ID", "手机号码", "所获奖励", "备注"};
        int[] columnWidth = {15, 10, 35, 11, 11, 11, 12, 12, 15, 14, 15};

        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelHeader.length; i++) {
            sheet.setColumnWidth(i, columnWidth[i]*256);
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(excelHeader[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);


            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("sheetName"));

            cell = row.createCell(1);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("rowNum"));

            cell = row.createCell(2);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wrongMessage"));

            cell = row.createCell(3);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wCity"));

            cell = row.createCell(4);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wDistrict"));

            cell = row.createCell(5);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wSchoolId"));

            cell = row.createCell(6);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wPresentUser"));

            cell = row.createCell(7);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wObjectId"));

            cell = row.createCell(8);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wBillId"));

            cell = row.createCell(9);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wOfferTempName"));

            cell = row.createCell(10);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i).get("wRemark"));

        }

        FileOutputStream out = null;
        try {
            File file = new File(fileDir);
            File excelFile = new File(fileDir + PresentDataUtil.FILE_SEPARATOR + "Excel导入失败数据信息.xls");
            if (!file.exists()) {
                file.mkdirs();
            }
            if (excelFile.exists()) {
                excelFile.delete();
            }
            out = new FileOutputStream(excelFile);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (wb != null) {
                wb.close();
            }
        }
    }

    /**
     * 下载Excel
     *
     * @param response
     * @param file
     * @throws Exception
     */
    public static void downloadWrongDataExcel(HttpServletResponse response, File file) throws Exception {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode("导入失败数据.xls", "UTF-8") + "\"");
        FileInputStream fs = null;
        ServletOutputStream out = response.getOutputStream();
        try {
            fs = new FileInputStream(file);
            // 写出流信息
            byte[] content = new byte[1024];
            int length;
            while ((length = fs.read(content)) != -1) {
                out.write(content, 0, length);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                fs.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}