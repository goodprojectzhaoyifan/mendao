package cn.mendao.req;

import java.util.List;

/**
 * Created by warden on 18/1/28.
 */
public class StuSchemeList {


    private String stuNo;
    private List<Schemes> schemes;


    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public List<Schemes> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Schemes> schemes) {
        this.schemes = schemes;
    }
}
