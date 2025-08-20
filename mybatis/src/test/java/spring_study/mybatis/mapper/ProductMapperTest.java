package spring_study.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring_study.mybatis.entity.Product;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    /* -- product CRUD review -- */
    @Test
    @Transactional
    void insertProduct() {
        Product product = new Product(null, "새우깡", 1000, 10);
        productMapper.insertProduct(product);
        System.out.println(product);
    }

    @Test
    @Transactional
    void selectAllProduct() {
        productMapper.insertProduct(new Product(null, "새우깡", 1000, 10));
        productMapper.insertProduct(new Product(null, "감자깡", 1500, 1));

        productMapper.selectAll().forEach(System.out::println);
    }

    @Test
    @Transactional
    void updateProductPrice() {
        productMapper.insertProduct(new Product(null, "새우깡", 1000, 10));
        Product product = new Product(null, "감자깡", 1500, 1);
        productMapper.insertProduct(product);

        productMapper.updateProductPriceById(product.getId(), 2000);
        System.out.println(productMapper.selectById(product.getId()));
    }

    @Test
    @Transactional
    void deleteProduct() {
        productMapper.insertProduct(new Product(null, "새우깡", 1000, 10));
        productMapper.insertProduct(new Product(null, "감자깡", 1500, 0));

        int deletedCnt = productMapper.deleteProductByStock(0);
        System.out.println(deletedCnt);
    }
}