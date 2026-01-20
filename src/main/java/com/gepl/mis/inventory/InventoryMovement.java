package com.gepl.mis.inventory;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="inventory_movement")
@Data
public class InventoryMovement extends BaseEntity {
    @Column(name="inventory_id",nullable = false)
    private Long inventoryId;

    @Column(name="movement_type",nullable = false,length=10)
    private String movementType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name="project_id")
    private Long projectId;

    @Column(name = "reference_type" ,length = 30)
    private String referenceType;

    @Column(name="reference_id")
    private Long referenceId;

    @Column(length = 255)
    private String remarks;

    @Column(name="created_by",length=100)
    private String createdBy;

}
