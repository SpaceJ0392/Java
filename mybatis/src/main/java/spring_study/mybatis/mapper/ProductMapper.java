package spring_study.mybatis.mapper;

import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import spring_study.mybatis.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    void insertProduct(Product product);

    List<Product> selectAll();
    Product selectById(Long id);

    void updateProductPriceById(@Param("id") Long id, @Param("price") Integer price);
    int updateProductPriceByName(@Param("name") String name, @Param("price") Integer price);

    int updateProductStockById(@Param("id") Long id, @Param("quantity") Integer quantity);

    int deleteProductByStock(Integer stock);
}
