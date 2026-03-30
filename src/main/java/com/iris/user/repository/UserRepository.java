package com.iris.user.repository;

import com.iris.user.model.User;
import com.iris.user.model.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public User findByUsername(String username) {
        return em.createQuery("""
            SELECT u
            FROM User u
            WHERE u.username = :username
        """, User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
