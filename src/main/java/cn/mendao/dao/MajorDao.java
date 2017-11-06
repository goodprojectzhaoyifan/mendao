package cn.mendao.dao;

import cn.mendao.bean.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface MajorDao {

    List<Major> getListByParam(@Param("type") int type, @Param("code") String code, @Param("majorName") String majorName);
}
