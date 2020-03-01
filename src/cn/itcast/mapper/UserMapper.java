package cn.itcast.mapper;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> findAllUsers();

    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insertUser(User user);

    int deleteUserById(Integer id);

    User findUserById(Integer id);

    int updateUser(User user);
}
