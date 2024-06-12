package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Repository
public class SignUpRepoImpl implements SignUpRepo{
@Autowired
private EntityManagerFactory entityManagerFactory;
    @Override
    public Optional<SignUpDto> save(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(signUpDto);
            entityTransaction.commit();
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return Optional.ofNullable(signUpDto);
    }

    @Override
    public boolean searchByEmail(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {

            Query query=entityManager.createQuery("select c from SignUpDto c where c.email=:email");
            query.setParameter("email",signUpDto.getEmail());

            List<SignUpDto> list=(List<SignUpDto>) query.getResultList();
            if(!list.isEmpty())
            {
                return true;
            }
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public boolean searchByPhone(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {

            Query query=entityManager.createQuery("select c from SignUpDto c where c.phone=:phone");

            query.setParameter("phone",signUpDto.getPhone());
            List<SignUpDto> list=(List<SignUpDto>) query.getResultList();
            if(!list.isEmpty())
            {
                return true;
            }
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return false;
    }
}
