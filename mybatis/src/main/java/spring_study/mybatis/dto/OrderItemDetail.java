package spring_study.mybatis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class OrderItemDetail {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Integer price;
}
