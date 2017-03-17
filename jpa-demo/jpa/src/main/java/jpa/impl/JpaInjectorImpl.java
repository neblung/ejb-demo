package jpa.impl;

import static java.util.Objects.requireNonNull;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import jpa.api.WithPersistence;
import jpa.wiring.JpaInjector;

@Startup
@Singleton(name = "JpaInjector")
public class JpaInjectorImpl implements JpaInjector {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private WithPersistence withPersistence;

    @PostConstruct
    void init() {
        requireNonNull(entityManagerFactory);
        withPersistence = new WithPersistenceJpa(entityManagerFactory);
    }

    @Override
    public WithPersistence withPersistence() {
        return withPersistence;
    }
}
