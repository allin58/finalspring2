package by.training.cryptomarket.dao.sql;



import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.exception.PersistentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class OrderDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Order> read() {
        Session session = sessionFactory.getCurrentSession();
        List<Order> list = session.createQuery("from Order").list();
        return list;
    }

    public void create(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
    }

    public void delete(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(order);
    }

    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
    }

    public Order read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Order.class, id);
    }


    public void delete(Integer identity) throws PersistentException {
        Order order = new Order();
        order.setIdentity(identity);
        Session session = sessionFactory.getCurrentSession();
        session.delete(order);
    }



}
