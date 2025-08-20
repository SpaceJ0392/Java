package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import spring_study.mybatis.dto.Order;

@Mapper
public interface OrderMapper {

    int insertOrder(Order order);

    //int updateOrderByUserIdAndProductId(Order order);
}
