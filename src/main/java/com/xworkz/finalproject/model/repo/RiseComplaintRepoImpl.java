package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.RiseComplaintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Repository
public class RiseComplaintRepoImpl implements RiseComplaintRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public Optional<RiseComplaintDto> saveComplaint(RiseComplaintDto riseComplaintDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(riseComplaintDto);
            entityTransaction.commit();
            return Optional.ofNullable(riseComplaintDto);
        }catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<RiseComplaintDto> searchByUid(int uid) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from RiseComplaintDto c where userId=:uid");
            query.setParameter("uid",uid);
            List<RiseComplaintDto> riseComplaintDtos=(List<RiseComplaintDto>) query.getResultList();
            return riseComplaintDtos;
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }
}
