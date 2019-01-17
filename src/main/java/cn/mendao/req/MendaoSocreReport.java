package cn.mendao.req;

/**
 * Created by warden on 19/1/16.
 */
public class MendaoSocreReport {

    private int topSocre;
    private int downSocre;
    private String socreDesc;


    public int getTopSocre() {
        return topSocre;
    }

    public void setTopSocre(int topSocre) {
        this.topSocre = topSocre;
    }

    public int getDownSocre() {
        return downSocre;
    }

    public void setDownSocre(int downSocre) {
        this.downSocre = downSocre;
    }

    public String getSocreDesc() {
        return socreDesc;
    }

    public void setSocreDesc(String socreDesc) {
        this.socreDesc = socreDesc;
    }
}
