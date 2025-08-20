package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import spring_study.mybatis.dto.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAllUsers();
    int insertUser(User user);
}
