package spring_study.mybatis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring_study.mybatis.dto.Product;
import spring_study.mybatis.dto.UserDto;
import spring_study.mybatis.mapper.ProductMapper;
import spring_study.mybatis.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(UserMapper userMapper,  ProductMapper productMapper) {
//        return args -> {
//            int john = userMapper.insertUser(new UserDto(null, "john", null, "1234@gmail.com"));
//            int kim = userMapper.insertUser(new UserDto(null, "kim", 12, null));
//
//            System.out.println("kim.id = " + kim);
//            System.out.println("john.id = " + john);
//
//            List<UserDto> users = userMapper.findAllUsers();
//            users.forEach(System.out::println);
//        };

        return args -> {
            //Create
            Product newProduct = new Product(null, "새우깡", 1500, 100);
            int insertProduct = productMapper.insertProduct(newProduct);
            System.out.println("insert : 새로운 데이터 id는 " + newProduct.getId());

            //Read
            List<Product> products = productMapper.selectAll();
            products.forEach(System.out::println);

            //Update
            Long targetId = products.stream()
                                    .filter(p -> "새우깡".equals(p.getName()))
                                    .findFirst()
                                    .map(Product::getId)
                                    .orElse(null);

            int updateTargetcnt = productMapper.updateProductPriceById(targetId, 1800);
            System.out.println(productMapper.selectById(targetId));

            //Delete
            int insertcnt = productMapper.insertProduct(new Product(null, "감자깡", 1500, 0));
            products = productMapper.selectAll();
            products.forEach(System.out::println);

            int deletedCnt = productMapper.deleteProductByStock(0);
            System.out.println(deletedCnt);

        };
    }
}
