package cn.mendao.service;

import cn.mendao.bean.MendaoTest;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MendaoTestService {


    MendaoTest findOne(Long id);

    List<MendaoTest> getList();

    void insertDate(MendaoTest mendaoTest);

    void updateDate(MendaoTest mendaoTest);
}
