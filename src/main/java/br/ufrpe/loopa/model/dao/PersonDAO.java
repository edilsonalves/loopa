package br.ufrpe.loopa.model.dao;

import br.ufrpe.loopa.model.bean.Person;
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
public class PersonDAO implements Serializable {

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

    public void create(Person person) {
        openConnection();

        try {
            EM.getTransaction().begin();
            EM.persist(person);
            EM.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            EM.getTransaction().rollback();
        } finally {
            closeConnection();
        }
    }

    public void update(Person person) {
        openConnection();

        try {
            EM.getTransaction().begin();
            EM.merge(person);
            EM.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            EM.getTransaction().rollback();
        } finally {
            closeConnection();
        }
    }

    public Person find(Long id) {
        Person person;
        openConnection();

        try {
            person = EM.find(Person.class, id);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            person = null;
        } finally {
            closeConnection();
        }

        return person;
    }

    public Person find(String cpf) {
        Person person;
        openConnection();

        try {
            Query query = EM.createQuery("SELECT p FROM Person p WHERE p.cpf = :cpf", Person.class);
            person = (Person) query.setParameter("cpf", cpf).getSingleResult();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            person = null;
        } finally {
            closeConnection();
        }

        return person;
    }

    public List<Person> findAll() {
        List<Person> persons;
        openConnection();

        try {
            Query query = EM.createQuery("SELECT p FROM Person p");
            persons = query.getResultList();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            persons = null;
        } finally {
            closeConnection();
        }

        return persons;
    }

    public void delete(Person person) {
        openConnection();

        try {
            EM.getTransaction().begin();
            person = EM.getReference(Person.class, person.getId());
            EM.remove(person);
            EM.getTransaction().commit();
        } catch (Exception e) {
            EM.getTransaction().rollback();
            System.out.println("Exception: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

}
