package cn.mendao.service;

import cn.mendao.bean.User;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface UserService {

    List<User> getUser(String username, String password);

    User findOne(Long id);
}
