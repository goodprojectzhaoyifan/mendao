package cn.mendao.service.impl;

import cn.mendao.bean.MendaoTopicLibrary;
import cn.mendao.dao.MendaoTopicLibraryDao;
import cn.mendao.service.MendaoTopicLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 18/1/25.
 */
@Service("mendaoTopicLibraryService")
public class MendaoTopicLibraryServiceImpl implements MendaoTopicLibraryService {

    @Autowired
    private MendaoTopicLibraryDao topicLibraryDao;

    @Override
    public MendaoTopicLibrary findOne(Long id) {
        return topicLibraryDao.findOne(id);
    }

    @Override
    public List<MendaoTopicLibrary> getListByTestId(long testId) {
        return topicLibraryDao.getListByTestId(testId);
    }

    @Override
    public void insertDate(MendaoTopicLibrary topicLibrary) {
        topicLibraryDao.insertDate(topicLibrary);
    }

    @Override
    public void updateDate(MendaoTopicLibrary topicLibrary) {
        topicLibraryDao.updateDate(topicLibrary);
    }

    @Override
    public void deleteByTestId(long testId) {
        topicLibraryDao.deleteByTestId(testId);
    }

}
