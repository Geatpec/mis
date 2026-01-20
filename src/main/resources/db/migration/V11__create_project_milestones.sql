CREATE TABLE project_milestones (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                    project_id BIGINT NOT NULL,

                                    milestone_name VARCHAR(100) NOT NULL,

                                    planned_date DATE NOT NULL,
                                    actual_date DATE,

                                    status VARCHAR(30),   -- PLANNED / COMPLETED / DELAYED

                                    created_at DATETIME NOT NULL,
                                    updated_at DATETIME
);
