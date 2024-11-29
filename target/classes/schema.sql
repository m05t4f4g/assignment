CREATE TABLE COURSES (
                         id INT NOT NULL,
                         name VARCHAR(100) NOT NULL,
                         category VARCHAR(20) NOT NULL,
                         rating INT NOT NULL,
                         description VARCHAR(1000) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE USERS_ENTITY (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY ,
                         name VARCHAR(100) NOT NULL,
                         pass VARCHAR(100) NOT NULL,
                         role VARCHAR(20) NOT NULL
);

