package com.xworkz.finalproject.model.repo;

import com.xworkz.finalproject.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class DepartmentAdminRepoImpl implements DepartmentAdminRepo{
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public AddDepartmentAdminDto findByEmail(String email) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from AddDepartmentAdminDto c where c.email=:email");
            query.setParameter("email",email);
            AddDepartmentAdminDto departmentAdminDto=(AddDepartmentAdminDto) query.getSingleResult();
            return departmentAdminDto;
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
    public DepartmentDto findByIssue1(String issue) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from DepartmentDto c where type=:id ");
            query.setParameter("id",issue);
            DepartmentDto departmentDto=(DepartmentDto) query.getSingleResult();
            return departmentDto;
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
    public List<RiseComplaintDto> findByIssue(String issue) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from RiseComplaintDto c where c.issueType=:issue");
            query.setParameter("issue",issue);
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

    @Override
    public boolean saveEmployee(EmployeeDto employeeDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(employeeDto);
            transaction.commit();
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
    public EmployeeDto findByEmailOrPhone(EmployeeDto employeeDto) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query=entityManager.createQuery("select c from EmployeeDto c where email=:email or phone=:phone");
            query.setParameter("email",employeeDto.getEmail());
            query.setParameter("phone",employeeDto.getPhone());
          EmployeeDto employeeDto1=(EmployeeDto)  query.getSingleResult();
          return employeeDto1;
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
    public List<DepartmentDto> departmrntList() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try{
            Query query= entityManager.createQuery("select c from DepartmentDto c");
            List<DepartmentDto> list=(List<DepartmentDto>) query.getResultList();
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

    @Override
    public DepartmentDto findById(int id) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from DepartmentDto c where id=:id");
            query.setParameter("id",id);
            DepartmentDto departmentDto=(DepartmentDto) query.getSingleResult();
            return departmentDto;
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
    public List<RiseComplaintDto> findByDeptId(int id) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from RiseComplaintDto c where deptId=:id");
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

    @Override
    public List<EmployeeDto> findEmpByDeptId(int id) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c from EmployeeDto c where deptId=:id ");
            query.setParameter("id",id);
            List<EmployeeDto> list=(List<EmployeeDto>) query.getResultList();
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
