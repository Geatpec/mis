CREATE TABLE production_orders (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                   project_id BIGINT NOT NULL,

                                   product_code VARCHAR(50) NOT NULL,
                                   product_name VARCHAR(100),

                                   planned_quantity INT NOT NULL,
                                   produced_quantity INT DEFAULT 0,

                                   status VARCHAR(30) NOT NULL,   -- PLANNED / IN_PROGRESS / COMPLETED / ON_HOLD

                                   start_date DATE,
                                   end_date DATE,

                                   remarks VARCHAR(255),

                                   created_by VARCHAR(100),

                                   created_at DATETIME NOT NULL,
                                   updated_at DATETIME
);
