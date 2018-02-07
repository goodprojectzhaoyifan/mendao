package cn.mendao.dao;

import cn.mendao.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface StudentDao {


    void insertDate(@Param("student") Student student);

    Student findOne(@Param("id") long id);

    void updateData(@Param("student") Student student);

    List<Student> getList(@Param("createdUser") Long createdUser,@Param("stuNo") String stuNo);

    List<Student> getByStuNo(@Param("stuNo") String stuNo);
}
