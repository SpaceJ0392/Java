package spring_study.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import spring_study.mybatis.dto.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insertProduct(Product product);

    List<Product> selectAll();
    Product selectById(Long id);

    int updateProductPriceById(@Param("id") Long id, @Param("price") Integer price);
    int updateProductPriceByName(@Param("name") String name, @Param("price") Integer price);

    int deleteProductByStock(Integer stock);
}
