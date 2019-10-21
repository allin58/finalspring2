package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.Coin;
import by.training.cryptomarket.exception.PersistentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CoinDaoImpl{

    @Autowired
    private SessionFactory sessionFactory;

    public List<Coin> read() {

        Session session = sessionFactory.getCurrentSession();
        List<Coin> list = session.createQuery("from Coin").list();


        return list;
    }

    public void create(Coin coin) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(coin);
    }

    public void delete(Coin coin) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(coin);
    }


    public void delete(Integer identity) throws PersistentException {
        Coin coin = new Coin();
        coin.setIdentity(identity);
        Session session = sessionFactory.getCurrentSession();
        session.delete(coin);
    }

    public void update(Coin coin) {
        Session session = sessionFactory.getCurrentSession();
        session.update(coin);
    }

    public Coin read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Coin.class, id);
    }


}
