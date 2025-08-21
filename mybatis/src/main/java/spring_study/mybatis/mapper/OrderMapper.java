package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import spring_study.mybatis.dto.OrderDetailDto;
import spring_study.mybatis.dto.OrderSummaryDto;
import spring_study.mybatis.dto.PageDto;
import spring_study.mybatis.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    void insertOrder(Order order);
    List<OrderSummaryDto> findOrdersByUserId(Long id);
    List<OrderSummaryDto> findOrdersByUserIdOnPaging(@Param("userId")Long userId, @Param("paging")PageDto pageDto);

    List<OrderDetailDto> findDetailOrderByOrderId(Long orderId);
    List<OrderDetailDto> findDetailOrderByOrderIdPaging(@Param("orderId")Long orderId, @Param("paging")PageDto pageDto);
}
