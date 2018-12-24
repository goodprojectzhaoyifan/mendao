package cn.mendao.bean;

import java.util.Date;

/**
 * Created by warden on 18/1/25.
 */
public class Student {

    private long id;
    private String name;
    private String stuNo;
    private String examNo;//准考证号码
    private String cardNo;//身份证号码
    private int major;//科类0:理科1:文科
    private int sex;//性别0:男1:女
    private String school;
    private int rank;//排名
    private int stuNum;//年纪人数
    private int stuType;//类型0:应届1:往届
    private int score;//分数
    private int precedence;//位次
    private String policy;//'优惠政策'
    private String limitContent;//'限制'
    private String other;//'其他'
    private int chinese;//语文成绩
    private int english;//'英语成绩'
    private int manth;//'数学成绩'
    private int physics;//'物理/政治成绩'
    private int chemistry;//'化学/历史成绩'
    private int biology;//'生物/地理成绩'
    private int comprehensive;//'综合成绩'
    private int sortNum;//排序
    private long createdUser;
    private Date createdTime;

    public Student(){
        if(name == null){
            name = "";
        }
        if(stuNo == null){
            stuNo = "";
        }
        if(examNo == null){
            examNo = "";
        }
        if(cardNo == null){
            cardNo = "";
        }
        if(school == null){
            school = "";
        }
        if(policy == null){
            policy = "";
        }
        if(limitContent == null){
            limitContent = "";
        }
        if(other == null){
            other = "";
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getStuNum() {
        return stuNum;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public int getStuType() {
        return stuType;
    }

    public void setStuType(int stuType) {
        this.stuType = stuType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPrecedence() {
        return precedence;
    }

    public void setPrecedence(int precedence) {
        this.precedence = precedence;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getLimitContent() {
        return limitContent;
    }

    public void setLimitContent(String limitContent) {
        this.limitContent = limitContent;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getManth() {
        return manth;
    }

    public void setManth(int manth) {
        this.manth = manth;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getChemistry() {
        return chemistry;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
    }

    public int getBiology() {
        return biology;
    }

    public void setBiology(int biology) {
        this.biology = biology;
    }

    public int getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(int comprehensive) {
        this.comprehensive = comprehensive;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(long createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
