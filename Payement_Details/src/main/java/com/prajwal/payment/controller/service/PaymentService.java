package com.prajwal.payment.controller.service;

import org.springframework.stereotype.Service;

import com.prajwal.payment.model.TransactionDetails;

@Service
public interface PaymentService 
{

	TransactionDetails createTransaction(Double amount);

}
