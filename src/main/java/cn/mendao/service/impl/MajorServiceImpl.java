package cn.mendao.service.impl;

import cn.mendao.bean.Major;
import cn.mendao.dao.MajorDao;
import cn.mendao.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
@Service("majorService")
public class MajorServiceImpl implements MajorService{

    @Autowired
    private MajorDao majorDao;

    @Override
    public List<Major> getListByParam(int type, String code, String majorName) {
        return majorDao.getListByParam(type, code, majorName);
    }
}
