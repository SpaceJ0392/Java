package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import spring_study.mybatis.dto.UserDto;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> findAllUsers();
    int insertUser(UserDto userDto);
}
