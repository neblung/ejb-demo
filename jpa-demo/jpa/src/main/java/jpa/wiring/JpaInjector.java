package jpa.wiring;

import static java.util.Objects.requireNonNull;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import jpa.api.WithPersistence;
import jpa.impl.WithPersistenceJpa;

@Startup
@Singleton
public class JpaInjector {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private WithPersistence withPersistence;

    @PostConstruct
    void init() {
        requireNonNull(entityManagerFactory);
        withPersistence = new WithPersistenceJpa(entityManagerFactory);
    }

    // We must not use CDI here
    public WithPersistence withPersistence() {
        return withPersistence;
    }
}
