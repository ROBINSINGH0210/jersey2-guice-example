package com.payment;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.payment.service.AnotherService;
import com.payment.service.TransferService;
import com.payment.service.impl.AnotherServiceImpl;
import com.payment.service.impl.SimpleServiceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GuiceModule extends AbstractModule {
	private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();

	@Override
	protected void configure() {
		bind(TransferService.class).to(SimpleServiceImpl.class);
		bind(AnotherService.class).to(AnotherServiceImpl.class);
		// install(new ScanningJpaRepositoryModule(repositoriesBasePackageName,
		// persistenceUnitName));
		// install(new JpaPersistModule("default"));
		// bind(JPAInitializer.class).asEagerSingleton();
	}
	
	@Provides @Singleton
	  public EntityManagerFactory provideEntityManagerFactory() {
	    Map<String, String> properties = new HashMap<>();
	    properties.put("hibernate.connection.driver_class", "org.h2.Driver");
	    properties.put("hibernate.connection.url", "jdbc:h2:test");
	    properties.put("hibernate.connection.username", "sa");
	    properties.put("hibernate.connection.password", "");
	    properties.put("hibernate.connection.pool_size", "1");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.put("hibernate.hbm2ddl.auto", "create");
	    return Persistence.createEntityManagerFactory("db-manager", properties);
	  }
	  
	  @Provides
	  public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
	    EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
	    if (entityManager == null) {
	      ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
	    }
	    return entityManager;
	  }

}