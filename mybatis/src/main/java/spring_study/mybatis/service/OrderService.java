package spring_study.mybatis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.dto.OrderRequest;
import spring_study.mybatis.entity.Order;
import spring_study.mybatis.entity.OrderItems;
import spring_study.mybatis.entity.OrderStatus;
import spring_study.mybatis.mapper.OrderItemsMapper;
import spring_study.mybatis.mapper.OrderMapper;
import spring_study.mybatis.mapper.ProductMapper;

import java.util.ArrayList;

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
}
