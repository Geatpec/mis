CREATE TABLE projects (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                          project_code VARCHAR(50) NOT NULL UNIQUE,
                          project_name VARCHAR(100) NOT NULL,

                          client_name VARCHAR(100),

                          planned_start_date DATE,
                          planned_end_date DATE,

                          planned_budget DECIMAL(14,2),

                          status VARCHAR(30),   -- PLANNED / ACTIVE / ON_HOLD / COMPLETED

                          created_by VARCHAR(100),

                          created_at DATETIME NOT NULL,
                          updated_at DATETIME
);