package spring_study.mybatis.mapper;

import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import spring_study.mybatis.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAllUsers();
    User findUserById(Long id);

    void insertUser(User user);
}
