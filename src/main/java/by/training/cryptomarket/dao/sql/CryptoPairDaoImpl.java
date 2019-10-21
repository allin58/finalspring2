package by.training.cryptomarket.dao.sql;



import by.training.cryptomarket.entity.CryptoPair;
import by.training.cryptomarket.exception.PersistentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class CryptoPairDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CryptoPair> read() {
        Session session = sessionFactory.getCurrentSession();
        List<CryptoPair> list = session.createQuery("from CryptoPair").list();
        return list;
    }

    public void create(CryptoPair cryptoPair) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(cryptoPair);
    }

    public void delete(CryptoPair cryptoPair) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cryptoPair);
    }

    public void update(CryptoPair cryptoPair) {
        Session session = sessionFactory.getCurrentSession();
        session.update(cryptoPair);
    }

    public CryptoPair read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CryptoPair.class, id);
    }


    public void delete(Integer identity) throws PersistentException {
        CryptoPair cryptoPair = new CryptoPair();
        cryptoPair.setIdentity(identity);
        Session session = sessionFactory.getCurrentSession();
        session.delete(cryptoPair);
    }

}
