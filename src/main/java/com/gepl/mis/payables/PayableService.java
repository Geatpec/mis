package com.gepl.mis.payables;

import com.gepl.mis.cash.CashLedger;
import com.gepl.mis.cash.CashLedgerRepository;
import com.gepl.mis.receivables.Receivable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PayableService {
    @Autowired
    private PayableRepository repository;
    @Autowired
    private CashLedgerRepository cashRepository;

    @Transactional
    public Payable create(PayableRequest request, String username) {
        Payable p = new Payable();
        p.setVendorName(request.getVendorName());
        p.setProjectId(request.getProjectId());
        p.setInvoiceNo(request.getInvoiceNo());
        p.setInvoiceDate(request.getInvoiceDate());
        p.setDueDate(request.getDueDate());
        p.setInvoiceAmount(request.getInvoiceAmount());
        p.setPaidAmount(BigDecimal.ZERO);
        p.setStatus("OPEN");
        p.setCreatedBy(username);

        return repository.save(p);
    }

    @Transactional
    public Payable pay(Long payableId, PayablePaymentRequest request, String username) {
        Payable p = repository.findById(payableId).orElseThrow(() -> new RuntimeException("Payable not found"));

        p.setPaidAmount(p.getPaidAmount().add(request.getAmount()));

        if (p.getPaidAmount().compareTo(p.getInvoiceAmount()) >= 0) {
            p.setStatus("PAID");
        } else {
            p.setStatus("PARTIAL");
        }

        // 🔴 AUTO CASH-OUT
        CashLedger cash = new CashLedger();
        cash.setType("OUT");
        cash.setAmount(request.getAmount());
        cash.setTxnDate(request.getPaymentDate());
        cash.setProjectId(p.getProjectId());
        cash.setReferenceType("PAYABLE");
        cash.setReferenceId(p.getId());
        cash.setCategory("VENDOR_PAYMENT");
        cash.setDescription("Payment to " + p.getVendorName());
        cash.setCreatedBy(username);

        cashRepository.save(cash);
        return repository.save(p);
    }
    public Page<Payable> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}

