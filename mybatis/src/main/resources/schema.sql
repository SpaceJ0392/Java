-- 기존에 USERS 테이블이 있다면 삭제
DROP TABLE IF EXISTS USERS;

-- USERS 테이블 생성
CREATE TABLE USERS (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(255) NOT NULL,
                       age INTEGER,
                       email VARCHAR(255)
);