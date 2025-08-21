package spring_study.mybatis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.dto.OrderRequest;
import spring_study.mybatis.dto.OrderSummaryDto;
import spring_study.mybatis.dto.PageDto;
import spring_study.mybatis.entity.OrderItems;
import spring_study.mybatis.entity.Product;
import spring_study.mybatis.entity.User;
import spring_study.mybatis.mapper.ProductMapper;
import spring_study.mybatis.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OrderServiceTest {

    @Autowired private OrderService orderService;
    @Autowired private ProductMapper productMapper;
    @Autowired private UserMapper userMapper;

    private User testUser;
    private final List<Product> testProducts = new ArrayList<>();

    @BeforeEach
    void initSet(){
        testUser = null;
        testProducts.clear();

        User user = new User(null, "A", 10, "abcd");
        userMapper.insertUser(user);
        testUser = user;

        Random random = new Random();
        for(int i = 1; i <= 20; i++){
            Product product = new Product(null, "product" + i, random.nextInt(1000, 10000), random.nextInt(10, 100));
            productMapper.insertProduct(product);
            testProducts.add(product);
        }
    }


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
        setOrderData(testUser.getId(), testProducts);

        //when & then
        orderService.getOrderSummaryList(1L).forEach(System.out::println);
    }

    @Test
    @Transactional
    public void getOrderDetailList() {
        //given
        setOrderData(testUser.getId(), testProducts);

        //when & then
        orderService.getOrderDetailList(1L).forEach(System.out::println);
    }

    @Test
    public void getOrderSummaryListPaging() {
        //given
        setOrderData(testUser.getId(), testProducts);

        //when
        List<OrderSummaryDto> res = orderService.getOrderSummaryListPaging(1L, new PageDto(1, 10));

        //then
        res.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void getOrderDetailListPaging() {
        //given
        OrderRequest res = setOrderData(testUser.getId(), testProducts);

        //when & then
        orderService.getOrderDetailListPaging(res.getOrderItems().get(0).getOrderId(),
                new PageDto(1, 10)).forEach(System.out::println);
    }


    private OrderRequest setOrderData(Long userId, List<Product> productList) {

        List<OrderItems>  orderItems = new ArrayList<>();
        for(Product product : productList){
            orderItems.add(new OrderItems(null, null, product.getId(), 1));
        }
        OrderRequest orderRequest = new OrderRequest(userId, orderItems);

        //when
        OrderRequest results = orderService.placeOrder(orderRequest);

        //then
        //results.getOrderItems().forEach(System.out::println);
        return results;
    }



}