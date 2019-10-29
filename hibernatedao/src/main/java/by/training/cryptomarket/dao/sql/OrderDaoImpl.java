package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class OrderDaoImpl extends BaseDao {




    public List<Order> read() throws Exception {
        List<Order> list = null;
        try {
            list = entityManager.createQuery("from Order").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method read()");
            throw new PersistentException();
        }
        return list;
    }

    public void create(Order order) throws Exception {

        try {
            entityManager.persist(order);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method create()");
            throw new PersistentException();
        }
    }

    public void delete(Order order) throws Exception {


        try {
            entityManager.remove(order);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(Order order) throws Exception {


        try {
            entityManager.merge(order);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Order read(Integer id) throws Exception {

        Order order = null;
        try {
            order = entityManager.find(Order.class, id);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method read()");
            throw new PersistentException();
        }
        return order;
    }





}
