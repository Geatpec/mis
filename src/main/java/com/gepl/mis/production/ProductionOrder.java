package com.gepl.mis.production;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Table(name = "production_orders")
@Entity
@Data
public class ProductionOrder extends BaseEntity {
    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Column(name = "product_code",nullable = false)
    private String productCode;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(name = "plannedQuantity",nullable = false)
    private Integer plannedQuantity;

    @Column(name = "produced_quantity")
    private Integer producedQuantity=0;

    @Column(nullable = false)
    private String status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private String remarks;

    @Column(name = "created_by")
    private String createdBy;


}
