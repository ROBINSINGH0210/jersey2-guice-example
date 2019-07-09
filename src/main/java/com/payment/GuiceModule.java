package com.payment;

import com.google.code.guice.repository.configuration.ScanningJpaRepositoryModule;
import com.google.inject.AbstractModule;
import com.payment.dao.AccountRepository;
import com.payment.service.TransferService;
import com.payment.service.impl.TransferServiceImpl;

import javax.persistence.EntityManager;

public class GuiceModule extends AbstractModule {
	private String persistenceUnitName = "testDB";
    private String repositoriesBasePackageName = AccountRepository.class.getPackage().getName();

    public GuiceModule(String persistenceUnitName, String repositoriesBasePackageName) {
        this.persistenceUnitName = persistenceUnitName;
        this.repositoriesBasePackageName = repositoriesBasePackageName;
    }
    
    
    public GuiceModule() {}
   
	@Override
	protected void configure() {
		bind(TransferService.class).to(TransferServiceImpl.class);
		install(new ScanningJpaRepositoryModule(repositoriesBasePackageName, persistenceUnitName));
	}
}