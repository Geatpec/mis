package com.gepl.mis.cash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashService {
    @Autowired
    private CashLedgerRepository repository;

    public CashLedger create(CashRequest request, String username){
        CashLedger entry= new CashLedger();
        entry.setType(request.getType());
        entry.setAmount(request.getAmount());
        entry.setDescription(request.getDescription());
        entry.setTxnDate(request.getTxnDate());
        entry.setProjectId(request.getProjectId());
        entry.setCreatedBy(username);
        entry.setReferenceType(request.getReferenceType());
        entry.setReferenceId(request.getReferenceId());
        entry.setCategory(request.getCategory());

        return repository.save(entry);
    }

    public CashLedger update(Long id, CashRequest request){
        CashLedger entry= repository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Cash Entry not found"));
        entry.setType(request.getType());
        entry.setAmount(request.getAmount());
        entry.setDescription(request.getDescription());
        entry.setReferenceType(request.getReferenceType());
        entry.setReferenceId(request.getReferenceId());
        entry.setCategory(request.getCategory());

        entry.setTxnDate(request.getTxnDate());

        return repository.save(entry);
    }


    public Page<CashLedger> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public List<CashLedger> getAll(){
        return repository.findAll();
    }
}
