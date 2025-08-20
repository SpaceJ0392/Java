package spring_study.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class Order {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Timestamp orderDate;

}
