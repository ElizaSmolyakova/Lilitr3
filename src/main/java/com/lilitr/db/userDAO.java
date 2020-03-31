package com.lilitr.db;

import com.lilitr.model.User;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class userDAO {

    private EntityManager manager;

    public userDAO(EntityManager manager){
        Objects.requireNonNull(manager, "Ooops! no manager so far!");
        this.manager=manager;
    }

    public List<User> findUserByName(String name){
        return manager.createQuery("SELECT u from User u WHERE u.userName =:searchName", User.class)
                .setParameter("searchName", name).getResultList();
    }

    public User addUser(String name){
        User user = new User();
        user.setUserName(name);
        manager.getTransaction().begin();
        try {
        manager.persist(user);}
        catch (Throwable e){
            manager.getTransaction().rollback();
            throw e;
        }
        manager.getTransaction().commit();

        return user;
    }

}
