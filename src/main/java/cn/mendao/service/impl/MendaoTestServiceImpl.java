package cn.mendao.service.impl;

import cn.mendao.bean.MendaoTest;
import cn.mendao.dao.MendaoTestDao;
import cn.mendao.service.MendaoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 18/1/25.
 */
@Service("mendaoTestService")
public class MendaoTestServiceImpl implements MendaoTestService{

    @Autowired
    private MendaoTestDao mendaoTestDao;

    @Override
    public MendaoTest findOne(Long id) {
        return mendaoTestDao.findOne(id);
    }

    @Override
    public List<MendaoTest> getList() {
        return mendaoTestDao.getList();
    }

    @Override
    public void insertDate(MendaoTest mendaoTest) {
        mendaoTestDao.insertDate(mendaoTest);
    }

    @Override
    public void updateDate(MendaoTest mendaoTest) {
        mendaoTestDao.updateDate(mendaoTest);
    }

}
