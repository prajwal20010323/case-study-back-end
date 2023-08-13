package com.prajwal.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prajwal.payment.controller.service.PaymentService;
import com.prajwal.payment.model.TransactionDetails;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PaymentController 
{
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable Double amount)
	{
		return paymentService.createTransaction(amount);
	}
}
