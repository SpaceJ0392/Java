package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import spring_study.mybatis.dto.OrderDetailDto;
import spring_study.mybatis.dto.OrderSummaryDto;
import spring_study.mybatis.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    void insertOrder(Order order);
    List<OrderSummaryDto> findOrdersByUserId(Long id);
    List<OrderDetailDto> findDetailOrderByOrderId(Long orderId);

    void selectOrderDetailByUserId(Long id);

    //int updateOrderByUserIdAndProductId(Order order);
}
