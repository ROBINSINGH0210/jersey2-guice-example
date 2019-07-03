package com.payment.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.payment.service.AnotherService;
import com.payment.service.SimpleService;

@Singleton
public class SimpleServiceImpl implements SimpleService {	
	@Inject
	AnotherService anotherService;

	@Override
	public String getMessage() {
		return "Howdy" + " " + anotherService.provideName();
	}
}