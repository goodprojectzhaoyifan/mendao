package cn.mendao.req;

/**
 * Created by warden on 18/12/25.
 */
public class MendaoTestReq {
    private long testId;
    private String testName;
    private String testImage;
    private String testIntroduction;
    private String testDesc;
    private String topicGroup;

    private int type;



    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestImage() {
        return testImage;
    }

    public void setTestImage(String testImage) {
        this.testImage = testImage;
    }

    public String getTestIntroduction() {
        return testIntroduction;
    }

    public void setTestIntroduction(String testIntroduction) {
        this.testIntroduction = testIntroduction;
    }

    public String getTestDesc() {
        return testDesc;
    }

    public void setTestDesc(String testDesc) {
        this.testDesc = testDesc;
    }

    public String getTopicGroup() {
        return topicGroup;
    }

    public void setTopicGroup(String topicGroup) {
        this.topicGroup = topicGroup;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
