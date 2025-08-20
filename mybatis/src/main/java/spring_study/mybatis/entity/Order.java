package spring_study.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Timestamp orderDate;
    private OrderStatus status;
}
