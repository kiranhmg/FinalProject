package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.PasswordResetDto;
import com.xworkz.finalproject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.security.util.PendingException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class PasswordResetRepoImpl implements PasswordResetRepo {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public boolean updateUserPassword(PasswordResetDto passwordResetDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query=entityManager.createQuery("update SignUpDto set userPassword=:pass where email=:email");
            query.setParameter("pass",passwordResetDto.getNewpassword());
            query.setParameter("email",passwordResetDto.getEmail());
            query.executeUpdate();
           entityTransaction.commit();
           return true;

        }
        catch (PendingException pendingException)
        {
            pendingException.printStackTrace();
            entityTransaction.rollback();
        }
        finally {
            entityManager.close();
        }
       return  false;
    }
}
