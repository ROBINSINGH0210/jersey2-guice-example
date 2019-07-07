package com.payment.resource;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.payment.service.TransferService;

@Path("/transfer")
public class TransferMoney {

	@Inject
	TransferService service;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt(@DefaultValue("0") @QueryParam("from") Long from, @DefaultValue("0") @QueryParam("to") Long to,
			@DefaultValue("0.0") @QueryParam("amount") Double amount) {
		return service.transferMoney(from, to, amount);
	}
}