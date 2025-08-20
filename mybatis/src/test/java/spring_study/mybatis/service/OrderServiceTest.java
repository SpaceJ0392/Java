package spring_study.mybatis.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.dto.OrderRequest;
import spring_study.mybatis.entity.OrderItems;
import spring_study.mybatis.entity.Product;
import spring_study.mybatis.entity.User;
import spring_study.mybatis.mapper.ProductMapper;
import spring_study.mybatis.mapper.UserMapper;

import java.util.List;


@SpringBootTest
class OrderServiceTest {

    @Autowired private OrderService orderService;
    @Autowired private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    @Transactional // 테스트 코드에서 쓰면 테스트 후 DB 롤백
    @DisplayName("성공 : 주문 성공")
    void placeOrderSuccess() {
        //given
        User user = new User(null, "A", 10, "abcd");
        Product productA = new Product(null, "새우깡", 1000, 9);
        Product productB = new Product(null, "감자깡", 1500, 7);

        userMapper.insertUser(user);
        productMapper.insertProduct(productA);
        productMapper.insertProduct(productB);

        OrderRequest orderRequest = new OrderRequest(user.getId(), List.of(
                new OrderItems(null, null, productA.getId(), 3),
                new OrderItems(null, null, productB.getId(), 5)
        ));

        //when
        OrderRequest results = orderService.placeOrder(orderRequest);

        //then
        results.getOrderItems().forEach(System.out::println);
    }




}