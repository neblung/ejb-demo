package jpa.impl;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import jpa.api.ItemRepository;
import jpa.api.WithPersistence;

public class WithPersistenceJpa implements WithPersistence {
    private final EntityManagerFactory entityManagerFactory;

    public WithPersistenceJpa(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public <T> T execute(Function<ItemRepository, T> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return function.apply(new ItemRepositoryJpa(entityManager));
        }
        finally {
            entityManager.close();
        }
    }
}
