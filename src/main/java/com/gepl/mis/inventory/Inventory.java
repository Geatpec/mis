package com.gepl.mis.inventory;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="inventory")
@Data
public class Inventory extends BaseEntity {
    @Column(name="item_code", nullable = false,unique = true)
    private String itemCode;

    @Column(name="item_category", nullable = false)
    private String itemCategory;

    @Column( nullable = false)
    private Integer quantity;

    private String unit;
    private String location;

    @Column(name="created_by")
    private String createdBy;

}
