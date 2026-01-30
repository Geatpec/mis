package com.gepl.mis.receivables;

import com.gepl.mis.cash.CashLedger;
import com.gepl.mis.cash.CashLedgerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ReceivableService {
    @Autowired
    private ReceivableRepository repository;

    @Autowired
    private CashLedgerRepository cashRepository;

    @Transactional
    public Receivable create(ReceivableRequest request, String username) {
        Receivable r = new Receivable();
        r.setClientName(request.getClientName());
        r.setProjectId(request.getProjectId());
        r.setInvoiceNo(request.getInvoiceNo());
        r.setInvoiceDate(request.getInvoiceDate());
        r.setDueDate(request.getDueDate());
        r.setInvoiceAmount(request.getInvoiceAmount());
        r.setReceivedAmount(BigDecimal.ZERO);
        r.setStatus("OPEN");
        r.setCreatedBy(username);

        return repository.save(r);
    }

    @Transactional
    public Receivable receivePayment(Long receivableId, ReceivablePaymentRequest request, String username) {
        Receivable r = repository.findById(receivableId).orElseThrow(() -> new RuntimeException("Receivable not found"));

        r.setReceivedAmount(r.getReceivedAmount().add(request.getAmount()));

        if (r.getReceivedAmount().compareTo(r.getInvoiceAmount()) >= 0) {
                r.setStatus("PAID");
            } else {
                r.setStatus("PARTIAL");
            }

            // auto cash in
            CashLedger cash = new CashLedger();
            cash.setType("IN");
            cash.setAmount(request.getAmount());
            cash.setTxnDate(request.getPaymentDate());
            cash.setProjectId(r.getProjectId());
            cash.setReferenceType("RECEIVABLE");
            cash.setCategory("CUSTOMER_PAYMENT");
            cash.setDescription("Payement against invoice: " + r.getInvoiceNo());
            cash.setCreatedBy(username);

            cashRepository.save(cash);
            return repository.save(r);
        }
    public Page<Receivable> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    }


