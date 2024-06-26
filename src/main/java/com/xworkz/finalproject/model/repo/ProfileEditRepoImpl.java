package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class ProfileEditRepoImpl implements ProfileEditRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public SignUpDto updateProfileDetails(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(signUpDto);
           entityTransaction.commit();
           return signUpDto;
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
            entityTransaction.rollback();
        }
        finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public SignUpDto searchByEmailAndPhone(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query=entityManager.createQuery("select c from SignUpDto c where email=:email or phone=:phone");
            query.setParameter("email",signUpDto.getEmail());
            query.setParameter("phone",signUpDto.getPhone());
            SignUpDto signUpDto1=(SignUpDto) query.getSingleResult();
            return signUpDto1;
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
}
