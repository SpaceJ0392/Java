package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import spring_study.mybatis.entity.Order;

@Mapper
public interface OrderMapper {

    void insertOrder(Order order);

    //int updateOrderByUserIdAndProductId(Order order);
}
