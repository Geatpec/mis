package com.gepl.mis.qc;

import com.gepl.mis.production.ProductionOrder;
import com.gepl.mis.production.ProductionOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcInspectionService {
    @Autowired
    private QcInspectionRepository repository;

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    @Transactional
    public QcInspection inspect(Long productionOrderId,
                                QcInspectionRequest request,
                                String inspector){
        validate(request);

        ProductionOrder po= productionOrderRepository.findById(productionOrderId)
                .orElseThrow(()->new RuntimeException("Production Order not found"));

        QcInspection qc=new QcInspection();
        qc.setProductionOrderId(po.getId());
        qc.setProjectId(po.getProjectId());
        qc.setInspectedQty(request.getInspectedQty());
        qc.setAcceptedQty(request.getAcceptedQty());
        qc.setReworkQty(request.getReworkQty());
        qc.setScrapQty(request.getScrapQty());
        qc.setStatus("COMPLETED");
        qc.setRemarks(request.getRemarks());
        qc.setInspectedBy(inspector);


        return repository.save(qc);
    }

    private void validate(QcInspectionRequest r){
        int total=r.getAcceptedQty()
                + r.getReworkQty()
                + r.getScrapQty();

        if(total!=r.getInspectedQty()){
            throw new RuntimeException("QC quantities must be equal inspected quantity");
        }
    }


}

