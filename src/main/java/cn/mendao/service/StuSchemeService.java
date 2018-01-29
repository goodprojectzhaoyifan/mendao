package cn.mendao.service;

import cn.mendao.bean.StuScheme;

import java.util.List;

/**
 * Created by warden on 18/1/28.
 */
public interface StuSchemeService {
    void insertData(StuScheme stuScheme);

    void deleteDataByStuNo(String stuNo);

    List<StuScheme> getListByStuNo(String stuNo);
}
