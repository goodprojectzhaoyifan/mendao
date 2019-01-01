package cn.mendao.service;

import cn.mendao.bean.MendaoTopicLibrary;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MendaoTopicLibraryService {


    MendaoTopicLibrary findOne(Long id);

    List<MendaoTopicLibrary> getListByTestId(long testId);

    void insertDate(MendaoTopicLibrary topicLibrary);

    void updateDate(MendaoTopicLibrary topicLibrary);

}
