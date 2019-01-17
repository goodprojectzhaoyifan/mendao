package cn.mendao.req;

import java.util.List;

/**
 * Created by warden on 19/1/16.
 */
public class MendaoTopicReport {

    private String titleWarning;
    private int orderType;
    private int showNum;
    private List<MendaoTopicReportList> topicReportList;

    public String getTitleWarning() {
        return titleWarning;
    }

    public void setTitleWarning(String titleWarning) {
        this.titleWarning = titleWarning;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }

    public List<MendaoTopicReportList> getTopicReportList() {
        return topicReportList;
    }

    public void setTopicReportList(List<MendaoTopicReportList> topicReportList) {
        this.topicReportList = topicReportList;
    }
}
