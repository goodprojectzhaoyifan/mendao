package cn.mendao.dao;

import cn.mendao.bean.MendaoTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MendaoTestDao {

    MendaoTest findOne(@Param("id") Long id);

    List<MendaoTest> getList(@Param("type")int type);

    int insertDate(@Param("mendaoTest") MendaoTest mendaoTest);

    void updateDate(@Param("mendaoTest") MendaoTest mendaoTest);
}
