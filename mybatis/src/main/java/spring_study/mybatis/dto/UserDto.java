package spring_study.mybatis.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class UserDto {

    private Long id;
    private String userName;
    private Integer age;
    private String email;
}
