package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.RiseComplaintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
@Repository
public class AdminViewComplaintRepoImpl implements AdminViewComplaintRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public List<RiseComplaintDto> findByIssueAndArea(String issue, String area) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from RiseComplaintDto c where issueType=:issue and area=:area");
            query.setParameter("issue",issue);
            query.setParameter("area",area);
            List<RiseComplaintDto> list=(List<RiseComplaintDto>) query.getResultList();
            return list;
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<RiseComplaintDto> findByIssueOrArea(String issue, String area) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from RiseComplaintDto c where issueType=:issue or area=:area");
            query.setParameter("issue",issue);
            query.setParameter("area",area);
            List<RiseComplaintDto> list=(List<RiseComplaintDto>) query.getResultList();
            return list;
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        return Collections.emptyList();
    }
}
