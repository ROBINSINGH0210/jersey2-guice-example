package com.payment.controller;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.payment.enums.TransferResponse;
import com.payment.service.TransferService;

@Path("/transfer")
public class TransferMoney {

	@Inject
	TransferService service;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String transferMoney(@DefaultValue("0") @QueryParam("from") Integer from,
			@DefaultValue("0") @QueryParam("to") Integer to, @DefaultValue("0.0") @QueryParam("amount") Double amount) {
		TransferResponse validateRequest = service.validateRequest(from, to, amount);
		return (validateRequest != null) ? validateRequest.name() : service.transferMoney(from, to, amount).name();
	}

}