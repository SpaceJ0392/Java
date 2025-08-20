package spring_study.mybatis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring_study.mybatis.dto.Order;
import spring_study.mybatis.dto.Product;
import spring_study.mybatis.dto.User;
import spring_study.mybatis.mapper.OrderMapper;
import spring_study.mybatis.mapper.ProductMapper;
import spring_study.mybatis.mapper.UserMapper;
import spring_study.mybatis.service.OrderService;

@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(UserMapper userMapper,  ProductMapper productMapper, OrderMapper orderMapper) {
        /* -- user CRUD test -- */
//        return args -> {
//            int cnt = userMapper.insertUser(new User(null, "john", null, "1234@gmail.com"));
//            cnt += userMapper.insertUser(new User(null, "kim", 12, null));
//            List<User> users = userMapper.findAllUsers();
//            users.forEach(System.out::println);
//        };

        /* -- product CRUD review -- */
//        return args -> {
//            //Create
//            Product newProduct = new Product(null, "새우깡", 1500, 100);
//            int insertProduct = productMapper.insertProduct(newProduct);
//            System.out.println("insert : 새로운 데이터 id는 " + newProduct.getId());
//
//            //Read
//            List<Product> products = productMapper.selectAll();
//            products.forEach(System.out::println);
//
//            //Update
//            Long targetId = products.stream()
//                                    .filter(p -> "새우깡".equals(p.getName()))
//                                    .findFirst()
//                                    .map(Product::getId)
//                                    .orElse(null);
//
//            int updateTargetcnt = productMapper.updateProductPriceById(targetId, 1800);
//            System.out.println(productMapper.selectById(targetId));
//
//            //Delete
//            int insertcnt = productMapper.insertProduct(new Product(null, "감자깡", 1500, 0));
//            products = productMapper.selectAll();
//            products.forEach(System.out::println);
//
//            int deletedCnt = productMapper.deleteProductByStock(0);
//            System.out.println(deletedCnt);
//
//        };

        /* -- order Transaction test -- */
        return args -> {
            int insertedcnt = userMapper.insertUser(new User(null, "A", 10, "abcd"));
            int insertProduct = productMapper.insertProduct(new Product(null, "새우깡", 1000, 9));

            OrderService orderService = new OrderService(orderMapper, userMapper, productMapper);
            orderService.placeOrder(new Order(null, 1L, 1L, 7, null));

            System.out.println(productMapper.selectById(1L));
        };
    }
}
