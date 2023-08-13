package com.prajwal.payment.controller.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.prajwal.payment.model.TransactionDetails;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService 
{
	private static final String KEY = "rzp_test_EoyFrpYbXFqk59";
	private static final String KEY_SECRET = "4FIINZYwDI68PeGlWBF6FoJ3";
	private static final String CURRENCY = "INR";

	
		public TransactionDetails createTransaction(Double amount)
		{
			
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("amount", (amount * 100));
				jsonObject.put("currency", CURRENCY);
				
				
				RazorpayClient razorpayClient = new RazorpayClient(KEY,KEY_SECRET);
				
				Order order = razorpayClient.orders.create(jsonObject);
				TransactionDetails transactionDetails = prepareTrasactionDetails(order);
				return transactionDetails;
			} catch (RazorpayException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public TransactionDetails prepareTrasactionDetails(Order order)
		{
			String orderId = order.get("id");
			String currency = order.get("currency");
			int amount = order.get("amount");
			System.out.println(order);
			TransactionDetails transactionDetails = new TransactionDetails(orderId,currency,amount,KEY);
			return transactionDetails;
		}
}
