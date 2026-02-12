package com.gepl.mis.qc;

import com.gepl.mis.procurement.Procurement;
import com.gepl.mis.production.ProductionOrder;
import com.gepl.mis.production.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<QcInspection> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

}

