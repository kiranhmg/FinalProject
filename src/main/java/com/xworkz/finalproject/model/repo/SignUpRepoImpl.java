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

    @Override
    public Optional<SignUpDto> searchByEmailAndPassword(String email, String password) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query=entityManager.createQuery("select c from SignUpDto c where c.email=:email and c.password=:password or c.userPassword=:user");
            query.setParameter("email",email);
            query.setParameter("password",password);
            query.setParameter("user",password);
            SignUpDto signUpDto=(SignUpDto)query.getSingleResult();
            return Optional.ofNullable(signUpDto);
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
    public Optional<SignUpDto> updateLoginCount(SignUpDto signUpDto,String email) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query=entityManager.createQuery("update SignUpDto set countLogin=:count where email=:email");
            query.setParameter("count",signUpDto.getCountLogin());
            query.setParameter("email",email);
            query.executeUpdate();
            entityTransaction.commit();
            return Optional.ofNullable(signUpDto);
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
    public SignUpDto searchByEmail1(String email) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {

            Query query=entityManager.createQuery("select c from SignUpDto c where c.email=:email");
            query.setParameter("email",email);

            SignUpDto list=(SignUpDto) query.getSingleResult();
            if(list!=null)
            {
                return list;
            }
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
    public SignUpDto updateAccountLock(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query= entityManager.createQuery("update SignUpDto set accountLock=:lock where email=:email ");
            query.setParameter("lock",signUpDto.getAccountLock());
            query.setParameter("email",signUpDto.getEmail());
            query.executeUpdate();
            entityTransaction.commit();
            return signUpDto;

        }catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public SignUpDto updatePasswordAndUserPassword(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query= entityManager.createQuery("update SignUpDto set password=:password,userPassword=:user where email=:email ");
            query.setParameter("password",signUpDto.getPassword());
            query.setParameter("user",signUpDto.getUserPassword());
            query.setParameter("email",signUpDto.getEmail());
            query.executeUpdate();
            entityTransaction.commit();
            return signUpDto;

        }catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
        return null;

    }

    @Override
    public SignUpDto updatePassword(SignUpDto signUpDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query=entityManager.createQuery("update SignUpDto set password=:pass where email=:email");
            query.setParameter("pass",signUpDto.getPassword());
            query.setParameter("email",signUpDto.getEmail());
            query.executeUpdate();
            entityTransaction.commit();
            return signUpDto;

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
