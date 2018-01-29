package cn.mendao.dao;

import cn.mendao.bean.StuScheme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 18/1/28.
 */
public interface StuSchemeDao {

    void insertData(@Param("stuScheme") StuScheme stuScheme);


    void deleteDataByStuNo(@Param("stuNo") String stuNo);

    List<StuScheme> getListByStuNo(@Param("stuNo") String stuNo);

    void updateChangeById(@Param("id") long id,@Param("schemeChange") int schemeChange);
}
