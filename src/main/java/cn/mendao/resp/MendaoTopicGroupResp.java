package cn.mendao.resp;

/**
 * Created by warden on 18/12/25.
 */
public class MendaoTopicGroupResp {

    private String topicGroupName;
    private String topicGroupId;
    private String topicGroupCode;
    private double topicGroupNum;
    private long topicGroupScore1;
    private long topicGroupScore2;

    public String getTopicGroupName() {
        return topicGroupName;
    }

    public void setTopicGroupName(String topicGroupName) {
        this.topicGroupName = topicGroupName;
    }

    public String getTopicGroupId() {
        return topicGroupId;
    }

    public void setTopicGroupId(String topicGroupId) {
        this.topicGroupId = topicGroupId;
    }

    public String getTopicGroupCode() {
        return topicGroupCode;
    }

    public void setTopicGroupCode(String topicGroupCode) {
        this.topicGroupCode = topicGroupCode;
    }

    public double getTopicGroupNum() {
        return topicGroupNum;
    }

    public void setTopicGroupNum(double topicGroupNum) {
        this.topicGroupNum = topicGroupNum;
    }

    public long getTopicGroupScore1() {
        return topicGroupScore1;
    }

    public void setTopicGroupScore1(long topicGroupScore1) {
        this.topicGroupScore1 = topicGroupScore1;
    }

    public long getTopicGroupScore2() {
        return topicGroupScore2;
    }

    public void setTopicGroupScore2(long topicGroupScore2) {
        this.topicGroupScore2 = topicGroupScore2;
    }
}
