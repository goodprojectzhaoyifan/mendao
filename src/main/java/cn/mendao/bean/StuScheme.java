package cn.mendao.bean;

/**
 * Created by warden on 18/1/26.
 */
public class StuScheme {

    private long id;
    private String stuNo;
    private String schemeName;
    private int schemeNum;
    private String schoolCode;
    private String schoolName;
    private String majorList;
    private int schemeChange;//是否服从调剂0:是1:否
    private int schemeType;


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

    public String getMajorList() {
        return majorList;
    }

    public void setMajorList(String majorList) {
        this.majorList = majorList;
    }

    public int getSchemeChange() {
        return schemeChange;
    }

    public void setSchemeChange(int schemeChange) {
        this.schemeChange = schemeChange;
    }

    public int getSchemeType() {
        return schemeType;
    }

    public void setSchemeType(int schemeType) {
        this.schemeType = schemeType;
    }
}
