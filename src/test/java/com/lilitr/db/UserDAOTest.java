package com.lilitr.db;

import com.lilitr.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private userDAO user;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        user = new userDAO(manager);
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
    public void addUserTest(){
        User newUser = user.addUser("test1");
        Assert.assertEquals(newUser.getUserName(),"test1");
    }

    @Test
    public void findUserTest(){
        User newUser1 = user.addUser("test1");
        User newUser2 = user.addUser("test2");
        List<User> foundUsers = user.findUserByName("test2");
        Assert.assertEquals(1, foundUsers.size());
        Assert.assertEquals(foundUsers.get(0).getUserName(), "test2");
    }

}
