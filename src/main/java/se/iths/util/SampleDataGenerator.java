package se.iths.util;


import se.iths.entity.Item;
import se.iths.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {

        User user1 = new User("Marcus", "marcus@yahoo.com");
        User user2 = new User("Annabella", "annabella@gmail.com");
        User user3 = new User("Petter", "petter@outlook.se");

        Item item1 = new Item("Couch", "Furniture", 1, 2000.00);
        Item item2 = new Item("PS7", "Consoles", 1, 9000.00);
        Item item3 = new Item("Wheel of Time", "Books", 1, 400.00);
        Item item4 = new Item("Mouse", "PC-Accessories", 1, 560.00);
        Item item5 = new Item("Winterjacket", "Clothes", 1, 1000.00);
        Item item6 = new Item("UE Boom 4", "Bluetooth-speakers", 1, 700.00);
        Item item7 = new Item("OnePlus 8 Pro", "Mobiles", 1, 10000.00);
        Item item8 = new Item("King-Sized Bed", "Furniture", 1, 20000.00);
        Item item9 = new Item("Samsung TV QLED 8k", "TV", 1, 35000.00);
        Item item10 = new Item("TV-Stand", "Furniture", 1, 2750.00);

        user1.addItem(item1);
        user1.addItem(item2);
        user1.addItem(item3);
        user2.addItem(item4);
        user2.addItem(item5);
        user2.addItem(item6);
        user3.addItem(item7);
        user3.addItem(item8);
        user3.addItem(item9);
        user3.addItem(item10);

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);

    }

}
