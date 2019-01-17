package cn.mendao.req;

import java.util.List;

/**
 * Created by warden on 19/1/16.
 */
public class MendaoTopicReportList {

    private String topicGroupId;
    private String title;
    private List<MendaoSocreReport> socreReport;

    public String getTopicGroupId() {
        return topicGroupId;
    }

    public void setTopicGroupId(String topicGroupId) {
        this.topicGroupId = topicGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MendaoSocreReport> getSocreReport() {
        return socreReport;
    }

    public void setSocreReport(List<MendaoSocreReport> socreReport) {
        this.socreReport = socreReport;
    }
}
