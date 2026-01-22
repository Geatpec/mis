package com.gepl.mis.finishedgoods;

import com.gepl.mis.inventory.InventoryMovementRequest;
import com.gepl.mis.inventory.InventoryMovementService;
import com.gepl.mis.production.ProductionOrder;
import com.gepl.mis.production.ProductionOrderRepository;
import com.gepl.mis.qc.QcInspection;
import com.gepl.mis.qc.QcInspectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishedGoodsInventoryService {
    @Autowired
    private FinishedGoodsInventoryRepository repository;

    @Autowired
    private QcInspectionRepository qcRepository;

    @Autowired
    private ProductionOrderRepository productionRepository;


    @Transactional
    public FinishedGoodsInventory moveToFGI(Long qcInspectionId, String username){
        QcInspection qc=qcRepository.findById(qcInspectionId)
                .orElseThrow(()->new RuntimeException("QC inspection not found"));

        ProductionOrder po=productionRepository.findById(qc.getProductionOrderId())
                .orElseThrow(()->new RuntimeException("Production Order not found"));

        if(qc.getAcceptedQty()<=0){
            throw new RuntimeException("No accepted quantity to move to FGI");
        }

        FinishedGoodsInventory fgi=new FinishedGoodsInventory();
        fgi.setProjectId(po.getProjectId());
        fgi.setProductionOrderId(po.getId());
        fgi.setProductCode(po.getProductCode());
        fgi.setProductName(po.getProductName());
        fgi.setQuantity(qc.getAcceptedQty());

      return  repository.save(fgi);



    }




}
