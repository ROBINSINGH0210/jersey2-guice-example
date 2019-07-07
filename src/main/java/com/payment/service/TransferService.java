package com.payment.service;

public interface TransferService {
	
	String transferMoney(Long from, Long to, Double amount);

}