package cn.mendao.bean;

import java.util.Date;

/**
 * Created by warden on 18/12/25.
 */
public class MendaoTopicLibrary {

    private long id;
    private long testId;
    private String topicLibraryName;
    private String topicGroupId;
    private String options;
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

    public String getTopicLibraryName() {
        return topicLibraryName;
    }

    public void setTopicLibraryName(String topicLibraryName) {
        this.topicLibraryName = topicLibraryName;
    }

    public String getTopicGroupId() {
        return topicGroupId;
    }

    public void setTopicGroupId(String topicGroupId) {
        this.topicGroupId = topicGroupId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
