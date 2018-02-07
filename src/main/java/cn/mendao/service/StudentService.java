package cn.mendao.service;

import cn.mendao.bean.Student;

import java.util.List;

/**
 * Created by warden on 18/1/25.
 */
public interface StudentService {

    void insertDate(Student student);

    Student findOne(long id);

    void updateData(Student oldStudent);

    List<Student> getList(Long createId,String stuNo);


    Student getByStuNo(String stuNo);
}
