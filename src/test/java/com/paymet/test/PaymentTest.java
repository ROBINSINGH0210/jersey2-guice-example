package com.paymet.test;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.payment.GuiceModule;
import com.payment.dao.ProductRepository;
import com.payment.service.TransferService;
import com.payment.service.impl.TransferServiceImpl;

public class PaymentTest {
	private static final String TEST_PERSISTENCE_UNIT_NAME = "testDB";
	private static final String REPOSITORIES_BASE_PACKAGE_NAME = ProductRepository.class.getPackage().getName();

	private TransferService service;
	private ProductRepository productRepository;

	public PaymentTest() {
		Injector injector = createInjector();
		service = injector.getInstance(TransferService.class);
		productRepository = injector.getInstance(ProductRepository.class);
	}

	private static Injector createInjector() {
		return Guice.createInjector(Stage.DEVELOPMENT,
				new GuiceModule(TEST_PERSISTENCE_UNIT_NAME, REPOSITORIES_BASE_PACKAGE_NAME));
	}

	@Before
	public void clear() {
		productRepository.deleteAll();
	}

	@Test
	public void testAdd() {
		String transferMoney = service.transferMoney(10000, 10001, 10.0d);
		// service.add(new Account(2l, "Dijon Mustarde"));
		System.out.println(transferMoney);
		assertNotNull(transferMoney);

	}

	// @Test
	// public void testFilter() {
	// service.add(new Product(1l, "Apple"));
	// service.add(new Product(2l, "Strawberry"));
	// service.add(new Product(3l, "Blue Berry"));
	// service.add(new Product(4l, "Lemon"));
	//
	// List<Product> filteredProducts = service.filter("berry");
	// assertTrue(filteredProducts.size() == 2);
	// assertTrue(filteredProducts.get(0).getId() == 2l);
	// assertTrue(filteredProducts.get(1).getId() == 3l);
	// }
	//
	// @Test
	// public void testAddAll() {
	// service
	// .addAll(Arrays.asList(new Product(1l, "Book"), new Product(2l, "Towel"), new
	// Product(3l, "Chair")));
	//
	// assertNotNull(service.get(1l));
	// assertNotNull(service.get(2l));
	// assertNotNull(service.get(3l));
	//
	// }
	//
	// @Test
	// public void testAddAllRollback() {
	// try {
	// service
	// .addAll(Arrays.asList(new Product(1l, "Bear"), new Product(2l, "Cat"), new
	// Product(1l, "Tiger")));
	// fail("Expected exception for id duplication");
	// } catch (RuntimeException e) {
	// // Expected rollback done
	// assertNull(service.get(1l));
	// assertNull(service.get(2l));
	// }
	// }

}