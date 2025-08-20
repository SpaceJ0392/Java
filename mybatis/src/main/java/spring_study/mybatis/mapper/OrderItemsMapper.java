package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import spring_study.mybatis.entity.OrderItems;

@Mapper
public interface OrderItemsMapper {

     void insertOrderItems(OrderItems orderItems);
}
