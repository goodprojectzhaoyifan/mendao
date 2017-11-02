package cn.mendao.dao;

import cn.mendao.bean.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface SchoolDao {

    List<School> getListByParam(@Param("type") int type,@Param("major") String major);
}
