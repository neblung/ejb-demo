package jpa.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

@Entity
@NamedQuery(name = "getAll", query = "SELECT i.text from Item i")
public class Item {
    @Id
    public String text;

    public Item() {
        // JPA
    }

    public Item(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static List<String> getAll(EntityManager entityManager) {
        TypedQuery<String> query = entityManager.createNamedQuery("getAll", String.class);
        return query.getResultList();
    }

    public static Optional<Item> create(EntityManager entityManager, String text) {
        if (entityManager.find(Item.class, text) != null) {
            return Optional.empty();
        }
        Item newItem = new Item(text);
        entityManager.persist(newItem);
        return Optional.of(newItem);
    }
}
