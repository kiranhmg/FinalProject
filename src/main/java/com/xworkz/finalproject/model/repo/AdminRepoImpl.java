package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.AdminDto;
import com.xworkz.finalproject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepoImpl implements AdminRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<AdminDto> saveAdmin(AdminDto adminDto)
    {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(adminDto);
            entityTransaction.commit();
            return Optional.ofNullable(adminDto);
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public AdminDto findByEmailAndPassword(AdminDto adminDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query=entityManager.createQuery("select c from AdminDto c where c.email=:email and c.password=:pass");
            query.setParameter("email",adminDto.getEmail());
            query.setParameter("pass",adminDto.getPassword());
            AdminDto adminDto1=(AdminDto) query.getSingleResult();
            return adminDto;
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<SignUpDto> viewSignUp(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query=entityManager.createQuery("select c from SignUpDto c");
            List<SignUpDto> list=(List<SignUpDto>) query.getResultList();
            return list;
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
