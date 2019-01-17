package cn.mendao.resp;

import java.util.List;

/**
 * Created by warden on 19/1/15.
 */
public class MendaoTopicReportResp {

    private String topicReportTitle;
    private List<TopicReport> topicReportList;

    public String getTopicReportTitle() {
        return topicReportTitle;
    }

    public void setTopicReportTitle(String topicReportTitle) {
        this.topicReportTitle = topicReportTitle;
    }

    public List<TopicReport> getTopicReportList() {
        return topicReportList;
    }

    public void setTopicReportList(List<TopicReport> topicReportList) {
        this.topicReportList = topicReportList;
    }
}
