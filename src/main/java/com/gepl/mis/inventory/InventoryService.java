package com.gepl.mis.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository repository;

    public Inventory addItem(InventoryRequest request, String username){
        repository.findByItemCode(request.getItemCode())
                .ifPresent(i->{
                    throw new RuntimeException("Item already exists");
                });
        Inventory item= new Inventory();
        item.setItemCode(request.getItemCode());
        item.setItemName(request.getItemName());
        item.setQuantity(request.getQuantity());
        item.setUnit(request.getUnit());
        item.setLocation(request.getLocation());
        item.setCreatedBy(username);

        return repository.save(item);

    }

    public Inventory updateStock(Long id, InventoryRequest request){
        Inventory item=repository.findById(id).orElseThrow(()->new RuntimeException("Item not found"));

        item.setItemName(request.getItemName());
        item.setQuantity(request.getQuantity());
        item.setUnit(request.getUnit());
        item.setLocation(request.getLocation());

        return repository.save(item);


    }

    public Page<Inventory> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public List<Inventory> getAll(){
        return repository.findAll();
    }
}
