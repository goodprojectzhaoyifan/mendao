package cn.mendao.resp;

/**
 * Created by warden on 18/12/26.
 */
public class MendaoTopicLibraryResp {

    private String topicGroupId;
    private String topicLibraryName;
    private String options;

    public String getTopicGroupId() {
        return topicGroupId;
    }

    public void setTopicGroupId(String topicGroupId) {
        this.topicGroupId = topicGroupId;
    }

    public String getTopicLibraryName() {
        return topicLibraryName;
    }

    public void setTopicLibraryName(String topicLibraryName) {
        this.topicLibraryName = topicLibraryName;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
