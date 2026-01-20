package com.gepl.mis.inventory;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryMovementService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMovementRepository movementRepository;

    @Transactional
    public InventoryMovement move(InventoryMovementRequest request,String username) {
        Inventory inventory = inventoryRepository.findById(request.getInventoryId()).orElseThrow(() -> new RuntimeException("Inventory not found"));

        if ("OUT".equalsIgnoreCase(request.getMovementType())) {

            if (request.getProjectId() == null) {
                throw new RuntimeException("Project is required for OUT movement");
            }
            if(inventory.getQuantity()< request.getQuantity()){
                throw new RuntimeException("Insufficient stock");
            }
            inventory.setQuantity(inventory.getQuantity()- request.getQuantity());
        }
        else if("IN".equalsIgnoreCase(request.getMovementType())){
            inventory.setQuantity(inventory.getQuantity()+ request.getQuantity());
        }
        else {
            throw new RuntimeException("Invalid movement type");
        }
        InventoryMovement movement = new InventoryMovement();
        movement.setInventoryId(request.getInventoryId());
        movement.setMovementType(request.getMovementType());
        movement.setQuantity(request.getQuantity());
        movement.setProjectId(request.getProjectId());
        movement.setReferenceType(request.getReferenceType());
        movement.setReferenceId(request.getReferenceId());
        movement.setRemarks(request.getRemarks());
        movement.setCreatedBy(username);

        inventoryRepository.save(inventory);
        return movementRepository.save(movement);
    }
}
