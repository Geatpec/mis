package com.gepl.mis.payables;

import com.gepl.mis.cash.CashLedger;
import com.gepl.mis.cash.CashLedgerRepository;
import com.gepl.mis.receivables.Receivable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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


        if (request.getAmount() == null) {
            throw new IllegalArgumentException("Amount is required");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Payable p = repository.findById(payableId)
                .orElseThrow(() -> new RuntimeException("Payable not found"));

        BigDecimal currentPaid =
                p.getPaidAmount() == null ? BigDecimal.ZERO : p.getPaidAmount();

        BigDecimal newPaid = currentPaid.add(request.getAmount());

        System.out.println("currentPaid = " + currentPaid);
        System.out.println("requestAmount = " + request.getAmount());
        System.out.println("invoiceAmount = " + p.getInvoiceAmount());
        System.out.println("newPaid = " + newPaid);

        if (newPaid.compareTo(p.getInvoiceAmount()) > 0) {

            throw new RuntimeException("Paid amount exceeds invoice amount");
        }



        p.setPaidAmount(newPaid);
        p.setStatus(
                newPaid.compareTo(p.getInvoiceAmount()) >= 0 ? "PAID" : "PARTIAL"
        );




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

         repository.save(p);
       cashRepository.save(cash);

    return p;
  }

    public Page<Payable> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}

