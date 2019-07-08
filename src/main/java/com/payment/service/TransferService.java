package com.payment.service;

public interface TransferService {
	
	String transferMoney(Integer from, Integer to, Double amount);

}