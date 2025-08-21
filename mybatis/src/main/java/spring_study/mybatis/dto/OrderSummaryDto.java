package spring_study.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import spring_study.mybatis.entity.OrderStatus;

import java.sql.Timestamp;

@Getter @Builder
@AllArgsConstructor
@ToString
public class OrderSummaryDto {
    private Long orderId;
    private String orderName; //~~ 외 1건
    private OrderStatus orderStatus;
    private Timestamp orderDate;
    private Integer totItemCnt;
}
