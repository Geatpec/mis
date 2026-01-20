-- V4__create_inventory.sql

CREATE TABLE inventory (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,

                           item_code VARCHAR(50) NOT NULL UNIQUE,
                           item_name VARCHAR(100) NOT NULL,

                           quantity INT NOT NULL,
                           unit VARCHAR(20),
                           location VARCHAR(50),

                           created_by VARCHAR(100),

                           created_at DATETIME NOT NULL,
                           updated_at DATETIME
);
