package br.ufrpe.loopa.model.dao;

import br.ufrpe.loopa.model.bean.Employee;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author edilson
 */
public class EmployeeDAO implements Serializable {

    private EntityManagerFactory EMF;
    private EntityManager EM;

    public void openConnection() {
        EMF = Persistence.createEntityManagerFactory("loopa_pu");
        EM = EMF.createEntityManager();
    }

    public void closeConnection() {
        EMF.close();
        EM.close();
    }

    public void create(Employee employee) {
        openConnection();

        try {
            EM.getTransaction().begin();
            EM.persist(employee);
            EM.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            EM.getTransaction().rollback();
        } finally {
            closeConnection();
        }
    }

    public void update(Employee employee) {
        openConnection();

        try {
            EM.getTransaction().begin();
            EM.merge(employee);
            EM.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            EM.getTransaction().rollback();
        } finally {
            closeConnection();
        }
    }

    public Employee find(Long id) {
        Employee employee;
        openConnection();

        try {
            employee = EM.find(Employee.class, id);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            employee = null;
        } finally {
            closeConnection();
        }

        return employee;
    }

    public Employee find(String email) {
        Employee employee;
        openConnection();

        try {
            Query query = EM.createQuery("SELECT e FROM Employee e WHERE e.email = :email", Employee.class);
            employee = (Employee) query.setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            employee = null;
        } finally {
            closeConnection();
        }

        return employee;
    }

    public List<Employee> findAll() {
        List<Employee> employees;
        openConnection();

        try {
            Query query = EM.createQuery("SELECT e FROM Employee e");
            employees = query.getResultList();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            employees = null;
        } finally {
            closeConnection();
        }

        return employees;
    }

    public void delete(Employee employee) {
        openConnection();

        try {
            EM.getTransaction().begin();
            employee = EM.getReference(Employee.class, employee.getId());
            EM.remove(employee);
            EM.getTransaction().commit();
        } catch (Exception e) {
            EM.getTransaction().rollback();
            System.out.println("Exception: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

}
