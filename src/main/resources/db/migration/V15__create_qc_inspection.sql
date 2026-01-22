CREATE TABLE qc_inspection (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,

                               production_order_id BIGINT NOT NULL,
                               project_id BIGINT NOT NULL,

                               inspected_qty INT NOT NULL,
                               accepted_qty INT DEFAULT 0,
                               rework_qty INT DEFAULT 0,
                               scrap_qty INT DEFAULT 0,

                               status VARCHAR(30) NOT NULL,   -- COMPLETED

                               remarks VARCHAR(255),
                               inspected_by VARCHAR(100),

                               created_at DATETIME NOT NULL,
                               updated_at DATETIME
);
