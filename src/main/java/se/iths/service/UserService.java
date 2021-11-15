package se.iths.service;

import se.iths.entity.Item;
import se.iths.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User createUser(User user) {
        // Adding new items for testing purposes
        user.addItem(new Item("Couch", "Furniture", 1, 500.00));
        user.addItem(new Item("RTX 3080", "PC-Components", 1, 12000.00));

        entityManager.persist(user);
        return user;
    }

}
