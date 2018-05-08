package cn.mendao.dao;

import cn.mendao.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by warden on 17/10/25.
 */
public interface UserDao {

    List<User> getUser(@Param("username") String username,@Param("password") String password);

    User findOne(@Param("id") Long id);

    List<User> getUserListByParam(@Param("username")String username,@Param("name") String name,@Param("status") int status);

    void insertDate(@Param("user")User user);

    void updateDate(@Param("user")User user);
}
