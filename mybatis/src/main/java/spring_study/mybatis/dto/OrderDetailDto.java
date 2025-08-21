package spring_study.mybatis.dto;

import lombok.*;
import spring_study.mybatis.entity.OrderStatus;
import spring_study.mybatis.entity.User;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class OrderDetailDto {
    private Long orderId;
    private OrderStatus orderStatus;
    private Timestamp orderDate;

    private User user;
    private List<OrderItemDetail> orderItemList;
}
