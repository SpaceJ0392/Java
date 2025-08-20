package spring_study.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {
    private final Long id;
    private final String name;
    private final Integer price;
    private final Integer stock;
}
