package com.payment.service.impl;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.payment.dao.PaymentDao;
import com.payment.dao.ProductRepository;
import com.payment.entity.Account;
import com.payment.service.TransferService;

@Singleton
public class TransferServiceImpl implements TransferService {
	@Inject
	ProductRepository paymentDao;

	@Override
	public String transferMoney(Integer from, Integer to, Double amount) {
		final Account fromAccount = paymentDao.findByAccountNumber(from);
		final Account toAccount = paymentDao.findByAccountNumber(to);
		if (fromAccount == null || toAccount == null) {
			return "From/To Account Not Valid";
		} else if (NumberUtils.DOUBLE_ZERO.compareTo(fromAccount.getCurrentBalance()) <= 0
				|| amount.compareTo(fromAccount.getCurrentBalance()) > 1) {
			return "Acoount Balance is not Enough.";
		}else {
			fromAccount.setCurrentBalance(fromAccount.getCurrentBalance() - amount);
			toAccount.setCurrentBalance(toAccount.getCurrentBalance() + amount);
			paymentDao.save(fromAccount);
			paymentDao.save(toAccount);
			return "success";
		}
	}
}