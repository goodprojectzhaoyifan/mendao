package cn.mendao.resp;

import java.util.List;

/**
 * Created by warden on 19/1/15.
 */
public class MendaoCommitResp {

    private String testName;
    private String reportDesc;
    private String tableTitle;
    private MendaoTablesSetResp tableSet;
    private MendaoTableResultResp tableResult;
    private MendaoTopicReportResp topicReport;
    private String moreSuggestTitle;
    private List<TopicReport> moreSuggest;
    private String reportResult;
    private String lastImage;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    public MendaoTablesSetResp getTableSet() {
        return tableSet;
    }

    public void setTableSet(MendaoTablesSetResp tableSet) {
        this.tableSet = tableSet;
    }

    public MendaoTableResultResp getTableResult() {
        return tableResult;
    }

    public void setTableResult(MendaoTableResultResp tableResult) {
        this.tableResult = tableResult;
    }

    public MendaoTopicReportResp getTopicReport() {
        return topicReport;
    }

    public void setTopicReport(MendaoTopicReportResp topicReport) {
        this.topicReport = topicReport;
    }

    public String getMoreSuggestTitle() {
        return moreSuggestTitle;
    }

    public void setMoreSuggestTitle(String moreSuggestTitle) {
        this.moreSuggestTitle = moreSuggestTitle;
    }

    public List<TopicReport> getMoreSuggest() {
        return moreSuggest;
    }

    public void setMoreSuggest(List<TopicReport> moreSuggest) {
        this.moreSuggest = moreSuggest;
    }

    public String getReportResult() {
        return reportResult;
    }

    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }

    public String getLastImage() {
        return lastImage;
    }

    public void setLastImage(String lastImage) {
        this.lastImage = lastImage;
    }
}
