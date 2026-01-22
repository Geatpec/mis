CREATE TABLE finished_goods_inventory (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                          project_id BIGINT NOT NULL,
                                          production_order_id BIGINT NOT NULL,

                                          product_code VARCHAR(50) NOT NULL,
                                          product_name VARCHAR(100),

                                          quantity INT NOT NULL,

                                          created_at DATETIME NOT NULL,
                                          updated_at DATETIME
);
