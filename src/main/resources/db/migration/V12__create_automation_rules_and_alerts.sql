CREATE TABLE automation_rules (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                  rule_name VARCHAR(100) NOT NULL,
                                  scope VARCHAR(20) NOT NULL,        -- PROJECT / ORG
                                  metric VARCHAR(50) NOT NULL,       -- BUDGET_UTILIZATION, CASH_FLOW
                                  operator VARCHAR(5) NOT NULL,      -- >, <, >=, <=
                                  threshold DECIMAL(10,2) NOT NULL,

                                  severity VARCHAR(20) NOT NULL,     -- LOW / MEDIUM / HIGH
                                  status VARCHAR(20) NOT NULL,       -- ACTIVE / DISABLED

                                  created_at DATETIME NOT NULL,
                                  updated_at DATETIME
);

CREATE TABLE alerts (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,

                        scope VARCHAR(20) NOT NULL,         -- PROJECT / ORG
                        entity_id BIGINT,                   -- project_id or NULL for ORG

                        message VARCHAR(255) NOT NULL,
                        severity VARCHAR(20) NOT NULL,      -- LOW / MEDIUM / HIGH
                        status VARCHAR(20) NOT NULL,        -- OPEN / ACKNOWLEDGED / CLOSED

                        created_at DATETIME NOT NULL,
                        updated_at DATETIME
);
