CREATE TABLE procurement (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,

                             project_id BIGINT NOT NULL,
                             bom_version VARCHAR(50) NOT NULL,

                             part_no VARCHAR(100) NOT NULL,
                             description VARCHAR(255),
                             supplier VARCHAR(100),

                             planned_qty INT NOT NULL,
                             ordered_qty INT NOT NULL,
                             received_qty INT DEFAULT 0,

                             rate DECIMAL(10,2) NOT NULL,
                             total_value DECIMAL(12,2),

                             lead_time INT,

                             excess_qty INT DEFAULT 0,

                             created_at DATETIME NOT NULL,
                             updated_at DATETIME
);
