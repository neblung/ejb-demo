package jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;

import jpa.api.ItemRepository;

class ItemRepositoryJpa implements ItemRepository {
    private final EntityManager entityManager;

    ItemRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> getAll() {
        return Item.getAll(entityManager);
    }

    @Override
    public boolean create(String itemText) {
        return Item.create(entityManager, itemText).isPresent();
    }
}
