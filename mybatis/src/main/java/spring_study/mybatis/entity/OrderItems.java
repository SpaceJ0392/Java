package spring_study.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrderItems {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
