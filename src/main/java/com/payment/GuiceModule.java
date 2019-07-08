package com.payment;

import com.google.code.guice.repository.configuration.ScanningJpaRepositoryModule;
import com.google.inject.AbstractModule;
import com.payment.service.TransferService;
import com.payment.service.impl.TransferServiceImpl;

import javax.persistence.EntityManager;

public class GuiceModule extends AbstractModule {
	private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();
//	Injector injector = Guice.createInjector(..., PersistenceService.usingJpa().buildModule());


	private String persistenceUnitName;
    private String repositoriesBasePackageName;

    public GuiceModule(String persistenceUnitName, String repositoriesBasePackageName) {
        this.persistenceUnitName = persistenceUnitName;
        this.repositoriesBasePackageName = repositoriesBasePackageName;
    }
    
    
    public GuiceModule() {}
   
	@Override
	protected void configure() {
		bind(TransferService.class).to(TransferServiceImpl.class);
		install(new ScanningJpaRepositoryModule(repositoriesBasePackageName, persistenceUnitName));
		// install(new JpaPersistModule("default"));
		// bind(JPAInitializer.class).asEagerSingleton();
	}
	
//	@Provides @Singleton
//	  public EntityManagerFactory provideEntityManagerFactory() throws ClassNotFoundException {
//	    Map<String, String> properties = new HashMap<>();
////	    properties.put("hibernate.connection.driver_class", "org.h2.Driver");
////	    properties.put("hibernate.connection.url", "jdbc:h2:file:~/data/testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
////	    properties.put("hibernate.connection.username", "sa");
////	    properties.put("hibernate.connection.password", "");
////	    properties.put("hibernate.connection.pool_size", "1");
////	    properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
////	    properties.put("hibernate.hbm2ddl.auto", "update");
////		Class c = Class.forName("org.hibernate.jpa.HibernatePersistenceProvider");
////		System.out.println(Persistence.getPersistenceUtil());
//	    return Persistence.createEntityManagerFactory("db-manager");
//	  }
	  
//	  @Provides
//	  public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
//	    EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
//	    if (entityManager == null) {
//	      ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
//	    }
//	    return entityManager;
//	  }

}