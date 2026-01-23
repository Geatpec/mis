package com.gepl.mis.procurement;

import com.gepl.mis.inventory.InventoryMovementRequest;
import com.gepl.mis.inventory.InventoryMovementService;
import com.gepl.mis.procurement.dto.ProcurementReceiptRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProcurementService {
    @Autowired
    private ProcurementRepository repository;

    @Autowired
    private InventoryMovementService inventoryMovementService;

    @Transactional
    public Procurement create(Procurement p){
        enrich(p);
        return repository.save(p);
    }

    @Transactional
    public Procurement update(Long id, Procurement updated){
        Procurement existing =get(id);

        existing.setReceivedQty(updated.getReceivedQty());
        existing.setRate(updated.getRate());
        existing.setLeadTime(updated.getLeadTime());
        enrich(existing);
        return repository.save(existing);
    }
    @Transactional
    public Procurement receiveMaterial(Long procurementId, ProcurementReceiptRequest request, String username){
        Procurement p= get(procurementId);
        int newReceivedQty= p.getReceivedQty() + request.getReceivedQty();

        if(newReceivedQty > p.getOrderedQty()) {
            throw new RuntimeException("Received quantity exceeds ordered quantity");
        }
            InventoryMovementRequest movement=new InventoryMovementRequest();
            movement.setInventoryId(request.getInventoryId());
            movement.setMovementType("IN");
            movement.setQuantity(request.getReceivedQty());
            movement.setProjectId(p.getProjectId());
            movement.setReferenceType("PROCUREMENT");
            movement.setReferenceId(p.getId());
            movement.setRemarks(request.getRemarks());

            inventoryMovementService.move(movement,username);

            p.setReceivedQty(newReceivedQty);
            enrich(p);
            return repository.save(p);

    }

    public Page<Procurement> byProject(
            Long projectId,
            Pageable pageable
    ) {
        return repository.findByProjectId(projectId, pageable);
    }

    public Page<Procurement> bySupplier(
            String supplier,
            Pageable pageable
    ) {
        return repository.findBySupplier(supplier, pageable);
    }



    public List<Procurement> byProject(Long projectId){
        return repository.findByProjectId(projectId);
    }
    public List<Procurement> bySupplier(String supplier){
        return repository.findBySupplier(supplier);
    }

    private void enrich(Procurement p){
        p.setTotalValue(p.getRate().multiply(BigDecimal.valueOf(p.getOrderedQty())));

        p.setExcessQty(Math.max(0,p.getReceivedQty() - p.getPlannedQty()));

    }
    private Procurement get(Long id){
        return repository.findById(id)
                .orElseThrow(()->new RuntimeException("Procurment record not found"));
    }

    public Page<Procurement> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
