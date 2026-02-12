package com.gepl.mis.receivables;

import com.gepl.mis.cash.CashLedger;
import com.gepl.mis.cash.CashLedgerRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ReceivableService {
    @Autowired
    private ReceivableRepository repository;

    @Autowired
    private CashLedgerRepository cashRepository;

    @Transactional
    public Receivable create(ReceivableRequest request, String username) throws BadRequestException {

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

        if (Boolean.TRUE.equals(request.getTdsApplicable())) {

            if (request.getTdsRate() == null || request.getTdsRate().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BadRequestException("TDS rate must be provided when TDS is applicable");
            }

            BigDecimal tdsAmount = request.getInvoiceAmount()
                    .multiply(request.getTdsRate())
                    .divide(BigDecimal.valueOf(100));

            r.setTdsApplicable(true);
            r.setTdsRate(request.getTdsRate());
            r.setTdsAmount(tdsAmount);
            r.setTdsDescription(request.getTdsDescription());

        } else {
            r.setTdsApplicable(false);
            r.setTdsRate(null);
            r.setTdsAmount(BigDecimal.ZERO);
            r.setTdsDescription(null);
        }



        r.setNetAmount(r.getInvoiceAmount().subtract(r.getTdsAmount()));


        return repository.save(r);
    }

    @Transactional
    public Receivable receivePayment(Long receivableId,
                                     ReceivablePaymentRequest request,
                                     String username) {

        if (request.getAmount() == null) {
            throw new IllegalArgumentException("Amount is required");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        Receivable r = repository.findById(receivableId)
                .orElseThrow(() -> new RuntimeException("Receivable not found"));

        BigDecimal currentReceived =
                r.getReceivedAmount() == null ? BigDecimal.ZERO : r.getReceivedAmount();

        BigDecimal newReceived = currentReceived.add(request.getAmount());

        if (newReceived.compareTo(r.getNetAmount()) > 0) {
            throw new RuntimeException("Received amount exceeds invoice amount");
        }

        r.setReceivedAmount(newReceived);
        r.setStatus(
                newReceived.compareTo(r.getNetAmount()) >= 0 ? "PAID" : "PARTIAL"
        );

        CashLedger cash = new CashLedger();
        cash.setType("IN");
        cash.setAmount(request.getAmount());
        cash.setTxnDate(request.getPaymentDate());
        cash.setProjectId(r.getProjectId());
        cash.setReferenceType("RECEIVABLE");
        cash.setReferenceId(r.getId()); // 🔴 YOU WERE MISSING THIS
        cash.setCategory("CUSTOMER_PAYMENT");
        cash.setDescription("Payment against invoice: " + r.getInvoiceNo());
        cash.setCreatedBy(username);

        cashRepository.save(cash);
        return repository.save(r);
    }


    public Page<Receivable> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    }


