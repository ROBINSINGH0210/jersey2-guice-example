package com.paymet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.payment.GuiceModule;
import com.payment.dao.AccountRepository;
import com.payment.entity.Account;
import com.payment.enums.TransferResponse;
import com.payment.service.TransferService;
import com.payment.service.impl.TransferServiceImpl;

public class PaymentTest {
	private static final String TEST_PERSISTENCE_UNIT_NAME = "testDB";
	private static final String REPOSITORIES_BASE_PACKAGE_NAME = AccountRepository.class.getPackage().getName();

	private TransferService service;
	private AccountRepository accountRepository;

	public PaymentTest() {
		Injector injector = createInjector();
		service = injector.getInstance(TransferService.class);
		accountRepository = injector.getInstance(AccountRepository.class);
	}

	private static Injector createInjector() {
		return Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(TEST_PERSISTENCE_UNIT_NAME, REPOSITORIES_BASE_PACKAGE_NAME));
	}

	@Before
	public void clear() {
		accountRepository.deleteAll();
		accountRepository.save(new Account(10000, 10.0));
		accountRepository.save(new Account(10001, 5.0));
		accountRepository.save(new Account(10002, 7.0));
	}

	//Response should not be null
	@Test
	public void testAdd() {
		TransferResponse transferMoney = service.transferMoney(10001, 10002, 1.0d);
		System.out.println(transferMoney);
		assertNotNull(transferMoney);

	}
	
	@Test
	public void testSuccessful() {
		TransferResponse resp = service.transferMoney(10001, 10002, 1.0d);
		System.out.println(resp);
		assertEquals(TransferResponse.SUCCESS, resp);

	}
	
	@Test
	public void testAccountNotPresent() {
		TransferResponse resp = service.transferMoney(10, 10002, 1.0d);
		System.out.println(resp);
		assertEquals(TransferResponse.ACCOUNT_INVALID, resp);

	}
	
	@Test
	public void testInvalidAccount() {
		TransferResponse resp = service.transferMoney(-100, 10002, 1.0d);
		System.out.println(resp);
		assertEquals(TransferResponse.ACCOUNT_INVALID, resp);

	}
	
	
	@Test
	public void testInsufficientFund() {
		TransferResponse resp = service.transferMoney(10001, 10002, 100.0d);
		System.out.println(resp);
		assertEquals(TransferResponse.INSUFFICIENT_FUND, resp);

	}

}