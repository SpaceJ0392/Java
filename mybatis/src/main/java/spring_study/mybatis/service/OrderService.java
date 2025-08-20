package spring_study.mybatis.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.dto.Order;
import spring_study.mybatis.dto.Product;
import spring_study.mybatis.mapper.OrderMapper;
import spring_study.mybatis.mapper.ProductMapper;
import spring_study.mybatis.mapper.UserMapper;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    public OrderService(OrderMapper orderMapper, UserMapper userMapper, ProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    @Transactional
    public void placeOrder(Order order) {

        //1. product 물량 감소
        int updateCnt = productMapper.updateProductStockById(order.getProductId(), order.getQuantity());

        if(updateCnt == 0){
            throw new RuntimeException("물품 수랑이 부족합니다.");
        }

        //2. 주문 추가.
        orderMapper.insertOrder(order);
    };
}
