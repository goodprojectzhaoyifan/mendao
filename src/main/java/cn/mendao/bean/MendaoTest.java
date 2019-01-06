package cn.mendao.bean;

/**
 * Created by warden on 18/12/25.
 */
public class MendaoTest {

    private long id;
    private String name;
    private String image;
    private String introduction;
    private String testDesc;
    private String topicGroup;
    private int status;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
