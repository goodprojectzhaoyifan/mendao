package cn.mendao.resp;

import java.util.List;

/**
 * Created by warden on 17/11/1.
 */
public class SchoolRespV2 {

    private long sort;
    private String code;
    private String schoolName;
    private long sortNum;
    private String msg2015;
    private String msg2016;
    private String msg2017;
    private long plan2018;
    private int planStatus2018;
    private String manager;
    private UtilResp province;
    private UtilResp city;
    private UtilResp level;
    private long color;
    private String majorPlan;
    private String precedence;
    private UtilResp property;
    private String describe;
    private String type;
    private List<MajorResp> majorList;


    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public long getSortNum() {
        return sortNum;
    }

    public void setSortNum(long sortNum) {
        this.sortNum = sortNum;
    }

    public String getMsg2015() {
        return msg2015;
    }

    public void setMsg2015(String msg2015) {
        this.msg2015 = msg2015;
    }

    public String getMsg2016() {
        return msg2016;
    }

    public void setMsg2016(String msg2016) {
        this.msg2016 = msg2016;
    }

    public String getMsg2017() {
        return msg2017;
    }

    public void setMsg2017(String msg2017) {
        this.msg2017 = msg2017;
    }

    public long getPlan2018() {
        return plan2018;
    }

    public void setPlan2018(long plan2018) {
        this.plan2018 = plan2018;
    }

    public int getPlanStatus2018() {
        return planStatus2018;
    }

    public void setPlanStatus2018(int planStatus2018) {
        this.planStatus2018 = planStatus2018;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public UtilResp getProvince() {
        return province;
    }

    public void setProvince(UtilResp province) {
        this.province = province;
    }

    public UtilResp getCity() {
        return city;
    }

    public void setCity(UtilResp city) {
        this.city = city;
    }

    public UtilResp getLevel() {
        return level;
    }

    public void setLevel(UtilResp level) {
        this.level = level;
    }

    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public String getMajorPlan() {
        return majorPlan;
    }

    public void setMajorPlan(String majorPlan) {
        this.majorPlan = majorPlan;
    }

    public String getPrecedence() {
        return precedence;
    }

    public void setPrecedence(String precedence) {
        this.precedence = precedence;
    }

    public UtilResp getProperty() {
        return property;
    }

    public void setProperty(UtilResp property) {
        this.property = property;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<MajorResp> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<MajorResp> majorList) {
        this.majorList = majorList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
