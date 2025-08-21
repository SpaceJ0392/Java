package spring_study.mybatis.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.dto.OrderRequest;
import spring_study.mybatis.dto.OrderSummaryDto;
import spring_study.mybatis.entity.OrderItems;
import spring_study.mybatis.entity.Product;
import spring_study.mybatis.entity.User;
import spring_study.mybatis.mapper.ProductMapper;
import spring_study.mybatis.mapper.UserMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OrderServiceTest {

    @Autowired private OrderService orderService;
    @Autowired private ProductMapper productMapper;
    @Autowired private UserMapper userMapper;

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

    @Test
    @Transactional
    void  placeOrderFailure() {
        User user = new User(null, "A", 10, "abcd");
        Product productA = new Product(null, "새우깡", 1000, 9);
        Product productB = new Product(null, "감자깡", 1500, 7);

        userMapper.insertUser(user);
        productMapper.insertProduct(productA);
        productMapper.insertProduct(productB);

        OrderRequest orderRequest = new OrderRequest(user.getId(), List.of(
                new OrderItems(null, null, productA.getId(), 2),
                new OrderItems(null, null, productB.getId(), 15)
        ));

        assertThrows(RuntimeException.class, () -> {
            orderService.placeOrder(orderRequest);
        }, "재고 부족으로 인한 runtime error");

        Product product = productMapper.selectById(productA.getId());
        assertEquals(product.getStock(), productB.getStock(), "실패시 재고는 그대로 유지");
    }

    @Test
    @Transactional
    public void getOrderSummaryList() {
        //given
        placeOrderHelper();

        //when & then
        orderService.getOrderSummaryList(1L).forEach(System.out::println);
    }

    @Test
    @Transactional
    public void getOrderDetailList() {
        //given
        placeOrderHelper();

        //when & then
        orderService.getOrderDetailList(1L).forEach(System.out::println);
    }



    private void placeOrderHelper() {
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