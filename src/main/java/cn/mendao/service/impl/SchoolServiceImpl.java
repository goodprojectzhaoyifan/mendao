package cn.mendao.service.impl;

import cn.mendao.bean.School;
import cn.mendao.dao.SchoolDao;
import cn.mendao.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
@Service("schoolService")
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public List<School> getListByParam(int type, String major) {
        return schoolDao.getListByParam(type, major);
    }
}
