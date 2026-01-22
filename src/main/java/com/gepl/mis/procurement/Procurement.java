package com.gepl.mis.procurement;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "procurement")
public class Procurement extends BaseEntity {
    @Column(name="project_id", nullable = false)
    private  Long projectId;

    @Column(name="bom_version", nullable = false)
    private String bomVersion;

    @Column(name="part_no", nullable = false)
    private String partNo;

    private String description;
    private String supplier;

    @Column(name="planned_qty", nullable = false)
    private Integer plannedQty;

    @Column(name="ordered_qty", nullable = false)
    private Integer orderedQty;

    @Column(name="received_qty")
    private Integer receivedQty=0;

    @Column(nullable = false)
    private BigDecimal rate;

    @Column(name="total_value")
    private BigDecimal totalValue;

    @Column(name="lead_time")
    private Integer leadTime;

    @Column(name="excess_qty")
    private Integer excessQty=0;


}
