package com.gepl.mis.qc;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class QcInspectionRequest {

    @Min(1)
    private Integer inspectedQty;

    @Min(0)
    private Integer acceptedQty;

    @Min(0)
    private Integer reworkQty;

    @Min(0)
    private Integer scrapQty;

    private String remarks;
}
