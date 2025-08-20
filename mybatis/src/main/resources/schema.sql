-- 기존에 USERS 테이블이 있다면 삭제
DROP TABLE IF EXISTS USERS;

-- USERS 테이블 생성
CREATE TABLE USERS (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(255) NOT NULL,
                       age INTEGER,
                       email VARCHAR(255)
);

DROP TABLE IF EXISTS PRODUCTS;

CREATE TABLE PRODUCTS (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        price INT NOT NULL,
                        stock INT DEFAULT 0
);

DROP TABLE IF EXISTS ORDERS;

CREATE TABLE ORDERS (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    user_id BIGINT NOT NULL ,
                    product_id BIGINT NOT NULL,
                    quantity INT NOT NULL,
                    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
                    FOREIGN KEY (user_id) REFERENCES USERS(id),
                    FOREIGN KEY (product_id) references PRODUCTS(id)
);