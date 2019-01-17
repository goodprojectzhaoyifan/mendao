package cn.mendao.bean;

import java.util.Date;

/**
 * Created by warden on 18/12/25.
 */
public class MendaoTestReport {

    private long id;
    private long testId;
    private String reportDesc;
    private int isShowReportDesc;
    private String tableSet;
    private int isShowTableSet;
    private String tableResult;
    private int isShowTableResult;
    private String topicReport;
    private int isShowTopicReportList;
    private String moreSuggest;
    private String moreSuggestTitle;
    private int isShowMoreSuggest;
    private String reportResult;
    private int isShowReportResult;
    private String lastImage;
    private int isShowLastImage;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public int getIsShowReportDesc() {
        return isShowReportDesc;
    }

    public void setIsShowReportDesc(int isShowReportDesc) {
        this.isShowReportDesc = isShowReportDesc;
    }

    public String getTableSet() {
        return tableSet;
    }

    public void setTableSet(String tableSet) {
        this.tableSet = tableSet;
    }

    public int getIsShowTableSet() {
        return isShowTableSet;
    }

    public void setIsShowTableSet(int isShowTableSet) {
        this.isShowTableSet = isShowTableSet;
    }

    public String getTableResult() {
        return tableResult;
    }

    public void setTableResult(String tableResult) {
        this.tableResult = tableResult;
    }

    public int getIsShowTableResult() {
        return isShowTableResult;
    }

    public void setIsShowTableResult(int isShowTableResult) {
        this.isShowTableResult = isShowTableResult;
    }

    public String getTopicReport() {
        return topicReport;
    }

    public void setTopicReport(String topicReport) {
        this.topicReport = topicReport;
    }

    public int getIsShowTopicReportList() {
        return isShowTopicReportList;
    }

    public void setIsShowTopicReportList(int isShowTopicReportList) {
        this.isShowTopicReportList = isShowTopicReportList;
    }

    public String getMoreSuggest() {
        return moreSuggest;
    }

    public void setMoreSuggest(String moreSuggest) {
        this.moreSuggest = moreSuggest;
    }

    public String getMoreSuggestTitle() {
        return moreSuggestTitle;
    }

    public void setMoreSuggestTitle(String moreSuggestTitle) {
        this.moreSuggestTitle = moreSuggestTitle;
    }

    public int getIsShowMoreSuggest() {
        return isShowMoreSuggest;
    }

    public void setIsShowMoreSuggest(int isShowMoreSuggest) {
        this.isShowMoreSuggest = isShowMoreSuggest;
    }

    public String getReportResult() {
        return reportResult;
    }

    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }

    public int getIsShowReportResult() {
        return isShowReportResult;
    }

    public void setIsShowReportResult(int isShowReportResult) {
        this.isShowReportResult = isShowReportResult;
    }

    public String getLastImage() {
        return lastImage;
    }

    public void setLastImage(String lastImage) {
        this.lastImage = lastImage;
    }

    public int getIsShowLastImage() {
        return isShowLastImage;
    }

    public void setIsShowLastImage(int isShowLastImage) {
        this.isShowLastImage = isShowLastImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
