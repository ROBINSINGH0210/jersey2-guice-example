package com.payment.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.payment.service.AnotherService;
import com.payment.service.TransferService;

@Singleton
public class SimpleServiceImpl implements TransferService {	
	@Inject
	AnotherService anotherService;

	@Override
	public String getMessage() {
		return "Howdy" + " " + anotherService.provideName();
	}
}