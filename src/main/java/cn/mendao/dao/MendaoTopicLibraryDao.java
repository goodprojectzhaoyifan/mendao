package cn.mendao.dao;

import cn.mendao.bean.MendaoTopicLibrary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MendaoTopicLibraryDao {

    MendaoTopicLibrary findOne(@Param("id") Long id);

    List<MendaoTopicLibrary> getListByTestId(@Param("testId") long testId);

    int insertDate(@Param("topicLibrary") MendaoTopicLibrary topicLibrary);

    void updateDate(@Param("topicLibrary") MendaoTopicLibrary topicLibrary);
}
