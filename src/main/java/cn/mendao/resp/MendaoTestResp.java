package cn.mendao.resp;

/**
 * Created by warden on 18/12/25.
 */
public class MendaoTestResp {

    private long testId;
    private String testName;
    private String testImage;
    private String testIntroduction;
    private String testDesc;
    private int status;


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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
