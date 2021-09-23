package com.sergeysitnkov.spring.boot.spring_boot.dao;


import com.sergeysitnkov.spring.boot.spring_boot.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOImpl  implements EmployeeDAO{
    @Autowired
    private EntityManager entityManager;


    public List<Employee> getAllEmployees() {
        Session sesssion =entityManager.unwrap(Session.class);
        List<Employee> allEmployees = sesssion.createQuery("from Employee ",Employee.class).getResultList();
        return allEmployees;
    }


    public void saveEmployee(Employee employee) {
      Session session =entityManager.unwrap(Session.class);
      session.saveOrUpdate(employee);
    }


    public Employee getEmployee(int id) {
        Session session=entityManager.unwrap(Session.class);
        Employee employee =session.get(Employee.class,id);
        return  employee;
    }


    public void deleteEmployee(int id) {
        Session session =entityManager.unwrap(Session.class);
        Query<Employee> query =session.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId",id);
        query.executeUpdate();
    }
}
