package com.payment.service.impl;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.payment.dao.PaymentDao;
import com.payment.entity.Account;
import com.payment.service.AnotherService;
import com.payment.service.TransferService;

@Singleton
public class SimpleServiceImpl implements TransferService {
	@Inject
	PaymentDao paymentDao;

	@Override
	public String transferMoney(Long from, Long to, Double amount) {
		final Account fromAccount = paymentDao.getAccountDetailsByAccountNumber(from);
		final Account toAccount = paymentDao.getAccountDetailsByAccountNumber(to);
		if (fromAccount == null || toAccount == null) {
			return "From/To Account Not Valid";
		} else if (NumberUtils.DOUBLE_ZERO.compareTo(fromAccount.getCurrentBalance()) <= 0
				|| amount.compareTo(fromAccount.getCurrentBalance()) > 1) {
			return "Acoount Balance is not Enough.";
		}else {
			
		}
		
		return null;
	}
}