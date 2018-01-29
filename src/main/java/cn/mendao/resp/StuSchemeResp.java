package cn.mendao.resp;

import java.util.List;

/**
 * Created by warden on 17/11/1.
 */
public class StuSchemeResp {

    private long id;
    private String stuNo;
    private String schemeName;
    private int schemeNum;
    private String schoolCode;
    private String schoolName;
    private List<StuSchemeMajorResp> majorList;
    private int schemeChange;//是否服从调剂0:是1:否


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public int getSchemeNum() {
        return schemeNum;
    }

    public void setSchemeNum(int schemeNum) {
        this.schemeNum = schemeNum;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<StuSchemeMajorResp> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<StuSchemeMajorResp> majorList) {
        this.majorList = majorList;
    }

    public int getSchemeChange() {
        return schemeChange;
    }

    public void setSchemeChange(int schemeChange) {
        this.schemeChange = schemeChange;
    }
}
