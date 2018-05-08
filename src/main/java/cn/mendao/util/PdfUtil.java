package cn.mendao.util;

import cn.mendao.bean.Student;
import cn.mendao.resp.StuSchemeMajorResp;
import cn.mendao.resp.StuSchemeResp;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by warden on 18/1/30.
 */
public class PdfUtil {


    public static void main(String[] args) throws Exception {
        List<StuSchemeResp> list = new ArrayList<>();

        for(int i=0;i<8;i++){
            StuSchemeResp stuSchemeResp = new StuSchemeResp();
            stuSchemeResp.setId(i);
            stuSchemeResp.setStuNo("12323131");
            stuSchemeResp.setSchemeName("志愿A");
            stuSchemeResp.setSchemeNum(1);
            stuSchemeResp.setSchoolName("陕西理工大学陕西理工大学陕西理工大学陕西理工大学陕西理工大学");
            stuSchemeResp.setSchoolCode("0011");
            stuSchemeResp.setSchemeChange(1);
            if(i==2){
                List<StuSchemeMajorResp> majorRespList = JSONObject.parseArray("[{\"majorCode\":\"002\",\"majorName\":\"计算机学\"},{\"majorCode\":\"002\",\"majorName\":\"英语\"},{\"majorCode\":\"002\",\"majorName\":\"英语\"}]", StuSchemeMajorResp.class);
                stuSchemeResp.setMajorList(majorRespList);
            }else if(i==3){
                List<StuSchemeMajorResp> majorRespList = JSONObject.parseArray("[]", StuSchemeMajorResp.class);
                stuSchemeResp.setMajorList(majorRespList);
            }else{
                List<StuSchemeMajorResp> majorRespList = JSONObject.parseArray("[{\"majorCode\":\"002\",\"majorName\":\"计算机学\"},{\"majorCode\":\"002\",\"majorName\":\"英语\"},{\"majorCode\":\"002\",\"majorName\":\"英语\"},{\"majorCode\":\"002\",\"majorName\":\"英语\"},{\"majorCode\":\"002\",\"majorName\":\"英语\"}]", StuSchemeMajorResp.class);
                stuSchemeResp.setMajorList(majorRespList);
            }


            list.add(stuSchemeResp);
        }

        Student student = new Student();
        student.setName("张三");
        student.setMajor(1);
        student.setScore(1000);
        student.setPrecedence(10);
        String pdfPath = "/Users/warden/Desktop/test4.pdf";
        String beijingPath = "/Users/warden/Desktop/beijing.png";
        createPdf(pdfPath, beijingPath, list, student, "管理员");

    }

    public static String createPdf(String pdfPath, String beijingPath, List<StuSchemeResp> list, Student student, String teacherName){
        String returnPath = pdfPath;
        try{
            //创建文件
            Document document = new Document();
            //建立一个书写器
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            //打开文件
            document.open();
            //中文字体,解决中文不能显示问题
            BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            //红色字体
            Font redFont = new Font(bfChinese);
            redFont.setColor(BaseColor.RED);

            //黑色字体
            Font blackFont = new Font(bfChinese);
            blackFont.setColor(BaseColor.BLACK);

            //添加内容
            Paragraph title = new Paragraph("门道中学生生涯规划指导  高考志愿报考方案", redFont);
            title.setAlignment(Element.ALIGN_CENTER);

            Paragraph title1 = new Paragraph("    学生姓名："+student.getName(), blackFont);
            if(student.getMajor() == 0){
                title1.add("        科类：理科");
            }else{
                title1.add("        科类：文史");
            }
            if(teacherName != null && !teacherName.equals("")){
                title1.add("        分数："+student.getScore()+"        位次："+student.getPrecedence()+"        指导老师：张三"+teacherName);
            }else{
                title1.add("        分数："+student.getScore()+"        位次："+student.getPrecedence()+"        指导老师：无");
            }

            title1.setAlignment(Element.ALIGN_CENTER);


            float[] widths = {1f,1f,2f,1f,4f,1f};
            PdfPTable table = new PdfPTable(widths);
            table.setPaddingTop(2f);
            table.setWidthPercentage(100); // 宽度100%填充
            table.setHorizontalAlignment(Element.ALIGN_LEFT);

            //合并单元格
            PdfPCell topCell = new PdfPCell();
            topCell.setPhrase(title);
            topCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            topCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            topCell.setMinimumHeight(33);//设置表格行高
            topCell.setRowspan(1);
            topCell.setColspan(6);
            topCell.setBackgroundColor(new BaseColor(235, 235, 235));
            table.addCell(topCell);

            PdfPCell topCell1 = new PdfPCell();
            topCell1.setPhrase(title1);
            topCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            topCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            topCell1.setMinimumHeight(25);//设置表格行高
            topCell1.setRowspan(1);
            topCell1.setColspan(6);
            topCell1.setBackgroundColor(new BaseColor(235, 235, 235));
            table.addCell(topCell1);


            for(int i=0;i<4;i++){
                PdfPCell firstCell = new PdfPCell();
                if(i == 0){
                    table.addCell(firstCell);
                }else if(i == 1){
                    Paragraph content = new Paragraph("院校代码 |  院校名称",getPdfChineseFont());
                    firstCell.setPhrase(content);
                    firstCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    firstCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    firstCell.setMinimumHeight(18);//设置表格行高
                    firstCell.setRowspan(1);
                    firstCell.setColspan(2);
                    table.addCell(firstCell);
                }else if(i == 2){
                    Paragraph content = new Paragraph("专业代码 |  专业名称",getPdfChineseFont());
                    firstCell.setPhrase(content);
                    firstCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    firstCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    firstCell.setMinimumHeight(18);//设置表格行高
                    firstCell.setRowspan(1);
                    firstCell.setColspan(2);
                    table.addCell(firstCell);
                }else if(i == 3){
                    Paragraph content = new Paragraph("是否服从调剂",getPdfChineseFont());
                    firstCell.setPhrase(content);
                    firstCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    firstCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    firstCell.setMinimumHeight(18);//设置表格行高
                    table.addCell(firstCell);
                }
            }


            if(list != null && list.size() >0){
                for(StuSchemeResp stuScheme:list){
                    int column = 1;
                    if(stuScheme.getMajorList() != null && stuScheme.getMajorList().size()>0){
                        column = stuScheme.getMajorList().size();
                    }

                    PdfPCell pdfCell = new PdfPCell();
                    pdfCell.setMinimumHeight(20);//设置表格行高
                    String SchemeName = "";
                    if(stuScheme.getSchemeNum() != 7){
                        SchemeName = "志愿"+stuScheme.getSchemeName();
                    }else{
                        SchemeName = "备选";
                    }
                    Paragraph paragraph = new Paragraph(SchemeName, getPdfChineseFont());
                    pdfCell.setRowspan(column);
                    pdfCell.setPhrase(paragraph);
                    pdfCell.setUseAscender(true);
                    pdfCell.setVerticalAlignment(pdfCell.ALIGN_MIDDLE);

                    PdfPCell pdfCell1 = new PdfPCell();
                    pdfCell1.setMinimumHeight(18);//设置表格行高
                    Paragraph paragraph1 = new Paragraph(stuScheme.getSchoolCode(), getPdfChineseFont());
                    pdfCell1.setRowspan(column);
                    pdfCell1.setPhrase(paragraph1);
                    pdfCell1.setUseAscender(true);
                    pdfCell1.setVerticalAlignment(pdfCell1.ALIGN_MIDDLE);

                    PdfPCell pdfCell2 = new PdfPCell();
                    pdfCell2.setMinimumHeight(18);//设置表格行高
                    Paragraph paragraph2 = new Paragraph(stuScheme.getSchoolName(), getPdfChineseFont());
                    pdfCell2.setRowspan(column);
                    pdfCell2.setPhrase(paragraph2);
                    pdfCell2.setUseAscender(true);
                    pdfCell2.setVerticalAlignment(pdfCell2.ALIGN_MIDDLE);

                    table.addCell(pdfCell);
                    table.addCell(pdfCell1);
                    table.addCell(pdfCell2);

                    List<StuSchemeMajorResp> majorRespList = stuScheme.getMajorList();
                    //对专业进行排序
                    Collections.sort(majorRespList, new Comparator<StuSchemeMajorResp>() {
                        @Override
                        public int compare(StuSchemeMajorResp s1, StuSchemeMajorResp s2) {
                            return s1.getMajorNum() - (s2.getMajorNum());
                        }
                    });

                    if(majorRespList != null && majorRespList.size()>0){
                        for (int i=0;i<column;i++){
                            PdfPCell pdfCell3 = new PdfPCell();
                            pdfCell3.setMinimumHeight(18);//设置表格行高
                            if(stuScheme.getMajorList().get(i).getMajorNum() != 7){
                                Paragraph paragraph3 = new Paragraph("专业"+stuScheme.getMajorList().get(i).getMajorNum(), getPdfChineseFont());
                                pdfCell3.setPhrase(paragraph3);
                            }else{
                                Paragraph paragraph3 = new Paragraph("备选专业", getPdfChineseFont());
                                pdfCell3.setPhrase(paragraph3);
                            }



                            table.addCell(pdfCell3);

                            PdfPCell pdfCell4 = new PdfPCell();
                            pdfCell4.setMinimumHeight(18);//设置表格行高
                            Paragraph paragraph4 = new Paragraph(stuScheme.getMajorList().get(i).getMajorCode()+"   "+stuScheme.getMajorList().get(i).getMajorName(), getPdfChineseFont());
                            pdfCell4.setPhrase(paragraph4);

                            table.addCell(pdfCell4);

                            if(i == 0){
                                PdfPCell pdfCell5 = new PdfPCell();
                                pdfCell5.setMinimumHeight(18);//设置表格行高
                                Paragraph paragraph5 = new Paragraph(stuScheme.getSchemeChange()==1?"是":"否", getPdfChineseFont());
                                pdfCell5.setRowspan(column);
                                pdfCell5.setPhrase(paragraph5);
                                pdfCell5.setUseAscender(true);
                                pdfCell5.setVerticalAlignment(pdfCell5.ALIGN_MIDDLE);
                                pdfCell5.setHorizontalAlignment(pdfCell5.ALIGN_CENTER);
                                table.addCell(pdfCell5);
                            }
                        }
                    }else{
                        PdfPCell pdfCell3 = new PdfPCell();
                        PdfPCell pdfCell4 = new PdfPCell();

                        table.addCell(pdfCell3);
                        table.addCell(pdfCell4);

                        PdfPCell pdfCell5 = new PdfPCell();
                        pdfCell5.setMinimumHeight(18);//设置表格行高
                        Paragraph paragraph5 = new Paragraph(stuScheme.getSchemeChange()==1?"是":"否", getPdfChineseFont());
                        pdfCell5.setRowspan(column);
                        pdfCell5.setPhrase(paragraph5);
                        pdfCell5.setUseAscender(true);
                        pdfCell5.setVerticalAlignment(pdfCell5.ALIGN_MIDDLE);
                        pdfCell5.setHorizontalAlignment(pdfCell5.ALIGN_CENTER);
                        table.addCell(pdfCell5);
                    }
                }
            }

            document.add(table);
            document.close();
            //关闭文档
            document.close();
            //关闭书写器
            writer.close();

            returnPath = createBeginImage(beijingPath,pdfPath);
        }catch (Exception e){
            e.printStackTrace();
        }

        return returnPath;
    }

    public static String createBeginText(String content,String pdfPath,String exportPath) throws Exception {

        //图片水印
        PdfReader reader = new PdfReader(pdfPath);
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(exportPath));

        //文字水印
        PdfContentByte over = stamp.getOverContent(1);
        over.beginText();
        BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        over.setFontAndSize(bf, 18);
        over.setTextMatrix(30, 30);
        over.showTextAligned(Element.ALIGN_LEFT, content, 230, 430, 45);
        over.endText();
        stamp.close();
        reader.close();
        return "";
    }

    public static String createBeginImage(String imagePath,String pdfPath) throws Exception {

        String exportPath = pdfPath.replace(".pdf","_1.pdf");

        //图片水印
        PdfReader reader = new PdfReader(pdfPath);
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(exportPath));

        Image img = Image.getInstance(imagePath);
        img.setAbsolutePosition(130, 400);
        PdfContentByte under = stamp.getUnderContent(1);
        under.addImage(img);

        Image img1 = Image.getInstance(imagePath);
        img1.setAbsolutePosition(130, 100);
        PdfContentByte under1 = stamp.getUnderContent(2);
        under.addImage(img1);

        Image img2 = Image.getInstance(imagePath);
        img2.setAbsolutePosition(130, 600);
        PdfContentByte under2 = stamp.getUnderContent(3);
        under.addImage(img2);

        stamp.close();
        reader.close();
        return exportPath;
    }

    public static Font getPdfChineseFont() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 9, Font.NORMAL);
        return fontChinese;
    }


}
