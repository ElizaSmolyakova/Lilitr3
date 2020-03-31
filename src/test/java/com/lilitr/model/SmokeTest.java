package com.lilitr.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

public class SmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
    }

    @After
    public void close() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void CreateUserTest() {
        User user = new User();
        user.setUserName("firstUser");
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

    }

    @Test
    public void CreateEventTest() {
        User user = new User();
        Event event = new Event(user);
        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(event);
        manager.getTransaction().commit();
    }

    @Test
    public void FindUserTest() {
        User user = new User();
        user.setUserName("firstUser");
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();

        User foundUser = manager.find(User.class, user.getUserId());
        manager.refresh(foundUser);
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(foundUser.getUserName(), "firstUser");
    }

    @Test
    public void testQuery() {
        CreateUserTest();
        Assert.assertEquals(manager.createNativeQuery("SELECT * from user").getResultList().size(), 1);
        manager.createQuery("SELECT u from User u WHERE u.userName = :name", User.class)
                .setParameter("name", "firstUser").getSingleResult();
    }


}
