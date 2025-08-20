package spring_study.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String userName;
    private Integer age;
    private String email;
}
