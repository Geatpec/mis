CREATE TABLE inventory_movement (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                    inventory_id BIGINT NOT NULL,
                                    movement_type VARCHAR(10) NOT NULL,   -- IN / OUT
                                    quantity INT NOT NULL,

                                    project_id BIGINT,                    -- REQUIRED for OUT
                                    reference_type VARCHAR(30),           -- PRODUCTION / ADJUSTMENT / RETURN
                                    reference_id BIGINT,

                                    remarks VARCHAR(255),

                                    created_by VARCHAR(100),

                                    created_at DATETIME NOT NULL,
                                    updated_at DATETIME
);
