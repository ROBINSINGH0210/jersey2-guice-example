package com.payment.enums;

public enum TransferResponse {
	SUCCESS("SUCCESS"), ACCOUNT_SAME("From and To Account are same"), ACCOUNT_INVALID(
			"From/To Account is not valid"), AMOUNT_INVALID("Transaction Amount not valid"),INSUFFICIENT_FUND("Acoount Balance is not Enough");
	private TransferResponse(String msg) {
	}
}
