package cn.mendao.service.impl;

import cn.mendao.bean.User;
import cn.mendao.dao.UserDao;
import cn.mendao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by warden on 18/1/25.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUser(String username, String password) {
        return userDao.getUser(username, password);
    }

    @Override
    public User findOne(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> getUserListByParam(String username, String name, int status) {
        return userDao.getUserListByParam(username, name, status);
    }

    @Override
    public void insertDate(User user) {
        userDao.insertDate(user);
    }

    @Override
    public void updateDate(User user) {
        userDao.updateDate(user);
    }

}
