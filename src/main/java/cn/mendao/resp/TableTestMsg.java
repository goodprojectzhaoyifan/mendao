package cn.mendao.resp;

/**
 * Created by warden on 19/1/15.
 */
public class TableTestMsg {

    private String topicGroupId;
    private String topicGroupCode;
    private String unitName;
    private String unitScore;


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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitScore() {
        return unitScore;
    }

    public void setUnitScore(String unitScore) {
        this.unitScore = unitScore;
    }
}
