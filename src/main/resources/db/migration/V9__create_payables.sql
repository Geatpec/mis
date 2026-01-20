CREATE TABLE payables (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                          vendor_name VARCHAR(100) NOT NULL,
                          project_id BIGINT NOT NULL,

                          invoice_no VARCHAR(50) NOT NULL,
                          invoice_date DATE NOT NULL,
                          due_date DATE NOT NULL,

                          invoice_amount DECIMAL(12,2) NOT NULL,
                          paid_amount DECIMAL(12,2) DEFAULT 0,

                          status VARCHAR(20) NOT NULL,  -- OPEN / PARTIAL / PAID

                          created_by VARCHAR(100),

                          created_at DATETIME NOT NULL,
                          updated_at DATETIME
);