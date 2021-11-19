package se.iths.service;

import se.iths.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    public Item updateName(long id, String name) {
        Item foundItem = entityManager.find(Item.class, id);
        foundItem.setName(name);
        return entityManager.merge(foundItem);
    }

    // JPQL Queries

    // Dynamic Query
    public List<Item> getNameByDynamicQuery(String name) {
        String query = "SELECT i from Item i WHERE i.name = '" + name + "'";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    // Named Parameters
    public List<Item> getByNameNamedParameters(String name) {
        String query = "SELECT i from Item i WHERE i.name = :name";
        return entityManager.createQuery(query, Item.class).setParameter("name", name).getResultList();
    }

    // Positional Parameters
    public List<Item> getByNamePositionalParameters(String name) {
        String query = "SELECT i FROM Item i WHERE i.name = ?1";
        return entityManager.createQuery(query, Item.class).setParameter(1, name).getResultList();
    }

    // WHere between
    public List<Item> getAllItemsBetweenPrice(double minPrice, double maxPrice) {
        String query = "SELECT i FROM Item i WHERE i.price BETWEEN :minPrice AND :maxPrice";
        return entityManager.createQuery(query, Item.class).setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice).getResultList();
    }

    public List<Item> getAllNames() {
        String query = "SELECT i.name FROM Item i";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    public List<Item> getAllItemsSortedByCategory() {
        String query = "SELECT i FROM Item i ORDER BY i.category";
        return entityManager.createQuery(query, Item.class).getResultList();
    }

    public double selectMaxPrice() {

        TypedQuery<Double> typedQuery = entityManager.createQuery("SELECT MAX(i.price) FROM Item i", Double.class);
        return typedQuery.getSingleResult();
    }

    // Get all items with @NamedQuery
    public List<Item> getAllWithNamedQuery() {
        return entityManager.createNamedQuery("itemEntity.FindAll", Item.class).getResultList();
    }

    public void updatePrice() {
        String query = "UPDATE Item i SET i.price=210.00 WHERE i.price > 10000.00";
        entityManager.createQuery(query).executeUpdate();
    }

}
