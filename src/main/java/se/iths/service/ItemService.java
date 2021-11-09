package se.iths.service;

import se.iths.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional // We tell it's in here we make transactions
public class ItemService {
    @PersistenceContext
    EntityManager entityManager; // Handles getting and writing from database

    public Item createItem(Item item) {
        entityManager.persist(item);
        return item;
    }

    public Item updateItem(Item item) {
        entityManager.merge(item);
        return item;
    }

    public Item findItemById(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> getAllItems() {
        return entityManager.createQuery("SELECT i from Item i", Item.class).getResultList();
    }

    public void deleteItem(Long id) {
        Item foundItem = entityManager.find(Item.class, id);
        entityManager.remove(foundItem);
    }

}
