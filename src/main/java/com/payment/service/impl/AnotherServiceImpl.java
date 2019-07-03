package com.payment.service.impl;

import com.payment.service.AnotherService;

public class AnotherServiceImpl implements AnotherService {
	@Override
	public String provideName() {
		return "foo";
	}
}