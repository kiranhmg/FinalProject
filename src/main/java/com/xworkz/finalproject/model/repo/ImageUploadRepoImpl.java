package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.ImageUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class ImageUploadRepoImpl implements ImageUploadRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public void saveImageDetails(ImageUploadDto imageDTO) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(imageDTO);
            entityTransaction.commit();
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }

    }

    @Override
    public Optional<List<ImageUploadDto>> findByUserId(Integer userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT i FROM ImageUploadDto i WHERE i.userId = :userId");
            query.setParameter("userId", userId);
           List<ImageUploadDto> list=(List<ImageUploadDto>) query.getResultList();
           return Optional.ofNullable(list);
        }
        catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        }finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public void updateImageDetails(ImageUploadDto imageDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(imageDTO);
            entityManager.getTransaction().commit();
        }
        catch (PersistenceException persistenceException)
        {
            persistenceException.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updateStatus(int userId) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query=entityManager.createQuery("update ImageUploadDto set status='inactive' where userId=:userId and status='active'");
            query.setParameter("userId",userId);
            query.executeUpdate();

            entityTransaction.commit();
            return true;
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
    public Optional<ImageUploadDto> findByUser(ImageUploadDto imageUploadDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT i FROM ImageUploadDto i WHERE i.userId = :userId");
            query.setParameter("userId",imageUploadDto.getUserId());
            ImageUploadDto list=(ImageUploadDto) query.getSingleResult();
            return Optional.ofNullable(list);
        }
        catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        }finally {
            entityManager.close();
        }
        return Optional.empty();
    }
}

