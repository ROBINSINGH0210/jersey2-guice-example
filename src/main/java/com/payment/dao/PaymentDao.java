package com.payment.dao;

public class PaymentDao {

//	protected EntityManager entityManager;
//
//	@Inject
//	public PaymentDao(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
//
//
//	@Transactional
//	public void save(Account from, Account to, Double amount) {
//		final Account fromAc = entityManager.find(Account.class, from.getId());
//		final Account toAc = entityManager.find(Account.class, to.getId());
//		entityManager.lock(fromAc, LockModeType.PESSIMISTIC_WRITE);
//		entityManager.lock(toAc, LockModeType.PESSIMISTIC_WRITE);
//		fromAc.setCurrentBalance(fromAc.getCurrentBalance() - amount);
//		toAc.setCurrentBalance(fromAc.getCurrentBalance() + amount);
//	}
//
//	@Transactional
//	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
//		return (Account) entityManager.createQuery("select e from account e where e.accountNumber=:accountNumber")
//				.setParameter("accountNumber", accountNumber).getSingleResult();
//	}

}