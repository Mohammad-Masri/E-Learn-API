package com.elearn.app.elearnapi.modules.PaymentTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.modules.Course.Course;

@Service
public class PaymentTransactionService {

    @Autowired
    private PaymentTransactionRepository transactionRepository;

    public PaymentTransaction save(PaymentTransaction paymentTransaction) {
        return this.transactionRepository.save(paymentTransaction);
    }

    public PaymentTransaction create(Course course) {
        PaymentTransaction paymentTransaction = new PaymentTransaction(course.getPrice());
        return this.save(paymentTransaction);
    }

}
