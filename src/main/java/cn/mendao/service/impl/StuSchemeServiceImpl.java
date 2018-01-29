package cn.mendao.service.impl;

import cn.mendao.bean.StuScheme;
import cn.mendao.dao.StuSchemeDao;
import cn.mendao.service.StuSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 18/1/28.
 */
@Service("stuSchemeService")
public class StuSchemeServiceImpl implements StuSchemeService{

    @Autowired
    private StuSchemeDao stuSchemeDao;

    @Override
    public void insertData(StuScheme stuScheme) {
        stuSchemeDao.insertData(stuScheme);
    }

    @Override
    public void deleteDataByStuNo(String stuNo) {
        stuSchemeDao.deleteDataByStuNo(stuNo);
    }

    @Override
    public List<StuScheme> getListByStuNo(String stuNo) {
        return stuSchemeDao.getListByStuNo(stuNo);
    }

    @Override
    public void updateChangeById(long id, int schemeChange) {
        stuSchemeDao.updateChangeById(id,schemeChange);
    }
}
