package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.EmployeeDto;
import com.xworkz.finalproject.dto.RiseComplaintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class EmployeeRepoimpl implements EmployeeRepo{
@Autowired
private EntityManagerFactory entityManagerFactory;
    @Override
    public EmployeeDto findByEmail(String email) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from EmployeeDto c where email=:email");
            query.setParameter("email",email);
            EmployeeDto employeeDto=(EmployeeDto) query.getSingleResult();
            return employeeDto;
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
    public boolean updateEmployeeOtp(EmployeeDto employeeDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(employeeDto);
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
    public List<RiseComplaintDto> findComplaintByEmpId(int id) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from RiseComplaintDto c where empId=:id");
            query.setParameter("id",id);
            List<RiseComplaintDto> list=(List<RiseComplaintDto>) query.getResultList();
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
