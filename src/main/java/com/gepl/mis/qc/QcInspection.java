package com.gepl.mis.qc;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "qc_inspection")
@Data
public class QcInspection extends BaseEntity {
    @Column(name = "production_order_id",nullable = false)
    private Long productionOrderId;

    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Column(name = "inspected_qty",nullable = false)
    private Integer inspectedQty;

    @Column(name = "accepted_qty")
    private Integer acceptedQty=0;

    @Column(name = "rework_qty")
    private Integer reworkQty=0;

    @Column(name = "scrap_qty")
    private Integer scrapQty=0;

    @Column(nullable = false)
    private String status;


    private String remarks;

    @Column(name = "inspected_by")
    private String inspectedBy;


}
