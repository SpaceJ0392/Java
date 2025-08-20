package spring_study.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional
    void findAllUsers() {
        userMapper.insertUser(new User(null, "홍길동", 24, "@email.com"));
        userMapper.insertUser(new User(null, "이명자", 42, "@email.com"));
        userMapper.findAllUsers().forEach(System.out::println);
    }

    @Test
    @Transactional
    void findUserById() {
        //given
        User user = new User(null, "홍길동", 24, "@email.com");
        userMapper.insertUser(user);

        //when & then
        System.out.println(userMapper.findUserById(user.getId()));
    }
}