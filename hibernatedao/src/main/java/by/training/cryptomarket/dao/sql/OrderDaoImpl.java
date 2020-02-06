package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.OrderDao;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Transactional
@Repository
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderDaoImpl extends BaseDao implements OrderDao {




    public List<Order> read() throws PersistentException {
        List<Order> list = null;
        try {
            list = entityManager.createQuery("from Order").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method read()");
            throw new PersistentException();
        }
        return list;
    }

    public Integer create(Order order) throws PersistentException {

        try {
            entityManager.persist(order);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method create()");
            throw new PersistentException();
        }
        return 0;
    }

    public void delete(Integer i) throws PersistentException {


        try {
            entityManager.remove(read(i));
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(Order order) throws PersistentException {


        try {
            entityManager.merge(order);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Order read(Integer id) throws PersistentException {

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
