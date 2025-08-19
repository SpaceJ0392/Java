package spring_study.mybatis.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Product {

    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
}
