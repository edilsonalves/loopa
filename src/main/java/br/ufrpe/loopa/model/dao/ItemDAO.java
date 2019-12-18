package br.ufrpe.loopa.model.dao;

import br.ufrpe.loopa.model.bean.Item;
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
public class ItemDAO implements Serializable {

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

    public void create(Item item) {
        openConnection();

        try {
            EM.getTransaction().begin();
            EM.persist(item);
            EM.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            EM.getTransaction().rollback();
        } finally {
            closeConnection();
        }
    }

    public void update(Item item) {
        openConnection();

        try {
            EM.getTransaction().begin();
            EM.merge(item);
            EM.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            EM.getTransaction().rollback();
        } finally {
            closeConnection();
        }
    }

    public Item find(Long id) {
        Item item;
        openConnection();

        try {
            item = EM.find(Item.class, id);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            item = null;
        } finally {
            closeConnection();
        }

        return item;
    }

    public List<Item> find(String name) {
        List<Item> items;
        openConnection();

        try {
            Query query = EM.createQuery("SELECT i FROM Item i WHERE i.name LIKE :name", Item.class);
            items = (List<Item>) query.setParameter("name", "%" + name + "%").getResultList();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            items = null;
        } finally {
            closeConnection();
        }

        return items;
    }

    public List<Item> findAll() {
        List<Item> items;
        openConnection();

        try {
            Query query = EM.createQuery("SELECT i FROM Item i");
            items = query.getResultList();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            items = null;
        } finally {
            closeConnection();
        }

        return items;
    }

    public void delete(Item item) {
        openConnection();

        try {
            EM.getTransaction().begin();
            item = EM.getReference(Item.class, item.getId());
            EM.remove(item);
            EM.getTransaction().commit();
        } catch (Exception e) {
            EM.getTransaction().rollback();
            System.out.println("Exception: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

}
