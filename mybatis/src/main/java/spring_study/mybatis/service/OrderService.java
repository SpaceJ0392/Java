package spring_study.mybatis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.dto.OrderDetailDto;
import spring_study.mybatis.dto.OrderRequest;
import spring_study.mybatis.dto.OrderSummaryDto;
import spring_study.mybatis.dto.PageDto;
import spring_study.mybatis.entity.Order;
import spring_study.mybatis.entity.OrderItems;
import spring_study.mybatis.entity.OrderStatus;
import spring_study.mybatis.mapper.OrderItemsMapper;
import spring_study.mybatis.mapper.OrderMapper;
import spring_study.mybatis.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final OrderItemsMapper orderItemsMapper;

    /* -- order Transaction test -- */
    @Transactional
    public OrderRequest placeOrder(OrderRequest request) {

        OrderRequest results = new OrderRequest(request.getUserId(), new ArrayList<>());

        int updated;
        for (OrderItems item : request.getOrderItems()){
            //1. product 물량 감소
            updated = productMapper.updateProductStockById(item.getProductId(), item.getQuantity());

            if(updated == 0){
                throw new RuntimeException(item.getProductId() + "번 물품 수랑이 부족합니다.");
            }
        }

        //2. 주문 추가.
        Order order = new Order(null, request.getUserId(), null, OrderStatus.ORDERED);
        orderMapper.insertOrder(order);

        for (OrderItems item : request.getOrderItems()){
            OrderItems orderItems = new OrderItems(null, order.getId(),
                                                        item.getProductId(), item.getQuantity());

            orderItemsMapper.insertOrderItems(orderItems);
            results.getOrderItems().add(orderItems);
        }

        return results;
    }

    public List<OrderSummaryDto> getOrderSummaryList(Long userId) {
        List<OrderSummaryDto> orders = orderMapper.findOrdersByUserId(userId);

        return orders.stream()
                .map(order -> {
                     String orderName = order.getOrderName();
                     if (order.getTotItemCnt() >= 1) orderName += " 외 " + (order.getTotItemCnt() - 1) + " 건";
                     return new OrderSummaryDto(order.getOrderId(), orderName, order.getOrderStatus(), order.getOrderDate(), order.getTotItemCnt());
        }).toList();
    }

    public List<OrderDetailDto>  getOrderDetailList(Long orderId) {
        return orderMapper.findDetailOrderByOrderId(orderId);
    }

    public List<OrderSummaryDto> getOrderSummaryListPaging(Long userId, PageDto pageDto) {
        List<OrderSummaryDto> orders = orderMapper.findOrdersByUserIdOnPaging(userId, pageDto);

        return orders.stream()
                .map(order -> {
                    String orderName = order.getOrderName();
                    if (order.getTotItemCnt() >= 1) orderName += " 외 " + (order.getTotItemCnt() - 1) + " 건";
                    return new OrderSummaryDto(order.getOrderId(), orderName, order.getOrderStatus(), order.getOrderDate(), order.getTotItemCnt());
                }).toList();
    }

    public List<OrderDetailDto>  getOrderDetailListPaging(Long orderId, PageDto pageDto) {
        return orderMapper.findDetailOrderByOrderIdPaging(orderId, pageDto);
    }

}
