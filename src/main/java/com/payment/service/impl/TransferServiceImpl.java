package com.payment.service.impl;

import javax.transaction.Transactional;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.payment.dao.PaymentDao;
import com.payment.dao.AccountRepository;
import com.payment.entity.Account;
import com.payment.enums.TransferResponse;
import com.payment.service.TransferService;

@Singleton
public class TransferServiceImpl implements TransferService {
	@Inject
	AccountRepository paymentDao;

	@Override
	@Transactional
	public TransferResponse transferMoney(Integer from, Integer to, Double amount) {
		final Account fromAccount = paymentDao.findByAccountNumber(from);
		final Account toAccount = paymentDao.findByAccountNumber(to);
		if (fromAccount == null || toAccount == null) {
			return TransferResponse.ACCOUNT_INVALID;
		} else if (NumberUtils.DOUBLE_ZERO.compareTo(fromAccount.getCurrentBalance()) >= 0
				|| amount.compareTo(fromAccount.getCurrentBalance()) == 1) {
			return TransferResponse.INSUFFICIENT_FUND;
		} else {
			fromAccount.setCurrentBalance(fromAccount.getCurrentBalance() - amount);
			toAccount.setCurrentBalance(toAccount.getCurrentBalance() + amount);
			paymentDao.save(fromAccount);
			paymentDao.save(toAccount);
			return TransferResponse.SUCCESS;
		}
	}

	@Override
	public TransferResponse validateRequest(Integer from, Integer to, Double amount) {
		if (from.compareTo(to) == 0)
			return TransferResponse.ACCOUNT_SAME;
		else if(from <= 0 || to<=0)
			return TransferResponse.ACCOUNT_INVALID;
		else if (amount <= 0.0)
			return TransferResponse.AMOUNT_INVALID;
		return null;
	}
}