package com.gepl.mis.qc;

import lombok.Data;

@Data
public class QcInspectionRequest {
    private Integer inspectedQty;
    private Integer acceptedQty;
    private Integer reworkQty;
    private Integer scrapQty;
    private String remarks;
}
