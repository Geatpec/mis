package com.gepl.mis.finishedgoods;

import com.gepl.mis.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "finished_goods_inventory")
public class FinishedGoodsInventory extends BaseEntity {
    @Column(name = "project_id",nullable = false)
    private Long projectId;

    @Column(name = "production_order_id",nullable = false)
    private Long productionOrderId;

    @Column(name = "product_code",nullable = false)
    private String productCode;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;


}
