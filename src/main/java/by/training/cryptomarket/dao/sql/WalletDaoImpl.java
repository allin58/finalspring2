package by.training.cryptomarket.dao.sql;




import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.exception.PersistentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class WalletDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Wallet> read() {
        Session session = sessionFactory.getCurrentSession();
        List<Wallet> list = session.createQuery("from Wallet").list();
        return list;
    }

    public void create(Wallet wallet) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(wallet);
    }

    public void delete(Wallet wallet) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(wallet);
    }

    public void update(Wallet wallet) {
        Session session = sessionFactory.getCurrentSession();
        session.update(wallet);
    }

    public Wallet read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Wallet.class, id);
    }


    public void delete(Integer identity) throws PersistentException {
        Wallet wallet = new Wallet();
        wallet.setIdentity(identity);
        Session session = sessionFactory.getCurrentSession();
        session.delete(wallet);
    }




}
