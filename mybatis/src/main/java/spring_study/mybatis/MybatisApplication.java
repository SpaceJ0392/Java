package spring_study.mybatis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring_study.mybatis.dto.UserDto;
import spring_study.mybatis.mapper.UserMapper;

import java.util.List;

@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(UserMapper userMapper) {
        return args -> {
            int john = userMapper.insertUser(new UserDto(null, "john", null, "1234@gmail.com"));
            int kim = userMapper.insertUser(new UserDto(null, "kim", 12, null));

            System.out.println("kim.id = " + kim);
            System.out.println("john.id = " + john);

            List<UserDto> users = userMapper.findAllUsers();
            users.forEach(System.out::println);
        };
    }
}
