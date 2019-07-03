package com.payment;

import com.google.inject.AbstractModule;
import com.payment.service.AnotherService;
import com.payment.service.SimpleService;
import com.payment.service.impl.AnotherServiceImpl;
import com.payment.service.impl.SimpleServiceImpl;

public class GuiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(SimpleService.class).to(SimpleServiceImpl.class);
		bind(AnotherService.class).to(AnotherServiceImpl.class);
	}
}