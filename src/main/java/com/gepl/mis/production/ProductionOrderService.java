package com.gepl.mis.production;

import com.gepl.mis.inventory.InventoryMovementRepository;
import com.gepl.mis.inventory.InventoryMovementRequest;
import com.gepl.mis.inventory.InventoryMovementService;
import com.gepl.mis.payables.Payable;
import com.gepl.mis.production.dto.ProductionMaterialConsumeRequest;
import com.gepl.mis.production.dto.ProductionOrderRequest;
import com.gepl.mis.production.dto.ProductionUpdateRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductionOrderService {
    @Autowired
    private ProductionOrderRepository repository;
    @Autowired
    private InventoryMovementService inventoryMovementService;

    @Transactional
    public ProductionOrder create(ProductionOrderRequest request,String username){
        ProductionOrder po=new ProductionOrder();
        po.setProjectId(request.getProjectId());
        po.setProductCode(request.getProductCode());
        po.setProductName(request.getProductName());
        po.setPlannedQuantity(request.getPlannedQuantity());
        po.setProducedQuantity(0);
        po.setStatus("PLANNED");
        po.setCreatedBy(username);

        return repository.save(po);
    }
    @Transactional
    public ProductionOrder start(Long id) {
        ProductionOrder po = get(id);
        po.setStatus("IN_PROGRESS");
        po.setStartDate(java.time.LocalDate.now());
        return repository.save(po);
    }

    @Transactional
    public ProductionOrder updateProduction(Long id, ProductionUpdateRequest request) {
        ProductionOrder po = get(id);
        po.setProducedQuantity(po.getProducedQuantity() + request.getProducedQuantity());

        if (po.getProducedQuantity() >= po.getPlannedQuantity()) {
            po.setStatus("COMPLETED");
            po.setEndDate(LocalDate.now());
        }
        return repository.save(po);
    }

    @Transactional
    public void consumeMaterial(Long productionOrderId, ProductionMaterialConsumeRequest request,String username){
        ProductionOrder po=get(productionOrderId);
        if(!"IN_PROGRESS".equals(po.getStatus())){
            throw new RuntimeException("Production must be IN_PROGRESS to consume material");

        }
        InventoryMovementRequest movement=new InventoryMovementRequest();
        movement.setInventoryId(request.getInventoryId());
        movement.setMovementType("OUT");
        movement.setQuantity(request.getQuantity());
        movement.setProjectId(po.getProjectId());
        movement.setReferenceType("PRODUCTION");
        movement.setReferenceId(po.getId());
        movement.setRemarks(request.getRemarks());

        inventoryMovementService.move(movement,username);
    }

    public List<ProductionOrder> getByProjectId(Long projectId) {
        return repository.findByProjectId(projectId);
    }

    public Page<ProductionOrder> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    private ProductionOrder get(Long id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("Production order not found"));
    }

}
