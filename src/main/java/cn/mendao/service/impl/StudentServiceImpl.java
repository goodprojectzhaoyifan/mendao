package cn.mendao.service.impl;

import cn.mendao.bean.Student;
import cn.mendao.dao.StudentDao;
import cn.mendao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 18/1/25.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    public void insertDate(Student student) {
        studentDao.insertDate(student);
    }

    @Override
    public Student findOne(long id) {
        return studentDao.findOne(id);
    }

    @Override
    public void updateData(Student student) {
        studentDao.updateData(student);
    }

    @Override
    public List<Student> getList(Long createdUser) {
        return studentDao.getList(createdUser);
    }
}
