package com.michael.multiformatter.repository;

import com.michael.multiformatter.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class NativeQueryRepository {
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    public List<Employer> runNativeQuery(String sqlScript) {
        Query query = entityManager.createNativeQuery(sqlScript, Employer.class);
        List<Employer> employers = query.getResultList();
        return employers;
    }
}
