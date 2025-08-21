package spring_study.mybatis.dto;

import lombok.*;
import spring_study.mybatis.entity.OrderItems;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderRequest {

    private Long userId;
    private List<OrderItems> orderItems;

    public OrderRequest(Long userId) {
        this.userId = userId;
    }

}
