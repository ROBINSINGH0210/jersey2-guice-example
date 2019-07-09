package com.payment.service;

import com.payment.enums.TransferResponse;

public interface TransferService {
	
	TransferResponse transferMoney(Integer from, Integer to, Double amount);

	TransferResponse validateRequest(Integer from, Integer to, Double amount);

}